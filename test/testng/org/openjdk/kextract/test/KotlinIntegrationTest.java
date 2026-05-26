/*
 * Copyright (c) 2024, Oracle and/or its affiliates. All rights reserved.
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

package org.openjdk.kextract.test;

import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

import testlib.KextractToolRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Integration tests for Kotlin code generation.
 * Tests the complete flow: C header -> Kotlin code generation -> syntax validation.
 */
public class KotlinIntegrationTest extends KextractToolRunner {

    private Path outputDir;

    @BeforeClass
    public void setup() throws IOException {
        outputDir = getOutputFilePath("kotlin_output");
    }

    @AfterClass
    public void tearDown() throws IOException {
        deleteDir(outputDir);
    }

    @Test
    public void testStructGeneration() throws IOException {
        Path header = getInputFilePath("kotlin/test_structs.h");
        
        // Run kextract with Kotlin target
        int result = run(outputDir,
            "-t", "org.openjdk.kextract.test",
            "-l", "testlib",
            "--target-language", "kotlin",
            header.toString()
        );
        
        assertEquals(result, 0, "kextract should succeed");
        
        // Check that .kt files were generated
        List<Path> ktFiles = listFiles(outputDir, ".kt");
        assertFalse(ktFiles.isEmpty(), "No .kt files generated");
        
        // Check for Point class
        Path pointFile = ktFiles.stream()
            .filter(p -> p.getFileName().toString().contains("test_structs"))
            .findFirst()
            .orElse(null);
        assertNotNull(pointFile, "test_structs.kt not generated");
        
        String content = readFile(pointFile);
        assertTrue(content.contains("class Point"), "Point class not found");
        assertTrue(content.contains("val x: Int"), "x property not found");
        assertTrue(content.contains("val y: Int"), "y property not found");
        assertTrue(content.contains("companion object"), "companion object not found");
        assertTrue(content.contains("val layout: GroupLayout"), "layout not found");
    }

    @Test
    public void testFunctionGeneration() throws IOException {
        Path header = getInputFilePath("kotlin/test_functions.h");
        
        int result = run(outputDir,
            "-t", "org.openjdk.kextract.test",
            "-l", "testlib",
            "--target-language", "kotlin",
            header.toString()
        );
        
        assertEquals(result, 0, "kextract should succeed");
        
        List<Path> ktFiles = listFiles(outputDir, ".kt");
        assertFalse(ktFiles.isEmpty(), "No .kt files generated");
        
        Path funcFile = ktFiles.stream()
            .filter(p -> p.getFileName().toString().contains("test_functions"))
            .findFirst()
            .orElse(null);
        assertNotNull(funcFile, "test_functions.kt not generated");
        
        String content = readFile(funcFile);
        assertTrue(content.contains("fun add"), "add function not found");
        assertTrue(content.contains("invokeExact"), "invokeExact not found");
        assertTrue(content.contains("try {"), "try-catch not found");
    }

    @Test
    public void testCombinedGeneration() throws IOException {
        Path header = getInputFilePath("kotlin/test_combined.h");
        
        int result = run(outputDir,
            "-t", "org.openjdk.kextract.test",
            "-l", "testlib",
            "--target-language", "kotlin",
            header.toString()
        );
        
        assertEquals(result, 0, "kextract should succeed");
        
        List<Path> ktFiles = listFiles(outputDir, ".kt");
        assertFalse(ktFiles.isEmpty(), "No .kt files generated");
        
        // Check that multiple classes are generated
        boolean hasPoint = false;
        boolean hasAdd = false;
        for (Path file : ktFiles) {
            String content = readFile(file);
            if (content.contains("class Point")) hasPoint = true;
            if (content.contains("fun add") || content.contains("fun create_point")) hasAdd = true;
        }
        assertTrue(hasPoint, "Point class not found in any file");
        assertTrue(hasAdd, "Functions not found in any file");
    }

    @Test
    public void testKotlinFileExtension() throws IOException {
        Path header = getInputFilePath("kotlin/test_structs.h");
        
        int result = run(outputDir,
            "-t", "org.openjdk.kextract.test",
            "-l", "testlib",
            "--target-language", "kotlin",
            header.toString()
        );
        
        assertEquals(result, 0, "kextract should succeed");
        
        // Verify all generated files have .kt extension
        List<Path> ktFiles = listFiles(outputDir, ".kt");
        List<Path> allFiles = listAllFiles(outputDir);
        
        // All source files should be .kt files
        for (Path file : allFiles) {
            String fileName = file.getFileName().toString();
            if (!fileName.endsWith(".kt") && !fileName.startsWith(".")) {
                // Allow directories and hidden files
                if (!file.toFile().isDirectory()) {
                    fail("Non-.kt file generated: " + fileName);
                }
            }
        }
        
        assertFalse(ktFiles.isEmpty(), "No .kt files found");
    }

    @Test
    public void testPackageDeclaration() throws IOException {
        Path header = getInputFilePath("kotlin/test_structs.h");
        
        int result = run(outputDir,
            "-t", "org.openjdk.kextract.test.kotlin",
            "-l", "testlib",
            "--target-language", "kotlin",
            header.toString()
        );
        
        assertEquals(result, 0, "kextract should succeed");
        
        List<Path> ktFiles = listFiles(outputDir, ".kt");
        for (Path file : ktFiles) {
            String content = readFile(file);
            assertTrue(content.startsWith("package org.openjdk.kextract.test.kotlin"),
                "Package declaration missing or incorrect in " + file);
        }
    }

    // --- Helper methods ---
    
    private List<Path> listFiles(Path dir, String extension) throws IOException {
        return java.nio.file.Files.walk(dir)
            .filter(p -> p.toString().endsWith(extension))
            .toList();
    }

    private List<Path> listAllFiles(Path dir) throws IOException {
        return java.nio.file.Files.walk(dir)
            .filter(p -> p.toFile().isFile())
            .toList();
    }

    private String readFile(Path file) throws IOException {
        return java.nio.file.Files.readString(file);
    }

    private void deleteDir(Path dir) throws IOException {
        if (java.nio.file.Files.exists(dir)) {
            java.nio.file.Files.walk(dir)
                .sorted((a, b) -> -a.compareTo(b)) // reverse order: delete files before dirs
                .forEach(p -> {
                    try {
                        java.nio.file.Files.deleteIfExists(p);
                    } catch (IOException e) {
                        // Ignore
                    }
                });
        }
    }
}
