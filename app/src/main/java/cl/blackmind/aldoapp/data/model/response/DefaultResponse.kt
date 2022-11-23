package cl.blackmind.aldoapp.data.model.response

import java.io.Serializable

data class DefaultResponse(
    val rut: String = "",
    val pin: String = "",
) : Serializable