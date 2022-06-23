package com.mantelpiecesb.moviesapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.mantelpiecesb.moviesapp.data.api.MoviesService
import com.mantelpiecesb.moviesapp.data.db.MovieDao
import com.mantelpiecesb.moviesapp.data.db.MoviesDatabase
import com.mantelpiecesb.moviesapp.utils.Constants.Companion.BASE_URL
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun baseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(logging())
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): MoviesService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(MoviesService::class.java)

    @Provides
    @Singleton
    fun provideMoviesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
        MoviesDatabase::class.java,
            "MoviesDB.db"
        ).build()

    @Provides
    fun provideArticleDao(appDatabase: MoviesDatabase): MovieDao {
        return appDatabase.getMoviesDao()
    }

}