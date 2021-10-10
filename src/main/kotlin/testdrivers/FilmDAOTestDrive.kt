package testdrivers

import dao.FilmDAO
import models.Film

class FilmDAOTestDrive {
    companion object : TestDriveDAOInterface {
        override fun selectTest(id: Int) {
            println("SelectTest")
            val film = FilmDAO.select(id)
            println(film)
            println("--------------------")
        }

        override fun selectAllTest() {
            println("SelectAllTest")
            val filmS = FilmDAO.selectAll()
            filmS.forEach { println(it) }
            println("--------------------\n")
        }

        override fun insertTest() {
            println("InsertTest")
            val t = Film(1111, "Insert", "Insert", "Insert", "0001/0001")
            FilmDAO.insert(t)
            println(t)
            println("--------------------\n")
        }

        override fun insertManyTest() {
            println("InsertManyTest")
            val t = Film(2111, "InsertMany", "InsertMany", "InsertMany", "0002/0002")
            val t2 = Film(3111, "InsertMany2", "InsertMany2", "InsertMany2", "0003/0003")
            val filmI = listOfNotNull<Film>(t, t2)
            filmI.forEach { println(it) }
            FilmDAO.insertMany(filmI)
            println("--------------------\n")
        }

        override fun updateTest(id: Int) {
            println("********UpdateTeste")
            val t = Film(id, "Update", "Update", "Update", "0004/0004")
            selectTest(id)
            FilmDAO.update(t)
            selectTest(id)
            println("********--------------------\n")
        }

        override fun deleteTest(id: Int) {
            println("********DeleteTest")
            selectTest(id)
            FilmDAO.delete(id)
            println("********--------------------\n")
        }

        override fun testAll(select: Int, update: Int, delete: Int) {
            super<TestDriveDAOInterface>.testAll(select, update, delete)
        }
    }
}