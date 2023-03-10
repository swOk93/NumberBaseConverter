package converter // Do not delete this line

fun converterTo(): Int {
    println("Enter source number:")
    var quotient = readln()
    println("Enter source base:")
    val base = readln().toDouble()
    var res = 0.0
    repeat(quotient.length) {
        if (base > 9 && it != quotient.length) {
            val n = quotient[it].uppercaseChar().code - 45
            res += n * Math.pow(base, (quotient.length-it-1).toDouble())
        }
        else if (it != quotient.length) {
            if (quotient[it].digitToInt() in 0..9) res += quotient[it].digitToInt() * Math.pow(base, (quotient.length-it-1).toDouble())
        }
        else res += quotient[it].digitToInt()
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

fun main() {
    var action = ""
    while (action != "/exit") {
        println("Do you want to convert /from decimal or /to decimal? (To quit type /exit)")
        action = readln()
        when (action) {
            "/to" -> println("Conversion to decimal result: ${converterTo()}")
            "/from" -> println("Conversion result: ${converterFrom()}")
        }
    }
}
