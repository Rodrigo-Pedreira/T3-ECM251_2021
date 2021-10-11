package controller

import testdrivers.FilmDAOTestDrive
import testdrivers.ReviewDAOTestDrive
import testdrivers.UserDAOTestDrive

class MainController {
    companion object {
        private fun printTestSeparator() { println("\n**__*************________***************________*************__**\n\n")}

        fun testDAOs(select: Int = 1, update: Int = 1, delete: Int = 1) {
            println("\n")
            ReviewDAOTestDrive.testAll(select, update, delete)
            printTestSeparator()

            FilmDAOTestDrive.testAll(select, update, delete)
            printTestSeparator()

            UserDAOTestDrive.testAll(select, update, delete)
        }
    }
}