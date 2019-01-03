package com.duongtung.newsapp.base.utils

import android.graphics.Color
import com.duongtung.newsapp.R
import com.duongtung.newsapp.data.actionbar.ActionBar

object DataUtilsApplication{
    @JvmStatic
    fun CreateActionBarHome(title : String) : ActionBar{
        var actionBar = ActionBar(title = title, leftButtonImage =  R.drawable.ic_dehaze_black_24dp,
            rightButtonImage = 0, backgroundActionBar = Color.WHITE)
        return actionBar
    }
}