package com.peeyoosh.navigation_paging_retrofit.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.peeyoosh.navigation_paging_retrofit.R
import com.peeyoosh.navigation_paging_retrofit.databinding.FragmentDetailsBinding
import com.peeyoosh.navigation_paging_retrofit.databinding.FragmentNewsBinding
import com.peeyoosh.navigation_paging_retrofit.databinding.ListItemBinding
import com.peeyoosh.navigation_paging_retrofit.retrofit.response.ArticleDataModel
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        binding.article = requireArguments()["article"] as ArticleDataModel

        Picasso.get().load(binding.article?.urlToImage)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(binding.detailsImage)

        return binding.root
    }
}