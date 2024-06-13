package put.inf154030.zwaar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import put.inf154030.zwaar.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<View>(R.id.logo)
        startAnimation(logo)
    }

    private fun startAnimation(logo: View) {
        logo.translationY = -2000f
        logo.animate()
            .translationY(0f)
            .setDuration(800)
            .withEndAction {
                Handler(Looper.getMainLooper()).postDelayed({
                    logo.animate()
                        .translationY(2000f)
                        .setDuration(500)
                        .withEndAction {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                }, 1000)
            }
    }
}
