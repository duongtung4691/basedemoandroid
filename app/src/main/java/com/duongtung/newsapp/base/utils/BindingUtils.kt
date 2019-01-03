package com.duongtung.newsapp.base.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.jsoup.Jsoup

object  BindingUtils{

    @BindingAdapter("imageResource")
    @JvmStatic fun ImageView.setImageResoucre( res : Int){
        if (res>0)
        this.setImageResource(res)
    }
    @BindingAdapter("adapter")
    @JvmStatic fun RecyclerView.setAdapter(adapter : RecyclerView.Adapter<*>){
        this.adapter = adapter
    }
    @BindingAdapter("textNews")
    @JvmStatic fun TextView.setText(text : String){
        val doc = Jsoup.parse(text.trim())
        val title = doc.getElementsByTag("img")[0].attr("title")
        this.text = Html.fromHtml(title)
    }

    @BindingAdapter("setImage")
    @JvmStatic fun setImage(view : ImageView,text :String){
        val doc = Jsoup.parse(text.trim())
        val src = doc.getElementsByTag("img")[0].attr("src")
        Glide.with(view).load(src).into(view)
    }

    @BindingAdapter("imageUrl")
    @JvmStatic fun ImageView.setImageResoucre( res : String){
       Glide.with(this).load(res).into(this)
    }

    @BindingAdapter("textHtml")
    @JvmStatic fun setTextHtml(view : TextView,text : String){
        view.text = Html.fromHtml("<b>$text</b>")
    }

}