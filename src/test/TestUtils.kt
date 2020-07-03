package test

fun <T> printAssert(actual: T, expect: T) {
    println("Actual:\n${actual}")
    println("Expect:\n${expect}")
    println()
}