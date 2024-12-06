package com.example.testdkatalis

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.testdkatalis.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding

    companion object {
        fun startActivity(context: Context, url: String) {
            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webview.webViewClient = WebViewClient()
        binding.webview.settings.userAgentString = R.string.app_name.toString()
        binding.webview.settings.loadWithOverviewMode = true
        binding.webview.loadUrl(intent.getStringExtra("url") ?: "")
        binding.webview.settings.javaScriptEnabled = true
        binding.webview.settings.domStorageEnabled = true
    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        // Load the URL
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        // ProgressBar will disappear once page is loaded
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
        }
    }
}