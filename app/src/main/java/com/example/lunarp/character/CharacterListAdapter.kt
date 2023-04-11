package com.example.lunarp.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lunarp.MainActivity
import com.example.lunarp.R
import com.example.lunarp.databinding.FragmentCharacterBinding
import com.example.lunarp.databinding.RowCharacterBinding
import com.google.android.material.snackbar.Snackbar

class CharacterListAdapter(var activity: MainActivity): ListAdapter<Character,
        CharacterListAdapter.ViewHolder>(Comparator()){
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.binding.tvname.setText("${data.name} niveau :   ${data.level}")
        holder.binding.tvrace.setText("${data.race.name}")
        holder.binding.tvrace2.setText("${data.classe.name}")

        holder.binding.imageViewDel.setImageResource(R.drawable.baseline_person_24)

        holder.binding.cardAddCharacter.setOnClickListener {
            Snackbar.make(it, "You click on ${data.name}", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}

class CharacterViewModel: ViewModel() {
    //Pas forcement besoin d'observable pour le moment
    var data  = mutableListOf<Character>()
}