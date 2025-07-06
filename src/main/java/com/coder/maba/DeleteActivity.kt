package com.coder.maba

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DeleteActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.delete_activity)
        dbHelper = DatabaseHelper(this)
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
        deleteView.setBackgroundColor(Color.CYAN)
        deleteView.setTextColor(Color.BLACK)
        addView.setBackgroundColor(Color.BLUE)
        addView.setTextColor(Color.CYAN)
        MyView.setBackgroundColor(Color.BLUE)
        MyView.setTextColor(Color.CYAN)

        //end navigation

        var nameList = dbHelper.getName()
        var ad = ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,nameList)
        var deleteViewList = findViewById<ListView>(R.id.deleteViewList)
        var deleteBtn = findViewById<Button>(R.id.deleteBtn)
        deleteViewList.adapter = ad
        deleteViewList.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        deleteBtn.setOnClickListener{
            for(i in nameList.indices){
                if(deleteViewList.isItemChecked(i) && dbHelper.deleteUser(nameList[i]) > 0){
                    Toast.makeText(this,"Deleted ${nameList.get(i)}",Toast.LENGTH_SHORT).show()
                }
            }
            deleteViewList.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_multiple_choice,dbHelper.getName())
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}
