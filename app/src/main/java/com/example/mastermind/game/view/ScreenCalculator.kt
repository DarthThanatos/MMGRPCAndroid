package com.example.mastermind.game.view

import android.app.Activity
import android.util.DisplayMetrics

data class ScreenSize(val width: Int, val height: Int)

class ScreenCalculator(activity: Activity){

    val screenSize = getScreenSize(activity)
    val screenWidth = screenSize.width
    val screenHeight = screenSize.height

    private fun getScreenHeight(activity: Activity, totalHeight: Int): Int{
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusHeight = if(resourceId > 0) activity.resources.getDimensionPixelSize(resourceId) else 0
        val styledAttrs = activity.theme.obtainStyledAttributes(intArrayOf(android.R.attr.actionBarSize))
        val actionBarHeight: Int = styledAttrs.getDimension(0, 0.0f).toInt()
        return totalHeight - actionBarHeight - statusHeight
    }

    private fun getScreenSize(activity: Activity): ScreenSize {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        val screenHeight = getScreenHeight(activity, metrics.heightPixels)
        val screenWidth = metrics.widthPixels
        return ScreenSize(screenWidth, screenHeight)
    }

}
