import it.docker_dsl.yaml.builder.YamlBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.FileReader

class YamlBuilderTest {


    /**
     * version: '3'
     * services:
     *   web:
     *     build: ""
     *     ports:
     *       - "5000:5000"
     *   redis:
     *     image: "redis:alpine"
     */
    @Test
    fun example1() {
        val fr = FileReader("src/test/resources/docker_compose/example1.yml")
        val yamlFile = fr.readLines().joinToString("\n")

        val yb = YamlBuilder()
        yb.writeTag("version", "'3'")
        yb.writeTag("services")
        yb.indent()
        yb.writeTag("web")
        yb.indent()
        yb.writeTag("build", "\"\"")
        yb.writeTagArray("ports", "\"5000:5000\"")
        yb.dedent()
        yb.writeTag("redis")
        yb.indent()
        yb.writeTag("image", "\"redis:alpine\"")
    }

    @Test
    fun addingTag() {
        val yb = YamlBuilder()
        yb.writeTag("version")

        val sb = StringBuilder().append("version:\n")
        assertEquals(sb.toString(), yb.toString())
    }

    @Test
    fun addingTagValue() {
        val yb = YamlBuilder()
        yb.writeTag("version", "'3'")

        val sb = StringBuilder().append("version: '3'\n")
        assertEquals(sb.toString(), yb.toString())
    }

    @Test
    fun writeTagArray() {
        val yb = YamlBuilder()
        yb.writeTagArray("ports", "\"5000:5000\"")

        val sb = StringBuilder().append("ports:\n")
                .append("  - \"5000:5000\"")
        assertEquals(sb.toString(), yb.toString())
    }
}