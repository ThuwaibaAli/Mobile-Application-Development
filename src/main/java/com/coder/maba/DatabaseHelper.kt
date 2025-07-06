package com.coder.maba

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "UserDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE Users(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT,phone TEXT)")
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS Users")
        onCreate(db)
    }

    fun insertUser(name: String, email: String,phone: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name", name)
        contentValues.put("email", email)
        contentValues.put("phone", phone)
        val result = db.insert("Users", null, contentValues)
        return result != -1L
    }
    fun getAllUsers(): List<String> {
        val userList = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users", null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                userList.add("Name:\n $name\nEmail:\n $email\nPhone:\n $phone")
            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList
    }
    fun getByIndex(index:String): String {
        val userList = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users", null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val email = cursor.getString(cursor.getColumnIndexOrThrow("email"))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                userList.add("Name: $name\nEmail: $email\nPhone: $phone")
            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList.get(index.toInt())
    }
    fun getName(): List<String> {
        val userList = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users", null)

        if (cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                userList.add(name)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList
    }
    fun getPhone(): List<String> {
        val userList = mutableListOf<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Users", null)

        if (cursor.moveToFirst()) {
            do {
                val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))
                userList.add(phone)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return userList
    }
    fun getUserId():List<Int>{
        val idlist = mutableListOf<Int>()
        val db = this.readableDatabase
        val c = db.rawQuery("SELECT * FROM Users",null)
        if(c.moveToFirst()){
            do {
                val id = c.getInt(c.getColumnIndexOrThrow("id"))
                idlist.add(id)
            }while(c.moveToFirst())
        }
        c.close()
        return idlist
    }
    fun deleteUser(name:String):Int{
        val db = this.writableDatabase
        return db.delete("Users","name=?",arrayOf(name))
    }
}