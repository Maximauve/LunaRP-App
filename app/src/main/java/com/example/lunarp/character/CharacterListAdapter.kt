package com.example.lunarp.character

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lunarp.MainActivity
import com.example.lunarp.R
import com.example.lunarp.RequestUtils
import com.example.lunarp.character.read.ViewCharacter
import com.example.lunarp.databinding.RowCharacterBinding
import com.example.lunarp.fragment.CharacterFragment
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CharacterListAdapter(private val context: Context, var activity: MainActivity): ListAdapter<Character,
        CharacterListAdapter.ViewHolder>(Comparator()){
    var fragment: CharacterFragment = CharacterFragment()

    class ViewHolder(var binding: RowCharacterBinding): RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowCharacterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.binding.tvname.setText("${data.name} niveau :   ${data.level}")
        holder.binding.tvrace.text = "${data.race.name}"
        holder.binding.tvrace2.text = "${data.classe.name}"

        holder.binding.imageViewDel.setImageResource(R.drawable.baseline_person_24)

        holder.binding.cardCharacter.setOnClickListener {
            Snackbar.make(it, "You click on ${data.name}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            val item = getItem(position)
            val intent = Intent(context, ViewCharacter::class.java)
            intent.putExtra("itemId", item.id)
            println("Go to ")
            context.startActivity(intent)

        }

        holder.binding.cardCharacter.setOnLongClickListener{
            Snackbar.make(it, "You hold on ${data.name}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()

            //TODO : delete
            AlertDialog.Builder(context).setTitle("Supprimer ce personnage")
                .setMessage("Etes-vous sûr de vouloir supprimer ${data.name}, ${data.race.name} ${data.classe.name} de niveau ${data.level}  ? ")
                .setCancelable(true)
                .setPositiveButton("Oui supprimer !") { dialogInterface, it ->
                    var retrofitCharacter = RequestUtils.retrofitBase.create<CharacterInterface>()
                    var retrofitDataCharacter = retrofitCharacter.deleteCharacter(CharacterDelete(data.id))
                    println("::::::DELETE ${data.id} :::::")
                    retrofitDataCharacter.enqueue(object: Callback<Any> {
                        override fun onResponse(
                            call: Call<Any>,
                            response: Response<Any>
                        ) {
                            val errorStr = response.errorBody()?.string()
                            if (response.isSuccessful){
                                println("--- DEL --- ${response.body()}")
                                this@CharacterListAdapter.notifyDataSetChanged()
                                fragment.updateList()

                            } else {
                                println("--- DEL --- ${response}")

                            }
                        }

                        override fun onFailure(call: Call<Any>, t: Throwable) {
                            println("::::::Delete Character fail:::::")
                            Log.d("ActivityCreateCharacterBinding", "onfailure: "+ t.message )
                        }

                    })
                }
                .setNegativeButton("Non"){dialogInterface, it ->
                    dialogInterface.cancel()
                }
                .show()
            true
        }

        holder.itemView.setOnClickListener {
        }
    }

}




class CharacterViewModel: ViewModel() {
    //Pas forcement besoin d'observable pour le moment
    var data  = mutableListOf<Character>()
}