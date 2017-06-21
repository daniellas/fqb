# FQB Functional Query Builder

FQB provides functional fluent interface for criteria queries building along with basic JPA Entity Manager operations like: persist/merge, delete, get.

## Why?

There are a few JPA query construction supporting libraries available:

* Spring Data JPA - [TBC]
* QueryDSL - [TBC]
* other not mentioned here I'm not aware of

FQB presents some other approach to this matter. Here below you can find reasons why I have created this library.

1. It was fun and good exercise
2. FQB is type safe, most of errors are detected during compile time
3. No JDK proxies or other magic under the hood
4. Minimal required runtime dependencies, only JPA 2.1 API 

There is one important thing to mention. FQB aims to give you ability of easy JPA queries construction whose structure can be defined during runtime depending on variables or method parameters values. 

## Work in progress

*Please note that this project is in early development stage so it's API can change without notice until release.*

Consecutive releases will follow [Semantic Versioning](http://semver.org/) principles. 

## How to use it?

Please see project tests to see usage examples.

## Requirements

### Java 8

You need Java 8 in order to build and use FQB

### JPA Metamodel 

JPA metamodel is required since FQB use it to resolve attributes of your entities for:

* restrictions
* ordering
* grouping
* expressions 

If you use **Apache Maven**, add below plugins configurations to your **pom.xml** to generate metamodel during maven build.

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.bsc.maven</groupId>
            <artifactId>maven-processor-plugin</artifactId>
            <version>3.1.0</version>
            <executions>
                <execution>
                    <id>process</id>
                    <goals>
                        <goal>process</goal>
                    </goals>
                    <phase>generate-sources</phase>
                    <configuration>
                        <processors>
                            <processor>
                                org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor
                            </processor>
                        </processors>
                    </configuration>
                </execution>
            </executions>
        </plugin>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>build-helper-maven-plugin</artifactId>
            <version>1.10</version>
            <executions>
                <execution>
                    <id>add-source</id>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>add-source</goal>
                    </goals>
                    <configuration>
                        <sources>
                            <source>${project.build.directory}/generated-sources/apt/</source>
                        </sources>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```  

