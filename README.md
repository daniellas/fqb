# FQB Functional Query Builder aka. FunctionalQube

## Why?

There are many JPA query construction supporting libraries available.

* Spring Data JPA - I really like it but [TBC]

FQB presents some other approach to this matter. Here below you can find reasons why I have created this library.

1. It was fun and good exercise
2. FQB is type safe, errors are detected during compile time
3. No proxies or other magic under the hood
4. Minimal required dependencies to run, only JPA 2.1 API 

There is one important thing to mention. FQB gives you ability to easily construct JPA queries whose structure can be defined during runtime depending on variables or method parameters values. 

## Work in progress

*Please note that this project is in early development stage so it's API can change without notice until release.*


## How to use it?

### Select

```java
// Add static imports to have faster access to helper methods
import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.sort.Sorts.*;

// Entity manager must be provided eg. by dependency injection
private EntityManager em;

// Retrieve all entities
Select.using(em).from(Entity.class).list();
```
### Select with paging
```java
// Retrieve first 10 results
Select.using(em).from(Entity.class).list(PageRequest.of(0,10));
```
### Distinct select
```java
// Retrieve distinct results
Select.using(em).distinct().from(Entity.class).list();
```
### Sorting
```java
// Sort ascending, attribute() is Sorts class static method
Select.using(em).from(Entity.class).orderBy(attribute(Entity_.id)).list();
// Sort descending
Select.using(em).from(Entity.class).orderBy(attribute(Entity_.id).reversed()).list();

// Sort using nested attributes, path() is Sorts class static method,
// get() is Paths class static method
Select.using(em).from(Entity.class).orderBy(path(get(Entity_.parent).get(Parent_.id)).list();

// You can also combine multiple sort parameters using Sorts class sorts() and by() static methods,
// composing them with then() method  
Select.using(em).from(Parent.class).orderBy(sorts(by(Entity_.id)).then(by(Entity_.name))).list();

// You can use above with Supplier in case of more sophisticated orders resolution required
// , eg. based on some variables or parameters values 
Select.using(em).from(Entity.class).orderBy(() -> {
    return Optional.ofNullable(value)
            .map(v -> sorts(by(Entity_.id)).then(by(Entity_.name)))
            .orElse(sorts(by(Entity_.id)));

```
### Single result
```java
// Retrieve single result
Select.using(em).from(Entity.class).get();
```  

## Requirements

### Java 8

You need Java 8 in order to build and use FQB

### JPA Metamodel 

JPA metamodel is required since FQB use it to resolve attributes of your entities for:

* ordering
* grouping
* expressions 

If you use maven, add below plugins configurations to your pom.xml

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

