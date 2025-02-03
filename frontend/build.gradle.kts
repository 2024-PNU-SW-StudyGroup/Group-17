// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) version "1.9.21" apply false
    alias(libs.plugins.dagger.hilt) apply false //  Hilt 플러그인 추가
}