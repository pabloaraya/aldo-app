package cl.blackmind.aldoapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.data.model.response.Response
import cl.blackmind.aldoapp.domain.usecase.ProductAssignmentUseCase
import com.qubits.fw3.corebase.extension.LiveResult

class SharedViewModel(
    private val productAssignmentUseCase: ProductAssignmentUseCase
) : ViewModel() {

    private val stationLiveData = MutableLiveData<String>()
    private val lastSerialLiveData = MutableLiveData<String>()
    private val lastRFIDLiveData = MutableLiveData<String>()

    private val currentDeviceLiveData = MutableLiveData<String>()
    private val hasSerialLiveData = MutableLiveData(false)
    private val hasRfidLiveData = MutableLiveData(false)

    val isDoneLiveData = LiveResult<cl.blackmind.aldoapp.data.model.response.Response<Boolean>>()

    fun getCurrentDevice() = currentDeviceLiveData
    fun setCurrentDevice(device: String) {
        currentDeviceLiveData.value = device
    }

    fun getStation() = stationLiveData
    fun setStation(station: String) {
        stationLiveData.value = station
    }

    fun getLastSerial() = lastSerialLiveData
    fun setLastSerial(serial: String) {
        lastSerialLiveData.value = serial
        hasSerialLiveData.value = true
    }

    fun getRFID() = lastRFIDLiveData
    fun setRFID(rfid: String) {
        lastRFIDLiveData.value = rfid
        hasRfidLiveData.value= true
    }

    fun clean() {
        lastSerialLiveData.value = ""
        lastRFIDLiveData.value = ""
        hasSerialLiveData.value = false
        hasRfidLiveData.value = false
    }

    fun assignment() {
        if (lastSerialLiveData.value != null && lastRFIDLiveData.value != null) {
            val serial = lastSerialLiveData.value!!
            val rfid = lastRFIDLiveData.value!!
            val productAssignment =
                cl.blackmind.aldoapp.data.model.request.ProductAssignment(serial, rfid)
            val productMap = HashMap<String, cl.blackmind.aldoapp.data.model.request.ProductAssignment>()
            productMap["PRODUCT"] = productAssignment
            productAssignmentUseCase.execute(isDoneLiveData, productMap)
        }
    }
}