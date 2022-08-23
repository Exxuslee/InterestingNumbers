package com.exxuslee.interestingnumbers.ui.first

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.exxuslee.interestingnumbers.databinding.FragmentFirstBinding
import com.exxuslee.testprofitof.utils.showIf
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment : Fragment() {

    private val viewModel: FirstViewModel by viewModel()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var firstAdapter: FirstAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firstAdapter = FirstAdapter(viewModel.selectedID.value ?: 0)
        binding.recyclerView.adapter = firstAdapter

        viewModel.dataFetchState.observe(viewLifecycleOwner) { state ->
            if (!state) {
                binding.errorText.visibility = View.VISIBLE
                Snackbar.make(requireView(),
                    "Oops! An error occured, check your connection and retry!",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.ids.observe(viewLifecycleOwner) { ids ->
            Log.d(TAG, ids.toString())
            firstAdapter.updateAdapter(ids)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { state ->
            binding.progressBar.showIf { state }
        }

        firstAdapter.onIDClickListener = {
            Log.d(TAG, "position ${it.first}")
            viewModel.navigate(it.first, it.second, view)
        }

        binding.fabRandom.setOnClickListener { viewModel.getRandomNumber() }

        binding.fabNumber.setOnClickListener {
            val msg = binding.input.text
            if (msg.isNotEmpty()) viewModel.getNumber(Integer.parseInt(msg.toString()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "testNumbers"
    }
}