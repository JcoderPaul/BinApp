package com.jcoderpaul.binkotveroneapp.api

import com.jcoderpaul.binkotveroneapp.pojo.BankCard
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/{bin}")
    fun getBinInfoFromSite(@Path("bin") enterBin: String): Single<BankCard>
}