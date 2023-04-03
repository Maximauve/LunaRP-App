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
import com.example.lunarp.character.*
import com.example.lunarp.character.creation.CreateCharacter
import com.example.lunarp.classes.ClassesClassItem
import com.example.lunarp.databinding.FragmentCharacterBinding
import com.example.lunarp.user.UserInterface
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding = FragmentCharacterBinding.inflate(layoutInflater)
        //Ma gestion des données
        val model by lazy { ViewModelProvider(this)[CharacterViewModel::class.java] }

        var adapter = CharacterListAdapter(activity as MainActivity)

        binding.rv.adapter = adapter
        binding.rv.layoutManager = GridLayoutManager(context,1)

        println("HERE")
        binding.cardAddCharacter.setOnClickListener { view ->
            println("Have to add character")
            Snackbar.make(view, "Have to add character", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val create = Intent(requireContext() , CreateCharacter::class.java )
            startActivity(create)
        }

        var retrofit = RequestUtils.retrofitBase.create(CharacterInterface::class.java)
        val retrofitData= retrofit.getAll()
        println("::::::Get Characters :::::")
        retrofitData.enqueue(object: Callback<List<Character>> {
            override fun onResponse(
                call: Call<List<Character>>,
                response: Response<List<Character>>
            ) {
                if (response.isSuccessful){
                    println("--> ${response.body()}")
                    response.body()?.forEach {
                        model.data.add(it)
                    }
                }else{
                    println("---> ${response.errorBody()}")
                }
                //organiser les données récupérées.
                Log.d("CharacterFragment", "Number of characters: ${model.data.size}")
                adapter.submitList(model.data.toList())
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Character>>, t: Throwable) {

                println("::::::Get characters failure:::::")
                Log.d("ActivityCharacterBinding", "onfailure: "+ t.message )
            }

        })

        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_character, container, false)
        return binding.root
    }
}
