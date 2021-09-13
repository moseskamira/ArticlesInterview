package com.interview.myarticlesapp.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class CredentialTest {
    @Test
    fun getApiKey() {
        assertThat(Credentials.returnApiKey()).isEqualTo("RREDo9ObCv9Smd9FqH45wcnXZ5RXUE9K")
    }
}