package br.maua.routes

import dao.UserDAO
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.sql.DriverManager

fun Route.userRouting() {
    route("/user"){
        get ("/getall"){
            val userDAO = UserDAO()
            val users = userDAO.getAll()
            if (users.isEmpty()){
                call.respondText("No user registered.",status = HttpStatusCode.NotFound)
            }
            call.respond(users)
        }
        get ("/getid/{id}"){
            val userDAO = UserDAO()
            val id = call.parameters["id"]!!.toInt()
            val user = userDAO.getOne(id)
            if (user == null){
                call.respondText("Invalid user id.",status = HttpStatusCode.BadRequest)
            } else {
                call.respond(user)
            }
        }

        //Salvar um usuário em formato JSON ?
        /*
        post {

            val user = call.receive<User>()
            userStorage.add(user)
            call.respondText(
                "User stored correctly",
                status = HttpStatusCode.Created
            )
        }
        //Deletar um usuário
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(
                HttpStatusCode.BadRequest
            )
            if (userStorage.removeIf{it.id == id.toInt()}){
                call.respondText(
                    "User removed correctly",
                    status = HttpStatusCode.Accepted
                )
            } else {
                call.respondText(
                    "Not Found",
                    status = HttpStatusCode.NotFound
                )
            }
        }*/
    }
}

fun Application.registerUserRoutes() {
    routing {
        userRouting()
    }
}