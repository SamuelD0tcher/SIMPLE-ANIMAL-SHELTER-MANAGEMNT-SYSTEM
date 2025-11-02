import java.util.*;
import javax.swing.*;

public class AnimalShelter {
    private List<Animal> animals;
    private FileHandler fileHandler;

    public AnimalShelter() {
        animals = new ArrayList<>();
        fileHandler = new FileHandler("animals.txt");

    }

    public void start() {
        // Load data from file
        animals = fileHandler.readFromFile();

        boolean exit = false;
        while (!exit) {
            String[] options = { "Add new animal", "Edit animal", "Search animal", "Delete animal", "View all animals",
                    "Exit" };
            int choice = JOptionPane.showOptionDialog(null, "Select an option:", "Animal Shelter",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            // options to add,edit,search,delete and view animals
            switch (choice) {
                case 0:
                    addAnimal();
                    break;
                case 1:
                    editAnimal();
                    break;
                case 2:
                    searchAnimal();
                    break;
                case 3:
                    deleteAnimal();
                    break;
                case 4:
                    viewAllAnimals();
                    break;
                case 5:
                    // exit option to terminate the program
                    exit();
                    exit = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option selected", "Animal Shelter",
                            JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }

        // Save data to file
        fileHandler.writeToFile(animals);
    }

    // methods of the options
    private void exit() {
        JOptionPane.showMessageDialog(null, "Thanks for using the animal shelter database");
    }

    private void addAnimal() {

        String kind = JOptionPane.showInputDialog("Enter animal kind:");
        while (kind == null || kind.trim().isEmpty() || !isString(kind)) {
            kind = JOptionPane.showInputDialog("Invalid input. Please enter animal kind:");
        }

        String name = JOptionPane.showInputDialog("Enter animal name:");
        while (name == null || name.trim().isEmpty() || !isString(name)) {
            name = JOptionPane.showInputDialog("Invalid input. Please enter animal name:");
        }

        String gender = JOptionPane.showInputDialog("Enter animal gender(M/F):");
        while (gender == null || gender.trim().isEmpty()
                || !gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F") || !isString(gender)) {
            gender = JOptionPane.showInputDialog("Invalid input. Please enter animal gender(M/F):");
        }

        String ageString = JOptionPane.showInputDialog("Enter animal age:");
        while (ageString == null || ageString.trim().isEmpty() || !isNumeric(ageString)) {
            ageString = JOptionPane.showInputDialog("Invalid input. Please enter animal age:");
        }

        int age = Integer.parseInt(ageString);
        if (kind.equals("Dog")) {
            String furcolour = JOptionPane.showInputDialog("Enter fur colour:");
            while (furcolour == null || furcolour.trim().isEmpty() || !isString(furcolour)) {
                furcolour = JOptionPane.showInputDialog("Invalid input. Please enter fur colour:");
            }
            Dog dog = new Dog(kind, name, gender, age, furcolour);
            animals.add(dog);
        } else {
            Animal animal = new Animal(kind, name, gender, age, ageString);
            animals.add(animal);
        }
        JOptionPane.showMessageDialog(null, "Animal added successfully", "Animal Shelter",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isString(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    private boolean isNumeric(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void editAnimal() {
        String name = JOptionPane.showInputDialog("Enter animal name to edit:");
        while (name == null || name.trim().isEmpty() || !isString(name)) {
            name = JOptionPane.showInputDialog("Invalid input. Please enter animal name:");
        }
        Animal animal = findAnimalByName(name);
        if (animal == null) {
            name = JOptionPane.showInputDialog("Animal not found. Please enter another name:");
            animal = findAnimalByName(name);
            while (animal == null) {
                name = JOptionPane.showInputDialog("Animal not found. Please enter another name:");
                animal = findAnimalByName(name);
            }
        }
        String kind = JOptionPane.showInputDialog("Enter new animal kind (leave blank to keep current value):");
        while (kind == null || !isString(kind) || !kind.trim().isEmpty() && !isString(kind)) {
            kind = JOptionPane.showInputDialog("Invalid input. Please enter animal kind:");
        }
        if (!kind.isEmpty()) {
            animal.setKind(kind);
        }
        String gender = JOptionPane
                .showInputDialog("Enter new animal gender(M/F) (leave blank to keep current value):");
        while (gender == null || !isString(gender)
                || !gender.trim().isEmpty() && !gender.equalsIgnoreCase("M") && !gender.equalsIgnoreCase("F")) {
            gender = JOptionPane.showInputDialog("Invalid input. Please enter animal gender(M/F):");
        }
        if (!gender.isEmpty()) {
            animal.setGender(gender);
        }
        String ageString = JOptionPane.showInputDialog("Enter new animal age (leave blank to keep current value):");
        while (ageString == null || !isNumeric(ageString)) {
            ageString = JOptionPane.showInputDialog("Invalid input. Please enter animal age:");
        }
        if (!ageString.isEmpty()) {
            int age = Integer.parseInt(ageString);
            animal.setAge(age);

        }
        JOptionPane.showMessageDialog(null, "Animal updated successfully", "Animal Shelter",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void searchAnimal() {
        boolean found = false;
        while (!found) {
            String name = JOptionPane.showInputDialog("Enter animal name to search:");
            Animal animal = findAnimalByName(name);
            if (animal == null) {
                int option = JOptionPane.showConfirmDialog(null, "Animal not found.\nDo you want to try again?",
                        "Animal Shelter", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, animal.toString(), "Animal Shelter",
                        JOptionPane.INFORMATION_MESSAGE);
                found = true;
            }
        }
    }

    private void deleteAnimal() {
        boolean found = false;
        while (!found) {
            String name = JOptionPane.showInputDialog("Enter animal name to delete:");
            Animal animal = findAnimalByName(name);
            if (animal == null) {
                int option = JOptionPane.showConfirmDialog(null, "Animal not found.\nDo you want to try again?",
                        "Animal Shelter", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.NO_OPTION) {
                    return;
                }
            } else {
                animals.remove(animal);
                JOptionPane.showMessageDialog(null, "Animal deleted successfully", "Animal Shelter",
                        JOptionPane.INFORMATION_MESSAGE);
                found = true;
            }
        }
    }

    private void viewAllAnimals() {
        Collections.sort(animals, new Comparator<Animal>() {
            @Override
            public int compare(Animal a1, Animal a2) {
                return a1.getName().compareToIgnoreCase(a2.getName());
            }
        });

        StringBuilder sb = new StringBuilder();
        for (Animal animal : animals) {
            sb.append(animal.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Animal Shelter", JOptionPane.INFORMATION_MESSAGE);
    }

    private Animal findAnimalByName(String name) {
        for (Animal animal : animals) {
            if (animal.getName().toUpperCase().equals(name.toUpperCase())) {
                return animal;
            }
        }
        return null;
    }
}