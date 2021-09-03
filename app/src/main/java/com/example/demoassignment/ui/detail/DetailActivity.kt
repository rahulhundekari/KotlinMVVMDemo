package com.example.demoassignment.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demoassignment.R
import com.example.demoassignment.data.module.Result
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.title = "Detailed Screen View"

        val result = intent.getParcelableExtra<Result>("RESULT")
        text.text = "$result"
    }
}