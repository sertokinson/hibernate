import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.sertok.hibernate.models.User;

import java.util.List;

public class HibernateTest {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        Session session = configuration.buildSessionFactory().openSession();
        Query<User> query = session.createQuery("from User user where user.name = :name", User.class);
        query.setParameter("name","Sergey");
        List<User> users = query.getResultList();
        System.out.println("user = "+ users);
    }
}
