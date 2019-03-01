package it.docker_dsl

class Runner {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            dockerCompose {
                version = "3"
                services {
                    name = "web"
                    build("./")

                    ports {
                        local = 5000
                        server = 5000
                    }
                    volume {
                        local = "."
                        server = "/usr"
                    }
                }

                services {
                    name = "redis"
                    image = "redis:alpine"
                }
            }
        }
    }
}