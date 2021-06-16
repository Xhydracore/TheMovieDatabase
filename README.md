# TMDB(The Movie Database)

A Movie Catalogue app illustrating Android development best practices with Android Jetpack. This application are developed to fulfill Submission [BAJP(Belajar Android Jetpack Pro)](https://www.dicoding.com/academies/129 "BAJP(Belajar Android Jetpack Pro)") by Dicoding

## Screenshot
<img src="https://github.com/Xhydracore/TheMovieDatabase/blob/submission_1/screenshot/main_light.jpg" width="30%" height="30%"/> <img src="https://github.com/Xhydracore/TheMovieDatabase/blob/submission_1/screenshot/detail_light.jpg" width="30%" height="30%"/>
> TMDB app UI in Light theme

<img src="https://github.com/Xhydracore/TheMovieDatabase/blob/submission_1/screenshot/main_dark.jpg" width="30%" height="30%"/> <img src="https://github.com/Xhydracore/TheMovieDatabase/blob/submission_1/screenshot/detail_dark.jpg" width="30%" height="30%"/>
> TMDB app UI in Dark theme

## Libraries Used
* [Foundation][0] - Components for core system capabilities, Kotlin extensions and support for multidex and automated testing.
  * [AppCompat][1] - Degrade gracefully on older versions of Android.
  * [Android KTX][2] - Write more concise, idiomatic Kotlin code.
  * [Test][4] - An Android testing framework for unit and runtime UI tests.
* [Architecture][10] - A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.
  * [Data Binding][11] - Declaratively bind observable data to UI elements.
  * [ViewModel][17] - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
* [UI][30] - Details on why and how to use UI Components in your apps - together or separate
  * [Animations & Transitions][31] - Move widgets and transition between screens.
  * [Fragment][34] - A basic unit of composable UI.
  * [Layout][35] - Layout widgets using different algorithms.
* Third party
  * [Glide][90] for image loading

[0]: https://developer.android.com/jetpack/components
[1]: https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat
[2]: https://developer.android.com/kotlin/ktx
[4]: https://developer.android.com/training/testing/
[10]: https://developer.android.com/jetpack/arch/
[11]: https://developer.android.com/topic/libraries/data-binding/
[17]: https://developer.android.com/topic/libraries/architecture/viewmodel
[30]: https://developer.android.com/guide/topics/ui
[31]: https://developer.android.com/training/animation/
[34]: https://developer.android.com/guide/components/fragments
[35]: https://developer.android.com/guide/topics/ui/declaring-layout
[90]: https://github.com/bumptech/glide
