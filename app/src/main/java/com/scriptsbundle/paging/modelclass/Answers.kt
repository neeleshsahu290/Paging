package com.scriptsbundle.paging.modelclass

data class Answers(
    val backoff: Int,
    val has_more: Boolean,
    val items: ArrayList<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)