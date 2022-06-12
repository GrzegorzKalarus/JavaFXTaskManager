package domain;

public class DataUser {
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private int age;

    public DataUser(int id,String username,String password, String name, String surname, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;

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



}