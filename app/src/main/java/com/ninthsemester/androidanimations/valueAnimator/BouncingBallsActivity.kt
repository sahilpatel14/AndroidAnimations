package com.ninthsemester.androidanimations.valueAnimator

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ninthsemester.androidanimations.R
import com.ninthsemester.androidanimations.common.ShapeHolder
import android.graphics.Shader
import android.graphics.RadialGradient
import android.view.MotionEvent
import android.R.attr.endY
import android.R.attr.startY
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.R.attr.duration
import android.opengl.ETC1.getHeight
import android.R.attr.duration
import android.R.attr.startY
import android.R.attr.endY
import android.animation.*
import android.widget.LinearLayout
import kotlin.math.roundToLong


class BouncingBallsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bouncing_balls)

        findViewById<LinearLayout>(R.id.container).addView(MyAnimationView(this))
    }

    class MyAnimationView(context: Context) : View(context) {

        val balls = ArrayList<ShapeHolder>()

        init {

            // Animate background color
            // Note that setting the background color will automatically invalidate the
            // view, so that the animated color, and the bouncing balls, get redisplayed on
            // every frame of the animation.

            val colorAnim = ObjectAnimator.ofInt(this, "backgroundColor", RED, BLUE)
            colorAnim.duration = 3000L
            colorAnim.setEvaluator(ArgbEvaluator())
            colorAnim.repeatCount = ValueAnimator.INFINITE
            colorAnim.repeatMode = ValueAnimator.REVERSE
            colorAnim.start()

        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {

            if (event?.action != MotionEvent.ACTION_DOWN &&
                    event?.action != MotionEvent.ACTION_MOVE) {
                return false
            }
            val newBall = addBall(event.x, event.y)

            // Bouncing animation with squash and stretch
            val startY = newBall.y as Float
            val endY = height - 50f
            val h = height

            val eventY = event.y


            val duration = (500 * ((h - eventY) / h)).roundToLong()
            val bounceAnim = ObjectAnimator.ofFloat(newBall, "y",startY, endY)
            bounceAnim.duration = duration
            bounceAnim.interpolator = AccelerateInterpolator()

            val squishAnim1 = ObjectAnimator.ofFloat(newBall, "x", newBall.x, newBall.x - 25f)
            squishAnim1.duration = duration/4
            squishAnim1.repeatCount = 1
            squishAnim1.repeatMode = ValueAnimator.REVERSE
            squishAnim1.interpolator = DecelerateInterpolator()

            val squishAnim2 = ObjectAnimator.ofFloat(newBall, "width", newBall.getWidth(), newBall.getWidth() + 50)
            squishAnim2.duration = duration/4
            squishAnim2.repeatCount = 1
            squishAnim2.repeatMode = ValueAnimator.REVERSE
            squishAnim2.interpolator = DecelerateInterpolator()

            val stretchAnim1 = ObjectAnimator.ofFloat(newBall, "y", endY, endY + 25f)
            stretchAnim1.duration = duration/4
            stretchAnim1.repeatCount = 1
            stretchAnim1.interpolator = DecelerateInterpolator()
            stretchAnim1.repeatMode = ValueAnimator.REVERSE

            val stretchAnim2 = ObjectAnimator.ofFloat(newBall, "height",
                    newBall.getHeight(), newBall.getHeight() - 25)
            stretchAnim2.duration = duration / 4
            stretchAnim2.repeatCount = 1
            stretchAnim2.interpolator = DecelerateInterpolator()
            stretchAnim2.repeatMode = ValueAnimator.REVERSE

            val bounceBackAnim = ObjectAnimator.ofFloat(newBall, "y", endY,
                    startY)
            bounceBackAnim.duration = duration
            bounceBackAnim.interpolator = DecelerateInterpolator()


            // Sequence the down/squash&stretch/up animations
            val bouncer = AnimatorSet()
            bouncer.play(bounceAnim).before(squishAnim1)
            bouncer.play(squishAnim1).with(squishAnim2)
            bouncer.play(squishAnim1).with(stretchAnim1)
            bouncer.play(squishAnim1).with(stretchAnim2)
            bouncer.play(bounceBackAnim).after(stretchAnim2)

            //  Fading animation. Remove the ball when animation is done
            val fadeAnim = ObjectAnimator.ofFloat(newBall, "alpha", 1f, 0f)
            fadeAnim.duration = 250
            fadeAnim.addListener(object : AnimatorListenerAdapter() {

                override fun onAnimationEnd(animation: Animator?) {
                    balls.remove((animation as ObjectAnimator).target) }
            })

            val animation = AnimatorSet()
            animation.play(bouncer).before(fadeAnim)

            animation.start()
            return true
        }


        private fun addBall(x : Float, y : Float) : ShapeHolder {

            val circle = OvalShape()
            circle.resize(x, y)
            val drawable = ShapeDrawable(circle)
            val shapeHolder = ShapeHolder(drawable)

            shapeHolder.x = (x - 25f)
            shapeHolder.y = (y - 25f)

            val red = (Math.random() * 255).toInt()
            val green = (Math.random() * 255).toInt()
            val blue = (Math.random() * 255).toInt()

            val color = -0x1000000 or (red shl 16) or (green shl 8) or blue
            val paint = drawable.paint

            val darkColor = -0x1000000 or (red / 4 shl 16) or (green / 4 shl 8) or blue / 4
            val gradient = RadialGradient(37.5f, 12.5f,
                    50f, color, darkColor, Shader.TileMode.CLAMP)
            paint.shader = gradient
            shapeHolder.paint = paint
            balls.add(shapeHolder)
            return shapeHolder
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)

            balls.forEach {

                canvas?.save()
                canvas?.translate(it.x.toFloat(), it.y.toFloat())
                it.shape?.draw(canvas)
                canvas?.restore()
            }
        }

        companion object {

            private val RED = -0x7f80
            private val BLUE = -0x7f7f01
            private val CYAN = -0x7f0001
            private val GREEN = -0x7f0080

        }

    }
}
