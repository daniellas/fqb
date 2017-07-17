# FQB Functional Query Builder

FQB provides functional fluent interface for criteria queries building along with basic JPA Entity Manager operations like: persist/merge, delete, get.

## Why?

I't common situation when some relational database is used in project. In Java it's SQL via JDBC or JPQL/CriteriaAPI via JPA the right tool to work with relational databases. There are higher level libraries and frameworks simplifying database queries, like **Spring DATA JPA**. I'm quite familiar with Spring DATA project. It's very nice and easy to use, but there often were some project specific requirements that made things complicated. If you need to perform simple queries, existing tools work just fine, but when you need to implement more sophisticated queries, you end up with:

- complicated JPQL queries
- manual JPQL queries construction
- direct JPA Criteria API

I wanted to have the tool allowing me to:

- compose sophisticated queries with ready to use building blocks
- create shared higher level building blocks by myself
- ensure type safety on compile time
- enable queries intercepting mechanism not coupling my project to some particular JPA provider

**FQB** was created with above requirements in mind.

## How to use it?

Please see project tests to see usage examples.

## Work in progress

*Please note that this project is in early development stage so it's API can change without notice until release.*

Consecutive releases will follow [Semantic Versioning](http://semver.org/) principles. 

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

