package com.example.lunarp.character.read

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunarp.R
import com.example.lunarp.ViewPagerAdapter
import com.example.lunarp.databinding.ActivityViewCharacterBinding
import com.google.android.material.tabs.TabLayout

class ViewCharacter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_character)
        println("Extras here : !!! ${intent.extras?.get("itemId")}!!!")

        var binding = ActivityViewCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabs: TabLayout = binding.tabsCreate
        val viewPager = binding.viewPager

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ViewCharacter_Global(), "Global")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}