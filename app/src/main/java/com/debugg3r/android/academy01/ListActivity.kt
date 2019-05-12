package com.debugg3r.android.academy01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.debugg3r.android.academy01.data.Activity
import com.debugg3r.android.academy01.data.DataProvider
import com.debugg3r.android.academy01.presenter.IListActivity
import com.debugg3r.android.academy01.presenter.ListPresenter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), IListActivity {
    private val adapter = ThemeListAdapter(listOf())
    private val presenter = ListPresenter.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val recyclerView = findViewById<RecyclerView>(R.id.list_recycler_vew)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }

    override fun updateData(newData: List<Activity>) {
        adapter.updateData(newData)
    }

    override fun showLoading() {
        this.runOnUiThread { list_progress.visibility = View.VISIBLE }
    }

    override fun showList() {
        this.runOnUiThread { list_progress.visibility = View.GONE }
    }

    override fun showMessage(text: String?) {
        this.runOnUiThread { Toast.makeText(this, text, Toast.LENGTH_LONG).show() }
    }
}
