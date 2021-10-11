package dao;

interface GenericDAOInterface {
    fun select(id:Int) : Any
    fun selectAll() : List<Any>
    fun insert(obj: Any)
    fun insertMany(list : List<Any>)
    fun update(obj : Any)
    fun delete(id : Int)
}
