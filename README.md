<!-- the line below needs to be an empty line C: (its because kramdown isnt
     that smart and dearly wants an empty line before a heading to be able to
     display it as such, e.g. website) -->

# Design pattern samples in Java

[![Join the chat at https://gitter.im/iluwatar/java-design-patterns](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/iluwatar/java-design-patterns?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

![Build status](https://travis-ci.org/iluwatar/java-design-patterns.svg?branch=master) [![Coverage Status](https://coveralls.io/repos/iluwatar/java-design-patterns/badge.svg?branch=master)](https://coveralls.io/r/iluwatar/java-design-patterns?branch=master) <a href="https://scan.coverity.com/projects/5634">
  <img alt="Coverity Scan Build Status"
       src="https://scan.coverity.com/projects/5634/badge.svg"/>
</a>


#### Browse and view all of the patterns on our Website: [iluwatar.github.io/java-design-patterns/](https://iluwatar.github.io/java-design-patterns/)

<a name="top"/>

# <a name="toc">Table of Contents</a>
 - <a href="#introduction">Introduction</a>
 - <a href="#faq">Frequently Asked Questions</a>
 - <a href="#how-to-contribute">How to contribute</a>
 - <a href="#versioning">Versioning</a>
 - <a href="#credits">Credits</a>
 - <a href="#license">License</a>


## <a name="introduction">Introduction</a>

Design patterns are formalized best practices that the programmer can use to
solve common problems when designing an application or system.

Design patterns can speed up the development process by providing tested, proven
development paradigms.

Reusing design patterns helps to prevent subtle issues that can cause major
problems, and it also improves code readability for coders and architects who
are familiar with the patterns.


# <a name="faq">Frequently asked questions</a> [&#8593;](#top)

**<a id="Q1">Q: What is the difference between State and Strategy patterns?</a>**

While the implementation is similar they solve different problems. The State
pattern deals with what state an object is in - it encapsulates state-dependent
behavior.
The Strategy pattern deals with how an object performs a certain task - it
encapsulates an algorithm.

**<a id="Q2">Q: What is the difference between Strategy and Template Method patterns?</a>**

In Template Method the algorithm is chosen at compile time via inheritance.
With Strategy pattern the algorithm is chosen at runtime via composition.

**<a id="Q3">Q: What is the difference between Proxy and Decorator patterns?</a>**

The difference is the intent of the patterns. While Proxy controls access to
the object Decorator is used to add responsibilities to the object.

**<a id="Q4">Q: What is the difference between Chain of Responsibility and Intercepting Filter patterns?</a>**

While the implementations look similar there are differences. The Chain of
Responsibility forms a chain of request processors and the processors are then
executed one by one until the correct processor is found. In Intercepting
Filter the chain is constructed from filters and the whole chain is always
executed.

**<a id="Q5">Q: What is the difference between Visitor and Double Dispatch patterns?</a>**

The Visitor pattern is a means of adding a new operation to existing classes.
Double dispatch is a means of dispatching function calls with respect to two
polymorphic types, rather than a single polymorphic type, which is what
languages like C++ and Java _do not_ support directly.

**<a id="Q6">Q: What are the differences between Flyweight and Object Pool patterns?</a>**

They differ in the way they are used.

Pooled objects can simultaneously be used by a single "client" only. For that,
a pooled object must be checked out from the pool, then it can be used by a
client, and then the client must return the object back to the pool. Multiple
instances of identical objects may exist, up to the maximal capacity of the
pool.

In contrast, a Flyweight object is singleton, and it can be used simultaneously
by multiple clients.

As for concurrent access, pooled objects can be mutable and they usually don't
need to be thread safe, as typically, only one thread is going to use a
specific instance at the same time. Flyweight must either be immutable (the
best option), or implement thread safety.

As for performance and scalability, pools can become bottlenecks, if all the
pooled objects are in use and more clients need them, threads will become
blocked waiting for available object from the pool. This is not the case with
Flyweight.



# <a name="how-to-contribute">How to contribute</a> [&#8593;](#top)

**To work on a new pattern** you need to do the following steps:

1. If there is no issue for the new pattern yet, raise new issue. Comment on
   the issue that you are working on it so that others don't start work on the
   same thing.
2. Fork the repository.
3. Create a new folder for the pattern. The rough structure of the new folder would be as follows:
    * etc (every resource related to the pattern, like diagrams)
    * src (the source code of the pattern)
    * index.md (the description of the pattern)
    * pom.xml (the maven pom.xml)
4. Implement the code changes in your fork. Remember to add sufficient comments
   documenting the implementation. Reference the issue id e.g. #52 in your
   commit messages.
5. Format the code according to [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
   * [Eclipse configuration](https://github.com/google/styleguide/blob/gh-pages/eclipse-java-google-style.xml)
   * [IntelliJ configuration](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)
6. Create a simple class diagram from your example code and put it inside of the etc folder.
7. Add description of the pattern in index.md and link to the class diagram.
   (Attention, all internal links must be relative to the pattern subdirectory, else the links dont link properly on the website)
8. Create a pull request.

**Structure of the index.md file**

```markdown
--- # this is so called 'Yaml Front Matter', read up on it here: http://jekyllrb.com/docs/frontmatter/
layout: pattern # layout must allways be pattern
title: Best Pattern Ever # the properly formatted title
folder: best-pattern-ever # the folder name in which this pattern lies
permalink: /patterns/best-pattern-ever/ # the permalink to the pattern, to keep this uniform please stick to /patterns/FOLDER/

# both categories and tags are Yaml Lists
# you can either just pick one or write a list with '-'s
# usable categories and tags are listed here: https://github.com/iluwatar/java-design-patterns/blob/gh-pages/_config.yml
categories: creational # categories of the pattern
tags: # tags of the pattern
 - best
 - ever
 - awesome
---

**Intent:** Makes your code awesome

![alt text](./etc/best_pattern.png "Best Pattern Ever")

**Applicability:** Use the Best Pattern Ever pattern when

* you want to be the best
* you need to ...

**Real world examples:**

* [Nowhere](http://no.where.com)
```

**To add a new category or tag** you need to edit the _config.yml file of the gh-pages branch.
In there you should find 2 yaml lists with the respective names 'category-list' and 'tag-list'

**To work on one of the non-pattern issues** you need to do the following steps:

1. Check that the issue has "help wanted" badge
2. Comment on the issue that you are working on it
3. Fork the repository.
4. Implement the code changes in your fork. Remember to add sufficient comments
   documenting the implementation. Reference the issue id e.g. #52 in your
   commit messages.
5. Create a pull request.

**For creating/editing UML diagrams** you need [ObjectAid UML Explorer for Eclipse](http://www.objectaid.com/home).

**For inspiration** check out the following sources:

* there is a good list of design patterns at [Wikipedia](http://en.wikipedia.org/wiki/Software_design_pattern)
* Martin Fowler's [Catalog of Patterns of Enterprise Application Architecture](http://martinfowler.com/eaaCatalog/)
* [pattern language for microservices](http://microservices.io/patterns/index.html)
* Microsoft's [Cloud Design Patterns](http://download.microsoft.com/download/B/B/6/BB69622C-AB5D-4D5F-9A12-B81B952C1169/CloudDesignPatternsBook-PDF.pdf)

**Links to patterns applied in real world applications** are welcome. The links
should be added to the corresponding section of the `index.md`.


# <a name="versioning">Versioning</a> [&#8593;](#top)

Java-design-patterns project uses [semantic versioning](http://semver.org/)
scheme. However, version numbers in this project do not signify binary releases
(since we don't make any) but rather milestones achieved on the roadmap. In
other words, version numbers are used only for project planning sake.


# <a name="credits">Credits</a> [&#8593;](#top)

* [Design Patterns: Elements of Reusable Object-Oriented Software](http://www.amazon.com/Design-Patterns-Elements-Reusable-Object-Oriented/dp/0201633612)
* [Effective Java (2nd Edition)](http://www.amazon.com/Effective-Java-Edition-Joshua-Bloch/dp/0321356683)
* [Java Generics and Collections](http://www.amazon.com/Java-Generics-Collections-Maurice-Naftalin/dp/0596527756/)
* [Let's Modify the Objects-First Approach into Design-Patterns-First](http://edu.pecinovsky.cz/papers/2006_ITiCSE_Design_Patterns_First.pdf)
* [Pattern Languages of Program Design](http://www.amazon.com/Pattern-Languages-Program-Design-Coplien/dp/0201607344/ref=sr_1_1)
* [Martin Fowler - Event Aggregator](http://martinfowler.com/eaaDev/EventAggregator.html)
* [TutorialsPoint - Intercepting Filter](http://www.tutorialspoint.com/design_pattern/intercepting_filter_pattern.htm)
* [Presentation Tier Patterns](http://www.javagyan.com/tutorials/corej2eepatterns/presentation-tier-patterns)
* [Functional Programming in Java: Harnessing the Power of Java 8 Lambda Expressions](http://www.amazon.com/Functional-Programming-Java-Harnessing-Expressions/dp/1937785467/ref=sr_1_1)
* [Martin Fowler - Service Layer](http://martinfowler.com/eaaCatalog/serviceLayer.html)
* [Martin Fowler - Specifications](http://martinfowler.com/apsupp/spec.pdf)
* [Martin Fowler - Tolerant Reader](http://martinfowler.com/bliki/TolerantReader.html)
* [Trygve Reenskaug - Model-view-controller](http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)
* [Flux - Application architecture for building user interfaces](http://facebook.github.io/flux/)
* [Richard Pawson - Naked Objects](http://downloads.nakedobjects.net/resources/Pawson%20thesis.pdf)
* [Patterns of Enterprise Application Architecture](http://www.amazon.com/Patterns-Enterprise-Application-Architecture-Martin/dp/0321127420)
* [Spring Data](http://www.amazon.com/Spring-Data-Mark-Pollack/dp/1449323952/ref=sr_1_1)
* [J2EE Design Patterns](http://www.amazon.com/J2EE-Design-Patterns-William-Crawford/dp/0596004273/ref=sr_1_2)
* [Marco Castigliego - Step Builder](http://rdafbn.blogspot.co.uk/2012/07/step-builder-pattern_28.html)
* [Douglas C. Schmidt and Charles D. Cranor - Half Sync/Half Async](http://www.cs.wustl.edu/~schmidt/PDF/PLoP-95.pdf)
* [Pattern Oriented Software Architecture Vol I-V](http://www.amazon.com/Pattern-Oriented-Software-Architecture-Volume-Patterns/dp/0471958697)


# <a name="license">License</a> [&#8593;](#top)

This project is licensed under the terms of the MIT license.
