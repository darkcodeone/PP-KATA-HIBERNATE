package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {

    private Util() {
    }



    public static SessionFactory getSessionFactory() {
        {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/userdb");
            properties.setProperty("hibernate.connection.username", "valerkamops");
            properties.setProperty("hibernate.connection.password", "amogus");
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            properties.setProperty("hibernate.use_sql_comments", "true");
            properties.setProperty("hibernate.show_sql", "true");
            properties.setProperty("hibernate.format_sql", "true");
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            Configuration cfg = new Configuration();
            cfg.addAnnotatedClass(jm.task.core.jdbc.model.User.class);
            cfg.setProperties(properties);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(cfg.getProperties())
                    .build();
            return cfg.buildSessionFactory(serviceRegistry);
        }
    }
}
