package converter // Do not delete this line

import java.math.BigInteger
import java.math.BigDecimal
import java.math.RoundingMode

var sourse = 0
var target = 0

fun converterTo(quot: String): String {
    var quotient = quot
    var res = BigInteger("0")
    repeat(quotient.length) {
        if (sourse > 9 && !quotient[it].isDigit()) {
            val n = (quotient[it].uppercaseChar().code - 55).toBigDecimal()
            res += (n * BigDecimal(sourse).pow(quotient.length-it-1)).setScale(0, RoundingMode.UNNECESSARY).toBigInteger()
        }
        else if (it != quotient.length) {
            if (quotient[it].digitToInt() in 0..9) {
                res += (BigDecimal(quotient[it].digitToInt()) * BigDecimal(sourse).pow(quotient.length-it-1)).setScale(0, RoundingMode.UNNECESSARY).toBigInteger()
            }
        }
    }
    return res.toString()
}

fun converterFrom(quot: String): String {
    var quotient = BigInteger("0")
    if (sourse != 10) quotient = converterTo(quot.toString()).toBigInteger()
    else quotient = quot.toBigInteger()
    var res = ""
    while (quotient != BigInteger.ZERO) {
        val rem = (quotient % target.toBigInteger()).toInt()
        if (rem in 0..9) res = rem.toString() + res
        else if (rem in 10..35) res = (55 + rem).toChar() + res
        quotient = quotient / target.toBigInteger()
    }
    if (res == "") return "0"
    return res
}

fun converter() {
    var quotient = ""
    while (quotient != "/back") {
        println("Enter number in base ${sourse} to convert to base ${target} (To go back type /back)")
        quotient = readln()
        if (quotient != "/back") {
            // if (!isNumeric(quotient)) continue
            if (target == 10) println("Conversion result: ${converterTo(quotient)}")
            else println("Conversion result: ${converterFrom(quotient)}")
        }
    }   
}

fun main() {
    var action = ""
    while (action != "/exit") {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        action = readln()
        if (action != "/exit") {
            val list = action.split(' ').map { it.toInt() }
            sourse = list[0]
            target = list[1]
            converter()
        }
    }
}
