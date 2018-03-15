package com.ninthsemester.androidanimations.viewGroupsLayoutChanges

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import com.ninthsemester.androidanimations.R

class LayoutAnimationDefaultActivity : AppCompatActivity() {

    var numButtons = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_animation_default)

        val container = findViewById<LinearLayout>(R.id.container)
        val addButton = findViewById<Button>(R.id.addNewButton)
        addButton.setOnClickListener {

            val newButton = Button(this)
            newButton.text = numButtons++.toString()
            newButton.setOnClickListener {
                container.removeView(it)
            }

            container.addView(newButton, Math.min(1, container.childCount))
        }


    }
}
