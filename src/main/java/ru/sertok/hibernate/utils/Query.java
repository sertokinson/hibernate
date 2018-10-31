package ru.sertok.hibernate.utils;

public class Query {
    public static final String SELECT_ALL = "SELECT * FROM fix_user";
    public static final String SELECT_BY_ID = "SELECT*FROM fix_user WHERE id=?";
    public static final String SELECT_BY_NAME = "SELECT*FROM fix_user WHERE name = ?";
    public static final String INSERT = "INSERT INTO fix_user (name, password, birthdate) VALUES (?,?,?)";

}
