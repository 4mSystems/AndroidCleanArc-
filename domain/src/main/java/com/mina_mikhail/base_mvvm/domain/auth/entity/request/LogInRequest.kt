package com.mina_mikhail.base_mvvm.domain.auth.entity.request

import com.google.gson.annotations.SerializedName

data class LogInRequest(
  @SerializedName("phone_email")
  var email: String,
  var password: String,
  var device_token: String
) {
  constructor() : this("", "", "")
}

class LogInValidationException(private val validationType: String) : Exception(validationType)