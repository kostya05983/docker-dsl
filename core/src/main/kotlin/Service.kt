import java.util.*

class Service {
    lateinit var name: String
    var image: String? = null
    var build: String? = null
    internal val ports = LinkedList<Port>()
    internal val volumes = LinkedList<Volume>()
    private val links = LinkedList<String>()


    fun ports(port: Port.() -> Unit) {
        ports.add(Port().apply(port))
    }

    fun volume(volume: Volume.() -> Unit) {
        volumes.add(Volume().apply(volume))
    }

    fun link(link: String) {
        links.add(link)
    }
}