# Verizon Test App

## Design & technical considerations

## Build with

### Android Architecture Components

A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.

* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata.html) - An observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel.html) - The ViewModel class allows data to survive configuration changes such as screen rotations.
* [Room](https://developer.android.com/topic/libraries/architecture/room.html) - The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

## How to test the application.

The application has a `<>` icon in the action bar to navigate to another screen, there you will find a `three point icon` to sort the list in `ASC`, `DESC` and concurring order and an `copy icon` to duplicate the list 1000 times.

```You can find the lib in the ./VerizonTest/VarizonLib/ directory```