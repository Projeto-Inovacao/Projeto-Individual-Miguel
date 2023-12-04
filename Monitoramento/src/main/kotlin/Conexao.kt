import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate
class Conexao {
    fun conectar(): JdbcTemplate {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
        dataSource.url= "jdbc:mysql://localhost:3306/nocline"
        dataSource.username = "noc_line"
        dataSource.password = "noc_line134#"
        return JdbcTemplate(dataSource)
    }

    fun conectar_server(): JdbcTemplate {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
        dataSource.url = "jdbc:sqlserver://52.22.58.174;database=nocline;encrypt=false;trustServerCertificate=false"
        dataSource.username = "sa"
        dataSource.password = "urubu100"
        return JdbcTemplate(dataSource)
    }

}