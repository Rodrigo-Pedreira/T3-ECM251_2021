package System

import dao.ReviewDAO

class System {
    //init?
    fun run(){
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
}