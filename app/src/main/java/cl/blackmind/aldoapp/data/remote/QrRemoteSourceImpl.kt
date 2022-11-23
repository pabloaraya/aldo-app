package cl.blackmind.aldoapp.data.remote

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.data.model.response.Response
import cl.blackmind.aldoapp.data.net.ApiService
import com.qubits.fw3.coredb.datastore.PreferencesDataStore

class QrRemoteSourceImpl(
    private val apiService: cl.blackmind.aldoapp.data.net.ApiService,
    private val dataStore: PreferencesDataStore
) : cl.blackmind.aldoapp.data.remote.QrRemoteSource {

    override suspend fun scanQr(qr: String): Boolean {

        val response = apiService.scanQr(id = qr)

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.code().toString())
        }
    }

    override suspend fun productAssignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): cl.blackmind.aldoapp.data.model.response.Response<Boolean> {

        val response = apiService.productAssignment(request = request)

        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw Exception(response.code().toString())
        }
    }

    override suspend fun productReassignment(request: cl.blackmind.aldoapp.data.model.request.ProductAssignment): Boolean {

        val response = apiService.productReassignment(request = request)

        if (response.isSuccessful) {
            return true
        } else {
            throw Exception(response.code().toString())
        }
    }

}