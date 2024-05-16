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

            val intent01 = Intent(applicationContext, Reservations::class.java)
            intent.putExtra("message key 02", deliveryStr)
            startActivity(intent01)
        }

        btnSave.setOnClickListener {
            // Get the number of rooms and the final price from the views
            val numberOfRooms = edBoxRoom.text.toString()
            val finalPrice = txtPrice.text.toString()

            // Create a new document
            val document = PdfDocument()

            // Create a PageInfo object
            val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()

            // Start a page
            val page = document.startPage(pageInfo)

            // Get the Canvas object from the page and use it to draw
            val canvas: Canvas = page.canvas
            val paint = Paint()
            paint.textSize = 14f
            canvas.drawText("Confirmation: You have saved your selection to the hotel.", 20f, 50f, paint)
            canvas.drawText("Number of rooms: $numberOfRooms", 20f, 70f, paint)
            canvas.drawText("Final price: $finalPrice", 20f, 90f, paint)

            // Finish the page
            document.finishPage(page)

            // Write the document contents to a file
            val filePath = getExternalFilesDir(null)?.absolutePath + "/confirmation.pdf"
            try {
                val fos = FileOutputStream(filePath)
                document.writeTo(fos)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            // Close the document
            document.close()
        }
    }
}