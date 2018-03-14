package com.ninthsemester.androidanimations.valueAnimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.Toast
import com.ninthsemester.androidanimations.R

//  https://developer.android.com/guide/topics/graphics/prop-animation.html#value-animator

class ValueAnimatorActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)

        setupValueAnimator()
    }


    private fun setupValueAnimator() {

        val redBall1 : View = findViewById(R.id.red_ball_1)
        val redBall2 : View = findViewById(R.id.red_ball_2)

        val startingPos = 0f
        val endingPos = 500f

        val diff = Math.abs(redBall1.x - redBall2.x)

        val duration = 600L
        val repeatForTimes = 5
        var currentRepetition = 0

        val updateListener = ValueAnimator.AnimatorUpdateListener {
            Log.d(TAG, "Updating Property with animated Value : ${it.animatedValue}")
            redBall1.translationY = it.animatedValue as Float
            redBall2.translationY = it.animatedValue as Float - diff
        }

        val valueAnimatorTranslateX = ValueAnimator.ofFloat(startingPos, endingPos)
        valueAnimatorTranslateX.duration = duration
        valueAnimatorTranslateX.addUpdateListener(updateListener)


        val valueAnimatorTranslateBack = ValueAnimator.ofFloat(endingPos, startingPos)
        valueAnimatorTranslateBack.duration = duration
        valueAnimatorTranslateBack.addUpdateListener(updateListener)

        val animatorSet = AnimatorSet()
        animatorSet.play(valueAnimatorTranslateBack).after(valueAnimatorTranslateX)
//        animatorSet.play(valueAnimatorTranslateX)
//        animatorSet.interpolator = BounceInterpolator()
        animatorSet.interpolator = AccelerateDecelerateInterpolator()

        animatorSet.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {
                currentRepetition += 1
            }

            override fun onAnimationEnd(animation: Animator?) {

                if (currentRepetition < repeatForTimes)
                    animation?.start()
            }

        })

        animatorSet.start()

    }

    companion object {

        const val TAG = "AA_ValueAnimator : "

    }
}
