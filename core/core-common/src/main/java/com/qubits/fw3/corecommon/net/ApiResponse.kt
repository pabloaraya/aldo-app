package com.qubits.fw3.corecommon.net

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("description")val description: String,
    @SerializedName("data")var data: T
)