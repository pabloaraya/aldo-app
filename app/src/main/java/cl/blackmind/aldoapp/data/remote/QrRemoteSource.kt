package cl.blackmind.aldoapp.data.remote

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.data.model.response.Response


interface QrRemoteSource {
    suspend fun scanQr(qr: String): Boolean
    suspend fun productAssignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): cl.blackmind.aldoapp.data.model.response.Response<Boolean>
    suspend fun productReassignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): Boolean
}