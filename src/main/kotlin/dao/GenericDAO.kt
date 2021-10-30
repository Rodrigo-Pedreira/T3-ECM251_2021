package dao

interface GenericDAO {
    fun getOne(id : Int) : Any
    fun getAll() : List<Any>
    fun addOne(obj : Any) : Unit
    fun addAll(list : List<Any>) : Unit
    fun delete(id : Int)
    fun update(obj : Any)
}