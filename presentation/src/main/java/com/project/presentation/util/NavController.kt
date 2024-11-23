package com.project.presentation.util

import android.view.View
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.project.presentation.MainActivity
import com.project.presentation.R


internal fun NavController.navigateWithClear(@IdRes resId: Int) {
    val navigationOptions = NavOptions.Builder()
        .setPopUpTo(destinationId = resId, inclusive = true, saveState = true)
        .setLaunchSingleTop(true)
        .build()

    navigate(resId, null, navigationOptions)
}


fun FragmentActivity.invisible() {
    (this as? MainActivity)?.let {
        val layout = findViewById<ConstraintLayout>(R.id.cl_main_navigation)
        layout.visibility = View.GONE
    }
}

fun FragmentActivity.visible() {
    (this as? MainActivity)?.let {
        val layout = findViewById<ConstraintLayout>(R.id.cl_main_navigation)
        layout.visibility = View.VISIBLE
    }
}