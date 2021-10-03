package testDriveG

import models.Films
import models.Review
import models.User
import testDriveG.dao.FilmDAO
import testDriveG.dao.ReviewDAO
import testDriveG.dao.UserDAO

class SistemaG {
    //init?
    fun run(){
        val userDAO = UserDAO()

        userDAO.delete(4)
        //User(0,"Stella","C0$7el@uhul","Co.Stella@hotmail.com")
        //User(0,"MaMe","dbc87VU43207","maismenos@hotmail.com")


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
        val users = userDAO.getAll()
        for (user in users){
            println(user)
        }
    }
}