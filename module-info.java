module Final.Project {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;
    requires javafx.fxml;
    requires javafx.controls;

    opens appSewaKamera;
    opens appSewaKamera.config;
    opens appSewaKamera.entities;
    opens appSewaKamera.repositories;
    opens appSewaKamera.services;
    opens appSewaKamera.views;
}