package com.ninthsemester.androidanimations

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.ninthsemester.androidanimations.objectAnimator.ObjectAnimatorActivity
import com.ninthsemester.androidanimations.valueAnimator.BouncingBallsActivity
import com.ninthsemester.androidanimations.valueAnimator.ValueAnimatorActivity
import com.ninthsemester.androidanimations.viewGroupsLayoutChanges.LayoutAnimationDefaultActivity
import com.ninthsemester.androidanimations.viewGroupsLayoutChanges.LayoutAnimationHideShowActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {

            R.id.menu_bouncing_balls -> openWindow(BouncingBallsActivity::class.java)
            R.id.menu_value_animator -> openWindow(ValueAnimatorActivity::class.java)
            R.id.menu_object_animator -> openWindow(ObjectAnimatorActivity::class.java)
            R.id.menu_custom_layout_animator -> openWindow(LayoutAnimationDefaultActivity::class.java)
            R.id.menu_default_layout_animator -> openWindow(LayoutAnimationHideShowActivity::class.java)

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun <T : AppCompatActivity>openWindow(classType : Class<T>) : Boolean{

        val intent = Intent(this, classType)
        startActivity(intent)

        return true
    }
}
