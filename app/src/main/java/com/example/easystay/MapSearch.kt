package com.example.easystay

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream
import java.io.IOException

class MapSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_search)

        val intent: Intent = intent
        val receive = intent.getStringExtra("message key")
        val greeting = "Welcome "+receive!!.toString()+"!"

        findViewById<TextView>(R.id.txtGreeting).setText(greeting)

        val btnSave = findViewById<Button>(R.id.btnSave)
        val edBoxRoom = findViewById<EditText>(R.id.edRoom)
        val txtPrice = findViewById<TextView>(R.id.txtPrice)


        btnSave.setOnClickListener {
            var deliveryStr: String = "Default user"
            deliveryStr = (findViewById<EditText>(R.id.edAddress).text.toString() + ", "
                    + edBoxRoom.text.toString() + ", "
                    + txtPrice.text.toString()
                    )

            val intent02 = Intent(applicationContext, Reservations::class.java)
            intent.putExtra("message key 02", deliveryStr)
            startActivity(intent02)
        }
    }
}