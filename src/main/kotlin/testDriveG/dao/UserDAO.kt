package testDriveG.dao

import dao.ConnectionDAO
import models.User

class UserDAO : GenericDAO{

    //Funcionando
    //Função para pegar um user com base no seu id.
    //Function to get one user from table by id.
    override fun getOne(id: Int): Any {
        val connectionDAO = ConnectionDAO()
        var user : User? = null        //isso aqui está ruim
        try {
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Users WHERE id = ${id};")
            while(resultSet?.next()!!){
                user = User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("email"))
            }
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally {
            connectionDAO.close()
        }
        return user!!
    }

    //Funcionando
    //Função para pegar toda a tabela 'Users'.
    //Function to get all elements from Users table.
    override fun getAll(): List<Any> {
        val connectionDAO = ConnectionDAO()
        val user = mutableListOf<User>()
        try{
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Users;")
            while(resultSet?.next()!!){
                user.add(User(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("password"),
                    resultSet.getString("email"))
                )
            }
        }
        catch(exception : Exception){
            exception.stackTrace
        }
        finally{
            connectionDAO.close()
        }
        return user
    }

    //Funcionando
    //Função que permite adicionar um user na tabela.
    //Function to add user in table.
    override fun addOne(obj: Any) {
        val connectionDAO = ConnectionDAO()
        try{
            val preparedStatement = connectionDAO.getPreparedStatement("""
                INSERT INTO Users
                (name, password, email)
                VALUES (?,?,?)
            """.trimIndent())
            val user = obj as User
            preparedStatement?.setString(1,user.name)
            preparedStatement?.setString(2,user.password)
            preparedStatement?.setString(3,user.email)
            preparedStatement?.executeUpdate()
        }
        catch (exception:Exception){
            exception.stackTrace
        }
        finally {
            connectionDAO.close()
        }
    }

    //Funcionando
    //Função que permite adicinar todos os users em uma lista.
    //Function to add all users in list to table.
    override fun addAll(list: List<Any>) {
        val connectionDAO = ConnectionDAO()
        try{
            val preparedStatement = connectionDAO.getPreparedStatement("""
                INSERT INTO Users
                (name, password, email)
                VALUES (?,?,?)
            """.trimIndent())
            for (obj in list){
                val user = obj as User
                preparedStatement?.setString(1,user.name)
                preparedStatement?.setString(2,user.password)
                preparedStatement?.setString(3,user.email)
                preparedStatement?.executeUpdate()
            }
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally{
            connectionDAO.close()
        }
    }

    //Funcionando
    //Função permite deletar um user da tabela pelo seu id.
    //Function to delete one user from table by the id.
    override fun delete(id: Int) {
        val connectionDAO = ConnectionDAO()
        try {
            val preparedStatement = connectionDAO.getPreparedStatement("""
                DELETE FROM Users
                WHERE id = ?;
                """.trimIndent()
            )
            preparedStatement?.setInt(1, id)
            preparedStatement?.executeUpdate()
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally{
            connectionDAO.close()
        }
    }

    //Funcionando
    //Função permite atualizar um user da tabela.
    //Function to update one user from table.
    override fun update(obj: Any) {
        val connectionDAO = ConnectionDAO()
        try {
            val preparedStatement = connectionDAO.getPreparedStatement("""
                UPDATE Users
                SET name = ?, password = ?, email = ?
                WHERE id = ?;
            """.trimIndent())
            val user = obj as User
            preparedStatement?.setString(1,user.name)
            preparedStatement?.setString(2,user.password)
            preparedStatement?.setString(3,user.email)
            preparedStatement?.setInt(4,user.id)
            preparedStatement?.executeUpdate()
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally{
            connectionDAO.close()
        }
    }
}