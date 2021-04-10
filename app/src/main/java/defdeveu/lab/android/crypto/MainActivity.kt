package defdeveu.lab.android.crypto

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        button.setOnClickListener {
            edit_text_cipher.setText(encrypt(edit_text_plain.text.toString()))
        }
    }

    @SuppressLint("GetInstance")
    private fun encrypt(value: String): String? {
        try {
            val sKeySpec = SecretKeySpec(KEY.toByteArray(Charset.defaultCharset()), "AES")
            val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING").apply {
                init(Cipher.ENCRYPT_MODE, sKeySpec)
            }
            val encrypted = cipher.doFinal(value.toByteArray())
            return encrypted.toString()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }

    companion object {
        //128 bit key
        const val KEY = "Bar12345Bar12345"
    }
}