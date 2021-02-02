public class Pass {
    private String name;
    private int age;

    public Pass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ":" + age;
    }
}