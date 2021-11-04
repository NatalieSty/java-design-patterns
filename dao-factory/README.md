---
layout: pattern
title: Dao Factory
folder: dao-factory
permalink: /patterns/dao-factory/
categories: Creational
language: en
tags:
- Decoupling
---

## Intent


## Explanation


## Class Diagram

## Applicability

## Known uses

## Related patterns

**Transfer Object**: A DAO uses Transfer Objects to transport data to and from its clients.

**Factory Method [GoF] and Abstract Factory [GoF]**: The Factory for Data Access Objects Strategy uses the Factory Method pattern to implement the concrete factories and its products (DAOs). For added flexibility, the Abstract Factory pattern may be employed as discussed in the strategies.

**Broker [POSA1]**: The DAO pattern is related to the Broker pattern, which describes approaches for decoupling clients and servers in distributed systems. The DAO pattern more specifically applies this pattern to decouple the resource tier from clients in another tier, such as the business or presentation tier