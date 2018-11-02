package ru.sertok.hibernate.dao.api;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.sertok.hibernate.models.User;

public interface UserDao extends CrudDao<User> {
    Boolean isExist(String name, String password);
}
