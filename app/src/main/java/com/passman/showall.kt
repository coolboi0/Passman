package com.passman

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class showall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showall)
        val helper = DatabaseHelper(applicationContext)
        val db=helper.readableDatabase
        val rs = db.rawQuery("SELECT *FROM DETAILS",null)
        val next=findViewById<Button>(R.id.btNext)
        val previous=findViewById<Button>(R.id.btPrevious)
        val update = findViewById<Button>(R.id.btUpdate)
        val delete = findViewById<Button>(R.id.btDelete)
        val listall = findViewById<Button>(R.id.btlistall)
        val showsite = findViewById<EditText>(R.id.etSiteShow)
        val showemail = findViewById<EditText>(R.id.etEmailShow)
        val showpassword = findViewById<EditText>(R.id.etPasswordShow)
        next.setOnClickListener {
            if (rs.moveToNext()) {
                showsite.setText(rs.getString(1))
               showemail.setText(rs.getString(2))
                showpassword.setText(rs.getString(3))
            }
            else{
                Toast.makeText(this,"This is last",Toast.LENGTH_SHORT).show()
        }
            previous.setOnClickListener {
                if (rs.moveToPrevious()) {
                    showsite.setText(rs.getString(1))
                    showemail.setText(rs.getString(2))
                    showpassword.setText(rs.getString(3))
                } else{
                    Toast.makeText(this,"This is first",Toast.LENGTH_SHORT).show()

                }
            }
            update.setOnClickListener {
                val cv = ContentValues()
                cv.put("website", showsite.text.toString())
                cv.put("email", showemail.text.toString())
                cv.put("password", showpassword.text.toString())
                db.update("DETAILS", cv, "_id = ?", arrayOf(rs.getString(0)))
                val ad = AlertDialog.Builder(this)
                ad.setTitle("Update")
                ad.setMessage("Updated successfully....")
                ad.setPositiveButton("OK",null)
                ad.show()
                rs.requery()
            }
            delete.setOnClickListener {
                db.delete("DETAILS", "_id = ?", arrayOf(rs.getString(0)))
                val ad = AlertDialog.Builder(this)
                ad.setTitle("Delete")
                ad.setMessage("Deleted successfully....")
                ad.setPositiveButton("OK",null)
                ad.show()
                rs.requery()
            }
            listall.setOnClickListener {
                val i=Intent(this,listOfAll::class.java)
                startActivity(i)
            }
        }

    }
}