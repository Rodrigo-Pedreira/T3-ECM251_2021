package testdrivers

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

class TestKtorAuth {
    companion object{

        fun getMd5Digest(str: String): ByteArray = MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))

        val myRealm = "Access to the '/' path"
        val userTable: Map<String, ByteArray> = mapOf(
            "jetbrains" to getMd5Digest("jetbrains:$myRealm:foobar"),
            "admin" to getMd5Digest("admin:$myRealm:password")
        )

        fun runTestAuthDigest (){
            //println("Pronto para realizar testes.")
            embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
                //
                /*install(Authentication) {
                    digest {
                        // Configure digest authentication

                    }
                }*/
                install(Authentication) {
                    digest("auth-digest") {
                        realm = myRealm
                        digestProvider { userName, realm ->
                            userTable[userName]
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