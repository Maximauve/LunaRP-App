package com.example.lunarp.character.creation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunarp.R
import com.example.lunarp.ViewPagerAdapter
import com.example.lunarp.databinding.ActivityCreateCharacterBinding
import com.example.lunarp.databinding.ActivityMainBinding
import com.example.lunarp.fragment.CampaignFragment
import com.example.lunarp.fragment.CharacterFragment
import com.google.android.material.tabs.TabLayout

class CreateCharacter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        var binding = ActivityCreateCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabs: TabLayout = binding.tabsCreate
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(CreateCharacter_Global(), "Global")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }
}