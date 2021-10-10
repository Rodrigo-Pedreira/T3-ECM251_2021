package testDriveG.dao

import dao.ConnectionDAO
import models.Review

class ReviewDAO : GenericDAO {

    //Funcionando
    //Função para pegaar um review com base no seu id.
    //Function to get one review from table by id.
    override fun getOne(id : Int): Any {
        val connectionDAO = ConnectionDAO()
        var review : Review? = null        //isso aqui está ruim
        try {
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Reviews WHERE id == ${id};")
            review = Review(
                resultSet!!.getInt("id"),
                resultSet.getInt("idUser"),
                resultSet.getInt("idFilm"),
                resultSet.getString("review"),
                resultSet.getInt("likes"),
                resultSet.getDouble("score"),
                resultSet.getString("date")

            )
        }
        catch(exception:Exception){
            exception.stackTrace
        }
        finally {
            connectionDAO.close()
        }
        return review!!
    }

    //Funcionando
    //Função para pegar toda a tabela 'Reviews'.
    //Function to get all elements from Reviews table.
    override fun getAll(): List<Any> {
        val connectionDAO = ConnectionDAO()
        val review = mutableListOf<Review>()
        try{
            val resultSet = connectionDAO.executeQuery("SELECT * FROM Reviews;")
            while(resultSet?.next()!!){
                review.add(Review(
                    resultSet.getInt("id"),
                    resultSet.getInt("idUser"),
                    resultSet.getInt("idFilm"),
                    resultSet.getString("review"),
                    resultSet.getInt("likes"),
                    resultSet.getDouble("score"),
                    resultSet.getString("date"))
                )
            }
        }
        catch(exception : Exception){
            exception.stackTrace
        }
        finally{
            connectionDAO.close()
        }
        return review
    }

    //Funcionando
    //Função que permite adicionar um review na tabela.
    //Function to add review in table.
    override fun addOne(obj : Any){
        val connectionDAO = ConnectionDAO()
        try{
            val preparedStatement = connectionDAO.getPreparedStatement("""
                INSERT INTO Reviews
                (idUser, idFilm, review, likes, score, date)
                VALUES (?,?,?,?,?,?)
            """.trimIndent())
            val review = obj as Review
            preparedStatement?.setInt(1,review.idUser)
            preparedStatement?.setInt(2,review.idFilm)
            preparedStatement?.setString(3,review.review)
            preparedStatement?.setInt(4,review.likes)
            preparedStatement?.setDouble(5,review.score)
            preparedStatement?.setString(6,review.date)
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
    //Função que permite adicinar todos os reviews em uma lista.
    //Function to add all reviews in list to table.
    override fun addAll(list : List<Any>){
        val connectionDAO = ConnectionDAO()
        try{
            val preparedStatement = connectionDAO.getPreparedStatement("""
                INSERT INTO Reviews
                (idUser, idFilm, review, likes, score, date)
                VALUES (?,?,?,?,?,?)
            """.trimIndent())
            for (obj in list){
                val review = obj as Review
                preparedStatement?.setInt(1,review.idUser)
                preparedStatement?.setInt(2,review.idFilm)
                preparedStatement?.setString(3,review.review)
                preparedStatement?.setInt(4,review.likes)
                preparedStatement?.setDouble(5,review.score)
                preparedStatement?.setString(6,review.date)
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
    //Função permite deletar um review da tabela pelo seu id.
    //Function to delete one review from table by the id.
    override fun delete(id : Int){
        val connectionDAO = ConnectionDAO()
        try {
            val preparedStatement = connectionDAO.getPreparedStatement("""
                DELETE FROM Reviews
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
    //Função permite atualizar um review da tabela.
    //Function to update one review from table.
    override fun update(obj : Any){
        val connectionDAO = ConnectionDAO()
        try {
            val preparedStatement = connectionDAO.getPreparedStatement("""
                UPDATE Reviews
                SET idUser = ?, idFilm = ?, review = ?, likes = ?, score = ?, date = ?
                WHERE id = ?;
            """.trimIndent())
            val review = obj as Review
            preparedStatement?.setInt(1,review.idUser)
            preparedStatement?.setInt(2,review.idFilm)
            preparedStatement?.setString(3,review.review)
            preparedStatement?.setInt(4,review.likes)
            preparedStatement?.setDouble(5,review.score)
            preparedStatement?.setString(6,review.date)
            preparedStatement?.setInt(7,review.id)
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