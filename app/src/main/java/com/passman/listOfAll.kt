package com.passman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.SearchView
import android.widget.SimpleCursorAdapter

class listOfAll : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_all)
        val listView=findViewById<ListView>(R.id.listview)
        val search=findViewById<SearchView>(R.id.search)
        val helper = DatabaseHelper(applicationContext)
        val db=helper.readableDatabase
        val rs = db.rawQuery("SELECT *FROM DETAILS",null)
        val adapter=SimpleCursorAdapter(applicationContext,android.R.layout.simple_expandable_list_item_2,rs,
            arrayOf("website","email"),
            intArrayOf(android.R.id.text1,android.R.id.text2),0)
        listView.adapter=adapter
        search.queryHint="Search among ${rs.count} passwords"

    }
}