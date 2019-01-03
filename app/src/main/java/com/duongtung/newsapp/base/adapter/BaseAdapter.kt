 package com.duongtung.newsapp.base.adapter

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class BaseAdapter<T,VB : ViewDataBinding> : RecyclerView.Adapter<BaseAdapter.BaseViewHolder<T,VB>>() {
    private var list : MutableList<T> = arrayListOf()
    private lateinit var binding : VB

    fun setList(list : MutableList<T>){
        this.list = list
        notifyDataSetChanged()
    }

    fun addElement(t:T){
        list.add(t)
        notifyDataSetChanged()
    }

    fun addElementPosition(t:T,i:Int){
        list.add(i,t)
        notifyDataSetChanged()
    }

    abstract fun getLayoutId() : Int
    abstract fun getIdVariable() : Int

    override fun onCreateViewHolder(viewHolder: ViewGroup, i: Int): BaseViewHolder<T, VB> {
        binding = DataBindingUtil.inflate(LayoutInflater.from(viewHolder.context),getLayoutId(),viewHolder,false)
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder : BaseViewHolder<T, VB>, i : Int) {
        viewHolder.setVariable(getIdVariable(), list[i])
    }

    class BaseViewHolder<T,VB : ViewDataBinding>(private var binding : VB) : RecyclerView.ViewHolder(binding.root) {
            fun setVariable(id : Int, t: T){
                binding.setVariable(id,t)
            }
    }
    override fun getItemCount() = list.size
}