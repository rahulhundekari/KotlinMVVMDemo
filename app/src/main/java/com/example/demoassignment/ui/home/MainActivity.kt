package com.example.demoassignment.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.demoassignment.R
import com.example.demoassignment.ui.detail.DetailActivity
import com.example.demoassignment.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()

    lateinit var adapter: ListDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "NY Times Most Popular"

        adapter = ListDataAdapter(arrayListOf())
        itemListView.setHasFixedSize(true)
        itemListView.adapter = adapter

        adapter.onItemClick = { result ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("RESULT", result)
            startActivity(intent)
        }

        getMostViewedData()
    }

    private fun getMostViewedData() {
        viewModel.getUserData()
            .observe(this, {
                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE

                        it.data?.let { resultList ->
                            adapter.setData(resultList)
                        }

                    }

                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }

                }
            })
    }
}