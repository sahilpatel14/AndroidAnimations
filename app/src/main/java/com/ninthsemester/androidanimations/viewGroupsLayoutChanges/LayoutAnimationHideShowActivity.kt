package com.ninthsemester.androidanimations.viewGroupsLayoutChanges

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import com.ninthsemester.androidanimations.R
import android.animation.ObjectAnimator
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.LayoutTransition
import android.animation.PropertyValuesHolder
import android.animation.Keyframe



/**
 * This application demonstrates how to use LayoutTransition to automate transition animations
 * as items are hidden or shown in a container.
 */
class LayoutAnimationHideShowActivity : AppCompatActivity() {

    private val numButtons = 1
    lateinit var container : ViewGroup
    private lateinit var mTransition : LayoutTransition

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation_hide_show)


        val hideGoneCb = findViewById<CheckBox>(R.id.hideGoneCb)

        container = LinearLayout(this)
        container.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        // Add a slew of buttons to the container. We won't add any more buttons at runtime, but
        // will just show/hide the buttons we've already created
        var i = 0
        while (i < 4) {

            val newButton = Button(this)
            newButton.text = i.toString()
            container.addView(newButton)

            newButton.setOnClickListener {
                it.visibility = if(hideGoneCb.isChecked) { View.GONE } else { View.VISIBLE }
            }
            i++
        }

        resetTransition()

        val parent = findViewById<ViewGroup>(R.id.parent)
        parent.addView(container)

        val addButton = findViewById<Button>(R.id.addNewButton)
        addButton.setOnClickListener {

            for (i in 0 until container.childCount) {
                val view = container.getChildAt(i) as View
                view.visibility = View.VISIBLE
            }
        }

        val customAnimCb = findViewById<CheckBox>(R.id.customAnimationCb)
        customAnimCb.setOnCheckedChangeListener { buttonView, isChecked ->

            val duration = when(isChecked){

                true -> {
                    mTransition.setStagger(LayoutTransition.CHANGE_APPEARING, 30)
                    mTransition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30)
                    setupCustomAnimations()
                    500L
                }
                false -> {
                    resetTransition()
                    300L
                }
            }
            mTransition.setDuration(duration)
        }
    }

    private fun resetTransition() {
        mTransition = LayoutTransition()
        container.layoutTransition = mTransition
    }

//    @SuppressLint("ObjectAnimatorBinding")
    private fun setupCustomAnimations() {


        // Changing while Adding
        val pvhLeft = PropertyValuesHolder.ofInt("left",0, 1)
        val pvhTop = PropertyValuesHolder.ofInt("top",0, 1)
        val pvhRight = PropertyValuesHolder.ofInt("right",0, 1)
        val pvhBottom = PropertyValuesHolder.ofInt("bottom",0,1)

        val scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f)
        val scaleY = PropertyValuesHolder.ofFloat("scaleY",1f, 0f, 1f)

        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(
                container, pvhLeft, pvhTop, pvhRight, pvhBottom, scaleX, scaleY)

        mTransition.setAnimator(LayoutTransition.CHANGE_APPEARING, objectAnimator)
        objectAnimator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {

                val view = (animation as ObjectAnimator).target as View
                view.scaleX = 1f
                view.scaleY = 1f
            }
        })


    // Changing while Removing
    val kf0 = Keyframe.ofFloat(0f, 0f)
    val kf1 = Keyframe.ofFloat(.9999f, 360f)
    val kf2 = Keyframe.ofFloat(1f, 0f)
    val pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2)
    val changeOut = ObjectAnimator.ofPropertyValuesHolder(
            container, pvhLeft, pvhTop, pvhRight, pvhBottom, pvhRotation).setDuration(mTransition.getDuration(LayoutTransition.CHANGE_DISAPPEARING))
    mTransition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, changeOut)
    changeOut.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(anim: Animator) {
            val view = (anim as ObjectAnimator).target as View
            view.rotation = 0f
        }
    })
    // Adding
    val animIn = ObjectAnimator.ofFloat(container, "rotationY", 90f, 0f).setDuration(mTransition.getDuration(LayoutTransition.APPEARING))
    mTransition.setAnimator(LayoutTransition.APPEARING, animIn)
    animIn.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(anim: Animator) {
            val view = (anim as ObjectAnimator).target as View
            view.rotationY = 0f
        }
    })
    // Removing
    val animOut = ObjectAnimator.ofFloat(container, "rotationX", 0f, 90f).setDuration(mTransition.getDuration(LayoutTransition.DISAPPEARING))
    mTransition.setAnimator(LayoutTransition.DISAPPEARING, animOut)
    animOut.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(anim: Animator) {
            val view = (anim as ObjectAnimator).target as View
            view.rotationX = 0f
        }
    })
        
    }
}
