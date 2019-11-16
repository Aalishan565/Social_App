package com.yasma.listeners

import com.yasma.dto.Album

interface AlbumViewListener {

    fun successResponse(body: List<Album>?)

    fun failureResponse(message: String)
}