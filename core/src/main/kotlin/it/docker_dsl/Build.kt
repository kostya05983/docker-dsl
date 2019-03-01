package it.docker_dsl

class Build {
    val context: String? = null
    val dockerFile: String? = null
    private val args: ArrayList<Arg> = ArrayList()

    fun arg(arg: Arg.()->Unit) {
        args.add(Arg().apply(arg))
    }
}