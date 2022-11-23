package com.qubits.fw3.coreui.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.qubits.fw3.tb.coreui.R

class FocusView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val paint: Paint = Paint()
    private var squarePaint: Paint = Paint()
    private var cornerPaint: Paint = Paint()
    private var bitmap: Bitmap? = null
    private var layer: Canvas? = null
    private var topV: Int = 0
    private var bottomV: Int = 0
    private var leftV: Int = 0
    private var rightV: Int = 0

    init {
        val backgroundAlpha = 0.8
        paint.color = ColorUtils.setAlphaComponent(
            ContextCompat.getColor(
                this.context,
                R.color.bg_blur
            ), (255 * backgroundAlpha).toInt()
        )

        squarePaint.color = ContextCompat.getColor(this.context, android.R.color.transparent)
        squarePaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmap == null) {
            configureBitmap()
        }

        val h7: Int = (height/13)

        topV = h7*3
        bottomV = h7*7
        leftV = (width -h7*4)/2
        rightV = width - leftV

        //draw background
        layer?.drawRect(0.0f, 0.0f, width.toFloat(), height.toFloat(), paint)

        val cornerRadius = convertDpToPixel(context, resources.getDimension(R.dimen.corner_radius_24dp))
        val cornerRadiusIn = convertDpToPixel(context, resources.getDimension(R.dimen.corner_radius_48dp))

        layer?.drawRoundRect(
            leftV.toFloat(), //oneSix,
            topV.toFloat(), //top,
            rightV.toFloat(), //(width.toFloat() - oneSix),
            bottomV.toFloat(), //bottom,
            cornerRadius,
            cornerRadius,
            squarePaint
        )

        //draw bitmap
        cornerPaint.style = Paint.Style.STROKE
        cornerPaint.strokeWidth = (leftV*0.10).toFloat()
        cornerPaint.color = ContextCompat.getColor(context, R.color.colorPrimary)

        cornerPaint.strokeJoin = Paint.Join.MITER

        canvas.drawBitmap(bitmap!!, 0.0f, 0.0f, paint)

        canvas.drawPath(
            createCornersPath(
                left = leftV.toFloat(), //oneSix,//(width*0.075).toFloat(),
                top = topV.toFloat(), //top,
                right = rightV.toFloat(), //(width.toFloat() - oneSix),//(width*0.925).toFloat(),
                bottom = bottomV.toFloat(), //bottom,
                cornerRadius =  cornerRadiusIn,
                cornerLength = (leftV*0.15).toFloat(),//cornerRadiusLength
            ), cornerPaint
        )
    }



    private fun createCornersPath(
        left: Float,
        top: Float,
        right: Float,
        bottom: Float,
        cornerRadius: Float,
        cornerLength: Float
    ): Path {
        val path = Path()

        // top left
        path.moveTo(left, (top + cornerRadius))
        path.arcTo(
            RectF(left, top, left + cornerRadius, top + cornerRadius),
            180f,
            90f,
            true
        )

        path.moveTo(left + (cornerRadius / 2f), top)
        path.lineTo(left + (cornerRadius / 2f) + cornerLength, top)

        path.moveTo(left, top + (cornerRadius / 2f))
        path.lineTo(left, top + (cornerRadius / 2f) + cornerLength)

        // top right
        path.moveTo(right - cornerRadius, top)
        path.arcTo(
            RectF(
                (right - cornerRadius).toFloat(),
                top.toFloat(),
                right.toFloat(),
                (top + cornerRadius).toFloat()
            ),
            270f,
            90f,
            true
        )

        path.moveTo(right - (cornerRadius / 2f), top)
        path.lineTo(right - (cornerRadius / 2f) - cornerLength, top)

        path.moveTo(right, top + (cornerRadius / 2f))
        path.lineTo(right, top + (cornerRadius / 2f) + cornerLength)

        // bottom left
        path.moveTo(left, bottom - cornerRadius)
        path.arcTo(
            RectF(left, bottom - cornerRadius, left+cornerRadius, bottom),
            90f,
            90f,
            true
        )

        path.moveTo(left + (cornerRadius / 2f), bottom)
        path.lineTo(left + (cornerRadius / 2f) + cornerLength, bottom)

        path.moveTo(left, bottom - (cornerRadius / 2f))
        path.lineTo(left, bottom - (cornerRadius / 2f) - cornerLength)

        // bottom right
        path.moveTo(left, bottom - cornerRadius)
        path.arcTo(
            RectF(right - cornerRadius, bottom - cornerRadius, right, bottom),
            0f,
            90f,
            true
        )

        path.moveTo(right - (cornerRadius / 2f), bottom)
        path.lineTo(right - (cornerRadius / 2f) - cornerLength, bottom)

        path.moveTo(right, bottom - (cornerRadius / 2f))
        path.lineTo(right, bottom - (cornerRadius / 2f) - cornerLength)

        return path
    }

    private fun configureBitmap() {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        layer = Canvas(bitmap!!)
    }

    private fun convertDpToPixel(context: Context?, dp: Float): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}