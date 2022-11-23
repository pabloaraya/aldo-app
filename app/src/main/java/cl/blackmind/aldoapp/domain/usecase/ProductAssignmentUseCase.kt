package cl.blackmind.aldoapp.domain.usecase

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.data.model.response.Response
import cl.blackmind.aldoapp.domain.repository.QrRepository
import com.qubits.fw3.corebase.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

class ProductAssignmentUseCase(
    private val repository: QrRepository
) : ResultUseCase<Map<String, cl.blackmind.aldoapp.data.model.request.ProductAssignment>, cl.blackmind.aldoapp.data.model.response.Response<Boolean>>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    companion object {
        const val PRODUCT = "PRODUCT"
    }

    override suspend fun executeOnBackground(params: Map<String, cl.blackmind.aldoapp.data.model.request.ProductAssignment>) : cl.blackmind.aldoapp.data.model.response.Response<Boolean> {
        return repository.productAssignment(request = params.getValue(PRODUCT))
    }
}