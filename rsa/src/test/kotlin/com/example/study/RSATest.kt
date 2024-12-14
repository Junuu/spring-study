package com.example.study

import org.junit.jupiter.api.Test
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import java.util.*
import javax.crypto.Cipher

class RSATest {

	@Test
	fun init() {
		val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
		keyPairGenerator.initialize(2048) // Key size
		val keyPair = keyPairGenerator.generateKeyPair()
		val publicKey: PublicKey = keyPair.public
		val privateKey: PrivateKey = keyPair.private

		// Step 2: Print keys in Base64 format
		println("Public Key: ${Base64.getEncoder().encodeToString(publicKey.encoded)}")
		println("Private Key: ${Base64.getEncoder().encodeToString(privateKey.encoded)}")
		println("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6RNJoHPJ2fIitZm+XJVGrUWOMXQOjVqaNvKJuOtCgHZorf/C+NuAYfR4QXCrJk1cKy7g5dYR3Q2XV/9K6E6pvk6xLiKEoXs3kgg0vBbqeAsJ1vxg/dQqqAaDW1tBm51SyzanChuo3VSGMBb+j4m4YbNpvjI4M/x+r07SnOcMcUIskiUhEC5XXbhn2ttD1dzXtk4P1Ob9K9FHtdf6QrSbScOhHhZ2OFxwkVd26nsd3/icnWgJ44N7HmowmSt+JGbKRvpdL2ruWBGGvyfgzpF9u2DikVxgANDVcoANXB1ILsTPCSJ1nQywT68meNG44GOZcVoXU9Ni/I0vsJuHQmp5NwIDAQAB".length)

		// Step 3: Encrypt a sample message with the public key
		val message = "Hello, RSA with Kotlin!"
		val cipherEncrypt = Cipher.getInstance("RSA")
		cipherEncrypt.init(Cipher.ENCRYPT_MODE, publicKey)
		val encryptedMessage = cipherEncrypt.doFinal(message.toByteArray())
		println("Encrypted Message: ${Base64.getEncoder().encodeToString(encryptedMessage)}")

		// Step 4: Decrypt the message with the private key
		val cipherDecrypt = Cipher.getInstance("RSA")
		cipherDecrypt.init(Cipher.DECRYPT_MODE, privateKey)
		val decryptedMessage = cipherDecrypt.doFinal(encryptedMessage)
		println("Decrypted Message: ${String(decryptedMessage)}")

	}

}
