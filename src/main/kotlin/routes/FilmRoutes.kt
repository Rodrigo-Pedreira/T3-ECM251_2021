package routes

import dao.FilmDAO
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Film


fun Route.filmRouting() {
    route("/film"){

        //Get all films in database
        get ("/selectAll"){
            val films = FilmDAO.selectAll()
            if (films[0].id == -1){
                call.respondText("No film registered.",status = HttpStatusCode.NotFound)
            }
            call.respond(films)
        }

        //Get film by id
        get ("/select/{id}"){
            try {
                val id = call.parameters["id"]?.toInt()
                val film = FilmDAO.select(id ?: 0)
                if (film.id == -1){
                    call.respondText("No film with id.",status = HttpStatusCode.BadRequest)
                } else {
                    call.respond(film)
                }
            }
            catch (exception:Exception){
                exception.stackTrace
            }
            finally {
                call.respondText("Invalid film id.", status = HttpStatusCode.Conflict)
            }
        }

        //Add film
        post ("/insert") {

            val parameters = call.receiveParameters()
            val name = parameters["name"] ?: return@post call.respondText(
                "Missing film name.",status = HttpStatusCode.NoContent)
            val genre = parameters["genre"] ?: return@post call.respondText(
                "Missing genre.",status = HttpStatusCode.NoContent)
            val director = parameters["director"] ?: return@post call.respondText(
                "Missing director.",status = HttpStatusCode.NoContent)
            val date = parameters["date"] ?: return@post call.respondText(
                "Missing date.",status = HttpStatusCode.NoContent)

            FilmDAO.insert(Film(1,name,genre,director,date))

            call.respondText(
                "Film stored correctly.",
                status = HttpStatusCode.Created
            )
        }

        //Update film
        put ("/update/{id}") {

            try {
                val id = call.parameters["id"]?.toInt() ?: return@put call.respondText(
                    "No id.",status = HttpStatusCode.BadRequest
                )

                val film = FilmDAO.select(id)

                if (film.id == -1) {
                    call.respondText("No user with such id.",status = HttpStatusCode.NotFound)
                } else {
                    val parameters = call.receiveParameters()
                    val name = parameters["name"] ?: film.name
                    val genre = parameters["genre"] ?: film.genre
                    val director = parameters["director"] ?: film.director
                    val date = parameters["date"] ?: film.date
                    //ou
                    /*
                    tem que atualizar !!
                    val name = parameters["name"] ?: return@put call.respondText(
                        "Missing user name.",status = HttpStatusCode.NoContent)
                    val password = parameters["password"] ?: return@put call.respondText(
                        "Missing password.",status = HttpStatusCode.NoContent)
                    val email = parameters["email"] ?: return@put call.respondText(
                        "Missing email.",status = HttpStatusCode.NoContent)
                    */
                    val newFilm = Film(id,name,genre,director,date)
                    FilmDAO.update(newFilm)
                    call.respondText("Update successful.",status = HttpStatusCode.Accepted)
                }
            }
            catch (exception:Exception){
                exception.stackTrace
            }
            finally{
                call.respondText("Invalid Id.",status = HttpStatusCode.BadRequest)
            }
        }

        //Delete film
        delete("/delete/{id}") {
            // se funcionar o teste da estrutura acima, aplicar aqui

            val id = call.parameters["id"] ?: return@delete call.respond(
                HttpStatusCode.BadRequest
            )

            val film = FilmDAO.select(id.toInt())

            if (film.id == -1){
                call.respondText(
                    "No film with this id.",
                    status = HttpStatusCode.NotFound
                )
            } else {
                FilmDAO.delete(id.toInt())
                call.respondText(
                    "Film removed sucefuly.",
                    status = HttpStatusCode.Accepted
                )
            }
        }
    }
}

fun Application.registerFilmRoutes() {
    routing {
        filmRouting()
    }
}