package testDriveG

import models.Films
import testDriveG.dao.FilmDAO

class SistemaG {
    //init?
    fun run(){
        var filmDAO = FilmDAO()
        filmDAO.addOne(Films(0,"Grown Ups","comedy","Dennis Dugan","09/24/2010"))
        var films = filmDAO.getAll()
        for (film in films){
            println(film)
        }
    }
}