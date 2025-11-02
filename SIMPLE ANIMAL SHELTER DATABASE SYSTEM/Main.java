import org.python.util.PythonInterpreter;

public class Main {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("welcome.py");

        // Call the welcome() function
        interpreter.exec("welcome()");

        interpreter.close();
        // Create an animal shelter object and start the program
        AnimalShelter animalShelter = new AnimalShelter();
        animalShelter.start();
    }
}

