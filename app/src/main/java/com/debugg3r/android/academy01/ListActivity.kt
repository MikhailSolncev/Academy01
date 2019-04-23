package com.debugg3r.android.academy01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.debugg3r.android.academy01.data.DataProvider

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView = findViewById<RecyclerView>(R.id.list_recycler_vew)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ThemeListAdapter(DataProvider.getData())
        recyclerView.setHasFixedSize(true)

    }
}
