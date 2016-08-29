# FQB Functional Query Builder aka. FunctionalQube

## Why?

There are many JPA query libraries available.

* Spring Data JPA - I really like it but [TBC]

FQB presents some other approach to this matter. Here below you can find reasons why I have created this library.

1. It was fun and good exercise
2. FQB is type safe, errors are detected during compile time
3. No proxies or other magic under the hood
4. Minimal required dependencies to run, only JPA 2.1 API 

There is one important thing to mention. FQB gives you ability to easily construct JPA queries whose structure can be changed during runtime. 

## How to use it?

### JPA metamodel is required. If you use maven, add below plugins configurations to your pom.xml

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
                            <processor>org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor</processor>
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

### Select


```java
// Entity manager must be provided eg. by dependency injection
private EntityManager em;
// Retrieve all entities
Select.using(em).from(Entity.class).list();
// Retrieve first 10 results
Select.using(em).from(Entity.class).list(Page.of(0,10));
```  