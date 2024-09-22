package ru.netology.nmedia

import android.view.View
import android.widget.TextView
import kotlin.math.floor
import kotlin.math.pow

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
        if (power > 0) {
            val intermedNum = floor(value.toDouble() * numBP / divider.toDouble()).toInt() / numBP
            if (intermedNum >= numBP
                || (intermedNum * numBP).toInt() % numBP.toInt() == 0
            ) {
                intermedNum.toInt()
            } else {
                intermedNum
            }
        } else value
    return "$num${postfixArray[power]}"
}

fun hideOldContent(boxView: View, textView: TextView) {
    textView.setText("")
    boxView.visibility = View.GONE
}