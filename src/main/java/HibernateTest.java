import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import ru.sertok.hibernate.models.User;

public class HibernateTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/jdbc");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "12355789");
        configuration.addAnnotatedClass(User.class);
        Session session = configuration.buildSessionFactory().openSession();
        User user = session.createQuery("from User user where user.id = 4", User.class).getSingleResult();
        session.beginTransaction();
        session.save(new User().withName("Den"));
        session.getTransaction().commit();
        System.out.println("user = "+ user);
    }
}
