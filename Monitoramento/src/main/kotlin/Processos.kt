import java.time.LocalDateTime

class Processos {
    lateinit var nome: String
    var PID: Int = 0
    var usoCPU: Double = 0.0
    var usoMemoria: Double = 0.0
    var memoriaVirtual: Double = 0.0
    lateinit var data_hora: LocalDateTime
}