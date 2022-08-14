package com.example.pt04prak2072028jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        return sf;
    }
}
