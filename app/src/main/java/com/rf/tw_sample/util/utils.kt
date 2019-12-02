package com.rf.tw_sample.util

import java.time.format.DateTimeFormatter

fun String.toSimpleDateFormat() = DateTimeFormatter.ofPattern("MMM dd YYYY").format(
    DateTimeFormatter.ISO_DATE_TIME.parse(this)
)