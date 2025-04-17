#!/usr/bin/env kotlin

@file:Repository("https://maven.aliyun.com/repository/public")
@file:DependsOn("net.iharder:base64:2.3.9")

import net.iharder.Base64

fun String.base64Decoded(): ByteArray {
    return Base64.decode(this)
}

fun ByteArray.toBase64String(): String {
    return Base64.encodeBytes(this)
}

println("5LiA5LqM5LiJ5Zub".base64Decoded().toString(Charsets.UTF_8))
