package com.example.lunarp.update

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.lunarp.*
import com.example.lunarp.character.CharacterBean
import com.example.lunarp.character.CharacterInterface
import com.example.lunarp.character.Character
import com.example.lunarp.character.CharacterUpdate
import com.example.lunarp.character.read.ViewCharacter
import com.example.lunarp.character.read.ViewCharacter_Global
import com.example.lunarp.classes.ClassInterface
import com.example.lunarp.classes.ClassesClassItem
import com.example.lunarp.databinding.FragmentCreateCharacterGlobalBinding
import com.example.lunarp.races.RaceClassItem
import com.example.lunarp.races.RaceInterface
import com.example.lunarp.user.User
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import android.content.Intent as Intent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpdateCharacter_Global.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateCharacter_Global : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        super.onCreate(savedInstanceState)
        val binding = FragmentCreateCharacterGlobalBinding.inflate(layoutInflater)

/*
        * Managae alignments
        */
        val alignments = resources.getStringArray(R.array.Alignments)
        val spinnerAlignment = binding.spinnerAlignment
        if (spinnerAlignment != null){
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, alignments)

            spinnerAlignment.adapter = adapter

            for (i in 0 until spinnerAlignment.adapter.count) {
                if (spinnerAlignment.adapter.getItem(i).toString() == activity!!.intent.extras?.get("alignment")) {
                    spinnerAlignment.setSelection(i)
                }
            }
        }
        /*
       * Manage race list
       */
        var races = listOf<RaceClassItem>()
        var retrofitRace = RequestUtils.retrofitBase.create(RaceInterface::class.java)
        var retrofitDataRace = retrofitRace.getData()
        println("::::::Get Race:::::")
        retrofitDataRace.enqueue(object: Callback<List<RaceClassItem>>{
            override fun onResponse(
                call: Call<List<RaceClassItem>>,
                response: Response<List<RaceClassItem>>
            ) {
                val errorStr = response.errorBody()?.string()
                if (response.isSuccessful){
                    println("---> ${response.body()}")
                    races = response.body()!!

                    /*
                    * Set the spinner
                    */
                    val raceNames = races.map { it.name }.toTypedArray()
                    val adapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_item, raceNames) }
                    adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerRace.adapter = adapter

                    for (i in 0 until binding.spinnerRace.adapter.count) {
                        if (binding.spinnerRace.adapter.getItem(i).toString() == activity!!.intent.extras?.get("raceName")) {
                            binding.spinnerRace.setSelection(i)
                        }
                    }
                }
                when (response.code()) {
                    400 ->{
                        Toast.makeText(context, "Non autorisé" , Toast.LENGTH_LONG).show()
                    }
                    401 -> {
                        Toast.makeText(context, "401", Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<List<RaceClassItem>>, t: Throwable) {

                println("::::::Get Race failure:::::")
                Log.d("ActivityCreateCharacterBinding", "onfailure: "+ t.message )
            }
        })

        /*
        * Manage class list
        */
        var classes = listOf<ClassesClassItem>()
        var retrofitClass = RequestUtils.retrofitBase.create(ClassInterface::class.java)
        val retrofitDataClass = retrofitClass.getData()
        println("::::::Get Class :::::")
        retrofitDataClass.enqueue(object: Callback<List<ClassesClassItem>>{

            override fun onResponse(
                call: Call<List<ClassesClassItem>>,
                response: Response<List<ClassesClassItem>>
            ) {
                val errorStr = response.errorBody()?.string()
                if (response.isSuccessful){
                    println("---> ${response.body()}")
                    classes = response.body()!!

                    /*
                    * Set the spinner
                    */
                    val classNames = classes.map { it.name }.toTypedArray()
                    val adapter = context?.let { ArrayAdapter<String>(it, android.R.layout.simple_spinner_item, classNames) }
                    adapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    binding.spinnerClass.adapter = adapter

                    for (i in 0 until binding.spinnerClass.adapter.count) {
                        if (binding.spinnerClass.adapter.getItem(i).toString() == activity!!.intent.extras?.get("classeName")) {
                            binding.spinnerClass.setSelection(i)
                        }
                    }
                }
                when (response.code()) {
                    400 ->{
                        Toast.makeText(context, "Non autorisé" , Toast.LENGTH_LONG).show()
                    }
                    401 -> {
                        Toast.makeText(context, "401", Toast.LENGTH_LONG).show()
                    }
                }

            }

            override fun onFailure(call: Call<List<ClassesClassItem>>, t: Throwable) {
                println("::::::Get Class failure:::::")
                Log.d("ActivityCreateCharacterBinding", "onfailure: "+ t.message )
            }
        })

        /*
        * Manage all values !
        */
        val name = binding.etName
        binding.description.setText(activity!!.intent.extras?.get("description").toString())
        name.text =  Editable.Factory.getInstance().newEditable(activity!!.intent.extras?.get("name").toString())
        val strength = binding.nbpStrenght
        strength.minValue = 1
        strength.maxValue = 20
        strength.value= activity?.intent?.extras?.get("strength") as? Int ?: 10
        val dexterity = binding.nbpDexterity
        dexterity.minValue = 1
        dexterity.maxValue = 20
        dexterity.value = activity?.intent?.extras?.get("dexterity") as? Int ?: 10
        dexterity.invalidate()
        val constitution = binding.nbpConstitution
        constitution.minValue = 1
        constitution.maxValue = 20
        constitution.value = activity?.intent?.extras?.get("constitution") as? Int ?:10
        constitution.invalidate()
        val wisdom = binding.nbpWisdom
        wisdom.minValue = 1
        wisdom.maxValue = 20
        wisdom.value = activity?.intent?.extras?.get("wisdom") as? Int ?: 10
        wisdom.invalidate()
        val intelligence = binding.nbpIntelligence
        intelligence.minValue = 1
        intelligence.maxValue = 20
        intelligence.value = activity?.intent?.extras?.get("intelligence") as? Int ?: 10
        intelligence.invalidate()
        val charisma = binding.nbpCharisma
        charisma.minValue = 1
        charisma.maxValue = 20
        charisma.value = activity?.intent?.extras?.get("charisma") as? Int ?: 10
        charisma.invalidate()


        binding.etName.performClick()
        binding.cancel.setOnClickListener {
            val intent = Intent(context, ViewCharacter::class.java)
            intent.putExtra("itemId", activity!!.intent.extras?.get("id") as Int)
            startActivity(intent)
        }

        binding.next.setOnClickListener  {view ->
            println("------------------> *Entries: ")
            Snackbar.make(view, "COUCOU", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            var strValue = strength.value
            var dexValue = dexterity.value
            var conValue = constitution.value
            var wisValue = wisdom.value
            var intValue = intelligence.value
            var chaValue = charisma.value
            println("$strValue | $dexValue | $conValue | $wisValue | $intValue | $chaValue")

            var raceSelected = binding.spinnerRace.selectedItem
            var classSelected = binding.spinnerClass.selectedItem
            var name = binding.etName.text
            var description = binding.description.text
            var alignment = binding.spinnerAlignment.selectedItem
            println("Je me nomme $name, je suis un jeune $raceSelected " +
                    "qui veux devenir le meilleur $classSelected du monde. " +
                    "On dit de moi que je suis un $alignment !")

            var classSelectedID = classes.find { it.name == classSelected }
            var raceSelectedID = races.find {it.name == raceSelected}
            var characterUpdate = CharacterUpdate(
                id = activity!!.intent.extras?.get("id") as Int,
                alignment = alignment.toString(),
                campaign= listOf(),
                charisma= chaValue,
                classe= classSelectedID!!.id,
                constitution= conValue,
                description= description.toString(),
                dexterity= dexValue,
                experience=0,
                intelligence= intValue,
                inventory = listOf(),
                level=1,
                name= name.toString(),
                race= raceSelectedID!!.id,
                spells= listOf(),
                strength= strValue,
                wisdom= wisValue
            )

            var retrofitCharacter = RequestUtils.retrofitBase.create<CharacterInterface>()
            println("Character create : $characterUpdate")
            var retrofitDataCharacter = retrofitCharacter.updateCharacter(characterUpdate)
            println("::::::Updating a CHARACTER:::::")
            retrofitDataCharacter.enqueue(object: Callback<Any>{
                override fun onResponse(
                    call: Call<Any>,
                    response: Response<Any>
                ) {
                    val errorStr = response.errorBody()?.string()
                    if (response.isSuccessful){
                        println("---> ${response.body()}")
                        val intent = Intent(context, ViewCharacter::class.java)
                        intent.putExtra("itemId", characterUpdate.id)
                        startActivity(intent)
                        activity!!.finish()
                    }else{
                        println("Character response is not a succes : ${response.code()}")
                        println("$errorStr")
                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    println("::::::Update Character fail:::::")
                    Log.d("ActivityUpdateCharacterBinding", "onfailure: "+ t.message )
                }

            })
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}