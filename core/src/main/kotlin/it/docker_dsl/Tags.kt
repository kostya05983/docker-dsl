package it.docker_dsl

enum class Tags(val value: String) {
    Version("version"), Services("services"),
    Build("build"), Ports("ports"),
    Image("image"), Volumes("volumes"),
    Context("context"), DockerFile("dockerfile"),
    Args("args");
}