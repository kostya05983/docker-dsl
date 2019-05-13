package org.docker_dsl.yaml.builder

/**
 * class for yaml buildings
 * @author kostya05983
 */
class YamlBuilder {
    companion object {
        private const val DEVIDER_WITH_NEWLINE = ":\n"
        private const val DEVIDER = ": "
        private const val INCREMENT_INDENT = "  "
        private const val ARRAY_KEY = "- "
    }

    private val currentIndent = StringBuilder()
    var amountIndent = 0

    private val sb = StringBuilder()

    /**
     * @param - tag which is wrote left
     * example
     * val yb = YamlBuilder()
     * yb.writeTag("version")
     * println(yb.toString)
     * Output:
     * version:
     */
    fun writeTag(tag: String) {
        sb.append(currentIndent).append(tag).append(DEVIDER_WITH_NEWLINE)
    }

    /**
     * @param tag - which is wrote left
     * @param value - value write right in yaml files from tag
     * Example:
     * val yb = YamlBuilder()
     * yb.writeTag("version", "'3'")
     * println(yb.toString())
     * Output:
     * version: '3'
     */
    fun writeTag(tag: String, value: String) {
        sb.append(currentIndent).append(tag).append(DEVIDER).append(value).append("\n")
    }

    /**
     * @param tag - writes left in yaml, key
     * @param list - array values of key
     * Example:
     * val yb = YamlBuilder()
     * yb.writeTagArray("ports", "/"5000:5000/"")
     * Output:
     * ports:
     *   - "5000:5000"
     */
    fun writeTagArray(tag: String, vararg list: String) {
        sb.append(currentIndent).append(tag).append(DEVIDER_WITH_NEWLINE)
        indent()
        for (value in list) {
            sb.append(currentIndent).append(ARRAY_KEY)
                    .append(value)
            if (list.last() == value) {
                sb.append("\n")
            }
        }
        dedent()
    }

    /**
     * increment indent in yaml file
     */
    fun indent() {
        amountIndent++
        currentIndent.append(INCREMENT_INDENT)
    }

    /**
     * decrement, makes DEDENT in yaml file
     */
    fun dedent() {
        amountIndent--
        currentIndent.clear()
        for (i in 0 until amountIndent) {
            currentIndent.append(INCREMENT_INDENT)
        }
    }

    override fun toString(): String {
        return sb.toString()
    }
}