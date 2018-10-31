package ru.sertok.hibernate.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.sertok.hibernate.dao.api.CrudDao;
import ru.sertok.hibernate.models.User;
import ru.sertok.hibernate.utils.Utils;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static ru.sertok.hibernate.utils.Constants.*;
import static ru.sertok.hibernate.utils.Query.*;

public class UserDaoJdbcTemplate implements CrudDao<User> {

    private JdbcTemplate jdbcTemplate;
    private String[] mutableHash = new String[1];

    private RowMapper<User> rowMapper = (resultSet, i) -> new User()
            .withId(resultSet.getInt(ID))
            .withName(resultSet.getString(NAME))
            .withBirthDate(resultSet.getDate(BIRTH_DATE))
            .withPassword(resultSet.getString(PASSWORD));

    public UserDaoJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(INSERT,model.getName(),model.getPassword(),model.getBirthDate());
    }

    @Override
    public Optional<User> find(Integer id) {
        List<User> query = jdbcTemplate.query(SELECT_BY_ID, rowMapper, id);
        return Optional.of(query.get(0));
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL,rowMapper);
    }

    public Boolean isExist(String name, String password) {
            List<User> listUsers = jdbcTemplate.query(SELECT_BY_NAME,rowMapper,name);
            for (User user : listUsers) {
                if (user.getName().equals(name) && Utils.verifyAndUpdateHash(password, user.getPassword(), hash -> {
                    mutableHash[0] = hash;
                    return true;
                }))
                    return true;
            }
        return false;
    }
}
