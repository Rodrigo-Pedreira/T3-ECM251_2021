package System

import dao.ReviewDAO
import io.ktor.features.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

import br.maua.routes.registerUserRoutes

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*


class System {
    //init?
    fun runDAOtest(){
        val reviewDAO = ReviewDAO()


        //reviewDAO.delete(7)

        //filmDAO.update(Films(3,"Parasite","thriller","Bong Joon-ho","11/07/2019"))
        //filmDAO.addOne(Films(0,"Grown Ups 2","comedy","Dennis Dugan","08/16/2013"))
/*
        filmDAO.addAll(
            listOf<Films>(
            Films(0,"Grown Ups 2","comedy","Dennis Dugan","08/16/2013"),
            Films(0,"The Fast and the Furious","action","Rob Cohen","09/28/2001")
            )
        )
*/
        val reviews = reviewDAO.getAll()
        for (review in reviews){
            println(review)
        }
    }

    fun runKtor(){
        embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            //configureRouting()

            install(ContentNegotiation) {
                json()
            }

            registerUserRoutes()
        }.start(wait = true)
    }
}

