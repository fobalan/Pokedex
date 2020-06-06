package br.com.flavio.pokedex.feature.pokedexlist.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flavio.pokedex.databinding.ActivityPokedexListBinding
import br.com.flavio.pokedex.extension.addOnScrollStateChanged
import br.com.flavio.pokedex.feature.pokedexdetail.ui.PokedexDetailActivity
import br.com.flavio.pokedex.feature.pokedexlist.ui.adapter.PokemonAdapter
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.PokedexListViewModel
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.event.ViewEvent
import br.com.flavio.pokedex.feature.pokedexlist.ui.viewmodel.state.ViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexListActivity : AppCompatActivity() {

    private val binding: ActivityPokedexListBinding by lazy {
        ActivityPokedexListBinding.inflate(layoutInflater)
    }
    private val viewModel: PokedexListViewModel by viewModel()
    private val pokemonAdapter: PokemonAdapter by lazy {
        PokemonAdapter(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureClickListeners()
        configureRecyclerView()
        configureObsevers()
        viewModel.getPokemonList()
    }

    private fun configureClickListeners() {
        binding.searchButton.setOnClickListener {
            viewModel.searchClickButton(binding.searchBar.text.toString())
        }
    }

    private fun configureObsevers() {
        viewModel.viewEvent.observe(this, Observer {
            when (it) {
                is ViewEvent.ShowProgressBar -> binding.progressBar.visibility = View.VISIBLE
                is ViewEvent.HideProgressBar -> binding.progressBar.visibility = View.GONE
                is ViewEvent.PokemonItemClick -> startPokemonDetailActivity(it.pokemonName)
                is ViewEvent.SearchPokemon -> {
                    binding.searchBar.text.clear()
                    startPokemonDetailActivity(it.pokemonName)
                }
            }
        })
        viewModel.viewState.observe(this, Observer {
            when (it) {
                is ViewState.PokemonListState -> pokemonAdapter.addAll(it.pokemonList)
            }
        })
    }

    private fun startPokemonDetailActivity(pokemonName: String) {
        val intent = PokedexDetailActivity.getIntent(this, pokemonName)
        startActivity(intent)
    }

    private fun configureRecyclerView() {
        with(binding.pokemonRecyclerView) {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PokedexListActivity)
            adapter = pokemonAdapter
            addOnScrollStateChanged {
                if (!it.canScrollVertically(1)) {
                    viewModel.scrollPokemonList()
                }
            }
        }
    }
}