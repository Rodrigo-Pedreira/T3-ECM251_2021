package testDriveG

import models.Films
import testDriveG.dao.FilmDAO

class SistemaG {
    //init?
    fun run(){
        var filmDAO = FilmDAO()
        //filmDAO.addOne(Films(0,"Parasite","thriller","Bong Joon-ho","11/07/2019"))
        //filmDAO.addOne(Films(0,"Parasite","thriller","Bong Joon-ho","11/07/2019"))
        //println("Info filme parasite:")
        //println(filmDAO.getOne("Parasite"))
        //println("Filme com id = 1:")
        //println(filmDAO.getOne(1))
        //println("Todos os filmes")

        //filmDAO.delete(4)

        filmDAO.update(Films(3,"etisaraP","thriller","Bong Joon-ho","11/07/2019"))

        var films = filmDAO.getAll()
        for (film in films){
            println(film)
        }
    }
}