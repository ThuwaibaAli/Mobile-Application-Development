package com.coder.maba

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
lateinit var dbHelper: DatabaseHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DatabaseHelper(this)
        var mylist = dbHelper.getName()
        var myListView = findViewById<ListView>(R.id.mylistviews)
        //navigation
        var deleteActivity = Intent(this,DeleteActivity::class.java)
        var addActivity = Intent(this,AddActivity::class.java)
        var viewActivity = Intent(this,MainActivity::class.java)
        var deleteView = findViewById<TextView>(R.id.delete)
        var addView = findViewById<TextView>(R.id.add)
        var MyView = findViewById<TextView>(R.id.view)
        deleteView.setOnClickListener{
            startActivity(deleteActivity)
        }
        addView.setOnClickListener{
            startActivity(addActivity)
        }
        MyView.setOnClickListener{
            startActivity(viewActivity)
        }
        deleteView.setBackgroundColor(Color.BLUE)
        deleteView.setTextColor(Color.CYAN)
        addView.setBackgroundColor(Color.BLUE)
        addView.setTextColor(Color.CYAN)
        MyView.setBackgroundColor(Color.CYAN)
        MyView.setTextColor(Color.BLACK)

        //end navigation
        val Adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,mylist)
        myListView.adapter = Adapter
        myListView.setOnItemClickListener() { parent, view, position, id ->
            val intentDetais = Intent(this,DetailActivity::class.java)
            intentDetais.putExtra("position","0$position")
            startActivity(intentDetais)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}
