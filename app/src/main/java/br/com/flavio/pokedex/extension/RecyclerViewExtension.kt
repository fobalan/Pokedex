package br.com.flavio.pokedex.extension

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addOnScrollStateChanged(function: (recyclerView: RecyclerView) -> Unit) {

    //adiciona a função no OnScrollListener() do recyclerview
    addOnScrollListener(
        object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                function(recyclerView)
            }
        }
    )
}