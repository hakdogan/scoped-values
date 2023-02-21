# Scoped Values

The `Scoped Values` API allows us to store and share immutable data for a bounded lifetime. It has been included in the JDK since Java 20 as an incubator API. This repository shows how you can use it with `Virtual Threads` and `Structured Concurrency`.

## Requirement
JDK 20 or later

## Ho to compile?
```bash
mvn clean install
jar cmf ./META-INF/MANIFEST.FM scoped-values.jar -C ./target/classes/ .
```

or

```bash
sh ./compile.sh   
```

## Ho to run?

```bash
java --enable-preview --add-modules jdk.incubator.concurrent -jar scoped-values.jar
```

or

```bash
sh ./run.sh   
```

## Relevant article is
[An introduction to Scoped Values in Java](https://medium.com/@hakdogan/an-introduction-to-scoped-values-in-java-7b63ff4364a6)
