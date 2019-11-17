package com.yasma.listeners

import com.yasma.dto.AlbumDetail

interface AlbumDetailActivityViewListener {
    fun successResponse(body: List<AlbumDetail>?)
    fun failureResponse(message: String)
}