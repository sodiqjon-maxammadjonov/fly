package com.sdk.fly.data.di

import com.sdk.fly.data.remote.api.VoiceApiService
import com.sdk.fly.domain.repository.VoiceRepository
import com.sdk.fly.domain.repository_impl.VoiceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import jakarta.inject.Singleton
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.elevenlabs.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideVoiceApiService(retrofit: Retrofit): VoiceApiService {
        return retrofit.create(VoiceApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideVoiceRepository(api: VoiceApiService): VoiceRepository {
        return VoiceRepositoryImpl(api)
    }
}
