package com.example.travel.core.extensions

private const val BILLION = 1000000000f
private const val MILLION = 1000000f
private const val THOUSAND = 1000f

fun Int.toSmallString() : String {
    return if (this >= BILLION) "${(this / BILLION)}B"
    else if (this >= MILLION) "${(this / MILLION)}M"
    else if (this >= THOUSAND) "${(this / THOUSAND)}K"
    else this.toString()
}