package com.exxuslee.interestingnumbers.ui.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val content = arguments?.getString("content") ?: "0 no data"
        return ComposeView(requireContext()).apply { setContent { Second(content) } }
    }
}