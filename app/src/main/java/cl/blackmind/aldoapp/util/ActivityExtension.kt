package cl.blackmind.aldoapp.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable


fun AppCompatActivity.startActivityExtension(cls: Class<out AppCompatActivity>, vararg extras: Serializable) {
    val intent = Intent(this, cls)

    for (i in extras.indices) {
        intent.putExtra("extra$i", extras[i])
    }

    startActivity(intent)
    overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
}