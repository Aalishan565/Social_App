package com.yasma.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.User
import com.yasma.listeners.UserDetailActivityViewListener
import com.yasma.presenterImplModels.UserDetailActivityPresenterImpl
import com.yasma.presenters.UserDetailActivityPresenter

class UserDetailActivity : AppCompatActivity(), UserDetailActivityViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        val bundle: Bundle? = intent.extras
        val userId = bundle?.get(MyAppConstant.USER_ID)
        val userDetailActivityPresenter = UserDetailActivityPresenterImpl(this)
        userDetailActivityPresenter.callUserDetailApi(userId as Int)
    }

    override fun successResponse(body: User?) {
        Toast.makeText(this, "" + body.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun failureResponse(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
