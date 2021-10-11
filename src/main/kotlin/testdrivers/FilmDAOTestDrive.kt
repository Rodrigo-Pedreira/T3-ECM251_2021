package testdrivers

import dao.FilmDAO
import models.Film

class FilmDAOTestDrive {
    companion object : TestDriveDAOInterface {
        override fun selectTest(id: Int) {
            super<TestDriveDAOInterface>.selectTest(id)
            val film = FilmDAO.select(id)
            println(film)
            printDash()
        }

        override fun selectAllTest() {
            super<TestDriveDAOInterface>.selectAllTest()
            val filmS = FilmDAO.selectAll()
            filmS.forEach { println(it) }
            printDash()
        }

        override fun insertTest() {
            super<TestDriveDAOInterface>.insertTest()
            val t = Film(1111, "Insert", "Insert", "Insert", "0001/0001")
            FilmDAO.insert(t)
            println(t)
            printDash()
        }

        override fun insertManyTest() {
            super<TestDriveDAOInterface>.insertManyTest()
            val t = Film(2111, "InsertMany", "InsertMany", "InsertMany", "0002/0002")
            val t2 = Film(3111, "InsertMany2", "InsertMany2", "InsertMany2", "0003/0003")
            val filmI = listOfNotNull<Film>(t, t2)
            filmI.forEach { println(it) }
            FilmDAO.insertMany(filmI)
            printDash()
        }

        override fun updateTest(id: Int) {
            super<TestDriveDAOInterface>.updateTest(id)
            val t = Film(id, "Update", "Update", "Update", "0004/0004")
            FilmDAO.update(t)
            selectTest(id)
            printStar()
        }

        override fun deleteTest(id: Int) {
            super<TestDriveDAOInterface>.deleteTest(id)
            FilmDAO.delete(id)
            printStar()
        }

        override fun testAll(select: Int, update: Int, delete: Int) {
            super<TestDriveDAOInterface>.testAll(select, update, delete)
            selectFromStringTest()
        }

        override fun printDash() {
            super<TestDriveDAOInterface>.printDash()
        }

        override fun printStar() {
            super<TestDriveDAOInterface>.printStar()
        }

        fun selectFromStringTest(
            name: String = "INSERTMANY",
            genre: String = "",
            director: String = "",
            date: String = "",
        ) {
            println("selectFromStringTest: name: $name, genre: $genre, date: $date")
            val t = FilmDAO.selectFromString(name, genre, director, date)
            t.forEach { println(it) }
            println("--------------------\n")
        }
    }
}