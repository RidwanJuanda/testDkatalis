package com.example.testdkatalis.ui.detailnews

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.testdkatalis.WebviewActivity
import com.example.testdkatalis.databinding.ActivityDetailNewsBinding
import com.example.testdkatalis.response.model.NewsData
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailNewsActivity : AppCompatActivity(), DetailNewsView {
    private lateinit var binding: ActivityDetailNewsBinding
    private val presenter by lazy { DetailNewsPresenter(this) }
    private var link: String? = null

    companion object {
        fun startActivity(context: Context, slugUrl: String) {
            val intent = Intent(context, DetailNewsActivity::class.java)
            intent.putExtra("slugUrl", slugUrl)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val slugUrl = intent.getStringExtra("slugUrl")
        slugUrl?.let { presenter.getNewsDetail(it) }

        binding.btnInfoLengkap.setOnClickListener {
            if (link?.isNotEmpty() == true)
                WebviewActivity.startActivity(this, "$link")
            else
                Toast.makeText(this, "Link atau Url tidak disertakan oleh penulis", Toast.LENGTH_SHORT).show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showData(data: NewsData?) {
        binding.apply {
            val imageData = data?.images?.shuffled()?.find { true }
            Glide.with(root)
                .load(imageData)
                .into(binding.imgNewsDetail)

            binding.tvTitleNews.text = data?.title ?: "no title"
            if (data?.startDateEvent!!.isNotEmpty()) {
                binding.tvDateNews.text = dateFormat(data.startDateEvent)
            } else
                binding.tvDateNews.text = "16 Desember 2024"

            if (data.location!!.isNotEmpty())
                binding.tvLocationNews.text = data.location
            else
                binding.tvLocationNews.text = "Lokasi tidak ditemukan"

            binding.tvDescNews.text = Html.fromHtml(data.content, Html.FROM_HTML_MODE_LEGACY)

            link = data.spotifyLink
        }
    }


    override fun onGetNewsDetail(data: NewsData?) {
        showData(data)
    }

    private fun dateFormat(input: String): String {
        // Format input
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")

        // Format output
        val outputFormat = SimpleDateFormat("dd MMMM yyyy", Locale("id", "ID"))

        // Parsing dan format ulang
        val date: Date = inputFormat.parse(input)!!
        return outputFormat.format(date)
    }

    override fun onLoading(isShow: Boolean) {
        Log.d("RJ", "onLoading :: $isShow")
    }

    override fun onNoData(msg: String?) {
        Log.d("RJ", "onNoData :: $msg")
    }

    override fun onBadRequest(msg: String?) {
        Log.d("RJ", "onBadRequest :: $msg")
    }

    override fun onUnauthorized(msg: String?) {
        Log.d("RJ", "onUnauthorized :: $msg")
    }
}