package appSewaKamera.repositories;

import appSewaKamera.entities.admin;
import java.util.ArrayList;

public interface AdminRepositories {
    ArrayList<admin> passAdminData();
    ArrayList<admin> getListAdmins();
    void add(admin adminDat);
    boolean delete(int num);
    boolean edit(String oldUsername, String username, String email, String password);





}
