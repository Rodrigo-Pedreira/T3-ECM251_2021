package dao;

import shared.SharedPath
import java.sql.*

public class ConnectionDAO {
    val connection : Connection?
    var statement : Statement?
    var resultSet : ResultSet?
    var preparedStatement : PreparedStatement?

    init {
        this.connection = DriverManager.getConnection(SharedPath.STRING_CONNECTION_JDBC)
        this.statement = null
        this.resultSet = null
        this.preparedStatement = null
    }

    fun executeQuery(sqlString : String) : ResultSet?{
        this.statement = this.connection?.createStatement()
        this.resultSet = this.statement?.executeQuery(sqlString)
        return this.resultSet
    }

    fun close(){
        this.resultSet?.close()
        this.statement?.close()
        this.preparedStatement?.close()
        this.connection?.close()
    }

    fun getPreparedStatement(sqlString: String): PreparedStatement? {
        this.preparedStatement = this.connection ?.prepareStatement(sqlString)
        return this.preparedStatement
    }
}
