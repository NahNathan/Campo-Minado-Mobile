package com.example.campominado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var bt_comojogar : Button
    lateinit var bt_jogar : Button
    lateinit var bt_scores : Button
    lateinit var bt_creditos : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_comojogar = findViewById(R.id.bt_comojogar)
        bt_jogar = findViewById(R.id.bt_jogar)
        bt_scores = findViewById(R.id.bt_scores)
        bt_creditos = findViewById(R.id.bt_creditos)

        bt_comojogar.setOnClickListener {
            val intent = Intent(this,Tela_ComoJogar::class.java)
            this.startActivity(intent)
        }

        bt_jogar.setOnClickListener {
            val intent = Intent(this,Tela_Dificuldade::class.java)
            this.startActivity(intent)
        }

        bt_scores.setOnClickListener {
            val intent = Intent(this,Tela_Scores::class.java)
            this.startActivity(intent)
        }

        bt_creditos.setOnClickListener {
            val intent = Intent(this,Tela_Creditos::class.java)
            this.startActivity(intent)
        }
    }
}