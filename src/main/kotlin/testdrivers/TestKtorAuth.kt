package testdrivers

import dao.UserDAO
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8


// Ajuda no flutter? : https://www.digitalocean.com/community/tutorials/flutter-flutter-http

//      como mandar body, mudar Authorization header (Auth no postman) ?
//          - https://docs.flutter.dev/cookbook/networking/authenticated-requests +
//          - https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Authorization ?

// extra flutter:   -https://pub.dev/documentation/http/latest/


// IMPORTANTE PARA ENTENDER  --->  https://ktor.io/docs/digest.html#flow

class TestKtorAuth {
    companion object{

        fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

        //Aparentemente o texto em si não importa, é mais uma informação para que mexer aqui.
        val myRealm = "bla bla"

        //Tentar trocar isso pelo database
        val userTable: Map<String, ByteArray> = mapOf(
            "jetbrains" to getMd5Digest("jetbrains:$myRealm:foobar"),
            "admin" to getMd5Digest("admin:$myRealm:password")
        )

        fun runTestAuthDigest (){

            embeddedServer(Netty, port = 8080, host = "0.0.0.0") {

                install(Authentication) {
                    digest("auth-digest") {

                        //Analizando real: como mudar, como guardar, ligado com o HA1, é guardado no db?
                        realm = myRealm


                        //Analizando digestProvider:
                        //  aparentemente o userName ele pega o que o usuário digitar;
                        //  realm é definido ali em cima, então pode trocar, mas myReal é definido bem antes e
                        //  ele é salvo na tabela ligado com o HA1, logo myReal está definido no ByteArray;
                        //  userTable pega o nome do usuário para achar o ByteArray ligado a esse nome na tabela,
                        //  logo, tem que ser salvo um ByteArray no DB?
                        digestProvider { userName, realm ->
                            userTable[userName]
                            //estrutura HA1: usuário:real:senha no getMd5Digest para virar ByteArray
                            //getMd5Digest("admin:$realm:password")
                            //no caso de cima, apenas admin poderia logar com a senha password
                            //Possivel solução:
                            //  como o usuário entra o userName, usar o userName para pegar senha do DB e
                            //  gerar o ByteArray aqui? ex.:
                            //      password = (faz um acesso no DB e pega a senha)
                            //      digestProvider { userName, realm -> getMd5Digest("$userName:$realm:$password") }
                            //  porém como esta mandando um hash em $password, a não ser que consiga mandar a senha em
                            //  hash, o usuário teria que digitar o hash da senha. Ou a senha ser salva sem hash no DB,
                            //  o que não é seguro e nem uma boa prática.
                        }
                    }
                }
                routing {
                    authenticate("auth-digest") {
                        get("/") {
                            call.respondText("Hello, ${call.principal<UserIdPrincipal>()?.name}!")
                        }
                    }
                }
            }.start(wait = true)
        }
    }

}