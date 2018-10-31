package ru.sertok.hibernate.models;

import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Date;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "fix_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private Date birthDate;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    private void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public User withName(String name){
        setName(name);
        return this;
    }

    public User withPassword(String password){
        setPassword(password);
        return this;
    }

    public User withBirthDate(Date birthDate){
        setBirthDate(birthDate);
        return this;
    }

    public User withId(Integer id){
        setId(id);
        return this;
    }

}
