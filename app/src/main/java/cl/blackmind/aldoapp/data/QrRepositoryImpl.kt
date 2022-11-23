package cl.blackmind.aldoapp.data

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.data.model.response.Response
import cl.blackmind.aldoapp.data.remote.QrRemoteSource
import cl.blackmind.aldoapp.domain.repository.QrRepository

class QrRepositoryImpl(
    private val masterDataSource: cl.blackmind.aldoapp.data.remote.QrRemoteSource
) : QrRepository {

    override suspend fun scanQr(qr: String): Boolean = masterDataSource.scanQr(qr = qr)

    override suspend fun productAssignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): cl.blackmind.aldoapp.data.model.response.Response<Boolean> = masterDataSource.productAssignment(request = request)

    override suspend fun productReassignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): Boolean = masterDataSource.productReassignment(request = request)
}