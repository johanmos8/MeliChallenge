package com.johanmos8.presentation.util

import android.content.Context
import coil.request.CachePolicy
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale


fun getAsyncImageModel(data: Any?, context: Context): ImageRequest {
    return ImageRequest.Builder(context)
        .data(data = data)
        .crossfade(true)
        .build()
}

fun Double.formatPrice(currencyId: String): String {
    val locale = Locale.getDefault()
    val currency = Currency.getInstance(currencyId)

    val currencyFormat = NumberFormat.getCurrencyInstance(locale)
    currencyFormat.currency = currency

    return currencyFormat.format(this)
}

fun String.convertToHttps(): String {
    // Verificar si la URL ya comienza con HTTPS
    if (startsWith("https://", ignoreCase = true)) {
        return this // La URL ya es HTTPS, no es necesario realizar cambios
    }

    // Reemplazar "http://" con "https://"
    return replace("http://", "https://", ignoreCase = true)
}
