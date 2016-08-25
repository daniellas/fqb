# FQB Functional Query Builder aka. FunctionalQube

## Why?

There are many JPA query libraries available.

* Spring Data JPA - I really like it 

FQB presents some other approach to this matter. Here below you can find reasons why I have created this library.

1. It was fun and good exercise
2. FQB is type safe, errors are detected during compile time
3. No proxies or other magic under the hood
4. Minimal required dependencies to run, only JPA 2.1 API 

There is one important thing to mention. FQB gives you ability to easily construct JPA queries whose structure can be changed during runtime.   