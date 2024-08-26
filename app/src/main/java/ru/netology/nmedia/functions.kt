package ru.netology.nmedia.ru.netology.nmedia

import ru.netology.nmedia.R
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.round

fun getLikeImg(likeByMe: Boolean): Int {
    return if (likeByMe) R.drawable.ic_favorite else R.drawable.ic_favorite_border
}

fun getTextFromNum(value: Int): String {
    val postfixArray: ArrayList<String> = arrayListOf("", "K", "M", "B")

    val thousand = 1_000.0
    val decimalCount = 1.0
    val numBP = 10.0.pow(decimalCount)

    var power = 0

    while (value >= thousand.pow(power + 1) && power <= postfixArray.size) {
        power++
    }
    val divider = thousand.pow(power.toDouble()).toInt()

    val num =
        if (power > 0)
            floor(value.toDouble() * numBP / divider.toDouble()).toInt() / numBP
        else value.toDouble()

    val numStr = (
            if (power > 0 && num <= numBP && (num * numBP).toInt() % numBP.toInt() != 0) {
                round(num * numBP) / numBP
            } else num.toInt()).toString()

    return "$numStr${postfixArray[power]}"
}
