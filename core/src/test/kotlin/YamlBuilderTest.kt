import it.docker_dsl.yaml.builder.YamlBuilder
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class YamlBuilderTest {


    /**
     * version: '3'
     *   services:
     *   web:
     *   build: ""
     *   ports:
     *   - "5000:5000"
    8   redis:
     *   image: "redis:alpine"
     */
    @Test
    fun example1() {

    }

    @Test
    fun addingIndent() {
        val yamlBuilder = YamlBuilder()
        yamlBuilder.writeTag("version", "'3'")

        yamlBuilder.addIndent()
        val sb = StringBuilder().append("version: '3'")
                .append("\n  ")
        assertEquals(yamlBuilder.toString(), sb.toString())
    }


}