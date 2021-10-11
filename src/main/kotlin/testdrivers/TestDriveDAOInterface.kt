package testdrivers
//TODO: JavaDocs
interface TestDriveDAOInterface {

    fun selectTest(id: Int = 1) { println("-------------------- SelectTest id: $id --------------------")}
    fun selectAllTest()         { println("-------------------- SelectAllTest --------------------")}
    fun insertTest()            { println("-------------------- InsertTest --------------------")}
    fun insertManyTest()        { println("-------------------- InsertManyTest --------------------")}
    fun updateTest(id: Int = 1) {
        println("******************** UpdateTeste id: $id ********************")
        selectTest(id)
    }
    fun deleteTest(id: Int = 1) {
        println("******************** DeleteTest id: $id ********************")
        selectTest(id)
    }
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
    fun printDash() { println("----------------------------------------\n")}
    fun printStar() { println("****************************************\n")}
}