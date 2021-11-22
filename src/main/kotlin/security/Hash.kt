
package security

import java.math.BigInteger
import java.security.MessageDigest

class Hash {
    companion object {
        //https://stackoverflow.com/questions/64171624/what-is-the-best-way-to-generate-an-md5-hash-in-kotlin
        fun md5(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
        //outro jeito?: https://gist.github.com/lovubuntu/164b6b9021f5ba54cefc67f60f7a1a25
    }
}