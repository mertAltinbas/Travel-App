package com.example.travel.core.base.exception

class CustomHttpException(val statusCode: Int, body:Any?, message:String?) : RuntimeException(message) {

}