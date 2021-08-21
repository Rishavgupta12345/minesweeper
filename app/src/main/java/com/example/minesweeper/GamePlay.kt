package com.example.minesweeper

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.board.*

class GamePlay: AppCompatActivity() {

    var choice : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.board)

        val intent = intent
        var flag = intent.getIntExtra("flag", 2)

        // Setting up board according to the option selected in MainActivity
        if (flag == 1) {
            var level = intent.getStringExtra("selectedLevel")
            if (level.equals("easy")) {
                SetUpBoard(6, 6, 8)
            } else if (level.equals("medium")) {
                SetUpBoard(10, 10, 18)
            } else if (level.equals("hard")) {
                SetUpBoard(14, 14, 28)
            }
        } else {
            var row = intent.getIntExtra("height", 0)
            var col = intent.getIntExtra("width", 0)
            var mine = intent.getIntExtra("mines", 0)
            SetUpBoard(row, col, mine)
        }

        // Restarting the game
        restart_button.setOnClickListener{
            gameRestart()
        }

    }

    private fun SetUpBoard (row: Int, col: Int, mine: Int)
    {

        action_click.setOnClickListener{
            if(choice==1) {
                action_click.setImageResource(R.drawable.flag)
                choice=2
            }else{
                action_click.setImageResource(R.drawable.mine)
                choice=1
            }
        }

        //set the layout
        var counter = 1

        val params1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,0
        )
        val params2 = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.MATCH_PARENT,
        )

        for(i in 0 until row) {
            val linearLayout = LinearLayout(this)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            linearLayout.layoutParams = params1
            params1.weight = 1.0F

            for (j in 0 until col) {

                // makeing of button
                val button = Button(this)
                button.id = counter
                button.textSize = 18.0F
                button.layoutParams = params2
                params2.weight = 1.0F
                button.setBackgroundResource(R.drawable.button)

                linearLayout.addView(button)
                counter++
            }
            main_buttons.addView(linearLayout)
        }

    }

    private fun gameRestart() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setMessage("Do you want to restart the game ?")
        builder.setTitle("Alert!")
        builder.setCancelable(false)

        builder.setPositiveButton("Yes"
        ){ dialog, which ->
            val intent = getIntent()
            finish()
            startActivity(intent)
        }

        builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
            }
        })

        val alertDialog = builder.create()
        alertDialog.show()
    }

    // On pressing back button
    override fun onBackPressed() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder.setMessage("Are you sure you want to exit the game?")
        builder.setTitle("Game is still ongoing!")
        builder.setCancelable(false)

        builder.setPositiveButton("Yes"
        ){ dialog, which ->
           // updateScore()
           // toMainActivity()
            finish()
            super.onBackPressed()
        }

        builder.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
            }
        })

        val alertDialog = builder.create()
        alertDialog.show()
    }

}