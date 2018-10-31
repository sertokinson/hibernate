package ru.sertok.hibernate.dao.impl;

import ru.sertok.hibernate.dao.api.CrudDao;
import ru.sertok.hibernate.models.User;
import ru.sertok.hibernate.utils.Utils;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static ru.sertok.hibernate.utils.Constants.*;
import static ru.sertok.hibernate.utils.Query.*;

public class UserDao implements CrudDao<User> {
    private Connection connection;
    private Statement statement;
    private String[] mutableHash = new String[1];

    public UserDao(Connection connection) {
        try {
            this.connection = connection;
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, user.getBirthDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> find(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return Optional.of(new User()
                        .withId(resultSet.getInt(ID))
                        .withName(resultSet.getString(NAME))
                        .withBirthDate(resultSet.getDate(BIRTH_DATE))
                        .withPassword(resultSet.getString(PASSWORD)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
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
        List<User> users = new LinkedList<>();
        try {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            users = createListUsers(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    private List<User> createListUsers(ResultSet resultSet) throws SQLException {
        List<User> users = new LinkedList<>();
        while (resultSet.next())
            users.add(new User()
                            .withId(resultSet.getInt("id"))
                            .withName(resultSet.getString("name"))
                            .withBirthDate(resultSet.getDate("birthDate"))
                            .withPassword(resultSet.getString("password"))
            );
        return users;
    }

    public Boolean isExist(String name, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT*FROM fix_user WHERE name = ?");
            preparedStatement.setString(1,name);
            List<User> listUsers = createListUsers(preparedStatement.executeQuery());
            for (User user : listUsers) {
                if (user.getName().equals(name) && Utils.verifyAndUpdateHash(password, user.getPassword(), hash -> {
                    mutableHash[0] = hash;
                    return true;
                }))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
