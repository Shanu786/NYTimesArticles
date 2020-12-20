# NYTimesArticles

New York Times Most Popular API Integration
--------------------------------------------

This is a simple app to hit the NY Times Most Popular Articles API and show a list of articles, that shows details when items on the list are tapped (a typical master/detail app).

###### build.gradle application module level
```
    def lifecycle_version = "2.2.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'

    // Retrofit Library
    implementation "com.squareup.retrofit2:retrofit:$retrofit"
    implementation "com.squareup.retrofit2:converter-gson:$retrofit"
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofit"

    // GSON Converter Library
    implementation "com.squareup.retrofit2:converter-gson:$gson"

    // Kotlin Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines"

    //Kodein Dependency Injection
    implementation "org.kodein.di:kodein-di-generic-jvm:6.5.1"
    implementation "org.kodein.di:kodein-di-framework-android-x:6.5.1"
    implementation "org.kodein.di:kodein-di-framework-android-core:6.5.1"

    // Android Navigation Architecture
    implementation "androidx.navigation:navigation-fragment-ktx:2.1.0"
    implementation "androidx.navigation:navigation-ui-ktx:2.1.0"

    implementation 'org.jetbrains.kotlinx:kotlinx-collections-immutable:0.1'
    implementation "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.0"

    // OkHttp Library
    implementation "com.squareup.okhttp3:okhttp-urlconnection:$okhttp"
    implementation "com.squareup.okhttp3:logging-interceptor:$okhttp"
    implementation "com.squareup.okhttp3:okhttp:$okhttp"
    
    
    ### Llibraries or tools Used.
    - Retrofit, OkHttp
    - Gson
    - Kodein Dependency Injection
    - Live Data
    - Data Binding
    
