package com.example.lunarp.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lunarp.MainActivity
import com.example.lunarp.R
import com.example.lunarp.databinding.FragmentCharacterBinding
import com.example.lunarp.databinding.RowCharacterBinding

class CharacterListAdapter(var activity: MainActivity): ListAdapter<CharacterBean,
        CharacterListAdapter.ViewHolder>(Comparator()){
    class ViewHolder(var binding: RowCharacterBinding): RecyclerView.ViewHolder(binding.root)

    class Comparator : DiffUtil.ItemCallback<CharacterBean>(){
        override fun areItemsTheSame(oldItem: CharacterBean, newItem: CharacterBean): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterBean, newItem: CharacterBean): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowCharacterBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)

        holder.binding.tvname.setText("${data.name}")

        holder.binding.imageViewDel.setImageResource(R.drawable.baseline_person_24)
    }
}