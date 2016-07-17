# goeuro-dev-test
Just another solution for goeuro/dev-test

Project URL:
https://github.com/goeuro/dev-test

What I've used:
- Maven
- HttpClient
- Gson

Implemented services:
- CitySearchService
- CSVGeneratorService
- IOService
- RestAPI
- GoEuroAPI

# How to use?
GoEuroTest.jar file is available inside the repository and you don't need to build the project.

java -jar ./GoEuroTest.jar "Berlin" logs=off

Both city name argument and logs=off are optional. If city name not provided in arguments list program will ask you to enter it.

If program catches a known exception(timeout, empty result and etc), will ask user to re-enter the city name.

Generated CSV file will be placed inside working directory and path will be printed in console.
