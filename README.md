# Educational Initiatives Repository

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-C71A36.svg)](https://maven.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A comprehensive collection of educational Java projects demonstrating software design patterns and practical application development. This repository serves as a learning resource for developers interested in mastering object-oriented design principles and building robust Java applications.

## 📁 Repository Structure

```
educational-initiatives/
├── design-patterns/          # Design Patterns Demonstration Project
│   ├── src/main/java/com/example/designpatterns/
│   ├── pom.xml
│   └── README.md
├── astronaut-scheduler/      # Astronaut Daily Schedule Organizer
│   ├── src/main/java/com/astronaut/scheduler/
│   ├── pom.xml
│   └── README.md
└── README.md                 # This file
```

## 🚀 Projects

### 1. Design Patterns Demo (`design-patterns/`)
A comprehensive Java Maven project demonstrating six essential design patterns across Behavioral, Creational, and Structural categories. Each pattern is implemented with practical examples and follows Java best practices.

**Key Features:**
- **Strategy Pattern**: Dynamic discount strategies for shopping carts
- **Observer Pattern**: Event-driven notifications for order status changes
- **Factory Method**: Polymorphic document parsing (JSON/XML)
- **Builder Pattern**: Flexible report construction with optional components
- **Adapter Pattern**: Payment gateway interface adaptation
- **Decorator Pattern**: Dynamic message service enhancements (compression/encryption)

**Technologies:** Java 17+, Maven, SLF4J + Logback, Interactive console application

### 2. Astronaut Daily Schedule Organizer (`astronaut-scheduler/`)
A robust console application designed to help astronauts efficiently manage their daily tasks and schedules. Built with enterprise-grade patterns and error handling.

**Key Features:**
- Task management with time slots and priorities (HIGH/MEDIUM/LOW)
- Conflict detection and notification system
- Observer pattern for real-time conflict alerts
- Factory pattern for task creation
- Comprehensive error handling and logging
- Interactive menu-driven interface

**Technologies:** Java 17+, Maven, SLF4J + Logback, Observer & Factory patterns

## 🛠️ Getting Started

### Prerequisites
- **Java 17+** - Download from [OpenJDK](https://openjdk.java.net/)
- **Maven 3.6+** - Download from [Maven](https://maven.apache.org/)

### Clone and Build
```bash
# Clone the repository
git clone https://github.com/cataclicyclic/educational-initiatives.git
cd educational-initiatives

# Build all projects
mvn clean package
```

### Run Projects

#### Design Patterns Demo
```bash
cd design-patterns
java -jar target/design-patterns-1.0-SNAPSHOT.jar
```

#### Astronaut Scheduler
```bash
cd astronaut-scheduler
java -jar target/astronaut-scheduler-1.0-SNAPSHOT.jar
```

## 📚 Learning Objectives

This repository demonstrates:

### Software Design Principles
- **SOLID Principles**: Single responsibility, Open-closed, Liskov substitution, Interface segregation, Dependency inversion
- **DRY (Don't Repeat Yourself)**: Code reusability and maintainability
- **KISS (Keep It Simple, Stupid)**: Clean and understandable code

### Design Patterns Mastery
- **Behavioral Patterns**: Strategy, Observer
- **Creational Patterns**: Factory Method, Builder
- **Structural Patterns**: Adapter, Decorator

### Java Best Practices
- Exception handling and validation
- Logging with SLF4J and Logback
- Maven project structure and dependency management
- Interactive console applications
- Input validation and error recovery

## 🎯 Use Cases

### For Students
- Learn design patterns through practical implementations
- Understand Java project structure and Maven builds
- Study clean code principles and best practices

### For Developers
- Reference implementations for common design patterns
- Examples of robust error handling and logging
- Templates for console-based Java applications

### For Educators
- Teaching materials for software design courses
- Practical examples for design pattern lectures
- Code review examples and discussions

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

### Contribution Guidelines
- Follow Java naming conventions and best practices
- Add comprehensive documentation and comments
- Include unit tests for new features
- Update README files as needed

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙋‍♂️ Support

If you have questions, suggestions, or need help:

- **Open an Issue**: For bugs, feature requests, or general questions
- **Pull Request**: For code contributions and improvements
- **Discussions**: For general discussions and learning

## 🔗 Related Resources

- [Design Patterns - Gang of Four](https://en.wikipedia.org/wiki/Design_Patterns)
- [Java Documentation](https://docs.oracle.com/en/java/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [SLF4J Documentation](http://www.slf4j.org/manual.html)

---


