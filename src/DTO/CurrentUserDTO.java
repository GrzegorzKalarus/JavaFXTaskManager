package DTO;

public class CurrentUserDTO {
    public String userName;
    public String name;
    public String surname;
    public int age;
    public String role;
    public String task;


    public CurrentUserDTO(String userName, String name, String surname, int age, String role, String task)
    {
        this.userName = userName;
        this.name=name;
        this.surname=surname;
        this.age=age;
        this.role=role;
        this.task=task;
    }
}
