package com.yasma.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.yasma.R
import com.yasma.appUtils.MyAppConstant
import com.yasma.dto.User
import com.yasma.listeners.UserDetailActivityViewListener
import com.yasma.presenterImplModels.UserDetailActivityPresenterImpl
import kotlinx.android.synthetic.main.activity_user_detail.*
import kotlinx.android.synthetic.main.row_item_post_detail.*

class UserDetailActivity : AppCompatActivity(), UserDetailActivityViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        toolbar.title = getString(R.string.user_detail_activity_title)
        val bundle: Bundle? = intent.extras
        val userId = bundle?.get(MyAppConstant.USER_ID)
        val userDetailActivityPresenter = UserDetailActivityPresenterImpl(this)
        userDetailActivityPresenter.callUserDetailApi(userId as Int)
    }

    override fun successResponse(body: User?) {
        tvName.text = body?.name
        tvEmail.text = body?.email
        tvBody.text = body?.address.toString()
    }

    override fun failureResponse(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
