# Contributing to algorithms

First off, thanks for taking the time to contribute! ❤️

All types of contributions are encouraged and valued. See the [Table of Contents](#table-of-contents) for different ways to help and details about 
how this project handles them. Please make sure to read the relevant section before making your contribution. It will make it a lot easier for us 
maintainers and smooth out the experience for all involved. The community looks forward to your contributions. 🎉

## Table of Contents

- [Adding a new algorithm](#adding-a-new-algorithm)
- [Tests](#tests)

## Adding a new algorithm
1. Make sure that your new algorithm does not already exist
2. If issue already does not exist, then create a new issue explaining the new algorithm or improvment of existing one
3. Identify label: algorithm/data-structure?
4. Add the algorithm to correct, meaningful package (existing or new): <pre>src/main/java/com/marcinseweryn/algorithms/...</pre>
5. Add tests for new algorithm: <pre>src/test/java/com/marcinseweryn/algorithms/...</pre>
6. Test algorithm (see [Tests](#tests))
7. Send pull request **only to dev branch**

## Tests
In this repository any new algorithm must include unit tests. All pull request without tests wont be submit.
Testing is done using [Junit 5.9.2](https://junit.org/junit5/docs/current/release-notes/index.html#release-notes-5.9.2) framework

To run all tests you can use
```
./gradlew test
```

To run single or subset of them
```
./gradlew --tests <the-fully-qualified-name-of-the-class> test 
```
For example
```
./gradlew --tests com.marcinseweryn.algorithms.sorting.MergeSortTest test 
```
For getting result of the previous tests use this task
```
./gradlew testReport
```

  
