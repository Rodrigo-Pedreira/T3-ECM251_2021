package testdrivers
//TODO: JavaDocs
interface DAOTestDriveInterface {
    fun selectTest(id: Int = 1)
    fun selectAllTest()
    fun insertTest()
    fun insertManyTest()
    fun updateTest(id: Int = 1)
    fun deleteTest(id: Int = 1)
    fun testAll(select: Int = 1, update: Int = 1, delete: Int = 1) {
        ReviewsDAOTestDrive.selectTest(select)
        ReviewsDAOTestDrive.selectAllTest()
        ReviewsDAOTestDrive.insertTest()
        ReviewsDAOTestDrive.insertManyTest()
        ReviewsDAOTestDrive.selectAllTest()
        ReviewsDAOTestDrive.updateTest(update)
        ReviewsDAOTestDrive.deleteTest(delete)
        ReviewsDAOTestDrive.selectAllTest()
    }
}