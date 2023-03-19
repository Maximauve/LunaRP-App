package com.example.lunarp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lunarp.character.creation.CreateCharacter
import com.example.lunarp.databinding.FragmentCharacterBinding
import com.google.android.material.snackbar.Snackbar

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentCharacterBinding.inflate(layoutInflater)
        println("HERE")

        binding.cardAddCharacter.setOnClickListener { view ->
            println("Have to add character")
            Snackbar.make(view, "Have to add character", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val create = Intent(requireContext() , CreateCharacter::class.java )
            startActivity(create)
        }
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_character, container, false)
        return binding.root
    }

}