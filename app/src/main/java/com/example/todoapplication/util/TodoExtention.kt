package com.example.todoapplication.util

fun <T> T?.toSafe(default: T): T = this ?: default