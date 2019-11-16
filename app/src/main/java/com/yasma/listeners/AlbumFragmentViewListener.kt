package com.yasma.listeners

import com.yasma.dto.Album

interface AlbumFragmentViewListener {

    fun successResponse(body: List<Album>?)

    fun failureResponse(message: String)
}