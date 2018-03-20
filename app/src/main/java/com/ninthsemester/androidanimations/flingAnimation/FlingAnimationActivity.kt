package com.ninthsemester.androidanimations.flingAnimation

import android.gesture.GestureLibrary
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import com.ninthsemester.androidanimations.R
import android.view.Display



class FlingAnimationActivity : AppCompatActivity() {

    lateinit var bot : View

    val flingAnimationX : FlingAnimation by lazy(LazyThreadSafetyMode.NONE) {
        FlingAnimation(bot, DynamicAnimation.X).setFriction(1.1f)
    }


    val flingAnimationY : FlingAnimation by lazy(LazyThreadSafetyMode.NONE) {
        FlingAnimation(bot, DynamicAnimation.Y).setFriction(1.1f)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fling_animation)

        bot = findViewById(R.id.bot)

        val gestureDetector = GestureDetector(this, gestureListener)
        bot.setOnTouchListener { _, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }
    }

    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {

            flingAnimationX.setStartVelocity(velocityX)
            flingAnimationY.setStartVelocity(velocityY)

            flingAnimationX.start()
            flingAnimationY.start()

            return true
        }

    }
}
