package com.ninthsemester.androidanimations.curvedMotion

import android.animation.ObjectAnimator
import android.graphics.Path
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.view.animation.PathInterpolator
import android.widget.ImageView
import com.ninthsemester.androidanimations.R

class CurvedMotionActivity : AppCompatActivity() {

    private lateinit var mImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curved_motion)

        mImageView = findViewById(R.id.iv_android)
        curvePath()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun curvePath() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            val path = Path()
            path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true)
//            val pathInterpolator = PathInterpolator(path)


//            val objectAnimator = ObjectAnimator.ofFloat(mImageView, "translationX", 100f)
            val objectAnimator = ObjectAnimator.ofFloat(mImageView, View.X, View.Y, path)
//            objectAnimator.interpolator = pathInterpolator
            objectAnimator.duration = 1000L
            objectAnimator.start()
        }
    }
}
