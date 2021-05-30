package com.example.core.di


import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.core.data.HyoukaRepository
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.room.HyoukaDatabase
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiService
import com.example.core.domain.respository.IHyoukaRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<HyoukaDatabase>().hyoukaDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("hyouka".toCharArray())
        val factory = SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            HyoukaDatabase::class.java, "hyouka"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()

    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IHyoukaRepository> { HyoukaRepository(get(), get()) }
}
