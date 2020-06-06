package br.com.flavio.pokedex

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.flavio.pokedex.feature.pokedexlist.ui.PokedexListActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed({
            toMain()
        }, 3000)
    }

    private fun toMain() {
        val intent = Intent(this, PokedexListActivity::class.java)
        startActivity(intent)
        finish()
    }
}