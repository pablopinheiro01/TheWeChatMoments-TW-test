package com.tws.moments.module

import android.content.Context
import com.tws.moments.imageloader.GlideImageLoader
import com.tws.moments.imageloader.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageLoaderModule{

    @Provides
    @Singleton
    fun provideGlideImageLoader( @ApplicationContext context: Context): ImageLoader {
        return GlideImageLoader(context)
    }

}