package com.coder.maba
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
class AddActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_activity)
        dbHelper = DatabaseHelper(this)
        //navigation
        val deleteActivity = Intent(this,DeleteActivity::class.java)
        val addActivity = Intent(this,AddActivity::class.java)
        val viewActivity = Intent(this,MainActivity::class.java)
        val deleteView = findViewById<TextView>(R.id.delete)
        val addView = findViewById<TextView>(R.id.add)
        val myView = findViewById<TextView>(R.id.view)
        deleteView.setOnClickListener{
            startActivity(deleteActivity)
        }
        addView.setOnClickListener{
            startActivity(addActivity)
        }
        myView.setOnClickListener{
            startActivity(viewActivity)
        }
        deleteView.setBackgroundColor(Color.BLUE)
        deleteView.setTextColor(Color.CYAN)
        addView.setBackgroundColor(Color.CYAN)
        addView.setTextColor(Color.BLACK)
        myView.setBackgroundColor(Color.BLUE)
        myView.setTextColor(Color.CYAN)
        //end navigation
        val nameList = dbHelper.getName()
        val ad = ArrayAdapter(this,android.R.layout.simple_list_item_1,nameList)
        val addViewList = findViewById<ListView>(R.id.listView)
        val addBtn = findViewById<Button>(R.id.addId)
        val nameId = findViewById<EditText>(R.id.nameId)
        val phoneId = findViewById<EditText>(R.id.phoneId)
        val emailId = findViewById<EditText>(R.id.emailId)
        addViewList.adapter = ad
        addBtn.setOnClickListener{
            if (phoneId.text.toString().startsWith("+") && !nameList.contains(nameId.text.toString()) && nameId.text.toString().length >= 3 && phoneId.text.toString().length == 13 && Patterns.EMAIL_ADDRESS.matcher(emailId.text.toString()).matches() && Patterns.PHONE.matcher(phoneId.text.toString()).matches()){
                dbHelper.insertUser(nameId.text.toString(),emailId.text.toString(),phoneId.text.toString())
                nameId.setText("")
                phoneId.setText("")
                emailId.setText("")
                addViewList.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dbHelper.getName())
            }
            else{
                Toast.makeText(this,"Invalid input",Toast.LENGTH_SHORT).show()
            }
        }
        addViewList.setOnItemClickListener() { parent, view, position, id ->
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
