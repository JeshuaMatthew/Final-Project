package services;


import entities.admin;
import repositories.AdminRepositoriesImpl;

import java.util.ArrayList;
import java.util.Objects;

public class AdminAccountRegistrationImpl implements AdminAccountRegistration {


    private AdminRepositoriesImpl adminRepositoriesImpl;

    public AdminAccountRegistrationImpl(AdminRepositoriesImpl adminRepositoriesImpl){
        this.adminRepositoriesImpl = adminRepositoriesImpl;
    }

    @Override
    public int addAdminDat(String userName, String email, String pwd) {

        admin newAdminDat = new admin();
        newAdminDat.setUsername(userName);
        newAdminDat.setEmail(email);
        newAdminDat.setPassword(pwd);

        if(isUsernameExist(newAdminDat.getUsername()))return 1;
        if(!emailValid(newAdminDat.getEmail()))return 2;
        if(!pwdValid(newAdminDat.getPassword()))return 3;


        adminRepositoriesImpl.add(newAdminDat);

        return 0;
    }

    private boolean isUsernameExist(String newUsername){
        ArrayList<admin> admins = adminRepositoriesImpl.passAdminData();
        for(admin i : admins){
            if(Objects.equals(newUsername, i.getUsername())){
                return true;
            }
        }
        return false;
    }

    private boolean checkEmailContainsAt(String username){
        return username.contains("@");
    }

    private boolean checkEmailContainsCom(String username){
        return username.contains(".com");
    }

    private boolean emailValid(String username){
        if(username.charAt(0) == '@')return false;

        return checkEmailContainsAt(username) && checkEmailContainsCom(username);
    }

    private boolean checkPwdContainsNum(String pwd){
        int countNums = 0;
        for (int i = 0; i < pwd.length(); i++){
            int pwdAsciiVal = pwd.charAt(i);
            if (pwdAsciiVal >= 48 && pwdAsciiVal <= 57)countNums++;
        }

        return countNums >= 1;
    }

    private boolean checkPwdContainsUpCase(String pwd){
        int countUpCase = 0;
        for (int i = 0; i < pwd.length(); i++){
            int pwdAsciiVal = pwd.charAt(i);
            if (pwdAsciiVal >= 65 && pwdAsciiVal <= 90)countUpCase++;
        }

        return countUpCase >= 1;
    }

    private boolean pwdValid(String pwd){
        return checkPwdContainsNum(pwd) && checkPwdContainsUpCase(pwd);
    }


    @Override
    public void editCurrentAdminDat(String oldUsername, String username, String email, String pwd){
        int currentAdminIndex = findUsername(oldUsername);
        if(currentAdminIndex != -1){

            int errTypes = 0;

            if(isUsernameExist(username))errTypes = 1;
            if(!emailValid(email))errTypes =  2;
            if(!pwdValid(pwd))errTypes = 3;

            String[] errMsg = {"Username Telah Digunakan !!", "Email Tidak Sesuai !!", "Password Harus memiliki Angka Dan Huruf Besar !!"};
            if (errTypes != 0){
                System.out.println(errMsg[errTypes -1]);
            }else{
                adminRepositoriesImpl.edit(oldUsername,username,email,pwd);
            }

        }
    }

    private int findUsername(String username){
        int counter = 0;
        ArrayList<admin>admins = adminRepositoriesImpl.passAdminData();
        for (admin adminData : admins){
            if(Objects.equals(adminData.getUsername(), username))return counter;
            counter++;
        }
        return -1;
    }

    @Override
    public boolean login(String userName, String pwd) {
        int currentAdminIndex = findUsername(userName);
        return checkPwd(pwd,currentAdminIndex) && currentAdminIndex != -1;
    }

    @Override
    public boolean delete(int num) {
       return adminRepositoriesImpl.delete(num);
    }

    @Override
    public ArrayList<admin> passAdminData() {
        return adminRepositoriesImpl.passAdminData();
    }

    private boolean checkPwd(String pwd, int currentAdminIndex){
        ArrayList<admin>admins = adminRepositoriesImpl.passAdminData();
        if(currentAdminIndex == -1)return false;
        return Objects.equals(admins.get(currentAdminIndex).getPassword(), pwd);
    }

}
