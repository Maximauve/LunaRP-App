package com.example.lunarp.character.update

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunarp.R
import com.example.lunarp.update.UpdateCharacter_Global
import com.example.lunarp.ViewPagerAdapter
import com.example.lunarp.databinding.ActivityTabCharacterBinding
import com.google.android.material.tabs.TabLayout

class UpdateCharacter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_character)

        var binding = ActivityTabCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabs: TabLayout = binding.tabsCreate
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UpdateCharacter_Global(), "Global")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)



    }
}