package kr.petchin.app.retrofit

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import kr.petchin.app.lib.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }

    val retrofitRx by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitApi::class.java)
    }
/*
    val retrofit= Retrofit.Builder()
        .baseUrl("https://book.interpark.com")
        .addConverterFactory(GsonConverterFactory.create()) // Json데이터를 사용자가 정의한 Java 객채로 변환해주는 라이브러리
        .build() //레트로핏 구현체 완성!

    val bookService=retrofit.create(BookService::class.java) //retrofit객체 만듦!

 */
}