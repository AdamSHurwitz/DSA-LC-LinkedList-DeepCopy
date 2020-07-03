package test

fun <T> printAssert(actual: T, expect: T) {
    println("Actual: ${actual}")
    println("Expect: ${expect}")
    println()
}