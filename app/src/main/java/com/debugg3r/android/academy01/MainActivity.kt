package com.debugg3r.android.academy01

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ListFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.activity_main_frame, ListFragment.newInstance("",""), "")
                    .commit()
        }

    }

    override fun onFragmentInteraction(time: String, title:String) {
        supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out, R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.activity_main_frame, DetailFragment.getInstance(time, title), "")
                .commit()
    }

}
