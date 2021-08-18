package com.kngrck.fooddeliveryfinal.di

import android.util.Log
import com.google.gson.Gson
import com.kngrck.fooddeliveryfinal.model.local.LocalDataSource
import com.kngrck.fooddeliveryfinal.model.remote.APIService
import com.kngrck.fooddeliveryfinal.model.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ActivityRetainedComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }


    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        localDataSource: LocalDataSource
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor { interceptor ->
                val token = localDataSource.getToken()
                Log.v("Network","token $token")
                val request =
                    interceptor.request().newBuilder().addHeader("Authorization", token!!).build()
                interceptor.proceed(request)
            }
            .build()
    }


    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: APIService,
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }


    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://us-central1-fooddeliverybootcamp.cloudfunctions.net/widgets/")
    }


}

data class EndPoint(val url: String)

data class Token(val token: String)
