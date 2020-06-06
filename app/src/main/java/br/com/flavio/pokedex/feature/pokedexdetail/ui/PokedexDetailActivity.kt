package br.com.flavio.pokedex.feature.pokedexdetail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.flavio.pokedex.R
import br.com.flavio.pokedex.databinding.ActivityPokedexDetailBinding
import br.com.flavio.pokedex.extension.fragmentTransaction
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.PokedexDetailViewModel
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.event.ViewEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PokedexDetailActivity : AppCompatActivity() {

    private val binding: ActivityPokedexDetailBinding by lazy {
        ActivityPokedexDetailBinding.inflate(layoutInflater)
    }

    private val idKey: String by lazy {
        intent.getStringExtra(ID_KEY)
    }

    private val viewModel: PokedexDetailViewModel by viewModel { parametersOf(idKey) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureFragment()
        configureObsevers()
    }

    private fun configureObsevers() {
        viewModel.viewEvent.observe(this, Observer { viewEvent ->
            when(viewEvent){
                is ViewEvent.NextPokemonEvent -> fragmentTransaction {
                    setCustomAnimations(
                        R.anim.enter_from_right,
                        R.anim.exit_to_left)
                    replace(binding.pokedexDetailContainer.id, PokedexDetailFragment())
                }
                is ViewEvent.PreviousPokemonEvent -> fragmentTransaction {
                    setCustomAnimations(
                        R.anim.enter_from_left,
                        R.anim.exit_to_right)
                    replace(binding.pokedexDetailContainer.id, PokedexDetailFragment())
                }
            }
        })
    }

    private fun configureFragment() {
        fragmentTransaction {
            replace(binding.pokedexDetailContainer.id, PokedexDetailFragment())
        }
    }

    companion object {
        private const val ID_KEY = "id"

        fun getIntent(context: Context, id: String): Intent =
            Intent(context, PokedexDetailActivity::class.java).apply {
                putExtra(ID_KEY, id)
            }
    }
}