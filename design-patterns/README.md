# Design Patterns Demo — Java (Maven)

This repository is a complete Java Maven project demonstrating six design patterns across Behavioral, Creational, and Structural categories. Each class is placed in its own file and follows Java best practices. The project includes logging (SLF4J + Logback), defensive programming, transient error handling (RetryPolicy), input validation, and an interactive long-running console app (menu driven) — implemented without a hard-coded `while(true)` loop.

## File tree

```
design-patterns-demo/
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

## How to build and run (step-by-step)

1. **Requirements**: Java 17+, Maven 3.6+.
2. Clone the project or create the folder structure above and place files accordingly.
3. From project root run: `mvn clean package` — this produces a shaded executable JAR in `target/`.
4. Run the app: `java -jar target/design-patterns-demo-1.0-SNAPSHOT.jar`.
5. Use the interactive menu in the console: type `1`..`6` to run the specific pattern demo, `menu` to show menu, and `exit` to stop.

## How to publish to GitHub (commands)

1. Initialize git and commit:

```bash
git init
git add .
git commit -m "Add Design Patterns Demo Java project"
```

2. Create a repository on GitHub (two options):

**A) Using GitHub web UI**
- Create a new repo named `design-patterns-demo`.
- Follow instructions to add remote and push:

```bash
git remote add origin git@github.com:<your-username>/design-patterns-demo.git
git branch -M main
git push -u origin main
```

**B) Using GitHub CLI** (if installed):

```bash
gh repo create design-patterns-demo --public --source=. --remote=origin --push
```

After pushing, you'll have a GitHub link like `https://github.com/<your-username>/design-patterns-demo` which you can share.
