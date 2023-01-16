package com.jcoderpaul.binkotveroneapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Bank (
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("phone")
    @Expose
    val phone: String? = null
)