package com.yasma.listeners

import com.yasma.dto.Post

interface PostViewListener {
    fun successResponse(body: List<Post>?)
    fun failureResponse(message: String)
}