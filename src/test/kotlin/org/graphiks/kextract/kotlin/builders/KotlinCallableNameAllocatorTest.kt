package org.graphiks.kextract.kotlin.builders

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class KotlinCallableNameAllocatorTest : FreeSpec({
    "suffixes a distinct raw selector only for the colliding Kotlin signature" {
        val names = KotlinCallableNameAllocator()

        names.allocate("foo_bar:baz:", "foo_bar_baz", listOf("Long", "Long")) shouldBe
            "foo_bar_baz"
        names.allocate("foo:bar_baz:", "foo_bar_baz", listOf("Long", "Long")) shouldBe
            "foo_bar_baz__objc_666f6f3a6261725f62617a3a"
        names.allocate("foo:bar_baz:", "foo_bar_baz", listOf("Long", "Long")) shouldBe
            "foo_bar_baz__objc_666f6f3a6261725f62617a3a"

        // Distinct parameter types remain regular Kotlin overloads and keep their legacy name.
        names.allocate("foo:bar_baz:", "foo_bar_baz", listOf("Int", "Int")) shouldBe
            "foo_bar_baz"
    }

    "keeps an extension and a top-level callable distinct when their receiver differs" {
        val names = KotlinCallableNameAllocator()

        names.allocate("Foo_bar:", "Foo_bar", listOf("Long"), receiver = "Foo") shouldBe "Foo_bar"
        names.allocate("bar:", "Foo_bar", listOf("Long")) shouldBe "Foo_bar"
    }

    "synthetic callables reserve their emitted name before later raw selectors" {
        val names = KotlinCallableNameAllocator()

        names.allocate("foo", "foo", emptyList()) shouldBe "foo"
        names.allocateSynthetic("NSStringAsString:foo", "fooAsString", emptyList()) shouldBe "fooAsString"
        names.allocate("fooAsString", "fooAsString", emptyList()) shouldBe
            "fooAsString__objc_666f6f4173537472696e67"
    }
})
