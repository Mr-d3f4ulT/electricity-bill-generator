# ⚡ Electricity Bill Generator

## 📘 Description

**Electricity Bill Generator** is a Java-based desktop application designed to automate the process of generating electricity bills based on user input. It allows users to input customer details and the number of electricity units consumed, calculates the total amount based on a fixed rate, and displays the bill in a formatted layout. Users can also export the bill as a professional PDF document.

This project demonstrates core **Object-Oriented Programming** concepts in Java and uses **Java Swing** for building the GUI and **iText** library for generating PDFs. It is a great educational tool and a practical utility app for basic billing needs.

---

## 🧠 Features

- GUI for easy input of customer data
- Real-time bill calculation
- PDF export functionality
- Clean object-oriented architecture
- Error handling for user-friendly experience

---

## 🛠️ Technologies & Concepts

- Java (OOP, Swing, Exception Handling)
- iText PDF library for export
- Java I/O (FileOutputStream)
- MVC-style separation
- Git for version control

---

## 🧱 Project Structure

| File               | Description |
|--------------------|-------------|
| `Main.java`        | Launches the application by initializing the GUI. |
| `Customer.java`    | A model class to hold customer information. |
| `Bill.java`        | Stores billing details like units, rate, total amount. |
| `BillService.java` | Contains business logic to generate the bill from raw input. |
| `BillingUI.java`   | Java Swing GUI that collects user input and displays/export bill. |
| `PDFExporter.java` | Exports the bill to a PDF using iText. |

---

## 📥 Installation & Usage

### 🔧 Prerequisites
- Java 8 or higher
- Git installed
- iText PDF JAR file (v2.1.7 recommended)

### ▶️ Steps

```bash
# 1. Clone the repo
git clone https://github.com/your-username/electricity-bill-generator.git
cd electricity-bill-generator

# 2. Compile the project
javac -cp ".;libs/itext-2.1.7.jar" *.java

# 3. Run the application
java -cp ".;libs/itext-2.1.7.jar" Main
