package com.scriptsbundle.paging.modelclass

import com.scriptsbundle.paging.db.User

data class Item(
    val answer_id: Int,
    val content_license: String,
    val creation_date: Int,
    val is_accepted: Boolean,
    val last_activity_date: Int,
    val last_edit_date: Int,
    val owner: User,
    val question_id: Int,
    val score: Int
)