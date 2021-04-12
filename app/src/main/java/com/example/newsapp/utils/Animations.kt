package com.example.newsapp.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController

/**
 * Animate views
 * */
fun animate(context: Context, resId: Int): Animation {
    return AnimationUtils.loadAnimation(context, resId)
}

fun layoutAnimation(context: Context, resId: Int): LayoutAnimationController {
    return AnimationUtils.loadLayoutAnimation(context, resId)
}