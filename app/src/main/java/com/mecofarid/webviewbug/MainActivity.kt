package com.mecofarid.webviewbug

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val PAGE_MAX_COUNT = 15
    private var loadedPageCount = 0
    private var attemptCount = 0
    private val link = "http://x.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (page in 0..PAGE_MAX_COUNT){
            WebView(this).also{ webView->
                webView.addListener()
                webView.loadUrl(link)
            }
        }

        findViewById<WebView>(R.id.webview).loadUrl(link)
    }

    private fun WebView.addListener(){
        attemptCount++
        Log.d("TAG", "onPageFinished: attempt $attemptCount")
        this.webViewClient = object: WebViewClient(){
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                loadedPageCount++
                Log.d("TAG", "onPageFinished: $loadedPageCount")
            }
        }
    }
}