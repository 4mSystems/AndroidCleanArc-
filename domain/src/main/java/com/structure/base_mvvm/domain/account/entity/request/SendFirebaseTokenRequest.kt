package com.structure.base_mvvm.domain.account.entity.request

data class SendFirebaseTokenRequest(
  var token: String,
  var deviceType: String,
  var deviceId: String
)