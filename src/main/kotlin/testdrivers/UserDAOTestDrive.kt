package testdrivers

import dao.UserDAO
import models.User

class UserDAOTestDrive {
    companion object : TestDriveDAOInterface {
        override fun selectTest(id: Int) {
            super<TestDriveDAOInterface>.selectTest(id)
            val user = UserDAO.select(id)
            println(user)
            printDash()
        }

        override fun selectAllTest() {
            super<TestDriveDAOInterface>.selectAllTest()
            val userS = UserDAO.selectAll()
            userS.forEach { println(it) }
            printDash()
        }

        override fun insertTest() {
            super<TestDriveDAOInterface>.insertTest()
            val t = User(1111,"Insert", "Insert", "Insert")
            UserDAO.insert(t)
            println(t)
            printDash()
        }

        override fun insertManyTest() {
            super<TestDriveDAOInterface>.insertManyTest()
            val t = User(2111,"InsertMany", "InsertMany", "InsertMany")
            val t2 = User(3111,"InsertMany2", "InsertMany2", "InsertMany2")
            val userI = listOfNotNull<User>(t, t2)
            userI.forEach { println(it) }
            UserDAO.insertMany(userI)
            printDash()
        }

        override fun updateTest(id: Int) {
            super<TestDriveDAOInterface>.updateTest(id)
            val t = User(id, "Update", "Update", "Update")
            UserDAO.update(t)
            selectTest(id)
            printStar()
        }

        override fun deleteTest(id: Int) {
            super<TestDriveDAOInterface>.deleteTest(id)
            UserDAO.delete(id)
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