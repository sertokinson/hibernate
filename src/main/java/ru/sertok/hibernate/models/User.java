package ru.sertok.hibernate.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "fix_user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String password;
    private Date birthDate;
}
