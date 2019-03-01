package it.docker_dsl

import java.util.*

class DockerCompose {
    lateinit var version: String //TODO в зависимости от версии варьеировать доступные функции докер композ
    private val services = LinkedList<Service>()
    private var currentTab = ""

    companion object {
        const val Delimeter = ":"
    }

    fun services(service: Service.() -> Unit) {
        services.add(Service().apply(service))
    }

    internal fun process() {
        val sb = StringBuilder()
        sb.appendTagValue(Tags.Version.value, "'$version'")
        sb.appendln()
        sb.appendTag(Tags.Services.value)
        currentTab = "\t"
        services.forEach {
            sb.appendService(it)
        }
        println(sb)
    }

    private fun StringBuilder.appendTagValue(tag: String, value: String): StringBuilder {
        return append(tag).append(Delimeter).append(value)
    }

    private fun StringBuilder.appendTagValueln(tag: String, value: String): StringBuilder {
        return appendTag(tag).append(Delimeter).append(value).appendln()
    }

    private fun StringBuilder.appendTag(tag: String): StringBuilder {
        return append(tag).append(Delimeter)
    }

    private fun StringBuilder.appendTagln(tag: String): StringBuilder {
        return append(tag).append(Delimeter).appendln()
    }

    private fun StringBuilder.appendBuild(build: Build): StringBuilder {
        append(currentTab).appendTagValue(Tags.Context.value, build.context!!)
        append(currentTab).appendTagValue(Tags.DockerFile.value, build.dockerFile!!)
        return this
    }

    private fun StringBuilder.appendService(service: Service) {
        append(currentTab).appendTag(service.name)
        currentTab += "\t"
        when {
            service.buildStr != null -> {
                append(currentTab).appendTagValue(Tags.Build.value, service.buildStr!!)
            }
            service.build != null -> {
                appendBuild(service.build!!)
            }
            service.image != null -> {
                append(currentTab).appendTagValue(Tags.Image.value, service.image!!)
            }
        }

        appendln()
        if (service.ports.isNotEmpty()) {
            append(currentTab).appendTag(Tags.Ports.value).appendln()
            currentTab = "\t\t\t"
            service.ports.forEach {
                append(currentTab).append("\"${it.local}").append(Delimeter).append("${it.server}\"").appendln()
            }
        }
        currentTab = "\t\t"

        if (service.volumes.isNotEmpty()) {
            append(currentTab).appendTag(Tags.Volumes.value).appendln()
            currentTab = "\t\t\t"
            service.volumes.forEach {
                append(currentTab).append(it.local).append(Delimeter).append(it.server)
            }
        }

        currentTab = "\t"
    }


}