package com.ninthsemester.androidanimations.cardFlipAnimation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninthsemester.androidanimations.R

/**
 * This example is for the card flip activity.
 * So far we have created four objectAnimator sets in
 * animator res folder.
 */

class CardFlipAnimationActivity : AppCompatActivity() {

    //  A flag to check if back is visible or front, of the card.
    private var mShowingBack : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_flip_animation)

        //  Loading CardFrontFragment to the container
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.container, CardFrontFragment())
                    .commit()
        }

        findViewById<View>(R.id.container).setOnClickListener { flipCard() }
    }


    private fun  flipCard() {


        if (mShowingBack) {
            //  Flip to front
            supportFragmentManager.popBackStack()
            mShowingBack = false
            return
        }

        //  Flip to back
        mShowingBack = true


        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        supportFragmentManager
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.card_flip_right_in,
                        R.animator.card_flip_right_out,
                        R.animator.card_flip_left_in,
                        R.animator.card_flip_left_out)


                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, CardBackFragment())

                // Add this transaction to the back stack, allowing users to press
                // Back to get to the front of the card.
                .addToBackStack(null)
                .commit()

    }


    //  Creating two fragments for front and back of card.
    class CardFrontFragment : Fragment(){

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            return inflater?.inflate(R.layout.fragment_card_front, container, false)
        }
    }

    class CardBackFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

            return inflater?.inflate(R.layout.fragment_card_back, container, false)
        }
    }



}
