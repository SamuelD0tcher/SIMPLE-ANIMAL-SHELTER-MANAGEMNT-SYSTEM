public class Animal {
    private String kind;
    private String name;
    private String gender;
    private int age;
    private String furcolour;

    // Constructor to initialize attributes
    public Animal(String kind, String name, String gender, int age, String furcolour2) {
        this.kind = kind;
        this.name = name;
        this.gender = gender;
        this.age = age;

    }

    // Accessors
    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getFurcolour() {
        return furcolour;
    }

    // Mutators
    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFurcolour(String furcolour) {
        this.furcolour = furcolour;
    }

    @Override
    public String toString() {
        return String.format(" %s: %s ,  %s, (%d years old),", kind, name, gender, age);
    }

}