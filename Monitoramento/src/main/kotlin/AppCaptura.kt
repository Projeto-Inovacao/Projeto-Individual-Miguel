import java.util.Scanner
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


}
// janela

    /* Criar um Scanner para ler a entrada do terminal
    val scanner = Scanner(System.`in`)

    // Solicitar ao usuário que insira um valor
    print("Digite um valor: ")

    // Ler o valor inserido pelo usuário
    val input = scanner.nextLine()

    // Mostrar o valor inserido
    println("Você digitou: $input")

    // Fechar o scanner para liberar recursos
    scanner.close()*/
