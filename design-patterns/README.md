# Design Patterns Demo — Java (Maven)

This repository is a complete Java Maven project demonstrating six design patterns across Behavioral, Creational, and Structural categories. Each class is placed in its own file and follows Java best practices. The project includes logging (SLF4J + Logback), defensive programming, transient error handling (RetryPolicy), input validation, and an interactive long-running console app (menu driven) — implemented without a hard-coded `while(true)` loop.

## Design Patterns Included

### 1. Strategy Pattern (Behavioral)
**Purpose:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable.  
**Use case:** Apply different discount strategies to a shopping cart at runtime.  
**Demo:** Shows no discount, percentage discount, and bulk discount strategies applied to cart items.

### 2. Observer Pattern (Behavioral)
**Purpose:** Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified.  
**Use case:** Notify multiple observers (email, audit log) when order status changes.  
**Demo:** Order status updates trigger notifications to email notifier and audit logger.

### 3. Factory Method (Creational)
**Purpose:** Defines an interface for creating objects, but lets subclasses decide which class to instantiate.  
**Use case:** Create different document parsers (JSON, XML) without specifying concrete classes.  
**Demo:** Factory creates appropriate parser based on file type and parses sample documents.

### 4. Builder Pattern (Creational)
**Purpose:** Separates the construction of a complex object from its representation, allowing the same construction process to create different representations.  
**Use case:** Build complex reports with optional sections, author, and footer.  
**Demo:** Constructs a report step-by-step with multiple sections and optional footer.

### 5. Adapter Pattern (Structural)
**Purpose:** Allows incompatible interfaces to work together by wrapping one interface with another.  
**Use case:** Adapt a third-party payment processor to work with standard payment gateway interface.  
**Demo:** PaymentAdapter converts ThirdPartyPaymentProcessor's complex API to simple PaymentGateway interface.

### 6. Decorator Pattern (Structural)
**Purpose:** Attaches additional responsibilities to an object dynamically, providing a flexible alternative to subclassing.  
**Use case:** Add compression and encryption to message sending without modifying the core service.  
**Demo:** Decorates a simple message service with compression and encryption decorators.

## File tree

```
design-patterns/
├── pom.xml
├── README.md
├── src
│   └── main
│       └── java
│           └── com
│               └── example
│                   └── designpatterns
│                       ├── Main.java
│                       ├── AppConfig.java
│                       ├── RetryPolicy.java
│                       ├── demos
│                       │   ├── StrategyDemo.java
│                       │   ├── ObserverDemo.java
│                       │   ├── FactoryDemo.java
│                       │   ├── BuilderDemo.java
│                       │   ├── AdapterDemo.java
│                       │   └── DecoratorDemo.java
│                       └── patterns
│                           ├── strategy
│                           │   ├── DiscountStrategy.java
│                           │   ├── NoDiscountStrategy.java
│                           │   ├── PercentageDiscountStrategy.java
│                           │   ├── BulkDiscountStrategy.java
│                           │   ├── Cart.java
│                           │   └── CartItem.java
│                           ├── observer
│                           │   ├── Order.java
│                           │   ├── OrderObserver.java
│                           │   ├── EmailNotifier.java
│                           │   └── AuditLogger.java
│                           ├── factory
│                           │   ├── DocumentParser.java
│                           │   ├── JsonParser.java
│                           │   ├── XmlParser.java
│                           │   └── ParserFactory.java
│                           ├── builder
│                           │   └── Report.java
│                           ├── adapter
│                           │   ├── PaymentGateway.java
│                           │   ├── ThirdPartyPaymentProcessor.java
│                           │   └── PaymentAdapter.java
│                           └── decorator
│                               ├── MessageService.java
│                               ├── SimpleMessageService.java
│                               ├── MessageDecorator.java
│                               ├── CompressionDecorator.java
│                               └── EncryptionDecorator.java
```

## How to Build and Run

1. **Requirements**: Java 17+, Maven 3.6+.
2. **Build**: `mvn clean package` — creates executable JAR in `target/`.
3. **Run**: `java -jar target/design-patterns-1.0-SNAPSHOT.jar`.
4. **Usage**: Type `1`..`6` to run demos, `menu` to show menu, `exit` to quit.

## How to Publish to GitHub

1. Initialize git and commit:
```bash
git init
git add .
git commit -m "Add Design Patterns Demo Java project"
```

2. Create repository on GitHub and push:
```bash
git remote add origin git@github.com:<your-username>/design-patterns.git
git branch -M main
git push -u origin main
