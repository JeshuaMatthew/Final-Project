module Final.Project {
    requires spring.context;
    requires spring.beans;
    requires java.sql;
    requires org.slf4j;
    opens appSewaKamera;
    opens appSewaKamera.config;
    opens appSewaKamera.entities;
    opens appSewaKamera.repositories;
    opens appSewaKamera.services;
    opens appSewaKamera.views;
}