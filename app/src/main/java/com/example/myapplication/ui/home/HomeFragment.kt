package com.example.myapplication.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.MyApplication
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.model.Article
import com.example.myapplication.network.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeFragment @Inject constructor() : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    // initialize binding==========================================
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // inject variables=============================================
    @Inject
    lateinit var homeViewModelFactory: HomeViewModelFactory
//    ================================================================


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as? MyApplication)?.appComponent?.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this, homeViewModelFactory)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            homeViewModel.newsArticles.collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        showProgressBar(true)
                    }

                    is Resource.Failure -> {
                        showProgressBar(false)
                        binding.txtErrorMessage.apply {
                            text = resource.throwable?.message
                            isVisible = resource.throwable?.message?.isNotEmpty() ?: false
                        }

                    }

                    is Resource.Success -> {
                        showProgressBar(false)
                        showArticles(resource.data)
                    }
                }
            }
        }
        homeViewModel.newsArticles
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.progressbar.isVisible = isLoading
    }

    private fun showArticles(articles: List<Article>?) {
        articles?.let {
            binding.rvArticles.adapter = ArticleAdapter(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}