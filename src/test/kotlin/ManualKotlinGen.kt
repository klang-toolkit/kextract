import org.openjdk.kextract.Declaration
import org.openjdk.kextract.impl.*
import org.openjdk.kextract.kotlin.KotlinGenerator
import org.openjdk.kextract.kotlin.models.KotlinSourceFile

fun main() {
    val logger = Logger.DEFAULT
    val clangArgs = mutableListOf("-I" + System.getProperty("user.dir"))
    val source = """
        typedef int my_int;

        typedef struct Point {
            int x;
            int y;
        } Point;

        int add(int a, int b);
    """.trimIndent()

    val parser = Parser(logger)
    val toplevel: Declaration.Scoped = parser.parse("test.h", source, clangArgs)

    val generator = KotlinGenerator()
    val files = generator.generate(toplevel, "test.h", "org.test")

    files.forEach { file ->
        println("=== Generated Kotlin File: ${file.getPath()} ===")
        println(file.contents)
        println()
    }
}
