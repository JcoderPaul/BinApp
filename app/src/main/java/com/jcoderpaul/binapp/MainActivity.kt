package com.jcoderpaul.binapp

import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jcoderpaul.binkotveroneapp.api.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun onClickShowBinInfo(view: View) {
        val bin: String = editTextBinNumber.text.toString().trim()
        if (bin != ""){
        disposable = ApiFactory.apiService
            .getBinInfoFromSite(bin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val luhnStatus: String = if (it.number?.luhn == true) {"Yes"} else "No"
                val binInfo = String.format(
                    """
                          Платежная система: %s
                          Тип карты: %s
                          Брэнд: %s
                          
                          CARD NUMBER
                          LENGTH: %s
                          LUHN: %s
                          
                          Страна: %s
                          Код валюты из ОКВ: %s
                          Тип валюты: %s
                          
                          Банк: %s
                          
                          Посетить сайт банка: 
                          %s 
                          
                          Позвонить в банк: 
                          %s
                          
                          """.trimIndent(),
                    // Pojo BankCard
                    it.scheme, it.type, it.brand,
                    // Pojo BankCard
                    it.number?.length, luhnStatus,
                    // Pojo Country
                    it.country?.name?: String, it.country?.numeric?: String,
                    it.country?.currency?: String,
                    // Pojo Bank
                    it.bank?.name?: String, it.bank?.url?: String,
                    it.bank?.phone?: String
                )
                textViewBinInfo.text = binInfo
                Linkify.addLinks(textViewBinInfo, Linkify.ALL)
            },{
                Toast
                    .makeText(this,getString(R.string.bad_connect_toast), Toast.LENGTH_SHORT)
                    .show()
            })
        } else {
            Toast
                .makeText(this,getString(R.string.empty_edit_text_field), Toast.LENGTH_SHORT)
                .show()
        }
    }
}