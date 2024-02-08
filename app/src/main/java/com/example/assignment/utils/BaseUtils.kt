package com.example.assignment.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import com.example.assignment.R
import java.security.AccessController.getContext

class BaseUtils {

    companion object {

        fun makeStatusBarTransparent(context: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                context.window.apply {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        decorView.systemUiVisibility =
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    } else {
                        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    }
                    statusBarColor = Color.TRANSPARENT
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        fun makeItemSelected(
            context: Context,
            imageView: ImageView,
            textView: TextView,
            linearLayout: LinearLayout
        ) {
            textView.setTextColor(textView.context.getColor(R.color.black))
            linearLayout.setBackgroundResource(R.drawable.background_selected)
            imageView.setColorFilter(getColor(context,R.color.orangeColor))

        }


        @RequiresApi(Build.VERSION_CODES.M)
        fun makeItemNotSelected(
            context: Context,
            imageView: ImageView,
            textView: TextView,
            linearLayout: LinearLayout
        ) {
            textView.setTextColor(textView.context.getColor(R.color.white))
            linearLayout.setBackgroundResource(R.drawable.background_not_selected)
            imageView.setColorFilter(getColor(context,R.color.white))
        }


        fun unSelectLayoutIcon(context: Context, imageView: ImageView) {
            imageView.setColorFilter(
                ContextCompat.getColor(context, R.color.darkGrey),
                PorterDuff.Mode.SRC_IN
            )
        }

        fun selectLayoutIcon(context: Context, imageView: ImageView) {
            imageView.setColorFilter(
                ContextCompat.getColor(context, R.color.white),
                PorterDuff.Mode.SRC_IN
            )
        }


    }


}