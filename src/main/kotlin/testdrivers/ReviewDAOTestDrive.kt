package testdrivers

import dao.ReviewDAO
import models.Review

//TODO: JavaDocs
class ReviewDAOTestDrive {
    companion object : TestDriveDAOInterface {
        override fun selectTest(id: Int) {
            super<TestDriveDAOInterface>.selectTest(id)
            val rev = ReviewDAO.select(id)
            println(rev)
            printDash()
        }

        override fun selectAllTest() {
            super<TestDriveDAOInterface>.selectAllTest()
            val revS = ReviewDAO.selectAll()
            revS.forEach { println(it) }
            printDash()
        }

        override fun insertTest() {
            super<TestDriveDAOInterface>.insertTest()
            val t = Review(1111, 1222, 1333, "Insert", 1444, 1555.0, "0001/0001")
            ReviewDAO.insert(t)
            println(t)
            printDash()
        }

        override fun insertManyTest() {
            super<TestDriveDAOInterface>.insertManyTest()
            val t = Review(2111, 2222, 2333, "InsertMany", 2444, 2555.0, "0002/0002")
            val t2 = Review(3111, 3222, 3333, "InsertMany2", 3444, 3555.0, "0003/0003")
            val revI = listOfNotNull<Review>(t, t2)
            revI.forEach { println(it) }
            ReviewDAO.insertMany(revI)
            printDash()
        }

        override fun updateTest(id: Int) {
            super<TestDriveDAOInterface>.updateTest(id)
            val t = Review(id, 4222, 4333, "Update", 4444, 4555.0, "0004/0004")
            ReviewDAO.update(t)
            selectTest(id)
            printStar()
        }

        override fun deleteTest(id: Int) {
            super<TestDriveDAOInterface>.deleteTest(id)
            ReviewDAO.delete(id)
            printStar()
        }

        override fun testAll(select: Int, update: Int, delete: Int) {
            super<TestDriveDAOInterface>.testAll(select, update, delete)
        }

        override fun printDash() {
            super<TestDriveDAOInterface>.printDash()
        }

        override fun printStar() {
            super<TestDriveDAOInterface>.printStar()
        }
    }
}