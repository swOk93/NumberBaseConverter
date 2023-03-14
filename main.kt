package converter // Do not delete this line

var sourse = 0
var target = 0

fun converterTo(): Int {
    println("Enter source number:")
    var quotient = readln()
    println("Enter source base:")
    val base = readln().toDouble()
    var res = 0.0
    repeat(quotient.length) {
        if (base > 9 && !quotient[it].isDigit()) {
            val n = quotient[it].uppercaseChar().code - 55
            res += n * Math.pow(base, (quotient.length-it-1).toDouble())
        }
        else if (it != quotient.length) {
            if (quotient[it].digitToInt() in 0..9) res += quotient[it].digitToInt() * Math.pow(base, (quotient.length-it-1).toDouble())
        }
    }
    return res.toInt()
}

fun converterFrom(): String {
    println("Enter number in decimal system:")
    var quotient = readln().toInt()
    println("Enter target base:")
    val base = readln().toInt()
    var res = ""
    while (quotient != 0) {
        val rem = quotient % base
        if (rem in 0..9) res = rem.toString() + res
        else if (rem in 10..35) res = (55 + rem).toChar() + res
        quotient = quotient / base
    }
    if (res == "") return "0"
    return res
}

fun converter(): String {
    var quotient = 0
    while (quotient != "/back") {
        println("Enter number in base ${sourse} to convert to base ${target} (To go back type /back)")
        var quotient = readln()
        if (quotient != "/back") {
        }
}

fun main() {
    var action = ""
    while (action != "/exit") {
        println("Enter two numbers in format: {source base} {target base} (To quit type /exit)")
        action = readln()
        if (action != "/exit") {
            val list = List(2) { action.split(' ') }
            sourse = list[0]
            target = list[1]
            converter()
        }
        // when (action) {
        //     "/to" -> println("Conversion to decimal result: ${converterTo()}")
        //     "/from" -> println("Conversion result: ${converterFrom()}")
        // }
    }
}
