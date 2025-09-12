# JavaRIG

[![Maven Central](https://img.shields.io/maven-central/v/io.github.oubaydos/java-random-generator?logo=apache-maven)](https://central.sonatype.com/artifact/io.github.oubaydos/java-random-generator)
[![Javadocs](https://javadoc.io/badge2/io.github.oubaydos/java-random-generator/javadoc.svg)](https://javadoc.io/doc/io.github.oubaydos/java-random-generator)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Description
---
Small, reflection-based random instance generator for Java. Given a type, JavaRIG builds a random instance by walking setters (and common JDK types), with guards to prevent infinite recursion.

Latest release: 1.1.1 (2023-10-28).

Supported types
---
> Source of truth: `io.javarig.TypeEnum` and the corresponding `TypeGenerator`.

| Category | Types |
|---|---|
| Primitives / wrappers | `Integer`, `Long`, `Double`, `Float`, `Short`, `Byte`, `Boolean`, `Character` |
| Text | `String` |
| Time | `java.time.Instant`, `java.util.Date`, `java.time.LocalDate` |
| Collections | `List` / `ArrayList` |
| Sets | `Set`, `HashSet`, `LinkedHashSet`, `TreeSet` |
| Maps | `Map`, `HashMap`, `TreeMap` |
| Other | Arrays, Enums, arbitrary Objects (via public no-args ctor + setters) |

Install
---
### Maven
```xml
<dependency>
  <groupId>io.github.oubaydos</groupId>
  <artifactId>java-random-generator</artifactId>
  <version>1.1.1</version>
  <scope>test</scope>
</dependency>
```

### Gradle

```gradle
gradletestImplementation("io.github.oubaydos:java-random-generator:1.1.1")
```

Artifact coordinates from Maven Central. Maven Repository

Quick start
---

```java
import io.javarig.RandomInstanceGenerator;

RandomInstanceGenerator rig = new RandomInstanceGenerator();

// 1) Simple POJO
User user = rig.generate(User.class);

// 2) Generics (e.g., Map<String, Integer>)
java.util.Map<String, Integer> scores = rig.generate(Map.class, String.class, Integer.class);

// 3) Collections with exact size
java.util.List<String> names = rig.withSize(10).generate(ArrayList.class, String.class);

// 4) Collections with size range [min, max)
java.util.Set<Long> ids = rig.withSize(3, 7).generate(HashSet.class, Long.class);

// 5) Regex-constrained String
String phone = rig.withRegexPattern("[a-zA-Z0-9,;:!?]").generate(String.class);

// 6) Arrays & enums
Color[] palette = rig.withSize(4).generate(Color[].class);
```

API essentials
---
#### RandomInstanceGenerator

```java
<T> T generate(Type objectType);
<T> T generate(Type objectType, Type... genericTypes);
RandomInstanceGenerator withSize(int size);                  // exact size
RandomInstanceGenerator withSize(int minSizeInclusive, int maxSizeExclusive);
RandomInstanceGenerator withRegexPattern(String regex);
RandomInstanceGenerator withOneTimeConfig(Configuration cfg);
```

- Use `withSize(...)` to control sizes for arrays/collections/strings for the next generation only (one-time config).
- `withRegexPattern(...)` constrains String values for the next generation. 
- Cycles in object graphs cause `NestedObjectRecursionException`.

Requirements & limitations
---

- POJO generation expects a **public no-args constructor** and **public setters** for fields to populate. 
- The generator keeps an internal stack to detect recursion; mutating and not thread-safe → create a generator per thread if needed. 
- Regex support is a practical subset; unsupported tokens are sanitized before use. 
- Defaults (e.g., size ranges) come from `Configuration`; override with `withSize(...)`.

Why JavaRIG?
---

It’s a tiny dependency that does the basics well handy for tests and fakes without bringing a heavyweight fixture library.

Roadmap
---

- More built-in generators (BigDecimal, UUID, etc.)
- Thread-safe mode 
- Public extensibility for custom TypeGenerators

Contributing
---

Issues and PRs welcome. Keep changes small and covered by tests. (See repo issues.) GitHub

License
---
MIT. See LICENSE. GitHub

Links
---

- **Repo**: github.com/oubaydos/javaRIG GitHub 
- **Maven Central artifact page**: io.github.oubaydos:java-random-generator Maven Repository

---
