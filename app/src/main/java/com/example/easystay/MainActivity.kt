package com.example.easystay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnLogIn = findViewById<Button>(R.id.btnLogin)

        btnLogIn.setOnClickListener {
            var deliveryStr: String = "Default user"
            if (findViewById<EditText>(R.id.edUsername).text.equals("pwd")) {
                deliveryStr = (findViewById<EditText>(R.id.edUsername).text.toString())
            }
            val intent = Intent(applicationContext, MapSearch::class.java)
            intent.putExtra("message key", deliveryStr)
            startActivity(intent)
        }
    }
}