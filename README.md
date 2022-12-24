# JavaRIG

Description
------------
This project aims to provide a random instances generator for any class in java,
it iterates through setters of an empty object and sets the fields values (using Java reflection api) 
randomly using one of the random generators defined in ``src/main/java/io.javarig.OldRandomGenerator.java``



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

we just started working on unit tests.

you are welcome to help on that matter.

Technologies
--------

* Java
* Maven
* JUNIT 5
* Java Reflection Api

Project Status
-------
this project is _in progress_, many things are not yet started

License
-------

Copyright (c) 2022 {Obaydah Bouifadene, Hamza Ben Yazid, Ismail Mosleh, Ibrahim Mestadi}

Licensed under
the [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Contact
-------
Check our github ☺
