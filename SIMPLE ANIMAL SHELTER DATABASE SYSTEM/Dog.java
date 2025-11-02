public class Dog extends Animal {

    private String furcolour;
    public Dog(String kind, String name, String gender, int age,String furcolour) {
        super(kind, name, gender, age,furcolour);
    
    }

    @Override
    public String toString() {
        return String.format(" %s:   %s ,    %s ,   %d years old,   furcolour:` %e", getKind(), getName(), getGender(), getAge(), furcolour);
    }
}
