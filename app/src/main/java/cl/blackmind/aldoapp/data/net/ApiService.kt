package cl.blackmind.aldoapp.data.net

import cl.blackmind.aldoapp.data.model.request.ProductAssignment
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("test")
    suspend fun scanQr(
        @Path("id") id: String
    ): Response<String>

    @POST("rfid/product/assignment")
    suspend fun productAssignment(
        @Body request: cl.blackmind.aldoapp.data.model.request.ProductAssignment
    ): Response<cl.blackmind.aldoapp.data.model.response.Response<Boolean>>

    @POST("rfid/product/reassignment")
    suspend fun productReassignment(
        @Body request: cl.blackmind.aldoapp.data.model.request.ProductAssignment
    ): Response<cl.blackmind.aldoapp.data.model.response.Response<Any>>
}