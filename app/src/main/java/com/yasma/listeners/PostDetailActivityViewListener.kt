package com.yasma.listeners

import com.yasma.dto.Post
import com.yasma.dto.PostDetail

interface PostDetailActivityViewListener {
    fun successResponse(body: List<PostDetail>?)
    fun failureResponse(message: String)
}