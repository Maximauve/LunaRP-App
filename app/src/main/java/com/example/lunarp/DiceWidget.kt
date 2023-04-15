package com.example.lunarp

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DiceWidget(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private var isOpen = false
    private var total = 0
    private var diceChosen = mutableListOf<Int>()

    init {
        inflate(context, R.layout.content_dices, this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        val fab_throw = findViewById<FloatingActionButton>(R.id.fab_throw)
        val fab_reset = findViewById<FloatingActionButton>(R.id.fab_reset)

        val fab_open = AnimationUtils.loadAnimation(context, R.anim.fab_open)
        val fab_close = AnimationUtils.loadAnimation(context, R.anim.fab_close)

        val fab_dice4 = findViewById<FloatingActionButton>(R.id.fab_dice4)
        val fab_dice6= findViewById<FloatingActionButton>(R.id.fab_dice6);
        val fab_dice8 = findViewById<FloatingActionButton>(R.id.fab_dice8)
        val fab_dice10 = findViewById<FloatingActionButton>(R.id.fab_dice10)
        val fab_dice12 = findViewById<FloatingActionButton>(R.id.fab_dice12)
        val fab_dice20 = findViewById<FloatingActionButton>(R.id.fab_dice20)
        val fab_dice100 = findViewById<FloatingActionButton>(R.id.fab_dice100)
        var total : Int = 0
        var diceChosen = (mutableListOf<Int>())

        fab.setOnClickListener {
            if (isOpen) {
                fab_dice4.startAnimation(fab_close);
                fab_dice6.startAnimation(fab_close);
                fab_dice8.startAnimation(fab_close)
                fab_dice10.startAnimation(fab_close)
                fab_dice12.startAnimation(fab_close)
                fab_dice20.startAnimation(fab_close)
                fab_dice100.startAnimation(fab_close)
                fab_throw.startAnimation(fab_close)
                fab_reset.startAnimation(fab_close)
                //fab.startAnimation(fab_anticlock);
                fab_dice4.isClickable = false;
                fab_dice6.isClickable = false;
                fab_dice8.isClickable = false
                fab_dice10.isClickable = false
                fab_dice12.isClickable = false
                fab_dice20.isClickable = false
                fab_dice100.isClickable = false
                fab_throw.isClickable = false
                fab_reset.isClickable = false
                isOpen = false
            } else {
                fab_dice4.startAnimation(fab_open);
                fab_dice6.startAnimation(fab_open);
                fab_dice8.startAnimation(fab_open)
                fab_dice10.startAnimation(fab_open)
                fab_dice12.startAnimation(fab_open)
                fab_dice20.startAnimation(fab_open)
                fab_dice100.startAnimation(fab_open)
                fab_throw.startAnimation(fab_open)
                fab_reset.startAnimation(fab_open)
                //fab.startAnimation(fab_anticlock);
                fab_dice4.isClickable = true;
                fab_dice6.isClickable = true;
                fab_dice8.isClickable = true;
                fab_dice10.isClickable = true;
                fab_dice12.isClickable = true;
                fab_dice20.isClickable = true;
                fab_dice100.isClickable = true;
                fab_throw.isClickable = true
                fab_reset.isClickable = true
                isOpen = true
            }
        }
        fab_dice4.setOnClickListener {
            diceChosen+=4
            Toast.makeText(context, "Un dé 4 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice6.setOnClickListener {
            diceChosen+=6
            Toast.makeText(context, "Un dé 6 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice8.setOnClickListener {
            diceChosen+=8
            Toast.makeText(context, "Un dé 8 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice10.setOnClickListener {
            diceChosen+=10
            Toast.makeText(context, "Un dé 10 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice12.setOnClickListener {
            diceChosen+=12
            Toast.makeText(context, "Un dé 12 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice20.setOnClickListener {
            diceChosen+=20
            Toast.makeText(context, "Un dé 20 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_dice100.setOnClickListener {
            diceChosen+=100
            Toast.makeText(context, "Un dé 100 en plus", Toast.LENGTH_SHORT).show()
        }
        fab_throw.setOnClickListener {
            var randomNumbers = mutableListOf<Int>()
            for (nb in diceChosen) {
                var x = (1..nb).random()
                randomNumbers.add(x)
                total += x
            }
            Toast.makeText(context, "Dé choisis : ${diceChosen.joinToString(" - ")} \n" +
                    "${randomNumbers.joinToString(" + ")} = $total", Toast.LENGTH_SHORT).show()
            total = 0
            diceChosen.clear()
        }
        fab_reset.setOnClickListener {
            diceChosen.clear()
            Toast.makeText(context, "Effacement des dé sélectionnés ! ", Toast.LENGTH_SHORT).show()
        }
    }
}
