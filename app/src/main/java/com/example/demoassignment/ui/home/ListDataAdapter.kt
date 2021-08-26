package com.example.demoassignment.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoassignment.R
import com.example.demoassignment.data.module.Result

class ListDataAdapter(private var dataSet: List<Result>) :
    RecyclerView.Adapter<ListDataAdapter.ViewHolder>() {

    var onItemClick: ((Result) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemTitle: TextView = view.findViewById(R.id.itemTitle)
        val itemSubTitle: TextView = view.findViewById(R.id.itemSubTitle)
        val itemPlace: TextView = view.findViewById(R.id.itemPlace)
        val itemDate: TextView = view.findViewById(R.id.itemDate)

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataSet[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_view, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val result = dataSet[position]
        viewHolder.itemTitle.text = result.title
        viewHolder.itemSubTitle.text = result.byline
        viewHolder.itemPlace.text = result.source
        viewHolder.itemDate.text = result.publishedDate

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun setData(dataSet: List<Result>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}