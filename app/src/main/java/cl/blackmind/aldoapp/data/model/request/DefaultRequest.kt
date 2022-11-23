package cl.blackmind.aldoapp.data.model.request

import java.io.Serializable

data class DefaultRequest(
    val rut: String = "",
    val pin: String = "",
) : Serializable