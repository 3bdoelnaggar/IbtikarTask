package com.elnaggar.ibtikartask

import com.elnaggar.data.IMAGE_BASE_URL
import com.google.common.truth.Truth.assertThat
import org.junit.Test



class UrlValidatorTest {
    @Test
    fun url_validator_EmptyUrl_ReturnFalse() {
        assertThat(UrlValidator.isValidImageUrl("")).isFalse()
    }

    @Test
    fun url_validator_WrongUrl_ReturnFalse() {
        assertThat(UrlValidator.isValidImageUrl("dddd.nfg")).isFalse()
    }
    @Test
    fun url_validator_CorrectUrl_ReturnTrue() {
        assertThat(UrlValidator.isValidImageUrl("$IMAGE_BASE_URL/kTy1kVpVLjEH3rXZ0zE3bvBhvIh.jpg")).isTrue()
    }
}