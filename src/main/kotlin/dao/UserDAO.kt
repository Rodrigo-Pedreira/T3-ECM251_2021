package dao

import models.User
import security.Hash

class UserDAO {
    companion object : GenericDAOInterface {

        override fun select(id: Int): User {
            var user: User? = null
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery("SELECT * FROM Users WHERE id = ${id};")

                while(resultSet?.next()!!){
                    user = User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("email"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                return User(-1, "ERROR", "ERROR", "ERROR")
            } finally {
                connection?.close()
            }
            //return return user ?: User(-1, "ERROR", "ERROR", "ERROR")
            return user!!
        }

        override fun selectAll(): List<User> {
            val user = mutableListOf<User>()
            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val resultSet = connection.executeQuery("SELECT * FROM Users;")
                while (resultSet?.next()!!) {
                    user.add(
                        User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("password"),
                            resultSet.getString("email"),
                        )
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return listOfNotNull<User>(User(-1, "ERROR", "ERROR", "ERROR"))
            } finally {
                connection?.close()
            }
            return user
        }

        override fun insert(obj: Any) {

            var connection: ConnectionDAO? = null
            try {
                connection = ConnectionDAO()
                val preparedStatement = connection.getPreparedStatement(
                    """
                        INSERT INTO Users
                            (name, password, email)
                            VALUES (?,?,?);
                    """.trimMargin()
                )
                val user = obj as User
                preparedStatement?.setString(1, user.name)
                //caso senha venha sem hash:
                preparedStatement?.setString(2, Hash.md5(user.password))
                //caso senha venha com hash:
                //preparedStatement?.setString(2, user.password)
                preparedStatement?.setString(3, user.email)
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
                        INSERT INTO Users
                            (name, password, email)
                            VALUES (?,?,?);
                    """.trimMargin()
                )
                val user = list as List<User>
                user.forEach {
                    preparedStatement?.setString(1, it.name)
                    //caso senha venha sem hash:
                    preparedStatement?.setString(2, Hash.md5(it.password))
                    //caso senha venha com hash:
                    //preparedStatement?.setString(2, user.password)
                    preparedStatement?.setString(3, it.email)
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
                        UPDATE Users
                            SET name = ?, password = ?, email = ?
                            WHERE id = ?;
                    """.trimMargin()
                )
                val user = obj as User
                preparedStatement?.setString(1, user.name)
                //caso senha venha sem hash:
                preparedStatement?.setString(2, Hash.md5(user.password))
                //caso senha venha com hash:
                //preparedStatement?.setString(2, user.password)
                preparedStatement?.setString(3, user.email)
                preparedStatement?.setInt(4, user.id)
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
                        DELETE FROM Users
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