package converter // Do not delete this line

import java.math.BigInteger

var sourse = 0
var target = 0

fun converterTo(quot: String): String {
    println("Enter source number:")
    var quotient = quot
    println("Enter source base:")
    var res = BigInteger("0")
    repeat(quotient.length) {
        if (sourse > 9 && !quotient[it].isDigit()) {
            val n = quotient[it].uppercaseChar().code - 55
            res += (n * Math.pow(sourse.toDouble(), (quotient.length-it-1).toDouble())).toInt().toBigInteger()
        }
        else if (it != quotient.length) {
            if (quotient[it].digitToInt() in 0..9) res += (quotient[it].digitToInt() * Math.pow(sourse.toDouble(), (quotient.length-it-1).toDouble())).toInt().toBigInteger()
        }
    }
    return res.toString()
}

fun converterFrom(quotient: String): String {
    var quotient = quotient.toBigInteger()
    if (sourse != 10) quotient = converterTo(quotient.toString()).toBigInteger()
    println("Enter target base:")
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
    println("Enter number in base ${sourse} to convert to base ${target} (To go back type /back)")
    quotient = readln()
    if (quotient != "/back") {
            // if (!isNumeric(quotient)) continue
        if (target == 10) println("Conversion result:${converterTo(quotient)}")
        else println("Conversion result:${converterFrom(quotient)}")
    }
}

fun main() {
    var action = ""
    while (action != "/exit") {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        action = readln()
        if (action != "/exit") {
            val list = List(2) { action.split(' ') }
            sourse = list[0].toInt()
            target = list[1].toInt()
            converter()
        }
    }
}
