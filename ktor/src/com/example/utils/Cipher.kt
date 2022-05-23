package utils

import com.auth0.jwt.algorithms.Algorithm

object Cipher {
    val algorithm: Algorithm = Algorithm.HMAC256("EtU0USaA9KlVjnbWVQSjsR6r0eQdn7DMbGA3rVj8ijTHE9Dm8dS7i2dmP9KjQER")

    fun encrypt(data: String?): ByteArray =
            algorithm.sign(data?.toByteArray())
}
