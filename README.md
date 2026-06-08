# Community-Worker-App
Mainly showcases my Unit Testing skills alongside my OOP Programming skills in Java

# 🚲 Bicycle Applet (Java Graphics Project)

## Overview

The Bicycle Applet is a Java Graphics project that demonstrates object-oriented programming, event-driven programming, and custom drawing using the Java 2D API. The application renders a scalable bicycle using geometric shapes and allows users to interact with it through mouse events.

## Features

- Draws a complete bicycle using Java 2D Graphics
- Object-Oriented Design with separate classes
- Scalable bicycle rendering
- Interactive mouse controls
- Dynamic bicycle repositioning
- Bicycle color customization
- Anti-aliased graphics for improved visual quality

## Technologies Used

- Java
- Java Applets
- Java 2D Graphics (Graphics2D)
- Object-Oriented Programming (OOP)
- Event Handling
- Swing Components

## Project Structure

```text
Bicycle Project
│
├── Bicycle.java
│   ├── Bicycle drawing logic
│   ├── Wheel rendering
│   ├── Frame rendering
│   ├── Handlebar rendering
│   └── Position and color management
│
└── BicycleApplet.java
    ├── User interaction
    ├── Mouse event handling
    ├── Coordinate input
    └── Bicycle display control
```

## How It Works

1. The user enters the initial rear-wheel coordinates.
2. The bicycle object is created and stored.
3. Pressing any mouse button displays the bicycle.
4. Middle-click changes the bicycle color to magenta.
5. Right-click prompts the user for new coordinates and moves the bicycle.
6. The bicycle is redrawn at the new location.

## Mouse Controls

| Action | Result |
|----------|----------|
| Any Mouse Button Press | Display Bicycle |
| Middle Click | Change Bicycle Color to Magenta |
| Right Click | Move Bicycle to New Coordinates |

## Concepts Demonstrated

### Object-Oriented Programming
- Classes and Objects
- Constructors
- Encapsulation
- Getters and Setters

### Java Graphics
- Graphics2D
- Shapes and Paths
- Rendering Hints
- Coordinate Transformations

### Event-Driven Programming
- MouseListener Interface
- User Interaction Handling
- Dynamic Repainting

## Sample Test Coordinates

```text
Initial Position:
X = 90
Y = 200

Move Tests:
X = 120, Y = 210
X = 150, Y = 180
```

## Skills Demonstrated

- Java Programming
- Object-Oriented Design
- GUI Development
- Java 2D Graphics
- Event Handling
- Problem Solving
- Software Design Principles

## Future Improvements

- Bicycle animation
- Keyboard controls
- Custom color picker
- Multiple bicycle instances
- Save and load bicycle positions
- Migration from Applets to Java Swing or JavaFX

## Author

**Sizwe Ramokhali**

Software Developer | Java Developer | Networking Enthusiast
