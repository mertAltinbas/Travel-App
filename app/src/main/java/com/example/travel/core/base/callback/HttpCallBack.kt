package com.example.travel.core.base.callback

import com.example.travel.core.base.exception.CustomHttpException

interface HttpCallBack<T> {

    fun onSuccess(data: T)

    fun onFailure(exception: CustomHttpException)
}