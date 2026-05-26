/*
 * Copyright (c) 2021, 2026, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package org.openjdk.kextract.test.toolprovider;

import testlib.TestUtils;
import org.testng.annotations.Test;
import testlib.KextractToolRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class TestFilters extends KextractToolRunner {
    @Test
    public void testFilter() {
        for (FilterKind expectedKind : FilterKind.values()) {
            Path filterOutput = getOutputFilePath("filters_" + expectedKind);
            Path filterH = getInputFilePath("filters.h");
            runAndCompile(filterOutput, expectedKind.filterOption, expectedKind.symbolName, filterH.toString());
            try (TestUtils.Loader loader = TestUtils.classLoader(filterOutput)) {
                Class<?> cls = loader.loadClass("filters_h");
                for (FilterKind kind : FilterKind.values()) {
                    Object res = kind.get(cls);
                    if (kind == expectedKind) {
                        assertNotNull(res);
                    } else {
                        assertNull(res);
                    }
                }
            } finally {
                TestUtils.deleteDir(filterOutput);
            }
        }
    }

    @Test
    public void testDumpIncludes() throws IOException {
        Path filterOutput = getOutputFilePath("filters_dump");
        try {
            Files.createDirectory(filterOutput);
            Path includes = filterOutput.resolve("test.conf");
            Path filterH = getInputFilePath("filters.h");
            runNoOuput("--dump-includes", includes.toString(), filterH.toString()).checkSuccess();
            List<String> includeLines = Files.readAllLines(includes);
            List<String> filteredLines = includeLines.stream().filter(line -> line.startsWith("--")).toList();
            assertEquals(filteredLines.size(), 9);
            // These will be in consistent order based on type and symbol name.
            assertTrue(filteredLines.get(0).startsWith("--include-constant BLUE "));
            assertTrue(filteredLines.get(1).startsWith("--include-constant GREEN "));
            assertTrue(filteredLines.get(2).startsWith("--include-constant RED "));
            assertTrue(filteredLines.get(3).startsWith("--include-constant _constant "));
            assertTrue(filteredLines.get(4).startsWith("--include-var _global "));
            assertTrue(filteredLines.get(5).startsWith("--include-function _function "));
            assertTrue(filteredLines.get(6).startsWith("--include-typedef _typedef "));
            assertTrue(filteredLines.get(7).startsWith("--include-struct _struct "));
            assertTrue(filteredLines.get(8).startsWith("--include-union _union "));
        } finally {
            TestUtils.deleteDir(filterOutput);
        }
    }

    enum FilterKind {
        VAR("_global", "--include-var"),
        FUNCTION("_function", "--include-function"),
        MACRO_CONSTANT("_constant", "--include-constant"),
        ENUM_CONSTANT("RED", "--include-constant"),
        TYPEDEF("_typedef", "--include-typedef"),
        STRUCT("_struct", "--include-struct"),
        UNION("_union", "--include-union");

        final String symbolName;
        final String filterOption;

        FilterKind(String symbolName, String filterOption) {
            this.symbolName = symbolName;
            this.filterOption = filterOption;
        }

        Object get(Class<?> headerClass) {
            return switch (this) {
                case FUNCTION, MACRO_CONSTANT, ENUM_CONSTANT -> findMethod(headerClass, symbolName);
                case VAR -> findMethod(headerClass, symbolName);
                case TYPEDEF -> findField(headerClass, symbolName);
                case STRUCT, UNION -> {
                    try {
                        yield headerClass.getClassLoader().loadClass(symbolName);
                    } catch (ReflectiveOperationException ex) {
                        yield null;
                    }
                }
            };
        }
    }
}
