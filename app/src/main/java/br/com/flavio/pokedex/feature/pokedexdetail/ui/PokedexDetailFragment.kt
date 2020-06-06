package br.com.flavio.pokedex.feature.pokedexdetail.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.flavio.pokedex.R
import br.com.flavio.pokedex.databinding.FragmentPokedexDetailBinding
import br.com.flavio.pokedex.feature.pokedexdetail.ui.adapter.PokemonTypeAdapter
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.PokedexDetailViewModel
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.event.ViewEvent
import br.com.flavio.pokedex.feature.pokedexdetail.ui.viewmodel.state.ViewState
import br.com.flavio.pokedex.model.Type
import com.bumptech.glide.RequestBuilder
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PokedexDetailFragment : Fragment() {

    private val binding: FragmentPokedexDetailBinding by lazy {
        FragmentPokedexDetailBinding.inflate(layoutInflater)
    }

    private val viewModel: PokedexDetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureObsevers()
        configureClickListeners()
        viewModel.getPokemon()
    }

    private fun configureObsevers() {
        viewModel.viewEvent.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewEvent.ShowProgressBar -> binding.detailProgressBar.visibility = View.VISIBLE
                is ViewEvent.HideProgressBar -> binding.detailProgressBar.visibility = View.GONE
                is ViewEvent.ShowNextButton -> binding.nextPokemonImageButton.visibility = View.VISIBLE
                is ViewEvent.HideNextButton -> binding.nextPokemonImageButton.visibility = View.GONE
                is ViewEvent.ShowPreviousButton -> binding.previousPokemonImageButton.visibility = View.VISIBLE
                is ViewEvent.HidePreviousButton -> binding.previousPokemonImageButton.visibility = View.GONE
            }
        })
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ViewState.PokemonNameState -> binding.namePokemonTextView.text = it.pokemonName
                is ViewState.PokemonWeightState -> binding.pokemonWeightTextView.text =
                    it.pokemonWeight
                is ViewState.PokemonHeightState -> binding.pokemonHeightTextView.text =
                    it.pokemonHeight
                is ViewState.PokemonImageState -> it.imageBuilder.into(binding.pokemonImageView)
                is ViewState.PokemonTypesState -> setTypeList(it.types)
                is ViewState.PokemonAbilitiesState ->
                    setListView(binding.pokemonAbilitiesListView, it.pokemonAbilities, it.listHeight)
                is ViewState.PokemonGamesState ->
                    setListView(binding.pokemonGamesListView, it.pokemonGames, it.listHeight)
                is ViewState.PokemonMovesState ->
                    setListView(binding.pokemonMovesListView, it.pokemonMoves, it.listHeight)
                is ViewState.PokemonExpandMoves -> expandPokemonMoves()
                is ViewState.PokemonHideMoves -> hidePokemonMoves()
                is ViewState.PokemonExpandGames -> expandPokemonGames()
                is ViewState.PokemonHideGames -> hidePokemonGames()
            }
        })
    }

    private fun configureClickListeners() {
        binding.pokemonMovesExpandImageButton.setOnClickListener {
            viewModel.movesButtonClick()
        }
        binding.pokemonGamesExpandImageButton.setOnClickListener {
            viewModel.gamesButtonClick()
        }
        binding.previousPokemonImageButton.setOnClickListener {
            viewModel.previousPokemonButtonClick()
        }
        binding.nextPokemonImageButton.setOnClickListener {
            viewModel.nextPokemonButtonClick()
        }
        binding.pokemonImageView.setOnClickListener {
            viewModel.changePokemonImage()
        }
    }

    private fun hidePokemonGames() {
        binding.pokemonGamesExpandImageButton.setImageResource(R.drawable.ic_expand_more)
        binding.pokemonGamesListView.visibility = View.GONE
    }

    private fun expandPokemonGames() {
        binding.pokemonGamesExpandImageButton.setImageResource(R.drawable.ic_expand_less)
        binding.pokemonGamesListView.visibility = View.VISIBLE
    }

    private fun hidePokemonMoves() {
        binding.pokemonMovesExpandImageButton.setImageResource(R.drawable.ic_expand_more)
        binding.pokemonMovesListView.visibility = View.GONE
    }

    private fun expandPokemonMoves() {
        binding.pokemonMovesExpandImageButton.setImageResource(R.drawable.ic_expand_less)
        binding.pokemonMovesListView.visibility = View.VISIBLE
    }

    private fun setListView(listView: ListView, list: List<String>, height: Int) {
        context?.let {
            with(listView){
                adapter = ArrayAdapter(it, R.layout.list_item_detail, list)
                layoutParams.height = height
            }
        }
    }

    private fun setTypeList(types: List<Type>) {
        with(binding.pokemonTypeRecyclerView) {
            setHasFixedSize(true)
            adapter = PokemonTypeAdapter(types)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}