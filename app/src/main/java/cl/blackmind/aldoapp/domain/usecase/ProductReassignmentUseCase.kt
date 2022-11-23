package cl.blackmind.aldoapp.domain.usecase

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import cl.blackmind.aldoapp.domain.repository.QrRepository
import com.qubits.fw3.corebase.coroutines.ResultUseCase
import kotlinx.coroutines.Dispatchers

class ProductReassignmentUseCase(
    private val repository: QrRepository
) : ResultUseCase<Map<String, cl.blackmind.aldoapp.data.model.request.ProductAssignment>, Boolean>(
    backgroundContext = Dispatchers.IO,
    foregroundContext = Dispatchers.Main
) {
    companion object {
        const val PRODUCT = "PRODUCT"
    }

    override suspend fun executeOnBackground(params: Map<String, cl.blackmind.aldoapp.data.model.request.ProductAssignment>): Boolean {
        val product = params.getValue(PRODUCT)

        return repository.productReassignment(request = product)
    }
}