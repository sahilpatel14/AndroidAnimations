package com.ninthsemester.androidanimations.circularRevealAnimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.TargetApi
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import com.ninthsemester.androidanimations.R
import kotlinx.android.synthetic.main.activity_circular_reveal.*


/**
 * Reveal animations are used to provide continuity when we show or hide
 * a group of UI elements
 *
 * ViewAnimationUtils.createCircularReveal() method enables you to animate a clipping circle to reveal or hide a view.
 * This animation is provided in the ViewAnimationUtils class, which is available for Android 5.0 (API level 21) and higher.
 *
 * https://developer.android.com/training/animation/reveal-or-hide-view.html#Reveal
 *
 */
class CircularRevealActivity : AppCompatActivity() {

    private lateinit var viewToBeDisplayed : View
    private lateinit var loadingView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_reveal)

        viewToBeDisplayed = findViewById(R.id.content)
        loadingView = findViewById(R.id.loading_spinner)

        //  Initially the view would be hidden
        viewToBeDisplayed.visibility = View.INVISIBLE

        Handler().postDelayed({
            circularRevealAnimation()
        }, 3000)

        findViewById<View>(R.id.some_text).setOnClickListener {
            circularRevealGoInvisible()
        }

        loadingView.setOnClickListener {
            circularRevealAnimation()
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun circularRevealGoInvisible() {

        // Check if the runtime version is at least Lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //  Getting the center for the clipping circle
            val cx = viewToBeDisplayed.width / 2
            val cy = viewToBeDisplayed.height / 2

            //  Getting the final radius for the clipping circle
            val initialRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            //  creating an animator for the view
            val animator = ViewAnimationUtils.createCircularReveal(viewToBeDisplayed, cx, cy, initialRadius, 0f)

            animator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    viewToBeDisplayed.visibility = View.INVISIBLE
                    loadingView.visibility = View.VISIBLE
                }

            })
            animator.start()
        }
        else {
            viewToBeDisplayed.visibility = View.INVISIBLE
            loadingView.visibility = View.VISIBLE
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun circularRevealAnimation() {

        loadingView.visibility = View.GONE
        // Check if the runtime version is at least Lollipop
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            //  Getting the center for the clipping circle
            val cx = viewToBeDisplayed.width / 2
            val cy = viewToBeDisplayed.height / 2

            //  Getting the final radius for the clipping circle
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

            //  creating an animator for the view
            val animator = ViewAnimationUtils.createCircularReveal(viewToBeDisplayed, cx, cy, 0f, finalRadius)

            viewToBeDisplayed.visibility = View.VISIBLE
            animator.start()
        }
        else {
            viewToBeDisplayed.visibility = View.VISIBLE
        }

    }
}
