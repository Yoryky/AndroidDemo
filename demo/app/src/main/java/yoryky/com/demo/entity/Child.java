package yoryky.com.demo.entity;

public class Child {
    private String name;
    private int age;
    //@Inject
    public Child(){
        this.name = "yjing";
        this.age = 12;
    }

    public Child(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
