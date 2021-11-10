package controller

import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.*
import io.ktor.application.*
import testdrivers.FilmDAOTestDrive
import testdrivers.ReviewDAOTestDrive
import testdrivers.UserDAOTestDrive
import routes.*

class MainController {
    companion object {
        private fun printTestSeparator() { println("\n**__*************________***************________*************__**\n\n")}

        fun testDAOs(select: Int = 1, update: Int = 1, delete: Int = 1) {
            println("\n")
            ReviewDAOTestDrive.testAll(select, update, delete)
            printTestSeparator()

            FilmDAOTestDrive.testAll(select, update, delete)
            printTestSeparator()

            UserDAOTestDrive.testAll(select, update, delete)
        }

        fun runKtor(){
            embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

                install(ContentNegotiation) {
                    json()
                }

                registerUserRoutes()
                registerFilmRoutes()
                registerReviewRoutes()
            }.start(wait = true)
        }
    }
}