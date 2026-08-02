package com.example.torchvault.security

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class EncryptionHelper {

    companion object {
        private const val ALGORITHM = "AES/GCM/NoPadding"
        private const val KEY_ALGORITHM = "AES"
        private const val GCM_TAG_LENGTH = 128
        private const val GCM_IV_LENGTH = 12
        private const val ITERATION_COUNT = 65536
        private const val KEY_LENGTH = 256

        fun encrypt(plainText: String, password: String): String {
            val iv = ByteArray(GCM_IV_LENGTH)
            SecureRandom().nextBytes(iv)

            val key = deriveKey(password)
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key, GCMParameterSpec(GCM_TAG_LENGTH, iv))

            val encrypted = cipher.doFinal(plainText.toByteArray(Charsets.UTF_8))
            val combined = iv + encrypted

            return Base64.encodeToString(combined, Base64.DEFAULT)
        }

        fun decrypt(encryptedText: String, password: String): String {
            val combined = Base64.decode(encryptedText, Base64.DEFAULT)

            val iv = combined.copyOfRange(0, GCM_IV_LENGTH)
            val encrypted = combined.copyOfRange(GCM_IV_LENGTH, combined.size)

            val key = deriveKey(password)
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(GCM_TAG_LENGTH, iv))

            return String(cipher.doFinal(encrypted), Charsets.UTF_8)
        }

        private fun deriveKey(password: String): SecretKeySpec {
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
            val spec = PBEKeySpec(password.toCharArray(), "torchvault_salt".toByteArray(), ITERATION_COUNT, KEY_LENGTH)
            val secretKey = factory.generateSecret(spec)
            return SecretKeySpec(secretKey.encoded, KEY_ALGORITHM)
        }
    }
}
