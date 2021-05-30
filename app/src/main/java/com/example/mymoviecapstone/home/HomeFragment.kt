package com.example.mymoviecapstone.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.data.Resource
import com.example.core.ui.ItemListAdapter
import com.example.mymoviecapstone.R
import com.example.mymoviecapstone.databinding.FragmentHomeBinding
import com.example.mymoviecapstone.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var hyoukaAdapter: ItemListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            hyoukaAdapter = ItemListAdapter()
            hyoukaAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.hyouka.observe(viewLifecycleOwner, { hyouka ->
                if (hyouka != null) {
                    when (hyouka) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            hyoukaAdapter.setData(hyouka.data)
                            hyoukaAdapter.notifyDataSetChanged()
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text = hyouka.message ?: getString(R.string.something_wrong)
                            Log.e("HomeTag", "Error")
                        }
                    }
                }
            })

            with(binding.rvHome) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = hyoukaAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}