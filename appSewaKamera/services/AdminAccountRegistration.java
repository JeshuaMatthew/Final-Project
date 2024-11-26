package appSewaKamera.services;

import appSewaKamera.entities.admin;

import java.util.ArrayList;

public interface AdminAccountRegistration {
    int addAdminDat(String userName, String email, String pwd);
    void editCurrentAdminDat(String oldUsername, String username, String email, String pwd);
    boolean login(String userName, String pwd);
    boolean delete(int num);
    ArrayList<admin>passAdminData();
}
