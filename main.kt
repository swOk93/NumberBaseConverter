package converter // Do not delete this line

fun converter(decimalNum: Int, base: Int): String {
    var quotient = decimalNum
    var res = ""
    while (quotient != 0) {
        val rem = quotient % base
        if (rem in 0..9) res = rem.toString() + res
        else if (rem in 10..35) res = (55 + rem).toChar() + res
        quotient = quotient / base
    }
    return res
}

fun main() {
    println("Enter number in decimal system:")
    val decimalNum = readln().toInt()
    println("Enter target base:")
    println("Conversion result: ${converter(decimalNum, readln().toInt())}")
}
