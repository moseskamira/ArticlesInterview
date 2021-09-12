package com.ndovu.myarticlesapp.utils

import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import retrofit2.Response

class ErrorHandler {
    companion object {
        fun <T> handleErrorMessage(response: Response<T>, errorDisplay: TextInputLayout) {
            var errorMessage = ""
            val jsonErrorResponse = JSONObject(response.errorBody()!!.string())
            if (jsonErrorResponse.has("fault")) {
                val faultObject = jsonErrorResponse.getJSONObject("fault");
                if (faultObject.has("faultstring")) {
                    errorMessage = faultObject.getString("faultstring");
                }
            }
            errorDisplay.error = errorMessage
        }
    }
}