package com.xw.drawlayouttest

import android.graphics.Color
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerListener: MyDrawerListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mToolbar.inflateMenu(R.menu.main)

        mToolbar.setNavigationIcon(R.mipmap.ic_launcher)
        mToolbar.setNavigationOnClickListener {
            mDrawLayout.openDrawer(Gravity.START)
        }

        mToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.mSettingMenu -> {
                    Toast.makeText(this, "settings", Toast.LENGTH_LONG).show()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        mDrawerListener = MyDrawerListener()
        mDrawLayout.addDrawerListener(mDrawerListener)
        mDrawLayout.setScrimColor(Color.RED)
        mNav.itemTextColor = null
        mNav.itemIconTintList = null
        //菜单点击事件
        mNav.setNavigationItemSelectedListener {
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onDestroy() {
        mDrawLayout.removeDrawerListener(mDrawerListener)
        super.onDestroy()
    }

    private class MyDrawerListener : DrawerLayout.DrawerListener {
        override fun onDrawerStateChanged(newState: Int) {
            //滑动的状态发生改变时调用
        }

        override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
            //滑动的过程中调用的方法

            val scale = 1 - slideOffset//1~0
            drawerView.alpha = 1 - scale
            val leftScale = (1 - 0.3 * scale).toFloat()
            drawerView.scaleX = leftScale//1~0.7
            drawerView.scaleY = leftScale//1~0.7


        }

        override fun onDrawerClosed(drawerView: View) {
            //关闭时调用的方法
        }

        override fun onDrawerOpened(drawerView: View) {
            //打开时调用的方法
        }
    }
}
