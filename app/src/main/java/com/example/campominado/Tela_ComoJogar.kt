package com.example.campominado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Tela_ComoJogar : AppCompatActivity() {
    lateinit var bt_Jogar : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_como_jogar)

        bt_Jogar = findViewById(R.id.bt_Jogar)

        bt_Jogar.setOnClickListener {
            val intent = Intent(this,Tela_OJogo::class.java)
            this.startActivity(intent)
        }
    }
}