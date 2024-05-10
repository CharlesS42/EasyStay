package com.example.easystay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MapSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_search)

        val intent: Intent = intent
        val receive = intent.getStringExtra("message key")
        val greeting = "Welcome "+receive!!.toString()+"!"

        findViewById<TextView>(R.id.txtGreeting).setText(greeting)

        val btnHotelSelect = findViewById<Button>(R.id.btnSave)

        btnHotelSelect.setOnClickListener {
            var deliveryStr: String = "Default user"
            deliveryStr = (findViewById<EditText>(R.id.edAddress).text.toString() + ", "
                    + findViewById<EditText>(R.id.edRoom).text.toString() + ", "
                    + findViewById<EditText>(R.id.txtPrice).text.toString()
                    )

            val intent01 = Intent(applicationContext, Reservations::class.java)
            intent.putExtra("message key 02", deliveryStr)
            startActivity(intent01)
        }
    }
}