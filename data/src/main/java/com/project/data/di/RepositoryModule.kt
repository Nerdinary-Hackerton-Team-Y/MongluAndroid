package com.project.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
//    @Provides
//    @Singleton
//    fun provideHomeRepository(nikeDatabase: NikeDatabase): HomeRepository {
//        return HomeRepositoryImpl(nikeDatabase = nikeDatabase)
//    }

//    @Provides
//    @Singleton
//    fun provideProductRepository(productService: ProductService): ProductRepository =
//        ProductRepositoryImpl(productService = productService)
}