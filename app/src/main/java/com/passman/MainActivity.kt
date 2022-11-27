package com.passman

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call.Details
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etSite=findViewById<EditText>(R.id.etSite)
        val etEmail=findViewById<EditText>(R.id.etEmail)
        val etPassword=findViewById<EditText>(R.id.etPassword)
        val btSave=findViewById<Button>(R.id.btSave)
        val btShowall=findViewById<Button>(R.id.btShowall)
        val site = etSite.text
        val email= etEmail.text
        val password = etPassword.text
        val helper = DatabaseHelper(applicationContext)
        val db = helper.writableDatabase
        btSave.setOnClickListener {
            val cv=ContentValues()
            cv.put("website",site.toString())
            cv.put("email",email.toString())
            cv.put("password",password.toString())
            db.insert("DETAILS",null,cv)
            val ad = AlertDialog.Builder(this)
            ad.setTitle("Save")
            ad.setMessage("Saved successfully....")
            ad.setPositiveButton("Done",null)
            ad.show()
            site.clear()
            password.clear()
            email.clear()
            etSite.requestFocus()

        }
        btShowall.setOnClickListener {
            val i =Intent(this,showall::class.java)
            startActivity(i)
        }

    }
}