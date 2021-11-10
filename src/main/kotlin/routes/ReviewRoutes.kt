package routes

import dao.ReviewDAO
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Review


fun Route.reviewRouting() {
    route("/review"){

        //Get all reviews in database
        get ("/selectAll"){
            val reviews = ReviewDAO.selectAll()
            if (reviews[0].id == -1){
                call.respondText("No review registered.",status = HttpStatusCode.NotFound)
            }
            call.respond(reviews)
        }

        //Get review by id
        get ("/select/{id}"){
            try {
                val id = call.parameters["id"]?.toInt()
                val review = ReviewDAO.select(id ?: 0)
                if (review.id == -1){
                    call.respondText("No review with id.",status = HttpStatusCode.BadRequest)
                } else {
                    call.respond(review)
                }
            }
            catch (exception:Exception){
                exception.stackTrace
            }
            finally {
                call.respondText("Invalid review id.", status = HttpStatusCode.Conflict)
            }
        }

        //Add review
        post ("/insert") {

            val parameters = call.receiveParameters()
            val idUser = parameters["idUser"] ?: return@post call.respondText(
                "Missing user id.",status = HttpStatusCode.NoContent)
            val idFilm = parameters["idFilm"] ?: return@post call.respondText(
                "Missing film id.",status = HttpStatusCode.NoContent)
            val review = parameters["review"] ?: return@post call.respondText(
                "Missing review.",status = HttpStatusCode.NoContent)
            val likes = parameters["likes"] ?: return@post call.respondText(
                "Missing likes.",status = HttpStatusCode.NoContent)
            val score = parameters["score"] ?: return@post call.respondText(
                "Missing score.",status = HttpStatusCode.NoContent)
            val date = parameters["date"] ?: return@post call.respondText(
                "Missing date.",status = HttpStatusCode.NoContent)

            ReviewDAO.insert(Review(1,idUser.toInt(),idFilm.toInt(),review,likes.toInt(),score.toDouble(),date))

            call.respondText(
                "Review stored correctly.",
                status = HttpStatusCode.Created
            )
        }

        //Update review
        put ("/update/{id}") {

            try {
                val id = call.parameters["id"]?.toInt() ?: return@put call.respondText(
                    "No id.",status = HttpStatusCode.BadRequest
                )

                val oldReview = ReviewDAO.select(id)

                if (oldReview.id == -1) {
                    call.respondText("No review with such id.",status = HttpStatusCode.NotFound)
                } else {
                    val parameters = call.receiveParameters()
                    val idUser = parameters["idUser"]?.toInt() ?: oldReview.idUser
                    val idFilm = parameters["idFilm"]?.toInt() ?: oldReview.idFilm
                    val review = parameters["review"] ?: oldReview.review
                    val likes = parameters["likes"]?.toInt() ?: oldReview.likes
                    val score = parameters["score"]?.toDouble() ?: oldReview.score
                    val date = parameters["date"] ?: oldReview.date
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
                    val newReview = Review(id,idUser,idFilm,review,likes,score,date)
                    ReviewDAO.update(newReview)
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

        //Delete review
        delete("/delete/{id}") {

            val id = call.parameters["id"] ?: return@delete call.respond(
                HttpStatusCode.BadRequest
            )

            val review = ReviewDAO.select(id.toInt())

            if (review.id == -1){
                call.respondText(
                    "No review with this id.",
                    status = HttpStatusCode.NotFound
                )
            } else {
                ReviewDAO.delete(id.toInt())
                call.respondText(
                    "Review removed sucefuly.",
                    status = HttpStatusCode.Accepted
                )
            }
        }
    }
}

fun Application.registerReviewRoutes() {
    routing {
        reviewRouting()
    }
}