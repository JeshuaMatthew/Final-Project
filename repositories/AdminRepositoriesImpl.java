package repositories;

import config.Database;
import entities.admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminRepositoriesImpl implements  AdminRepositories{
    private final Database database;

    public AdminRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<admin> passAdminData() {
        return getListAdmins();
    }

    @Override
    public ArrayList<admin> getListAdmins(){
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM admin";
        ArrayList<admin> adminList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                admin adminRow = new admin();
                String username = resultSet.getString(1);
                String email = resultSet.getString(2);
                String password = resultSet.getString(3);
                int id = resultSet.getInt(4);


                adminRow.setEmail(email);
                adminRow.setUsername(username);
                adminRow.setPassword(password);
                adminRow.setId(id);

                adminList.add(adminRow);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return adminList;
    }

    @Override
    public void add(admin adminDat) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO admin(username,email,password) VALUES(?,?,?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,adminDat.getUsername());
            preparedStatement.setString(2,adminDat.getEmail());
            preparedStatement.setString(3,adminDat.getPassword());


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert successful !");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean delete(int num) {
        String sqlStatement = "DELETE FROM admin WHERE id = ?";
        Connection connection = database.getConnection();

        int id = -1;

        ArrayList<admin>adminList = getListAdmins();

        if(num >= 0 && num < adminList.size()) {
            id = adminList.get(num).getId();
        } else {
            return false;
        }


        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1,id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Delete Successful !");
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    @Override
    public boolean edit(String oldUsername, String username, String email, String password) {
        String sqlStatement = "UPDATE admin set username = ?, email = ?, password = ? WHERE username = ?";
        Connection conn = database.getConnection();

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,oldUsername);

            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0) {
                System.out.println("Update successful !");
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}


