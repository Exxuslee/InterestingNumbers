package com.exxuslee.interestingnumbers.ui.first

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.exxuslee.interestingnumbers.R

class FirstAdapter(private var selectedPosition: Int = 0) :
    ListAdapter<Pair<Int, String>, FirstAdapter.FirstHolder>(FirstDiffCallback()) {

    var onIDClickListener: ((Pair<Int, String>) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_fist, parent, false)
        return FirstHolder(view)
    }

    override fun onBindViewHolder(holder: FirstHolder, position: Int) {
        holder.name.text = getItem(position).first.toString()
        holder.content.text = getItem(position).second
        holder.itemView.setBackgroundColor(
            if (selectedPosition == position) Color.LTGRAY else Color.TRANSPARENT)
        holder.itemView.setOnClickListener {
            notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(position)
            onIDClickListener?.invoke(getItem(holder.adapterPosition))
        }
    }

    fun updateAdapter(ids: Map<Int, String>?) {
        submitList(ids?.toList())
    }

    class FirstHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.name)
        val content: TextView = view.findViewById(R.id.content)
    }

}