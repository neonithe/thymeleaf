package se.lexicon.mattias.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "student")
public class Student {

    /** Varibles **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @Size(min=2,max=20,message="Min 2 Max 20 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(regexp="^(.+)@(.+)$", message = "Invalid email pattern")
    @Column(name = "email")
    private String email;

    /** Constructors **/
    public Student() { }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /** getter and setter **/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Email: " + email;
    }
}
