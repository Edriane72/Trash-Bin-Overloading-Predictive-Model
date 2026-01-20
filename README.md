# Trash Bin Overloading Predictive Model

This project simulates trash bin filling over time and predicts which bins are most likely to overflow.  
It uses a Python backend for the simulation and a Java Swing GUI to display rankings and bin status.

## Features
- Run simulations with different scenarios (normal, sunny, rain, etc.)
- Advance time by 1 hour to simulate trash accumulation
- Clear all bins
- Collect only the full bins
- Color-coded fill levels (white / yellow / red)

## How It Works
- Python runs the simulation and saves results to CSV files.
- Java reads the CSV files and displays the ranking in a table.
- The GUI buttons control the simulation (run, +1 hour, clear, collect).

## How to Run

### Compile the Java GUI
From the project root folder:

```bash
javac -d . java_gui\main\*.java java_gui\model\*.java java_gui\view\*.java java_gui\controller\*.java
Run the program
bash
Copy code
java java_gui.main.AppMain
Make sure Python is installed and added to your system PATH.

Project Structure
python_backend/ – Python simulation logic

java_gui/ – Java Swing GUI (view, controller, model)

output/ – generated CSV files (results and bin state)

Notes
This project was created for learning and demonstration purposes.
The simulation logic is simplified and not based on real sensor data.