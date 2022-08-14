module com.example.pt10prak2072028hibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.pt10prak2072028hibernate.model;
    opens com.example.pt10prak2072028hibernate.util;
    opens com.example.pt10prak2072028hibernate.dao;
    opens com.example.pt10prak2072028hibernate to javafx.fxml;
    exports com.example.pt10prak2072028hibernate;
}