package com.debugg3r.android.academy01

import android.app.FragmentManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(), ListFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var fragment = ListFragment.newInstance("","")

        supportFragmentManager
                .beginTransaction()
                .add(R.id.activity_main_frame, fragment, "")
                .commit()

    }

    override fun onFragmentInteraction(uri: Uri?) {
    }

}
