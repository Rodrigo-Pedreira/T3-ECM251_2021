package dao;

import shared.SharedPath
import java.sql.*

class ConnectionDAO {
    private val connection: Connection? = DriverManager.getConnection(SharedPath.STRING_CONNECTION_JDBC)
    private var statement: Statement? = null
    private var resultSet: ResultSet? = null
    private var preparedStatement: PreparedStatement? = null

    fun executeQuery(sqlString: String): ResultSet? {
        this.statement = this.connection?.createStatement()
        this.resultSet = this.statement?.executeQuery(sqlString)
        return this.resultSet
    }

    fun getPreparedStatement(sqlString: String): PreparedStatement? {
        this.preparedStatement = this.connection?.prepareStatement(sqlString)
        return this.preparedStatement
    }

    fun close() {
        this.resultSet?.close()
        this.statement?.close()
        this.preparedStatement?.close()
        this.connection?.close()
    }
}
