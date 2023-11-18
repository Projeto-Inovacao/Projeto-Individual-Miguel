import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import java.time.format.DateTimeFormatter
class MonitoramentoRepositorio {
    fun iniciar() {
        var jdbcTemplate = Conexao().conectar()
    }


}