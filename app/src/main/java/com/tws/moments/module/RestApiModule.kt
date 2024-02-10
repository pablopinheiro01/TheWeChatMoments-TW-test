package com.tws.moments.di.module

import com.tws.moments.data.api.MomentService
import com.tws.moments.data.repository.MomentRepository
import com.tws.moments.data.repository.MomentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

private const val BASE_URL = "http://192.168.15.152:2727/"

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule{

    @Provides
    @Singleton
    @MomentServiceRetrofit
    fun provideRetrofit(client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideMomentService(@MomentServiceRetrofit retrofit: Retrofit): MomentService {
        return retrofit.create(MomentService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideMomentRepositoryImpl( @com.tws.moments.di.module.MomentService service : MomentService): MomentRepository {
//        return MomentRepositoryImpl(service)
//    }

}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MomentServiceRetrofit
