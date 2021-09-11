package com.ndovu.myarticlesapp.utils

import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import retrofit2.Response

class ErrorHandler {
    companion object {
        fun <T> handleErrorMessage(response: Response<T>, errorDisplay: TextInputLayout) {
            var errorMessage = ""
            val jsonErrorResponse = JSONObject(response.errorBody()!!.string())
            errorMessage = if (jsonErrorResponse.has("message")) {
                jsonErrorResponse.getString("message")
            } else {
                jsonErrorResponse.getString("errorMessage")
            }
            errorDisplay.error = errorMessage
        }
    }
}