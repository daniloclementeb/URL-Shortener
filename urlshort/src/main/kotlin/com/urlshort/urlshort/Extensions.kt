package com.urlshort.urlshort

import java.math.BigInteger
import java.security.MessageDigest

fun String.encodeToID(truncateLength: Int = 6): String {
    // hash String with MD5
    val hashBytes = MessageDigest.getInstance("MD5").digest(this.toByteArray(Charsets.UTF_8))
    // transform to human readable MD5 String
    val hashString = String.format("%032x", BigInteger(1, hashBytes))
    // truncate MD5 String
    // return id
    return hashString.take(truncateLength)
}