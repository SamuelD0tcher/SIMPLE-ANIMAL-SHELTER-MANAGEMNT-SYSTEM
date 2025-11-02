import java.io.*;
import java.util.*;

public class FileHandler {
    private final String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

    public List<Animal> readFromFile() {
        // code to read data from file
        List<Animal> animals = new ArrayList<>();
        try {
            File file = new File(filename);
            if (!file.exists()) {
                return animals;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length != 4 && parts.length != 5) {
                    continue;
                }
                String kind = parts[0];
                String name = parts[1];
                String gender = parts[2];
                int age = Integer.parseInt(parts[3]);
                String furcolour = " ";
                if (parts.length >= 5 && kind.equals("Dog")) {
                    if (parts[4] != null)
                        furcolour = parts[4];
                    Dog dog = new Dog(kind, name, gender, age, furcolour);
                    animals.add(dog);
                } else {
                    Animal animal = new Animal(kind, name, gender, age, furcolour);
                    animals.add(animal);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return animals;
    }

    public void writeToFile(List<Animal> animals) {
        // code to write data into
        try {
            FileWriter writer = new FileWriter(filename);
            for (Animal animal : animals) {
                if (animal instanceof Dog) {
                    Dog dog = (Dog) animal;
                    writer.write(String.format("%s,%s,%s,%d,%s\n", dog.getKind(), dog.getName(), dog.getGender(),
                            dog.getAge(), dog.getFurcolour()));
                } else {
                    writer.write(String.format("%s,%s,%s,%d\n", animal.getKind(), animal.getName(), animal.getGender(),
                            animal.getAge()));
                }

            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}