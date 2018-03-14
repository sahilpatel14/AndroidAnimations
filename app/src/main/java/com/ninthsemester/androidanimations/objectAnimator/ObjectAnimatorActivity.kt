package com.ninthsemester.androidanimations.objectAnimator

import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ninthsemester.androidanimations.R

class ObjectAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        setupAnimation()
    }


    private fun setupAnimation() {

        val ball = findViewById<View>(R.id.red_ball_1)

        val animation = ObjectAnimator.ofFloat(ball, "translationY", 300f,900f,500f,200f)

        animation.duration = 3000L
        animation.start()

    }
}
