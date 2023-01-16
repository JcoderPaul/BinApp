package com.jcoderpaul.binkotveroneapp.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BankCard (
    @SerializedName("number")
    @Expose
    val number: CardNumber? = null,

    @SerializedName("scheme")
    @Expose
    val scheme: String? = null,

    @SerializedName("type")
    @Expose
    val type: String? = null,

    @SerializedName("brand")
    @Expose
    val brand: String? = null,

    @SerializedName("country")
    @Expose
    val country: Country? = null,

    @SerializedName("bank")
    @Expose
    val bank: Bank? = null
)