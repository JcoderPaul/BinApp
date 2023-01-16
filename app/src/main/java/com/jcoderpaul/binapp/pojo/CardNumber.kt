package com.jcoderpaul.binkotveroneapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CardNumber (
    @SerializedName("length")
    @Expose
    val length: Int? = null,
    @SerializedName("luhn")
    @Expose
    val luhn: Boolean? = null
)