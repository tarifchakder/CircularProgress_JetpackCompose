# CircularProgress JetpackCompose

An android library to easily add circular progress bar into your [Jetpack Compose](https://developer.android.com/jetpack/compose) apps.

## Have a Look
<img src="screenshot/circular.gif" width="200" >&emsp;<img src="screenshot/determinate.gif" width="200" >&emsp;<img src="screenshot/indeterminate.gif" width="200" >

## Download
Add JitPack repository to your root `build.gradle` file
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add the dependency to your app `build.gradle` file
```
dependencies {
    implementation 'com.github.tarifchakder:CircularProgress_JetpackCompose:1.0'
}
