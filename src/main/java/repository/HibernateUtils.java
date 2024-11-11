package repository;

import entity.Users;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {

    private static final SessionFactory FACTORY;

    static {
        try {
            Configuration conf = new Configuration();
            Properties properties = new Properties();
            properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
            properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=PolyOE;Encrypt=True;TrustServerCertificate=True");
            properties.put(Environment.USER, "sa");
            properties.put(Environment.PASS, "123");
            properties.put(Environment.SHOW_SQL, "true");
            conf.setProperties(properties);
            conf.addAnnotatedClass(Users.class);
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(conf.getProperties()).build();
            FACTORY = conf.buildSessionFactory(registry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void shutdown() {
        getFACTORY().close();
    }
}
