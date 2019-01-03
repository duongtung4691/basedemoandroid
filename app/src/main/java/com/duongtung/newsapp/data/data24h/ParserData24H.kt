package com.duongtung.newsapp.data.data24h

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class ParserData24H @SuppressLint("SetJavaScriptEnabled", "ObsoleteSdkInt") constructor(context: Context, url: String) {

    private var webView: WebView = WebView(context)
    private var onload  : OnLoadWebview
        get() = onload
        set(value) {onload = value}


    init {
        if (onload!=null)onload.onPageLoading()
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.javaScriptEnabled = true
        webView.settings.loadsImagesAutomatically = true
        val userAgent = webView.settings.userAgentString
        webView.settings.allowFileAccessFromFileURLs = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        } else {
            webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        }
        webView.settings.userAgentString = userAgent
        webView.loadUrl(url)
        webView.addJavascriptInterface(MyJavaScriptInterface(onload), "HtmlHandler")
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                webView.loadUrl(
                    "javascript:window.HtmlHandler.handleHtml" +
                            "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');"
                )

            }
        }
    }


    private class MyJavaScriptInterface(private var onload  : OnLoadWebview?) {
        var load: Boolean = true

        @JavascriptInterface
        fun handleHtml(html: String) {
            // Use jsoup on this String here to search for your content.
            if (load) {
                val doc = Jsoup.parse(html)
                if (doc.select("div.sbNws").first() != null) doc.select("div.sbNws").first().remove()
                if (doc.select("div.mnWrap").first() != null) doc.select("div.mnWrap").first().remove()
                if (doc.select("span.sbNws").first() != null) doc.select("span.sbNws").first().remove()
                if (doc.select("div.eventList").first() != null) doc.select("div.eventList").first().remove()
                if (doc.select("div.flRt").first() != null) doc.select("div.flRt").first().remove()
                if (doc.select("div.bv-lq").first() != null) doc.select("div.bv-lq").first().remove()
                if (doc.select("div.mrT15.mrB15").first() != null) doc.select("div.mrT15.mrB15").first().remove()
                if (doc.select("div[id*=div_inpage_banner]").first() != null) doc.select("div[id*=div_inpage_banner]").first().remove()
                if (doc.select("div.nwsTthEvt.nwsTthEvtD.mrT20.blk") != null) doc.select("div.nwsTthEvt.nwsTthEvtD.mrT20.blk").remove()
                if (doc.select("section.blk.mgt10.tcbds") != null) doc.select("section.blk.mgt10.tcbds").remove()
                if (doc.select("section.tttd.blk.mrT15.mrB15") != null) doc.select("section.tttd.blk.mrT15.mrB15").remove()
                if (doc.select("section.bnr.clF.mrB10") != null) doc.select("section.bnr.clF.mrB10").remove()
                if (doc.select("section.bnr.clF.mrT5") != null) doc.select("section.bnr.clF.mrT5").remove()
                if (doc.select("div[id*=topnav]") != null) doc.select("div[id*=topnav]").remove()
                if (doc.select("div[id*=banner_on_page_container]") != null) doc.select("div[id*=banner_on_page_container]").remove()
                if (doc.select("div.icoSocial.txtRt.blk.mrT5") != null) doc.select("div.icoSocial.txtRt.blk.mrT5").remove()
                if (doc.select("footer") != null) doc.select("footer").remove()
                if (doc.select("section.bnr.clF") != null) doc.select("section.bnr.clF").remove()

                if (onload!=null)onload!!.onPageLoaded(doc)

//            if (element.select("main.div_news_content_3")!=null)
//            webView.post {
//                webView.loadDataWithBaseURL(
//                    url,
//                    doc.html(),
//                    "text/html",
//                    "UTF-8",
//                    null
//                )
//            }
                load = false
            }

        }
    }


    interface OnLoadWebview{
        fun onPageLoading()
        fun onPageLoaded(doc : Document)
    }
}