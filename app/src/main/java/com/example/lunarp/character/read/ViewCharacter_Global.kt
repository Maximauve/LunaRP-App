package com.example.lunarp.character.read

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.lunarp.RequestUtils
import com.example.lunarp.character.Character
import com.example.lunarp.character.CharacterInterface
import com.example.lunarp.databinding.FragmentReadCharacterGlobalBinding
import com.typesafe.config.impl.Parseable.newString
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewCharacter_Global : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        val binding = FragmentReadCharacterGlobalBinding.inflate(layoutInflater)

        println("Extras here ! ${activity!!.intent.extras?.get("itemId")}")

        val id = activity!!.intent.extras?.get("itemId")

        var retrofit = RequestUtils.retrofitBase.create(CharacterInterface::class.java)
        val retrofitData= retrofit.getOne(id.toString())
        println("::::::Get One Character: N°$id :::::")
        retrofitData.enqueue(object: Callback<Character> {
            override fun onResponse(
                call: Call<Character>,
                response: Response<Character>
            ) {
                if (response.isSuccessful){
                    println("--> ${response.body()}")

                    binding.tvName.hint = "${response.body()?.name} lvl ${response.body()?.level}"
                    binding.tvRace.text = response.body()?.race?.name
                    binding.tvClass.text = response.body()?.classe?.name
                    binding.tvAlignment.text = response.body()?.alignment
                    binding.tvStrenght.text= response.body()?.strength.toString()
                    binding.tvDexterity.text= response.body()?.dexterity.toString()
                    binding.tvConstitution.text = response.body()?.constitution.toString()
                    binding.tvIntelligence.text = response.body()?.intelligence.toString()
                    binding.tvWisdom.text = response.body()?.wisdom.toString()
                    binding.tvCharisma.text = response.body()?.charisma.toString()

                }else{
                    println("---> ${response.errorBody()}")
                }
                //organiser les données récupérées.
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {

                println("::::::Get character failure:::::")
                Log.d("ActivityCharacterBinding", "onfailure: "+ t.message )
            }

        })

        binding.cancel.setOnClickListener {
                activity!!.finish()
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}