package dao

import models.Film

class FilmDAO {
    companion object : GenericDAOInterface {

        override fun select(id: Int): Film {
            val film: Film?
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery("SELECT * FROM Films WHERE id = ${id};")
                film = Film(
                    resultSet!!.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("genre"),
                    resultSet.getString("director"),
                    resultSet.getString("date"),
                )
            } catch (e: Exception) {
                e.printStackTrace()
                return Film(-1, "ERROR", "ERROR", "ERROR", "ERROR")
            } finally {
                connection?.close()
            }
            return film!!
        }

        fun selectFromString(name: String = "", genre: String = "", director: String = "", date: String = ""): Film {
            val film: Film?
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery(
                    "SELECT * " +
                            "FROM  Films " +
                            "WHERE name     LIKE '%${name}%' " +
                            "AND   genre    LIKE '%${genre}%' " +
                            "AND   director LIKE '%${director}%'" +
                            "AND   date     LIKE '%${date}%';"
                )
                film = Film(
                    resultSet!!.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("genre"),
                    resultSet.getString("director"),
                    resultSet.getString("date")
                )
            } catch (e: Exception) {
                e.printStackTrace()
                return Film(-1, "ERROR", "ERROR", "ERROR", "ERROR")
            } finally {
                connection?.close()
            }
            return film!!
        }

        override fun selectAll(): List<Film> {
            val films = mutableListOf<Film>()
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery("SELECT * FROM Films;")
                while (resultSet?.next()!!) {
                    films.add(
                        Film(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("genre"),
                            resultSet.getString("director"),
                            resultSet.getString("date"),
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return listOfNotNull<Film>(Film(-1, "ERROR", "ERROR", "ERROR", "ERROR"))
            } finally {
                connection?.close()
            }
            return films
        }

        override fun insert(obj: Any) {
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val preparedStatement = connection.getPreparedStatement(
                    """
                        INSERT INTO Films
                            (name, genre, director, date)
                            VALUES (?, ?, ?, ?);
                    """.trimMargin()
                )
                val film = obj as Film
                preparedStatement?.setString(1, film.name)
                preparedStatement?.setString(2, film.genre)
                preparedStatement?.setString(3, film.director)
                preparedStatement?.setString(4, film.date)
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
                        INSERT INTO Films
                            (name, genre, director, date)
                            VALUES (?, ?, ?, ?);
                    """.trimMargin()
                )
                val films = list as List<Film>
                films.forEach {
                    preparedStatement?.setString(1, it.name)
                    preparedStatement?.setString(2, it.genre)
                    preparedStatement?.setString(3, it.director)
                    preparedStatement?.setString(4, it.date)
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
                        UPDATE Films
                            SET name = ?, genre = ?, director = ?, date = ?
                            WHERE id = ?;
                    """.trimMargin()
                )
                val film = obj as Film
                preparedStatement?.setString(1, film.name)
                preparedStatement?.setString(2, film.genre)
                preparedStatement?.setString(3, film.director)
                preparedStatement?.setString(4, film.date)
                preparedStatement?.setInt(5, film.id)
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
                        DELETE FROM Films
                            WHERE id =?;
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
