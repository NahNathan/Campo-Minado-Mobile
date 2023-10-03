package com.example.campominado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Tela_Dificuldade : AppCompatActivity() {
    lateinit var bt_facil : Button
    lateinit var bt_medio : Button
    lateinit var bt_dificil : Button
    lateinit var bt_legendario : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_dificuldade)

        bt_facil = findViewById(R.id.bt_facil)
        bt_medio = findViewById(R.id.bt_medio)
        bt_dificil = findViewById(R.id.bt_dificil)
        bt_legendario = findViewById(R.id.bt_legendario)

        bt_facil.setOnClickListener {
            val intent = Intent(this,Tela_OJogo::class.java)
            this.startActivity(intent)
        }

        bt_medio.setOnClickListener {
            val intent = Intent(this,Tela_OJogo::class.java)
            this.startActivity(intent)
        }

        bt_dificil.setOnClickListener {
            val intent = Intent(this,Tela_OJogo::class.java)
            this.startActivity(intent)
        }

        bt_legendario.setOnClickListener {
            val intent = Intent(this,Tela_OJogo::class.java)
            this.startActivity(intent)
        }
    }
}