package com.example.composecleanarchitecture.di

import android.content.Context
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.http.DefaultHttpEngine
import com.apollographql.apollo3.network.okHttpClient
/*import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient*/
import com.example.composecleanarchitecture.BuildConfig
import com.example.composecleanarchitecture.data.network.ApiResponseCallAdapterFactory
import com.example.composecleanarchitecture.data.network.IApiService
import com.example.composecleanarchitecture.db.room.RoomHelper
import com.example.composecleanarchitecture.db.shared_preference.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        preferencesHelper: SharedPreferencesHelper,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(4, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer 89016413ef47b07c06e8145f2d1700b18c253b75fbc0a609ca074341ca8957c75c0d8532599f0293e107603b326d03b40799e95826bf89d34a28c6d991ea0ad234055e83c9717d9919faa74e4360df6d441771744f4d9178e43b33ee3e89e786892eba4348004ef3c22aab74d2c7d3bd9a0cc48ed9feaa4d39d419968d02031a")

                chain.proceed(request.build())
            }.addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://content.rafkhata.com/graphql/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory())
            .build()
    }


    @Provides
    @Singleton
    fun provideGraphQlApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://content.rafkhata.com/graphql/")
            .httpEngine(DefaultHttpEngine(timeoutMillis = 600000))
            .okHttpClient(okHttpClient).build()
    }



    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): IApiService {
        return retrofit.create(IApiService::class.java)
    }

    @Provides
    @Singleton
    fun providePreference(@ApplicationContext context: Context): SharedPreferencesHelper {
        return SharedPreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideRoomHelper(@ApplicationContext context: Context): RoomHelper {
        return RoomHelper(context)
    }

}