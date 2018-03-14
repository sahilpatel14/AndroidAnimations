package com.ninthsemester.androidanimations.common

import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.drawable.ShapeDrawable
import android.opengl.ETC1.getWidth
import android.R.attr.shape
import android.opengl.ETC1.getHeight



/**
 * Created by sahil-mac on 14/03/18.
 */

/**
 * A data structure that holds a Shape and various properties that can be used to define
 * how the shape is drawn.
 */
class ShapeHolder() {

    var x = 0f
    var y = 0f
    var shape : ShapeDrawable? = null
    var color = 0
    set(value) {
        shape?.paint?.color = value
        field = value
    }

    var gradient : RadialGradient? = null

    var alpha = 1f
    set(value) {
        shape?.alpha = ((alpha * 255f) + .5f).toInt()
    }

    var paint : Paint? = null


    constructor(shapeDrawable: ShapeDrawable) : this() {
        shape = shapeDrawable
    }

    fun getWidth(): Float {
        return shape?.shape!!.width
    }

    fun setWidth(width: Float) {
        val s = shape?.shape
        s?.resize(width, s.height)
    }

    fun getHeight(): Float {
        return shape?.shape!!.height
    }

    fun setHeight(height: Float) {
        val s = shape?.shape
        s?.resize(s.width, height)
    }

}