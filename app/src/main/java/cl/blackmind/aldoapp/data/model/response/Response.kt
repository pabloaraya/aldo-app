package cl.blackmind.aldoapp.data.model.response

data class Response<T>(var status: Boolean, var statusCode: Int, var description: String, var data: T)
