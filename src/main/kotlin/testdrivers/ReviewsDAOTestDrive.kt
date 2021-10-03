package testdrivers

import dao.ReviewDAO
import models.Review
//TODO: JavaDocs
class ReviewsDAOTestDrive {
    companion object : DAOTestDriveInterface {
        override fun selectTest(id: Int) {
            println("SelectTest")
            val rev = ReviewDAO.select(id)
            println(rev)
            println("--------------------")
        }

        override fun selectAllTest() {
            println("SelectAllTest")
            val revS = ReviewDAO.selectAll()
            revS.forEach { println(it) }
            println("--------------------\n")
        }

        override fun insertTest() {
            println("InsertTest")
            val t = Review(1111, 1222, 1333, "Insert", 1444, 1555.0, "0001/0001")
            ReviewDAO.insert(t)
            println(t)
            println("--------------------\n")
        }

        override fun insertManyTest() {
            println("InsertManyTest")
            val t = Review(2111, 2222, 2333, "InsertMany", 2444, 2555.0, "0002/0002")
            val t2 = Review(3111, 3222, 3333, "InsertMany2", 3444, 3555.0, "0003/0003")
            val revI = listOfNotNull<Review>(t, t2)
            revI.forEach { println(it) }
            ReviewDAO.insertMany(revI)
            println("--------------------\n")
        }

        override fun updateTest(id: Int) {
            println("********UpdateTeste")
            val t = Review(id, 4222, 4333, "Update", 4444, 4555.0, "0004/0004")
            selectTest(id)
            ReviewDAO.update(t)
            selectTest(id)
            println("********--------------------\n")
        }

        override fun deleteTest(id: Int) {
            println("********DeleteTest")
            selectTest(id)
            ReviewDAO.delete(id)
            println("********--------------------\n")
        }

        override fun testAll(select: Int, update: Int, delete: Int) {
            super<DAOTestDriveInterface>.testAll(select, update, delete)
        }
    }
}