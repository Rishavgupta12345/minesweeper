package com.example.minesweeper

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.custom_layout_maker.*

class CustomBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_layout_maker)

        //user instructions for the custom board
        NOTICE.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)

            builder.setTitle("NOTICE")
            builder.setMessage("The game has its maximum height and width to a limit which is 20.\n" +
                    "\n" +
                    "And logically you can't enter the mines more than the number of cells present (which is height x width) .\n" +
                    "\n" +
                    "Happy mine hunting!!!")

            builder.setCancelable(false)

            builder.setPositiveButton("OK"
            ){ dialog, which ->

            }

            val alertDialog = builder.create()
            alertDialog.show()
        }

        // edit text
        val height = findViewById<TextInputLayout>(R.id.hi)
        val width = findViewById<TextInputLayout>(R.id.wi)
        val mines = findViewById<TextInputLayout>(R.id.mi)


        // when user clicks submit button
        submit.setOnClickListener {

            //after clicking submit button, this will transfer or pass the value which the user has entered
            var heigh = Integer.parseInt(height.editText?.text.toString())
            var widt = Integer.parseInt(width.editText?.text.toString())
            var mine = Integer.parseInt(mines.editText?.text.toString())

            // passing the values to the gameplay activity
            val intent = Intent(this, GamePlay::class.java).apply {
                putExtra("height", heigh)  //put the value
                putExtra("width", widt)
                putExtra("mines", mine)
            }
            startActivity(intent)
        }

    }
}