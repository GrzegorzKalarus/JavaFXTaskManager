package domain;

public class Data {

    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private int age;
    private String role;
    private String task;


    public Data(int id, String username, String password, String name, String surname, int age, String role, String task) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.role=role;
        this.task=task;

    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getAge() {
        return age;
    }
    public String getRole(){return role;}
    public String getTask(){return task;}


}