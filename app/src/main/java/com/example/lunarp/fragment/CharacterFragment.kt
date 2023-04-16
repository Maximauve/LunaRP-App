package com.example.lunarp.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lunarp.MainActivity
import com.example.lunarp.RequestUtils
import com.example.lunarp.SessionManager
import com.example.lunarp.character.Character
import com.example.lunarp.character.CharacterListAdapter
import com.example.lunarp.character.CharacterViewModel
import com.example.lunarp.character.creation.CreateCharacter
import com.example.lunarp.databinding.FragmentCharacterBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterFragment : Fragment() {

    //Ma gestion des données
    val model by lazy { ViewModelProvider(this)[CharacterViewModel::class.java] }
    public lateinit var adapter: CharacterListAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = FragmentCharacterBinding.inflate(layoutInflater)

        adapter = CharacterListAdapter(activity as MainActivity, MainActivity())
        adapter.fragment = this
        binding.rv.adapter = adapter
        binding.rv.layoutManager = GridLayoutManager(context,1)

        binding.cardAddCharacter.setOnClickListener { view ->
            println("Have to add character")
            Snackbar.make(view, "Have to add character", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val create = Intent(requireContext() , CreateCharacter::class.java )
            startActivity(create)
        }

        updateList()
        println("Update end")

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_character, container, false)
        return binding.root
    }
    fun updateList() {
        model.data.clear() // Clear the old list data

        println("Update Begin")
        SessionManager.characters?.forEach {
            model.data.add(it)
            println(it)
        }
        //organiser les données récupérées.
        Log.d("CharacterFragment", "Number of characters: ${model.data.size}")
        adapter.submitList(model.data.toList())
        adapter.notifyDataSetChanged()
    }
}
