package com.kngrck.fooddeliveryfinal.utils

import android.content.Context
import android.widget.Toast

fun showErrorToast(context: Context, text: String = "Failed to retrieve data!") {
    val toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast.show()
}
