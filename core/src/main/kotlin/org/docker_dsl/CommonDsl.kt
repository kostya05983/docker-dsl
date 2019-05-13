package org.docker_dsl

fun dockerCompose(dockerCompose: DockerCompose.() -> Unit) {
    val instance = DockerCompose().apply(dockerCompose)
    instance.process()
}