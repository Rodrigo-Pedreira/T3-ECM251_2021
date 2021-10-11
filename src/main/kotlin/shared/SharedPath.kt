package shared;

import java.sql.DriverManager

class SharedPath {
    companion object{
        //const val STRING_CONNECTION_JDBC = "jdbc:sqlite:bancot3t4.db"
        //val connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/t3t4database", "root", "root")
        const val STRING_CONNECTION_JDBC = "jdbc:mysql://127.0.0.1:3306/t3t4database"
        const val DATABASE_USERNAME = "root"
        const val DATABASE_PASSWORD = "root"
    }
}
