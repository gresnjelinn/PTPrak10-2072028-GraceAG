package com.example.pt10prak2072028hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HiberUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        return sf;
    }
}
