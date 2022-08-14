module com.example.pt04prak2072028jdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.pt04prak2072028jdbc to javafx.fxml;
    exports com.example.pt04prak2072028jdbc;
    exports com.example.pt04prak2072028jdbc.util;
    exports com.example.pt04prak2072028jdbc.model;
    exports com.example.pt04prak2072028jdbc.dao;
}