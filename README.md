# KotlinJetpackUsers
UsersApp is a demo app build to demonstrate the usage of Jetpack components in Kotlin. This could be used as an app template for other apps

This app uses below concepts:
1) MVVM Architecture - https://developer.android.com/jetpack/docs/guide
2) Nagivation https://developer.android.com/guide/navigation
3) Data Binding https://developer.android.com/topic/libraries/data-binding
4) Retrofit 2 - https://square.github.io/retrofit/
5) Coroutines - https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html
6) Kodein DI - https://github.com/Kodein-Framework/Kodein-DI

For VM to Data Repository to web service, Suspended functions are used. In ViewModel, liveDataScope is used to call suspended funcations of Data Repository.

Special thanks to https://reqres.in/ for the hosted APIs



