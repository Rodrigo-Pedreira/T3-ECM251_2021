package br.maua.routes

import dao.UserDAO
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.User
import java.sql.DriverManager

fun Route.userRouting() {
    route("/user"){

        //Get all users in database
        get ("/getall"){
            val userDAO = UserDAO()
            val users = userDAO.getAll()
            if (users.isEmpty()){
                call.respondText("No user registered.",status = HttpStatusCode.NotFound)
            }
            call.respond(users)
        }

        //Get user by id
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

        //Add user
        post ("/addone") {

            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: return@post call.respondText(
                "Missing user name.",status = HttpStatusCode.NoContent)
            val password = parameters["password"] ?: return@post call.respondText(
                "Missing password.",status = HttpStatusCode.NoContent)
            val email = parameters["email"] ?: return@post call.respondText(
                "Missing email.",status = HttpStatusCode.NoContent)

            UserDAO().addOne(User(1,name,password,email))

            call.respondText(
                "User stored correctly",
                status = HttpStatusCode.Created
            )
        }

        //Update user
        put ("/update/{id}") {

            val id = call.parameters["id"]?.toInt() ?: return@put call.respond(
                HttpStatusCode.BadRequest
            )

            val userDAO = UserDAO()
            val user = userDAO.getOne(id)

            if (user == null) {
                call.respondText("No user with such id.",status = HttpStatusCode.NotFound)
            } else {

                val parameters = call.receiveParameters()
                val name = parameters["name"] ?: user.name
                val password = parameters["password"] ?: user.password
                val email = parameters["email"] ?: user.email
                //ou
                /*
                val name = parameters["name"] ?: return@put call.respondText(
                    "Missing user name.",status = HttpStatusCode.NoContent)
                val password = parameters["password"] ?: return@put call.respondText(
                    "Missing password.",status = HttpStatusCode.NoContent)
                val email = parameters["email"] ?: return@put call.respondText(
                    "Missing email.",status = HttpStatusCode.NoContent)
                */

                val newUser = User(id,name,password,email)

                userDAO.update(newUser)

                call.respondText("Update successful.",status = HttpStatusCode.Accepted)
            }
        }

        //Delete user
        delete("/delete/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(
                HttpStatusCode.BadRequest
            )

            val userDAO = UserDAO()
            val user = userDAO.getOne(id.toInt())

            if (user == null){
                call.respondText(
                    "No user with this id.",
                    status = HttpStatusCode.NotFound
                )
            } else {
                userDAO.delete(id.toInt())
                call.respondText(
                    "User removed sucefuly.",
                    status = HttpStatusCode.Accepted
                )
            }
        }
    }
}

fun Application.registerUserRoutes() {
    routing {
        userRouting()
    }
}