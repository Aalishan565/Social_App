package com.yasma.listeners

import com.yasma.dto.User

interface UserDetailActivityViewListener {
    fun successResponse(body: User?)

    fun failureResponse(message: String)

}