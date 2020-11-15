package com.elnaggar.ibtikartask

object UrlValidator {
    fun isValidImageUrl(url:String):Boolean{
        if(url.isEmpty()){
            return false
        }
        if(url.contains(".jpg").not()){
            return false
        }
        return true

    }
}