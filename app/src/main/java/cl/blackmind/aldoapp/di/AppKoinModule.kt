package cl.blackmind.aldoapp.di

import android.content.Context
import android.net.ConnectivityManager
import cl.blackmind.aldoapp.BuildConfig
import cl.blackmind.aldoapp.data.QrRepositoryImpl
import cl.blackmind.aldoapp.data.net.ApiService
import cl.blackmind.aldoapp.data.remote.QrRemoteSource
import cl.blackmind.aldoapp.data.remote.QrRemoteSourceImpl
import cl.blackmind.aldoapp.domain.repository.QrRepository
import cl.blackmind.aldoapp.domain.usecase.*
import cl.blackmind.aldoapp.ui.viewmodel.QrViewModel
import cl.blackmind.aldoapp.ui.viewmodel.SharedViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.qubits.fw3.coredb.datastore.AppDataStore
import com.qubits.fw3.coredb.datastore.PreferencesDataStore
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private val TIMEOUT: Long = 30

val appModule = module {
    /* Android Services */
    single {
        androidContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}

val networkModule = module {
    factory { provideOkHttpClient(androidContext()) }
    factory { provideRetrofit(get(), get()) }
    single { provideApiService(get()) }
    single { providesGsonConverterFactory() }
}

val dataModule = module {
    single<PreferencesDataStore> {
        AppDataStore(androidContext())
    }

    /* DataSources*/
    factory<cl.blackmind.aldoapp.data.remote.QrRemoteSource> {
        cl.blackmind.aldoapp.data.remote.QrRemoteSourceImpl(
            get(),
            get()
        )
    }
    /* Repositories */
    factory<QrRepository> { cl.blackmind.aldoapp.data.QrRepositoryImpl(get()) }
    /* Remote Mappers*/

}

val viewModelModule = module {
    viewModel { QrViewModel(get(), get()) }
    single { SharedViewModel(get()) }
}

val domainModule = module {
    //USE CASE
    factory { ScanQr(get()) }
    factory { ProductReassignmentUseCase(get()) }
    factory { ProductAssignmentUseCase(get()) }
}

fun provideRetrofit(providesGsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(providesGsonConverterFactory)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(context: Context): OkHttpClient {
    val logging = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    return OkHttpClient.Builder().apply {
        connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        readTimeout(TIMEOUT, TimeUnit.SECONDS)
        addInterceptor(
            Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header("Accept", "application/json")
                return@Interceptor chain.proceed(builder.build())
            }
        )
        retryOnConnectionFailure(false)
    }.build()
}


fun providesGsonConverterFactory(): GsonConverterFactory {
    val gson = GsonBuilder().setLenient().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).serializeNulls().create()

    return GsonConverterFactory.create(gson)
}


fun provideApiService(retrofit: Retrofit): cl.blackmind.aldoapp.data.net.ApiService =
    retrofit.create(cl.blackmind.aldoapp.data.net.ApiService::class.java)