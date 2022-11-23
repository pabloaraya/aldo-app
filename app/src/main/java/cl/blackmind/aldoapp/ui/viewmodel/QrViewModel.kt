package cl.blackmind.aldoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import cl.blackmind.aldoapp.domain.usecase.ScanQr
import com.qubits.fw3.corebase.extension.LiveResult
import com.qubits.fw3.coredb.datastore.PreferencesDataStore

class QrViewModel(
    private val scanQr: ScanQr,
    private val dataStore: PreferencesDataStore
) : ViewModel() {
    val codeLiveData = LiveResult<Boolean>()

    fun scanBardCode(
        code: String,
    ) = scanQr.execute(
        codeLiveData, mapOf(
            ScanQr.QR_CODE to code,
        )
    )
}