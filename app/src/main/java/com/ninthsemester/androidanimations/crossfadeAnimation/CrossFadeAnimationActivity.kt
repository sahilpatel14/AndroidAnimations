package com.ninthsemester.androidanimations.crossfadeAnimation

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.ninthsemester.androidanimations.R


/**
 * There are three common animations while showing or hiding a view.
 * 1. CrossFade Animation
 * 2. Card flip Animation
 * 3. Circular Reveal Animation
 *
 * This one is an example of loadTextWithCrossFade Animation.
 *
 * https://developer.android.com/training/animation/reveal-or-hide-view.html#Crossfade
 */
class CrossFadeAnimationActivity : AppCompatActivity() {


    //  Create member variables for the views we want to loadTextWithCrossFade.
    //  Progress bar and textView in this case.
    private lateinit var mContentView : View
    private lateinit var mLoadingView : View

    private var mShortAnimationDuration : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crossfade_animation)

        mContentView = findViewById(R.id.content)
        mLoadingView = findViewById(R.id.loading_spinner)

        //  For the view that is being faded in, set its visibility to GONE
        mContentView.visibility = View.GONE


        //  Setting the duration of fade animation
        mShortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()


        //  Showing progress dialog for three seconds and then showing the text.
        Handler().postDelayed({
            loadTextWithCrossFade()
        }, 3000)

    }

    private fun loadTextWithCrossFade() {

        //  For the view that is fading in (TextView) set the alpha to 0 and visibility to VISIBLE
        //  (It was set to GONE before )
        mContentView.alpha = 0f
        mContentView.visibility = View.VISIBLE


        //  For the view that is fading in (TextView) animate to alpha to go from 0 to 1
        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null)


        //  For the view that is fading out (progressBar) animate the alpha from 1 to 0
        //  When the animation is done, set the visibility to GONE
        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {

                        //  When animation ends, set Visibility to gone.
                        mLoadingView.visibility = View.GONE
                    }
                })

    }
}
