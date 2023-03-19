package com.example.lunarp.character.creation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lunarp.R
import com.example.lunarp.databinding.ActivityCreateCharacterBinding

class CreateCharacter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_character)

        var binding = ActivityCreateCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.next.setOnClickListener  { view ->
            println("Entries: ")
            var nameText = binding.name.text.toString()
            var raceText = binding.race.text.toString()
            var classText = binding.classId.text.toString()
            var levelText = binding.level.text.toString()
            var expText = binding.exp.text.toString()
            var alignmentText  = binding.alignment.text.toString()
            var descriptionText = binding.description.text.toString()

            var strValue = binding.strength.text.toString()
            var dexValue = binding.desxterity.text.toString()
            var conValue = binding.constitution.text.toString()
            var wisValue = binding.wisdom.text.toString()
            var intValue = binding.intelligence.text.toString()
            var chaValue = binding.charisma.text.toString()

            println(nameText)
            println(raceText)
            println(classText)
            println(levelText)
            println(expText)
            println(alignmentText)
            println(descriptionText)
            println("$strValue | $dexValue | $conValue | $wisValue | $intValue | $chaValue")

        }

    }
}