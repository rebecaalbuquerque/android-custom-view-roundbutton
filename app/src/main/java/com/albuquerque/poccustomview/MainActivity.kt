package com.albuquerque.poccustomview

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //roundButton.setIcon(ContextCompat.getDrawable(this,R.drawable.ic_edit_white_24dp))
        roundButton.setText("7")

    }
}
