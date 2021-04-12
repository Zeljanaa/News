package com.example.newsapp.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.example.newsapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber

/**
 * Contains all functions that appear to be repetitive through out the project
 * e.g Toasts, SnackBar, Alert Dialogs
 * */

/**
 * Toast message
 * e.g Instead of writing in the program whenever you want to toast a message
 * ->  Toast.makeText(context, "Toast Message", Toast.LENGTH_SHORT).show()
 *     you will be writing
 * ->  requireContext.toast("Toast Message")
 * same to the rest
 * */
fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).apply {
        show()
    }
}

/**
 * SnackBar
 * */
fun View.snackBar(msg: String) {
    Snackbar.make(this, msg, Snackbar.LENGTH_LONG).also { snackBar ->
        snackBar.setAction("ok") {
            snackBar.dismiss()
        }
    }.show()
}

/**
 * Check if edit text is empty
 * returns true if edit text is empty and false if its not
 * */

fun isEditEmpty(string: String): Boolean {
    return string == ""
}

/**
 * Color extraction from image
 * */
fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

fun dynamicImageColorExtraction(
    context: Context,
    imageView: Bitmap,
    linearLayout: LinearLayout
) {
    try {
        val vibrantSwatch = createPaletteSync(imageView).darkMutedSwatch
        linearLayout.setBackgroundColor(
            (vibrantSwatch?.rgb
                ?: ContextCompat.getColor(context, R.color.white))
        )
    } catch (e: Exception) {
        Timber.d("NewsApp : could not extract colors")
    }
}