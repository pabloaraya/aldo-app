package cl.blackmind.coreui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.qubits.fw3.tb.coreui.R

class SplashView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttribute: Int = 0
) : RelativeLayout(context, attributeSet, defStyleAttribute) {
    init {
        View.inflate(context, R.layout.view_splash, this)
    }
}