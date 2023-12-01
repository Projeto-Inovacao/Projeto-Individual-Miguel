import com.github.britooo.looca.api.core.Looca
import java.util.Scanner
import java.util.concurrent.TimeUnit
import java.time.LocalDateTime
import java.util.Timer
import java.util.TimerTask

val looca = Looca()

fun main() {
    val repositorio = MonitoramentoRepositorio()
    repositorio.iniciar()
    val cinza = "\u001B[0m"
    val dark_blue = "\u001B[34m"
    val scanner = Scanner(System.`in`)
    val assistente_nocline = "${dark_blue}[Assistente Nocline]:${cinza}"

    println("""
        $assistente_nocline Boas-Vindas! Você está agora acessando o sistema de monitoramento da nocline.
        $assistente_nocline Para prosseguirmos preciso validar seu cadastro dentro da nossa plataforma.
        """.trimIndent()
    )

    val novoUsuario = Usuario()
    novoUsuario.cadastrarEmail(scanner, assistente_nocline)
    novoUsuario.cadastrarSenha(scanner, assistente_nocline)

    println("$assistente_nocline Tudo certo! Irei validar seu login na plataforma")

    val loginRepositorio = LoginRepositorio()
    loginRepositorio.iniciar()

    if (loginRepositorio.validarLogin(novoUsuario)) {
        println("$assistente_nocline " + loginRepositorio.comprimentar(novoUsuario))
        println("$assistente_nocline Agora você irá prosseguir para etapa de monitoramento.")

        var fk_empresa = loginRepositorio.verificarEmpresa(novoUsuario)
        var listaDeMaquinas = loginRepositorio.mostrarMaquina(fk_empresa)

        println("$assistente_nocline Digite o ID da máquina que você deseja monitorar:\n\r $listaDeMaquinas")
        var id_maquina = scanner.nextLine().toInt()

        val repositorio = DadosRepositorios()
        repositorio.iniciar()

        println("$assistente_nocline O monitoramento irá inicializar agora!")

        while (true) {
            capturar_processo(repositorio, id_maquina, fk_empresa)
            capturar_janela(repositorio, id_maquina, fk_empresa)
            capturar_rede(repositorio, id_maquina, fk_empresa)
        }
    } else {
        println("$assistente_nocline Não conseguimos validar seu login dentro da nossa plataforma, caso você ache que isso é um erro, por favor, entre em contato conosco!")
    }
}
fun capturar_processo(repositorio: DadosRepositorios, id_maquina: Int, fk_empresa: Int) {
    val novoProcesso = repositorio.capturarDadosP(looca)
    repositorio.cadastrarProcesso(novoProcesso, id_maquina, fk_empresa)
    Timer().schedule(object : TimerTask() {
        override fun run() {
            val novoProcesso = repositorio.capturarDadosP(looca)
            repositorio.cadastrarProcesso(novoProcesso, id_maquina, fk_empresa)
        }
    }, 60000)
}

fun capturar_janela(repositorio: DadosRepositorios, id_maquina: Int, fk_empresa: Int) {
    val novaJanela = repositorio.capturarDadosJ(looca)
    repositorio.cadastrarJanela(novaJanela, id_maquina, fk_empresa)
    Timer().schedule(object : TimerTask() {
        override fun run() {
            val novaJanela = repositorio.capturarDadosJ(looca)
            repositorio.cadastrarJanela(novaJanela, id_maquina, fk_empresa)
        }
    }, 60000)
}

fun capturar_rede(repositorio: DadosRepositorios, id_maquina: Int, fk_empresa: Int) {
    val novaRede = repositorio.capturarDadosR(looca)
    repositorio.cadastrarRede(novaRede, id_maquina, fk_empresa)
    Timer().schedule(object : TimerTask() {
        override fun run() {
            val novaRede = repositorio.capturarDadosR(looca)
            repositorio.cadastrarRede(novaRede, id_maquina, fk_empresa)
        }
    }, 60000)
}