package testDriveG

import models.Films
import models.Review
import testDriveG.dao.FilmDAO
import testDriveG.dao.ReviewDAO

class SistemaG {
    //init?
    fun run(){
        val reviewDAO = ReviewDAO()

        reviewDAO.update(Review(5,3,1,"Homem De Ferro S2",25,4.3,"10/3/2021"))

        //filmDAO.delete(4)

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
}