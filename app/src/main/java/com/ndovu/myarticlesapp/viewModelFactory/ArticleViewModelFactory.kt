package com.ndovu.myarticlesapp.viewModelFactory

import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.ndovu.myarticlesapp.viewModels.ArticleViewModel


class ArticleViewModelFactory internal constructor(
    private val errorDisplay: TextInputLayout,
    private val progressBar: ProgressBar
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ArticleViewModel::class.java) -> {
                ArticleViewModel(errorDisplay, progressBar) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}