package converter // Do not delete this line

import java.math.BigInteger
import java.math.BigDecimal
import java.math.RoundingMode

var sourse = 0
var target = 0

fun converterTo(quotient: String): String {
    var res = BigDecimal("0")
    repeat(quotient.length) {
        if (sourse > 9 && !quotient[it].isDigit()) {
            val n = (quotient[it].uppercaseChar().code - 55).toBigDecimal()
            res += n * BigDecimal(sourse).pow(quotient.length-it-1)
        }
        else if (it != quotient.length) {
            if (quotient[it].digitToInt() in 0..9) {
                res += BigDecimal(quotient[it].digitToInt()) * BigDecimal(sourse).pow(quotient.length-it-1)
            }
        }
    }
    return res.setScale(0, RoundingMode.UNNECESSARY).toString()
}

fun converterFrom(quot: String): String {
    var quotient = BigInteger("0")
    if (sourse != 10) quotient = converterTo(quot).toBigInteger()
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
            if (quotient.contains('.')) {
                var (integer, fraction) = quotient.split('.')
                if (target == 10) {
                    println("Conversion result: ${converterTo(integer).toBigDecimal() + converterFractionToDecimal(fraction).toBigDecimal()}")
                }
                else println("Conversion result: ${converterFrom(integer)}.${converterFractionFromDecimal(fraction)}")
            }
            else if (target == 10) println("Conversion result: ${converterTo(quotient)}")
            else println("Conversion result: ${converterFrom(quotient)}")
        }
    }   
}

fun converterFractionFromDecimal(frac: String): String {
    var fraction = BigDecimal("0")
    if (sourse != 10) { fraction = BigDecimal(converterFractionToDecimal(frac)) }
    else fraction = BigDecimal("0.$frac")
    var res = ""
    while (fraction > BigDecimal.ZERO && res.length < 5) {
        val intNum = (fraction * BigDecimal(target)).setScale(0, RoundingMode.DOWN).toInt()
        fraction = fraction * BigDecimal(target) - BigDecimal(intNum)
        res += if (intNum > 9) { (55 + intNum).toChar() } else { intNum.toString() }
    }
    while (res.length < 5) res += "0"
    return res
}

fun converterFractionToDecimal(fraction: String): String {
    var quotient = fraction
    var res = BigDecimal("0")
    repeat(quotient.length) {
        if (sourse > 9 && !quotient[it].isDigit()) {
            val n = (quotient[it].uppercaseChar().code - 55).toBigDecimal()
            res += BigDecimal("1.00000") / BigDecimal(sourse).pow(it+1) * n
        }
        else {
            if (quotient[it].digitToInt() in 0..9) {
                res += BigDecimal("1.00000") / BigDecimal(sourse).pow(it+1) * BigDecimal(quotient[it].digitToInt())
            }
        }
    }
    return res.setScale(5, RoundingMode.DOWN).toString()
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
