package testdrivers
//TODO: JavaDocs
interface TestDriveDAOInterface {
    fun selectTest(id: Int = 1)
    fun selectAllTest()
    fun insertTest()
    fun insertManyTest()
    fun updateTest(id: Int = 1)
    fun deleteTest(id: Int = 1)
    fun testAll(select: Int = 1, update: Int = 1, delete: Int = 1) {
        selectTest(select)
        selectAllTest()
        insertTest()
        insertManyTest()
        selectAllTest()
        updateTest(update)
        deleteTest(delete)
        selectAllTest()
    }
}