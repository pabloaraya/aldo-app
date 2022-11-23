package cl.blackmind.aldoapp.domain.repository

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.data.model.response.Response

interface QrRepository {
    suspend fun scanQr(qr: String): Boolean
    suspend fun productAssignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): cl.blackmind.aldoapp.data.model.response.Response<Boolean>
    suspend fun productReassignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): Boolean
}