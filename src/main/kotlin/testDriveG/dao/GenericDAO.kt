package testDriveG.dao

interface GenericDAO {
    fun getOne(id : Int) : Any
    fun getAll() : List<Any>
    fun addOne(obj : Any) : Unit
    fun delete(id : Int) : Any
    fun update() : Any
}