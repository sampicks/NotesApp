package com.peeyoosh.navigation_paging_retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.peeyoosh.navigation_paging_retrofit.NewsViewModel
import com.peeyoosh.navigation_paging_retrofit.R
import com.peeyoosh.navigation_paging_retrofit.adapter.AdapterItemClickListener
import com.peeyoosh.navigation_paging_retrofit.adapter.NewsPagingAdapter
import com.peeyoosh.navigation_paging_retrofit.databinding.FragmentNewsBinding
import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), AdapterItemClickListener {

    // another way of using viewmodel in fragment
    private val newsViewModel by viewModels<NewsViewModel>()
    private val newsPagingAdapter = NewsPagingAdapter(this)

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsViewModel.listData.observe(viewLifecycleOwner) {
            newsPagingAdapter.submitData(lifecycle, it)
        }

        binding.newsRecycler.adapter = newsPagingAdapter

        newsPagingAdapter.addLoadStateListener { state ->
            when (state.refresh) {
                is LoadState.Loading -> {
                    binding.newsProgress.visibility = View.VISIBLE
                }
                is LoadState.NotLoading -> {
                    binding.newsProgress.visibility = View.GONE
                }
                is LoadState.Error -> {
                    binding.newsProgress.visibility = View.GONE
                    Toast.makeText(requireContext(), "Error Occured", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun clickListener(article: ArticleDataModel) {
        findNavController().navigate(
            R.id.action_newsFragment_to_detailsFragment,
            bundleOf("article" to article)
        )
    }
}