package cl.blackmind.aldoapp.domain.usecase

import cl.blackmind.aldoapp.domain.repository.QrRepository
import com.qubits.fw3.corebase.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers


class ScanQr(
    private val repository: QrRepository
) : ResultUseCase<Map<String, String>, Boolean>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    companion object {
        const val QR_CODE = "QR_CODE"
    }

    override suspend fun executeOnBackground(params: Map<String, String>): Boolean {
        val qrCode = params.getValue(QR_CODE)

        return repository.scanQr(qrCode)
    }
}