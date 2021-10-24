package com.structure.base_mvvm.domain.auth.entity.request

data class ForgetPasswordRequest(
  var phone: String,
  var type: String
) {
  constructor() : this("", "")
}

class ForgetPasswordValidationException(private val validationType: String) :
  Exception(validationType)