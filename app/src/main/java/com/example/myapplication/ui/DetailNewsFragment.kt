package com.example.myapplication.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.myapplication.MyApplication
import com.example.myapplication.databinding.FragmentDetailNewsBinding
import com.example.myapplication.model.Article
import com.example.myapplication.network.Resource
import com.example.myapplication.ui.home.HomeViewModel
import com.example.myapplication.ui.home.HomeViewModelFactory
import com.example.myapplication.utils.Pattern_DD_MM_YYYY
import com.example.myapplication.utils.getFormattedDate
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailNewsFragment : Fragment() {

    private var _binding: FragmentDetailNewsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

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
            ViewModelProvider(requireActivity(), homeViewModelFactory)[HomeViewModel::class.java]
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
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
                        showArticle(homeViewModel.selectedPos.value?.let { resource.data?.get(it) })
                    }
                }
            }
        }
    }

    private fun showArticle(article: Article?) {

        article?.let {
            binding.newsImage.load(article.urlToImage)
            binding.txtTitle.text = it.title
            binding.txtAuthorName.text = it.author
            binding.txtPublishedTime.text =
                it.publishedAt?.let { it1 -> getFormattedDate(Pattern_DD_MM_YYYY, it1) }
        }
    }

    private fun showProgressBar(isLoading: Boolean) {
        binding.progressbar.isVisible = isLoading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}