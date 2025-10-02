# Astronaut Daily Schedule Organizer

## Overview
The Astronaut Daily Schedule Organizer is a Java console application designed to help astronauts efficiently manage their daily tasks and schedules. It supports adding, removing, editing, and viewing tasks with priorities and time slots, while ensuring no scheduling conflicts occur.

## Features
- Add tasks with description, start time, end time, and priority (HIGH, MEDIUM, LOW)
- Remove tasks by ID
- View all scheduled tasks
- Edit existing tasks
- Mark tasks as completed
- View tasks filtered by priority
- Conflict detection and notification using observer pattern
- Robust error handling for invalid times, conflicts, and missing tasks
- Logging of operations and errors using SLF4J and Logback

## Technologies
- Java 17+
- Maven for build and dependency management
- SLF4J + Logback for logging
- Observer pattern for conflict notifications
- Factory pattern for task creation

## Getting Started

### Prerequisites
- Java 17 or higher installed
- Maven 3.6 or higher installed

### Build
Clone the repository and navigate to the `astronaut-scheduler` directory:
```bash
git clone https://github.com/cataclicyclic/educational-initiatives.git
cd educational-initiatives/astronaut-scheduler
```

Build the project using Maven:
```bash
mvn clean package
```

### Run
Run the application using the generated JAR file:
```bash
java -jar target/astronaut-scheduler-1.0-SNAPSHOT.jar
```

### Usage
The application runs in the console with a menu-driven interface. Commands include:
- `1` - Add Task
- `2` - Remove Task (by ID)
- `3` - View All Tasks
- `4` - Edit Task
- `5` - Mark Task Completed
- `6` - View Tasks by Priority
- `menu` - Show menu options
- `exit` - Quit the application

### Example
```
=== Astronaut Daily Schedule Organizer ===
1 - Add Task
2 - Remove Task (by ID)
3 - View All Tasks
4 - Edit Task
5 - Mark Task Completed
6 - View Tasks by Priority
menu - show this menu
exit - quit
Enter command: 1
Description: Spacewalk preparation
Start time (HH:mm): 09:00
End time (HH:mm): 11:00
Priority (HIGH, MEDIUM, LOW): HIGH
Task added successfully.
```

## Project Structure
```
astronaut-scheduler/
├── pom.xml
├── README.md
├── src
│   └── main
│       └── java
│           └── com
│               └── astronaut
│                   └── scheduler
│                       ├── Main.java
│                       ├── factory
│                       ├── manager
│                       ├── models
│                       ├── observer
│                       ├── exceptions
│                       └── utils
└── target
```

## Contributing
Contributions are welcome! Please fork the repository and submit pull requests.

## License
This project is licensed under the MIT License.

## Contact
For questions or support, please open an issue on GitHub.
