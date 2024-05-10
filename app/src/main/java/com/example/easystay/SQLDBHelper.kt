package com.example.easystay

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.Date

class SQLDBHelper (context:Context, factory:SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION)
{
    // contract class to set constraints of the database (version, database name, etc.)
    companion object{
        private val DATABASE_NAME = "easyStayDB"
        private val DATABASE_VERSION = 1
        // ID is indexing value in database for future table (look bellow)
        val ID_COL = "id"
        val ADDRESS = "address"
        val NO_ROOMS = "noRooms"
        val PRICE = "price"
        val NO_CARD = "noCard"
        val CARDHOLDER = "cardholder"
        val EXPIRATION_DATE = "expDate"
        val SECURITY_CODE = "secCode"
        // table name
        val TABLE_NAME = "Reservation"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // step 01: Create the query and execute it to create the data table in the database
        val query = (
                "CREATE TABLE " + TABLE_NAME + "(" +
                        ID_COL + " INTEGER PRIMARY KEY, " +
                        ADDRESS + " TEXT, " +
                        NO_ROOMS + " INTEGER, " +
                        PRICE + " DOUBLE, " +
                        NO_CARD + " TEXT, " +
                        CARDHOLDER + " TEXT, " +
                        EXPIRATION_DATE + " TEXT, " +
                        SECURITY_CODE + " INTEGER" +
                        ")"
                )

        // step 02: Execute the query so that it can create the table
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS " + TABLE_NAME
        db.execSQL(query)
        onCreate(db)
        /// if you want to use a predefined database, keep this method empty
    }

    public fun AddInfo(address: String, noRooms: Int, price: Double, noCard: String, cardholder: String, expDate: String, secCode: Int){
        // step 01: create a group values/group of contents/ List of values
        val values = ContentValues()
        // step 02: add your entries to the values
        values.put(ADDRESS, address)
        values.put(NO_ROOMS, noRooms)
        values.put(PRICE, price)
        values.put(NO_CARD, noCard)
        values.put(CARDHOLDER, cardholder)
        values.put(EXPIRATION_DATE, expDate)
        values.put(SECURITY_CODE, secCode)
        // step 03: we need to make our database writeable so that it can write in the sql file
        val db = this.writableDatabase
        // step 04: Database insertion operation with execution
        db.insert(TABLE_NAME, null, values)
        // step 05: Close database
        db.close()
    }
    public fun GetInfo():Cursor? // Cursor is the return of a list of rows in a database table
    {
        // step 01: make the database readable
        val db = this.readableDatabase
        // step 02: Create query
        val query = "Select * FROM " + TABLE_NAME
        // return the whole data table or your queried values
        return db.rawQuery(query, null)
    }
    public fun UpdateInfo(id: Int, address: String, noRooms: Int, price: Double, noCard: String, cardholder: String, expDate: String, secCode: Int) {
        // step 01: create a group values/group of contents/ List of values
        val values = ContentValues()
        // step 02: add your entries to the values
        values.put(ADDRESS, address)
        values.put(NO_ROOMS, noRooms)
        values.put(PRICE, price)
        values.put(NO_CARD, noCard)
        values.put(CARDHOLDER, cardholder)
        values.put(EXPIRATION_DATE, expDate)
        values.put(SECURITY_CODE, secCode)
        // step 03: we need to make our database writeable so that it can write in the sql file
        val db = this.writableDatabase
        // step 04: Database updating operation with execution
        db.update(TABLE_NAME, values, "ID_COL=${id.toString()}", null)
        // step 05: Close database
        db.close()
    }
    public fun DeleteInfo(id: Int){
        // step 01: we need to make our database writeable so that it can write in the sql file
        val db = this.writableDatabase
        // step 02: Database deletion operation with execution
        db.delete(TABLE_NAME,"ID_COL=${id.toString()}", null)
        // step 03: Close database
        db.close()
    }
}