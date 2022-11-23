package com.qubits.fw3.corecommon.extensions

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.webkit.URLUtil
import android.widget.AutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*
import java.util.regex.Pattern

fun strikeThroughSpannableString(text: String): SpannableString {
    val spannable = SpannableString(text)
    spannable.setSpan(StrikethroughSpan(), 0, text.length, 0)
    return spannable
}

fun String.isEmailValid(): Boolean {
    val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,8}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun Int.formatNotificationCount(): String {
    return if (this > 9) {
        "9+"
    } else {
        this.toString()
    }
}

fun String.getDescriptionFromErrorBody(): String {
    runCatching {
        val errorObj = JSONObject(this)
        return if (errorObj.has("description")) errorObj.getString("description") else ""
    }
    return this
}

fun String.verifyPhone(): String {
    return if (this.isEmpty() || this == "0") {
        "Número no disponible"
    } else {
        "+$this"
    }
}

fun String.verifyPrice(): String {
    return if (this.startsWith("$")) {
        this
    } else {
        "$$this"
    }
}

val String.containsLatinLetter: Boolean
    get() = matches(Regex(".*[A-Za-z].*"))

val String.containsDigit: Boolean
    get() = matches(Regex(".*[0-9].*"))

val String.isAlphanumeric: Boolean
    get() = matches(Regex("[A-Za-z0-9]*"))

val String.hasLettersAndDigits: Boolean
    get() = containsLatinLetter && containsDigit

val String.isIntegerNumber: Boolean
    get() = toIntOrNull() != null

val String.toDecimalNumber: Boolean
    get() = toDoubleOrNull() != null

fun TextInputLayout.removeErrorIfItsEnabled() {
    if (this.isErrorEnabled) {
        this.isErrorEnabled = false
    }
}

fun TextInputLayout.validateInputIsNotEmptyOrShowError(errorMessage: String): Boolean {
    return if (this.editText?.text.toString().isNotEmpty()) {
        true
    } else {
        this.apply {
            error = errorMessage
            isErrorEnabled = true
        }
        false
    }
}

fun TextInputLayout.setText(text: String) {
    this.apply {
        editText?.text?.clear()
        editText?.text?.append(text)
    }
}

fun AutoCompleteTextView.validateTextIsNotEmptyOrShowError(
    input: TextInputLayout,
    errorMessage: String
): Boolean {
    return if (this.text.toString().isNotEmpty()) {
        true
    } else {
        input.apply {
            error = errorMessage
            isErrorEnabled = true
        }
        false
    }
}

fun TextInputLayout.showError(errorMessage: String) {
    this.apply {
        error = errorMessage
        isErrorEnabled = true
    }
}

fun String.encodeSexToApiRequirements(): String {
    return when (this.toLowerCase(Locale.getDefault())) {
        "mujer" -> "M"
        "hombre" -> "H"
        else -> "O"
    }
}

fun String.decodeSexToUiRequirements(): String {
    return when (this.toLowerCase(Locale.getDefault())) {
        "m" -> "Mujer"
        "h" -> "Hombre"
        else -> ""
    }
}

fun String.normalizeDateFormatToApiRequirements(): String {
    kotlin.runCatching {
        val date = this.split("/")
        val day = if (date[0].length == 1) "0" + date[0] else date[0]
        val month = if (date[1].length == 1) "0" + date[1] else date[1]
        val year = date[2]
        return "$year-$month-$day"
    }
    return this
}

fun String.normalizeDateFormatToUiRequirements(): String {
    kotlin.runCatching {
        val date = this.split("-")
        val day = date[0]
        val month = date[1]
        val year = date[2]
        return "$day/$month/$year"
    }
    return this
}

fun normalizeDayOrMonth(date: Int): String {
    return if (date < 10) {
        return "0$date"
    } else {
        date.toString()
    }
}

fun String.isValidUrl() : Boolean {
    return URLUtil.isValidUrl(this)
}

fun String.validateRut() : Boolean {
    return if (this.isEmpty()) true else this.matches(Regex("""^\d{1,2}\.\d{3}\.\d{3}[-][0-9kK]{1}${'$'}"""))
}


fun rutFormatter(rut: String?): String? {
    if (rut == null) return null
    var original = rut.replace("[^kK0-9]".toRegex(), "")
    if (original.length <= 1) {
        return original
    }
    if (original.length > 9) {
        original = original.substring(0, 9)
    }
    val verifier = original[original.length - 1]
    original = original.substring(0, original.length - 1)
    original = original.replace("[^0-9]".toRegex(), "")
    return if (original.isNotEmpty()) {
        val number: String = formatThousands(original)
        "$number-$verifier"
    } else {
        "" + verifier
    }
}

fun formatThousands(input: String): String {
    val number = input.replace(".", "")
    val df = DecimalFormat(
        "#,###",
        DecimalFormatSymbols(Locale("es", "CL"))
    )
    return df.format(number.toLong())
}