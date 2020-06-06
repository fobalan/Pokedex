package br.com.flavio.pokedex.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.fragmentTransaction(function : FragmentTransaction.() -> Unit){
    val transaction = supportFragmentManager.beginTransaction()
    transaction.let(function)
    transaction.commit()
}