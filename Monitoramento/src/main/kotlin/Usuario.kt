import java.util.*

open class Usuario {
    lateinit var email: String
    lateinit var senha: String
    private var emailValido = false
    private var senhaValido = false
    var tempo_reset = Thread.sleep(3000)

    fun cadastrarEmail(scanner: Scanner, assistente_nocline: String) {
        while (!emailValido) {
            println("$assistente_nocline Digite seu email de acesso:")
            email = scanner.nextLine()
            emailValido = validarEmail()
            if (!emailValido) {
                tempo_reset
                println("$assistente_nocline Hmm... Este email não parece válido. Tente novamente.")
                tempo_reset
            }
        }
    }
    private fun validarEmail(): Boolean {
        return "@" in email
    }

    fun cadastrarSenha(scanner: Scanner, assistente_nocline: String) {
        while (!senhaValido) {
            println("$assistente_nocline Digite sua senha de acesso:")
            senha = scanner.nextLine()
            senhaValido = validarSenha()
            if (!senhaValido) {
                tempo_reset
                println("$assistente_nocline Sua senha precisa ter no minimo 8 digitos. Tente novamente.")
                tempo_reset
            }
        }
    }

    private fun validarSenha(): Boolean {
        return senha.length >= 8
    }
}