package com.duongtung.newsapp.ui.activity.main

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.JsonReader
import android.util.Log
import android.view.Gravity
import com.duongtung.newsapp.R
import com.duongtung.newsapp.base.BaseActivity
import com.duongtung.newsapp.databinding.MainActivityBinding
import com.google.gson.Gson
import com.google.gson.TypeAdapter
import kotlinx.android.synthetic.main.nav_header.view.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.StringReader


class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {
    private val permission = arrayOf<String>(
        Manifest.permission.GET_ACCOUNTS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.INTERNET
    )
    private val PERMISSIONALL = 1
    private val OVERLAY_REQUEST = 2
    override fun setBindingViewModel() {
        if (!hasPermissions(context = this,  permissions = *permission)) {
            ActivityCompat.requestPermissions(this, permission, PERMISSIONALL)
        }

        binding.viewModel = viewModel

        viewModel.getActionBar().observe(this, Observer { data ->
            binding.actionBar.data = data
        })

        binding.actionBar.leftImageButton.setOnClickListener {
            if (binding.drawer.isDrawerOpen(Gravity.START))
                binding.drawer.closeDrawer(Gravity.START)
            else
                binding.drawer.openDrawer(Gravity.START)
        }

        viewModel.getAccount(context = this).observe(this, Observer { name ->
            binding.navView.nav_header_textView.text = name
        })

        viewModel.getMyList().observe(this, Observer { list ->
            viewModel.adapter.setList(list =  list!!.toMutableList())
            Log.d("duongtung1991", list!![0].image.mediaDetails.sizes.thumbnail.sourceUrl)
        })
//        viewModel.getList24h().observe(this, Observer { list ->
//            viewModel.adapter.setList(list = list!!.rss.channel.item.toMutableList())
//            val des = list!!.rss.channel.item[0].description
//            val doc = Jsoup.parse(des.trim())
//            val src = doc.getElementsByTag("img")[0].attr("src")
//            val title = doc.getElementsByTag("img")[0].attr("title")
//        })

//        requestSystemAlertPermission(this, null, OVERLAY_REQUEST)
    }

    override fun getViewMode() = MainViewModel::class.java

    override fun getLayout() = R.layout.main_activity

    fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PERMISSIONALL) {
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun requestSystemAlertPermission(context: Activity?, fragment: Fragment?, requestCode: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return
        val packageName = if (context == null) fragment!!.activity!!.packageName else context.packageName
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
        if (fragment != null && !Settings.canDrawOverlays(this@MainActivity))
            fragment!!.startActivityForResult(intent, requestCode)
        else if (!Settings.canDrawOverlays(this@MainActivity)) {
            context!!.startActivityForResult(intent, requestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONALL) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //granted
            } else {
                //not granted
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
