package com.qubits.fw3.corecommon.extensions

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import androidx.core.view.isVisible

fun ImageView.load(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}

fun ImageView.loadWithRoundedTransform24dp(url: String) {
    Glide.with(this)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(72))
        .into(this)
}

fun ImageView.loadWithRoundedTransformAndPlaceholder(url: String, color: Int) {
    val drawable = CircularProgressDrawable(this.context)
    drawable.setColorSchemeColors(color)
    drawable.centerRadius = 50f
    drawable.strokeWidth = 10f
    drawable.start()
    Glide.with(this)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                this@loadWithRoundedTransformAndPlaceholder.isVisible = false
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                drawable.stop()
                return false
            }
        })
        .placeholder(drawable)
        .transform(CenterCrop(), RoundedCorners(72))
        .into(this)
}

fun ImageView.loadWithRoundedTransform24dp(drawable: Drawable?) {
    Glide.with(this)
        .load(drawable)
        .transform(CenterCrop(), RoundedCorners(72))
        .into(this)
}

fun ImageView.setHeightSameAsWidth() {
    val widthOfImage: Int = layoutParams.width
    layoutParams.height = widthOfImage
}

fun ImageView.setColorFilterResource(@ColorRes color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        colorFilter = BlendModeColorFilter(ContextCompat.getColor(context, color), BlendMode.SRC_OVER)
    } else {
        setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_OVER)
    }
}
