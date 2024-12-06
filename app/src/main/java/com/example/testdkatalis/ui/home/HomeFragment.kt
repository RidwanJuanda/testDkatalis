package com.example.testdkatalis.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testdkatalis.WebviewActivity
import com.example.testdkatalis.databinding.FragmentHomeBinding
import com.example.testdkatalis.response.BannersData
import com.example.testdkatalis.response.model.NewsData
import com.example.testdkatalis.ui.detailnews.DetailNewsActivity
import com.example.testdkatalis.ui.home.adapter.CarouselAdapter
import com.example.testdkatalis.ui.home.adapter.NewsOtherAdapter
import com.example.testdkatalis.ui.home.adapter.NewsTopAdapter
import com.example.testdkatalis.utils.LinearItemDecoration
import java.util.Timer
import java.util.TimerTask

class HomeFragment : Fragment(), HomeView {
    private lateinit var binding: FragmentHomeBinding
    private val presenter by lazy { HomePresenter(this) }
    private var carouselLayoutManager: LinearLayoutManager? = null
    private var newsTopLayoutManager: LinearLayoutManager? = null
    private var newsOtherLayoutManager: LinearLayoutManager? = null
    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var newsTopAdapter: NewsTopAdapter
    private lateinit var newsOtherAdapter: NewsOtherAdapter
    private var dataCarousel: MutableList<BannersData> = mutableListOf()
    private var dataNewsTop: MutableList<NewsData> = mutableListOf()
    private var dataNewsOther: MutableList<NewsData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        setupAdapter()
    }

    private fun setupAdapter() {
        // init banner adapter
        carouselAdapter = CarouselAdapter {
            it.link?.let { it1 -> WebviewActivity.startActivity(requireContext(), it1) }
        }
        carouselLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvCarouselHome.adapter = carouselAdapter
        binding.rvCarouselHome.layoutManager = carouselLayoutManager
        binding.rvCarouselHome.addItemDecoration(
            LinearItemDecoration(20, 0, 10, 10)
        )

        // init news top adapter
        newsTopAdapter = NewsTopAdapter {
            DetailNewsActivity.startActivity(requireContext(), it)
        }
        newsTopLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvNewsTopHome.adapter = newsTopAdapter
        binding.rvNewsTopHome.layoutManager = newsTopLayoutManager

        // init news other adapter
        newsOtherAdapter = NewsOtherAdapter {
            DetailNewsActivity.startActivity(requireContext(), it)
        }
        newsOtherLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.rvNewsOtherHome.adapter = newsOtherAdapter
        binding.rvNewsOtherHome.layoutManager = newsOtherLayoutManager
    }

    private fun getData(){
        presenter.getBanner()
        presenter.getNewsTop()
        presenter.getNewsOther()
    }

    override fun onGetBanners(data: List<BannersData>?) {
        dataCarousel.clear()
        data?.let { dataCarousel.addAll(it) }
        carouselAdapter.setData(dataCarousel)

        if (carouselAdapter.itemCount != 0) {
            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    if ((carouselLayoutManager?.findFirstCompletelyVisibleItemPosition()
                            ?: 0) < (carouselAdapter.itemCount - 1)
                    ) {
                        carouselLayoutManager?.smoothScrollToPosition(
                            binding.rvCarouselHome, RecyclerView.State(),
                            carouselLayoutManager?.findLastCompletelyVisibleItemPosition()?.plus(1) ?: 0
                        )
                    } else if ((carouselLayoutManager?.findLastCompletelyVisibleItemPosition()
                            ?.plus(1) ?: 0) == (carouselAdapter.itemCount)
                    ) {
                        carouselLayoutManager?.smoothScrollToPosition(
                            binding.rvCarouselHome,
                            RecyclerView.State(),
                            0
                        )
                    }
                }
            }, 0, 3000)
        }

    }

    override fun onGetTopNews(data: List<NewsData>?) {
        dataNewsTop.clear()
        data?.let { dataNewsTop.addAll(it) }
        newsTopAdapter.setData(dataNewsTop)
    }

    override fun onGetOtherNews(data: List<NewsData>?) {
        dataNewsOther.clear()
        data?.let { dataNewsOther.addAll(it) }
        newsOtherAdapter.setData(dataNewsOther)
    }

    override fun onLoading(isShow: Boolean) {
        // response untuk mengatur loading progress
        Log.d("RJ", "onLoading :: $isShow" )
    }

    override fun onNoData(msg: String?) {
        Log.d("RJ", "onNoData :: $msg" )
    }

    override fun onBadRequest(msg: String?) {
        Log.d("RJ", "onBadRequest :: $msg" )
    }

    override fun onUnauthorized(msg: String?) {
        Log.d("RJ", "onUnauthorized :: $msg" )
    }
}