package dao

import models.Review

//TODO:JavaDocs
class ReviewDAO {
    companion object : GenericDAOInterface {

        override fun select(id: Int): Review {
            var reviews: Review? = null
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery("SELECT * FROM reviews WHERE id = ${id};")
                while (resultSet?.next()!!) {
                    reviews = Review(
                        resultSet.getInt("id"),
                        resultSet.getInt("idUser"),
                        resultSet.getInt("idFilm"),
                        resultSet.getString("review"),
                        resultSet.getInt("likes"),
                        resultSet.getDouble("score"),
                        resultSet.getString("date"),
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return Review(-1, -1, -1, "ERROR", -1, -1.0, "ERROR")
            } finally {
                connection?.close()
            }
            //return reviews ?: Review(-1, -1, -1, "ERROR", -1, -1.0, "ERROR")
            return reviews!!
        }

        override fun selectAll(): List<Review> {
            val reviews = mutableListOf<Review>()
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery("SELECT * FROM reviews;")
                while (resultSet?.next()!!) {
                    reviews.add(
                        Review(
                            resultSet.getInt("id"),
                            resultSet.getInt("idUser"),
                            resultSet.getInt("idFilm"),
                            resultSet.getString("review"),
                            resultSet.getInt("likes"),
                            resultSet.getDouble("score"),
                            resultSet.getString("date"),
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return listOfNotNull<Review>(Review(-1, -1, -1, "ERROR", -1, -1.0, "ERROR"))
            } finally {
                connection?.close()
            }
            return reviews
        }

        override fun insert(obj: Any) {
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val preparedStatement = connection.getPreparedStatement(
                    """
                    INSERT INTO reviews
                        (idUser, idFilm, review, likes, score, date)
                        VALUES (?,?,?,?,?,?);
                    """.trimMargin()
                )
                val review = obj as Review
                preparedStatement?.setInt(1, review.idUser)
                preparedStatement?.setInt(2, review.idFilm)
                preparedStatement?.setString(3, review.review)
                preparedStatement?.setInt(4, review.likes)
                preparedStatement?.setDouble(5, review.score)
                preparedStatement?.setString(6, review.date)
                preparedStatement?.executeUpdate()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.close()
            }
        }

        override fun insertMany(list: List<Any>) {
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val preparedStatement = connection.getPreparedStatement(
                    """
                    INSERT INTO reviews
                        (idUser, idFilm, review, likes, score, date)
                        VALUES (?,?,?,?,?,?);
                    """.trimMargin()
                )
                val review = list as List<Review>
                review.forEach {
                    preparedStatement?.setInt(1, it.idUser)
                    preparedStatement?.setInt(2, it.idFilm)
                    preparedStatement?.setString(3, it.review)
                    preparedStatement?.setInt(4, it.likes)
                    preparedStatement?.setDouble(5, it.score)
                    preparedStatement?.setString(6, it.date)
                    preparedStatement?.executeUpdate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.close()
            }
        }

        override fun update(obj: Any) {
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val preparedStatement = connection.getPreparedStatement(
                    """
                    UPDATE reviews
                        SET idUser = ?, idFilm = ?, review = ?, likes = ?, score = ?, date = ?
                        WHERE id = ?;
                    """.trimMargin()
                )
                val review = obj as Review
                preparedStatement?.setInt(1, review.idUser)
                preparedStatement?.setInt(2, review.idFilm)
                preparedStatement?.setString(3, review.review)
                preparedStatement?.setInt(4, review.likes)
                preparedStatement?.setDouble(5, review.score)
                preparedStatement?.setString(6, review.date)
                preparedStatement?.setInt(7, review.id)
                preparedStatement?.executeUpdate()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.close()
            }
        }

        override fun delete(id: Int) {
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val preparedStatement = connection.getPreparedStatement(
                    """
                    DELETE FROM reviews
                        WHERE id = ?;
                    """.trimMargin()
                )
                preparedStatement?.setInt(1, id)
                preparedStatement?.executeUpdate()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                connection?.close()
            }
        }
    }
}