package com.example.easystay

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.io.FileOutputStream
import java.io.IOException

class Reservations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservations)

        val intent: Intent = intent
        // details from map search activity
        val hotelReservationDetails = intent.getStringExtra("message key 02")
        val arrayOfDetails = hotelReservationDetails!!.split(", ");

        val btnHotelSelect = findViewById<Button>(R.id.btnTransaction)

        val cardNum = findViewById<EditText>(R.id.edCardNumber)
        val cardHolder = findViewById<EditText>(R.id.edCardHolder)
        val expDate = findViewById<EditText>(R.id.edExpiration)
        val secCode = findViewById<EditText>(R.id.edCode)

        // Get the number of rooms and the final price from the intent
        val hotelAddress = arrayOfDetails[0]
        val numberOfRooms = arrayOfDetails[1]
        val finalPrice = arrayOfDetails[2]
        btnHotelSelect.setOnClickListener{

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
            canvas.drawText("Confirmation: You have saved your selection to the hotel.", 20f, 30f, paint)
            canvas.drawText("Hotel Address: $hotelAddress", 20f, 70f, paint)
            canvas.drawText("Number of rooms: $numberOfRooms", 20f, 90f, paint)
            canvas.drawText("Final price: $finalPrice", 20f, 110f, paint)

            canvas.drawText("Credit Card Information:", 20f, 150f, paint)
            canvas.drawText("Card Holder: $cardHolder", 20f, 170f, paint)
            canvas.drawText("Card Number: $cardNum", 20f, 190f, paint)

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