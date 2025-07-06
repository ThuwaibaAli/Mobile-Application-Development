package com.coder.maba
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class DetailActivity : AppCompatActivity() {
    lateinit var dbHelper: DatabaseHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
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
        deleteView.setBackgroundColor(Color.BLUE)
        deleteView.setTextColor(Color.CYAN)
        addView.setBackgroundColor(Color.BLUE)
        addView.setTextColor(Color.CYAN)
        MyView.setBackgroundColor(Color.BLUE)
        MyView.setTextColor(Color.CYAN)

        //end navigation
       // val callBtn = findViewById<Button>(R.id.call)
        var name = intent.getStringExtra("position")
        var nameDetail = dbHelper.getByIndex(name.toString())
       var tvDetail = findViewById<TextView>(R.id.details)
        tvDetail.text = nameDetail
       /* callBtn.setOnClickListener{
            val IntentCall = Intent(Intent.ACTION_CALL)
            val ind = name.toString().toInt()
            val num = dbHelper.getPhone()[ind]
            IntentCall.data = Uri.parse("tel:$num")
            Toast.makeText(this,"NUMBER: $num",Toast.LENGTH_SHORT).show()
            startActivity(IntentCall)
        }*/

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}