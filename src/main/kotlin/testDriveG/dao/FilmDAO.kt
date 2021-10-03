package testDriveG.dao

import dao.ConnectionDAO
import models.Films

class FilmDAO : GenericDAO {

    //Funcionou  -  Precisa Melhorar !!
    //Função para pegaar um filme com base no seu id.
    //Function to get one film from the table by id.
    override fun getOne(id : Int): Any {
        val connectionDAO = ConnectionDAO()
        var film : Films? = null        //isso aqui está ruim
        try {
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Films WHERE id == ${id};")
            film = Films(
                resultSet!!.getInt("id"),       //Esse "!!" me preocupa
                resultSet.getString("name"),
                resultSet.getString("genre"),
                resultSet.getString("director"),
                resultSet.getString("date")
            )
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally {
            connectionDAO.close()
        }
        return film!!
    }

    //Funcionou  -  Precisa Melhorar !!
    //Função para pegar um filme da tabela pelo nome.
    //Function to get one film from the table by the name.
    fun getOne(name : String): Any {
        val connectionDAO = ConnectionDAO()
        var film : Films? = null        // Isso aqui está ruim
        try {
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Films WHERE name == \"${name}\";")
            film = Films(
                resultSet!!.getInt("id"),           //O "!!" me preocupa
                resultSet.getString("name"),
                resultSet.getString("genre"),
                resultSet.getString("director"),
                resultSet.getString("date")
            )
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally {
            connectionDAO.close()
        }
        return film!!
    }

    override fun getAll(): List<Any> {
        val connectionDAO = ConnectionDAO()
        val films = mutableListOf<Films>()
        try{
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Films;")
            while(resultSet?.next()!!){
                films.add(
                    Films(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getString("director"),
                        resultSet.getString("date"),
                    )
                )
            }
        }
        catch(exception : Exception){
            exception.stackTrace
        }
        finally{
            connectionDAO.close()
        }
        return films
    }

    //Funcionando  -  Melhorar !
    //Função que permite adicionar um filme na tabela.
    //Function to add film in table.
    override fun addOne(obj : Any){
        val connectionDAO = ConnectionDAO()
        val preparedStatement = connectionDAO.getPreparedStatement("""
            INSERT INTO Films
            (name, genre, director, date)
            VALUES (?, ?, ?, ?)
        """.trimIndent())
        val film = obj as Films
        preparedStatement?.setString(1,film.name)
        preparedStatement?.setString(2,film.genre)
        preparedStatement?.setString(3,film.director)
        preparedStatement?.setString(4,film.date)
        preparedStatement?.executeUpdate()
        //connectionDAO.commit()
        connectionDAO.close()
    }

    //fun addAll(): Any {}      Precisa mesmo?

    override fun delete(id : Int): Any {
        TODO("Not yet implemented")
    }

    override fun update(): Any {
        TODO("Not yet implemented")
    }

}