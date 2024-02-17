package com.example.study

fun String.toFullWidthCharacter(): String{
    val sb = StringBuilder()
    for (i in this.indices) {
        var c = this[i]
        // English alphabet or special characters
        if (c >= 0x21.toChar() && c <= 0x7e.toChar()) {
            c += 0xfee0
        }
        // space (=space)
        else if (c == 0x20.toChar()) {
            c = 0x3000.toChar()
        }
        sb.append(c)
    }
    return sb.toString()
}

fun String.toHalfWidthCharacter(): String{
    val sb = StringBuilder()
    for (i in this.indices) {
        var c = this[i]
        // English alphabet or special characters
        if (c >= '！' && c <= '～') {
            c -= 0xfee0
        }
        // space (=space)
        else if (c == '　') {
            c = 0x20.toChar()
        }
        sb.append(c)
    }
    return sb.toString()
}