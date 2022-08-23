package com.exxuslee.interestingnumbers.ui.first

import androidx.recyclerview.widget.DiffUtil

class FirstDiffCallback : DiffUtil.ItemCallback<Pair<Int, String>>() {

    override fun areItemsTheSame(oldItem: Pair<Int, String>, newItem: Pair<Int, String>): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(
        oldItem: Pair<Int, String>, newItem: Pair<Int, String>,
    ): Boolean {
        return oldItem.second == newItem.second
    }
}