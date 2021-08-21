package com.kngrck.fooddeliveryfinal.utils

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun showErrorToast(context: Context, text: String = "Failed to retrieve data!") {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}

