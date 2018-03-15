package com.ninthsemester.androidanimations.usingObjectAnimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.ninthsemester.androidanimations.R
import kotlinx.android.synthetic.main.activity_sample_anim.*

class UsingObjectAnimatorActivity : AppCompatActivity() {

    private lateinit var mEditText : EditText
    private lateinit var mButton : Button
    private lateinit var mTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_using_object_animator)

        mEditText = findViewById(R.id.et_enter_text)
        mButton = findViewById(R.id.btn_login)
        mTextView = findViewById(R.id.tv_welcome_text)
        mTextView.visibility = View.GONE


        moveViewToX()

        mButton.setOnClickListener {

            if (mEditText.text.isNullOrEmpty()) {
                invalidInputAnimation()
            } else {
                afterClick()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private fun moveViewToX() {

        val animator = ObjectAnimator.ofFloat(mEditText, "translationY", -800f)
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.duration = 1000L

        val scaleXAnimator = ObjectAnimator.ofFloat(mEditText, "scaleX",0f,1.5f)
        scaleXAnimator.duration = 1000L

        val scaleYAnimator = ObjectAnimator.ofFloat(mEditText, "scaleY", 0f, 1.5f)
        scaleYAnimator.duration = 1000L

        val animationSet = AnimatorSet()
        animationSet.play(animator).with(scaleXAnimator)
        animationSet.play(scaleXAnimator).with(scaleYAnimator)
        animationSet.start()

        val btnScaleXAnimator = ObjectAnimator.ofFloat(mButton, "scaleX", 0f,1.5f)
        btnScaleXAnimator.setCurrentFraction(0.3f)
        btnScaleXAnimator.duration = 1000L

        val btnScaleYAnimator = ObjectAnimator.ofFloat(mButton, "scaleY", 0f, 1.5f)
        btnScaleYAnimator.setCurrentFraction(0.3f)
        btnScaleYAnimator.duration = 1000L

        val btnAnimator = ObjectAnimator.ofFloat(mButton, "translationY", -200f)
        btnAnimator.setCurrentFraction(0.3f)
        btnAnimator.duration = 1000L

        val btnAnimationSet = AnimatorSet()
        btnAnimationSet.play(btnScaleXAnimator).with(btnScaleYAnimator)
        btnAnimationSet.play(btnScaleXAnimator).with(btnAnimator)
        btnAnimationSet.start()

    }

    private fun afterClick() {

        val btnScaleXAnimator = ObjectAnimator.ofFloat(mButton, "scaleX",1.5f, 0f)
        btnScaleXAnimator.duration = 700L

        val btnScaleYAnimator = ObjectAnimator.ofFloat(mButton, "scaleY", 1.5f, 0f)
        btnScaleYAnimator.duration = 700L

        val btnTranslateXAnimator = ObjectAnimator.ofFloat(mButton, "translationY",200f)
        btnTranslateXAnimator.duration = 700L

        val btnAnimatorSet = AnimatorSet()
        btnAnimatorSet.play(btnScaleXAnimator).with(btnScaleYAnimator)
        btnAnimatorSet.play(btnTranslateXAnimator).with(btnScaleXAnimator)
        btnAnimatorSet.start()


        val etAlphaAnimator = ObjectAnimator.ofFloat(mEditText, "alpha", 1f,0f)
        etAlphaAnimator.duration = 200L

        val tvScaleXAnimator = ObjectAnimator.ofFloat(mTextView, "scaleX", 0f, 1.5f)
        tvScaleXAnimator.duration = 1000L

        val tvScaleYAnimator = ObjectAnimator.ofFloat(mTextView, "scaleY", 0f, 1.5f)
        tvScaleYAnimator.duration = 1000L

        val fieldAnimatorSet = AnimatorSet()
        fieldAnimatorSet.play(etAlphaAnimator)
        fieldAnimatorSet.play(tvScaleXAnimator).after(etAlphaAnimator)
        fieldAnimatorSet.play(tvScaleXAnimator).with(tvScaleYAnimator)

        fieldAnimatorSet.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                mTextView.visibility = View.VISIBLE
            }
        })

        fieldAnimatorSet.start()
    }

    private fun invalidInputAnimation() {

        mEditText.error = "Field can not be empty."

        val btnTranslateXAnimator = ObjectAnimator.ofFloat(mEditText, "translationX",20f)
        btnTranslateXAnimator.duration = 100L
        btnTranslateXAnimator.repeatCount = 3

        val btnTranslateNegXAnimator = ObjectAnimator.ofFloat(mEditText, "translationX",-20f)
        btnTranslateNegXAnimator.duration = 100L
        btnTranslateNegXAnimator.repeatCount = 3

        val animatorSet = AnimatorSet()
        animatorSet.play(btnTranslateXAnimator).with(btnTranslateNegXAnimator)
        animatorSet.start()
    }
}
