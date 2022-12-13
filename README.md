# textiv

## Description
Textiv supports following text data transformations:
* Case change;
* Duplicate elimination;
* Expanding/collapsing abbreviations;
* Numbers to words;
* String inversion;
* Escape characters into LaTex form.
* Fix typos 

The project ~is~ must be compatible with REST.

## Building instructions

To build the executable `.jar` you can use:
```
mvn install
```

This will create an executable `target/io-project-architecture-*.jar` file that can be run using:
```
java -jar target/io-project-architecture-*.jar
```
