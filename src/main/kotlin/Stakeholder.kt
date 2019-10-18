import java.nio.charset.StandardCharsets
import java.security.*
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

const val CRYPTO_METHOD = "RSA"
const val CRYPTO_BITS = 2048
const val CRYPTO_TRANSFORM = "RSA/ECB/OAEPWITHSHA1AndMGF1Padding"


/**
 * RSA implementation based on article "Implementing RSA Cryptography in Kotlin"
 * https://www.masinamichele.it/2018/02/13/implementing-rsa-cryptography-in-kotlin/
 */
class Stakeholder (val name: String) {

    private val RSAKeyPair: KeyPair

    init {

        // create key pair generator
        val keyPairGenerator = KeyPairGenerator.getInstance(CRYPTO_METHOD)

        keyPairGenerator.initialize(CRYPTO_BITS)
        RSAKeyPair = keyPairGenerator.genKeyPair()

        val message = "Hello World".toByteArray(StandardCharsets.UTF_8)
        println("Message: ${message.toString(StandardCharsets.UTF_8)}")

        val encryptedMessage = this.encrypt(message, RSAKeyPair.public)
        println("Encrypted message: ${encryptedMessage.toString(StandardCharsets.UTF_8)}")

        val decryptedMessage = this.decrypt(encryptedMessage, RSAKeyPair.private)
        println("Decrypted Message: ${decryptedMessage.toString(StandardCharsets.UTF_8)}")
    }

    /**
     * encrypts a message using string version of key.
     */
    fun decrypt(message: ByteArray, key: PrivateKey): ByteArray {

        val cipher = Cipher.getInstance(CRYPTO_TRANSFORM)

        cipher.init(Cipher.DECRYPT_MODE, key)
        return cipher.doFinal(message)
    }

    /**
     * encrypts a message using string version of key.
     */
    fun encrypt(message: ByteArray, key: PublicKey): ByteArray {

        val cipher = Cipher.getInstance(CRYPTO_TRANSFORM)

        cipher.init(Cipher.ENCRYPT_MODE, key)
        return cipher.doFinal(message)
    }
}
