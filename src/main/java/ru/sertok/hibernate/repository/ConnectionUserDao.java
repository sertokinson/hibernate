package ru.sertok.hibernate.repository;

import lombok.NoArgsConstructor;
import ru.sertok.hibernate.dao.impl.UserDao;
import ru.sertok.hibernate.dao.impl.UserDaoJdbcTemplate;

@NoArgsConstructor
public class ConnectionUserDao extends DateBaseRepository{

    public UserDaoJdbcTemplate getUserDaoJdbcTemplate(){
        return new UserDaoJdbcTemplate(driverManagerDataSource);
    }
    public UserDao getUserDao() {
        return new UserDao(connection);
    }
}
