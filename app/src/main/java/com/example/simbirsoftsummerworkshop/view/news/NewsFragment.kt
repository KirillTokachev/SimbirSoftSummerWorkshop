package com.example.simbirsoftsummerworkshop.view.news

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simbirsoftsummerworkshop.R
import com.example.simbirsoftsummerworkshop.adapters.BaseAdapter
import com.example.simbirsoftsummerworkshop.adapters.JsonAdapter
import com.example.simbirsoftsummerworkshop.databinding.FragmentNewsBinding
import com.example.simbirsoftsummerworkshop.model.DatasServise
import com.example.simbirsoftsummerworkshop.utils.factory
import com.example.simbirsoftsummerworkshop.view.fragments.BaseFragment
import com.example.simbirsoftsummerworkshop.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment<FragmentNewsBinding>() {
    private val viewModel: NewsViewModel by activityViewModels { factory() }

    override fun getViewBinding() = FragmentNewsBinding.inflate(layoutInflater)

    override fun setUpViews() {
        setupNewsLIst()
        setUpButton()
    }

    private fun setupNewsLIst() {
        if (DatasServise().getInstance().getNews().isEmpty()) {
            val newsList = JsonAdapter().getNews(requireContext())
            val baseAdapter = BaseAdapter(newsList)
            recycler_view_news.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = baseAdapter
                baseAdapter.itemClickListener = { view, item, position ->
                    findNavController().navigate(R.id.action_to_nav_graph_detail)
                    viewModel.saveAndInitDetailNews(newsList[position])
                }
            }
        } else {
            setUpSortedNewsLIst()
        }
    }

    private fun setUpSortedNewsLIst() {
        viewModel.news.observe(viewLifecycleOwner) { news ->
            val baseAdapter = BaseAdapter(news)
            recycler_view_news.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = baseAdapter
                baseAdapter.itemClickListener = { view, item, position ->
                    findNavController().navigate(R.id.action_to_nav_graph_detail)
                }
            }
        }
    }

    private fun setUpButton() {
        filter_button.setOnClickListener {
            findNavController().navigate(R.id.action_newsFragment_to_filterFragment)
            viewModel.saveNews(JsonAdapter().getNews(requireContext()))
        }
    }
}