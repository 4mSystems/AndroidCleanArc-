object Config {
  object AppConfig {
    const val appId = "com.mina_mikhail.base_mvvm"
    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val versionCode = 1
    const val versionName = "1"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  object Dependencies {
    const val jitPackURL = "https://jitpack.io"
    const val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs =
      "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val google_services = "com.google.gms:google-services:${Versions.google_services}"
  }

  object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    const val hilt = "dagger.hilt.android.plugin"
    const val androidLibrary = "com.android.library"
    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    const val ktLint = "org.jlleitschuh.gradle.ktlint"
    const val google_services = "com.google.gms.google-services"
  }

  object Modules {
    const val domain = ":domain"
    const val data = ":data"
    const val presentation = ":presentation"
    const val prettyPopUp = ":prettyPopUp"
    const val appTutorial = ":appTutorial"
    const val imagesSlider = ":imagesSlider"
  }

  object Environments {
    const val debugBaseUrl = "\"https://aber.my-staff.net/api/\""
    const val releaseBaseUrl = "\"https://aber.my-staff.net/api/\""
  }
}