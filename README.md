# JavaRIG

Description
------------
This project aims to provide a random instances generator for any class in java,
it iterates through setters of an empty object and sets the fields values (using Java reflection api) 
randomly using one of the random generators defined in ``src/main/java/io.javarig.RandomInstanceGenerator.java``



UseCase
------------
This generator will mainly be used is Random-Testing of all java software products.

How To use
------------
in order to generate a random object of type YourClass, this class should have 
* public no arguments constructor
* public setters for fields you want to generate randomly 
    - naming convention for setters is setFieldName
    - fields with no publicly accessed setters will be left as the default value.

giving a class Car that respects the requirements above, this code will generate a random car
```java
RandomInstanceGenerator randomInstanceGenerator = new RandomInstanceGenerator();
Car car = randomInstanceGenerator.generate(Car.class);
```
#### Predefined Java Classes
inorder to generate an object for a certain predefined java class or primitive type, 
you may use the same method mentioned above
```java
String randomString = randomInstanceGenerator.generate(String.class);
```
#### RandomInstanceGenerator::generate
the generate method represents the entrypoint for our program, it accepts multiple arguments.
The easiest way to use it is with a single argument that represents the target class
#### Collection Generation
to generate a collection (classes that have a size; arrays, java collections, strings...)
you may use the generate method with extra arguments
##### Exact size
```java
  Double[] doubles = randomInstanceGenerator.generate(Double[].class, 6);
```
this piece of code will generate an array of length 6, that contains randomly generated Doubles
if this size is not giving, it will be randomly generated between **5 and 15**
##### Size between two bounds
```java
  Double[] doubles = randomInstanceGenerator.generate(Double[].class, 6,9);
```
this will generate an array of doubles of a random size from {6,7,8}

#### Generic Types
to generate a class that has a generic type (a Map per example) you may 
pass the generic types last in the argument list, and use any of the 
RandomInstanceGenerator::generate methods
```java
  HashMap<String,Float> map = randomInstanceGenerator.generate(HashMap.class , String.class , Float.class);
```
to precise the wanted size:
```java
  int size = 5; // per example
  HashMap<String,Float> map = randomInstanceGenerator.generate(HashMap.class , String.class ,size, Float.class);
```


Javadocs
------------
javadocs are not yet hosted but will be soon


Contributing
------------
This is a new project, so we welcome all contributions, feel free to open a pull request

Project Structure
--------

    .
    ├── ...
        ├── ...    
            ├── io.javarig.exception                # package containing our custom exceptions
            ├── io.javarig.generator                # generators for specific types: array, list, map, string, primitive ...
            ├── io.javarig.RandomInstanceGenerator  # Entrypoint for our Project, contains generate method
            ├── io.javarig.testclasses              # test classes, supplied to our testing methods.
            ├── io.javarig.generation               # Junit testing classes.
    ├── README.md, LICENSE...   # other files

To build sources locally follow these instructions.

### Tests

unit tests are done , with a 98% coverage

Technologies
--------

* Java
* Maven
* JUNIT 5
* Java Reflection Api

Project Status
-------
this project is _in progress_, the first release is planned before the end of 2022

License
-------

Copyright (c) 2022 {Obaydah Bouifadene, Hamza Ben Yazid, Ismail Mosleh, Ibrahim Mestadi}

Licensed under
the [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Contact
-------
Check our github ☺
