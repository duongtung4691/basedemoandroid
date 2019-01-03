package com.duongtung.newsapp.ui.adapter

import com.duongtung.newsapp.BR
import com.duongtung.newsapp.R
import com.duongtung.newsapp.base.adapter.BaseAdapter
import com.duongtung.newsapp.data.baomoi.ItemBaoMoi
import com.duongtung.newsapp.data.data24h.ItemNew
import com.duongtung.newsapp.databinding.ItemBaoMoiBinding
import com.duongtung.newsapp.databinding.ItemViewBinding

class ItemAdapter : BaseAdapter<ItemBaoMoi,ItemBaoMoiBinding>() {

    override fun getLayoutId()= R.layout.item_bao_moi

    override fun getIdVariable()= BR.item

}