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
```

## Usage
```kotlin
@Composable
fun simpleCircularProgress() {
    SimpleCircularProgress(
        radius = 80.dp,
        progress = progress.value,
        maxProgress = 100F,
        indicatorColor = Color.Blue,
        indicatorWidth = 10.dp,
        trackColor = Color.Blue.copy(0.2f),
        trackWidth = 10.dp,
        cornerRadius = true,
        startAngle = 0f,
        durationInMilliSecond = 2000
    )
}
```

For animations, you can use any animation API provided by Compose depending on your animation scenario.

```kotlin
val progress by animateFloatAsState(/* progress value */)

CircularProgressBar(
    modifier = Modifier.size(120.dp),
    progress = progress,
    progressMax = 100f,
    progressBarColor = Color.Blue,
    progressBarWidth = 20.dp,
    backgroundProgressBarColor = Color.Gray,
    backgroundProgressBarWidth = 10.dp,
    roundBorder = true,
    startAngle = 90f
)
```


