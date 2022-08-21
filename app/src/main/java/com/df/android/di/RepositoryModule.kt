package com.df.android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class GithubRepositoryModule {

    @Binds
    abstract fun bindRepository(repository: GithubRepositoryImpl): GithubRepository

}


