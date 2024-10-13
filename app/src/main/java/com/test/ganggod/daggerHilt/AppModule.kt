package com.test.ganggod.daggerHilt

import android.content.Context
import com.coder.vincent.sharp_retrofit.call_adapter.flow.FlowCallAdapterFactory
import com.test.ganggod.database.chatHistory.ChatHistoryDatabase
import com.test.ganggod.database.savedUser.SavedUserDatabase
import com.test.ganggod.network.login.LoginService
import com.test.ganggod.network.lottery.LotteryService
import com.test.ganggod.network.profile.ProfileService
import com.test.ganggod.network.register.RegisterService
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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideClient(@ApplicationContext context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(3000L, TimeUnit.MILLISECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptor = loggingInterceptor)
            .build()
    }

    /**
     * 在目录中使用Hilt提供多个相对类型的实例对象，通过"@Named"注解进行区分
     * 也可以通过"@Qualifier"限定符定义新的注解进行区分*/

    @Singleton
    @Provides
    @Named("upload")
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://blob.datapipe.top")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("chat")
    fun provideRetrofitChat(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://c.datapipe.top")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    @Named("gang")
    fun provideRetrofitGang(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://116.198.204.233:8080")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideLoginService(@Named("gang") retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterService(@Named("gang") retrofit: Retrofit):RegisterService{
        return retrofit.create(RegisterService::class.java)
    }

    @Singleton
    @Provides
    fun provideLotteryService(@Named("gang")retrofit: Retrofit):LotteryService{
        return retrofit.create(LotteryService::class.java)
    }

    @Singleton
    @Provides
    fun provideProfileService(@Named("gang")retrofit: Retrofit): ProfileService {
        return retrofit.create(ProfileService::class.java)
    }

    @Singleton
    @Provides
    fun provideChatHistoryDatabase(@ApplicationContext context: Context): ChatHistoryDatabase {
        return ChatHistoryDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideChatHistoryDao(database: ChatHistoryDatabase) = database.chatHistoryDao()

    @Singleton
    @Provides
    fun provideSavedUserDatabase(@ApplicationContext context: Context): SavedUserDatabase {
        return SavedUserDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideSavedUserDao(database: SavedUserDatabase) = database.chatHistoryDao()

}