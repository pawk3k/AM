package com.example.detailflow.adapters

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.detailflow.activities.RunDetailActivity
import com.example.detailflow.fragments.RunDetailFragment
import com.example.detailflow.activities.RunListActivity
import com.example.detailflow.R

class RunRecyclerAdapter(
    private val parentActivity: RunListActivity,
    private val values: List<String>,
    private val data: HashMap<String, String>,
    private val tablet : Boolean
) :
    RecyclerView.Adapter<RunRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.toString()
        val detailContainer: NestedScrollView? =
            holder.itemView.findViewById(R.id.item_detail_container)


        with(holder.itemView) {
            tag = item
            setOnClickListener() {
                val item = this.tag as String
                val description = data[item]
                println(description)
                if (tablet) {
                    val fragment = RunDetailFragment().apply {
                        arguments = Bundle().apply {
                            putString(RunDetailFragment.ARG_ITEM_ID, description)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(this.context, RunDetailActivity::class.java).apply {
                        putExtra(RunDetailFragment.ARG_ITEM_ID, description)
                    }
                    this.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val contentView: TextView = view.findViewById(R.id.content)
    }

}