package repositories;


import config.Database;
import entities.stok;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StokRepositoriesImpl implements StokRepositories {
    private final Database database;
    public StokRepositoriesImpl(Database database) {
        this.database = database;
    }

    @Override
    public ArrayList<stok> passStokDat() {
        return getStockList();
    }



    @Override
    public ArrayList<stok> getStockList() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT * FROM barang";
        ArrayList<stok> stoks = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                stok stokDat = new stok();
                int id = resultSet.getInt(1);
                String nama = resultSet.getString(2);
                int stok = resultSet.getInt(3);
                int harga = resultSet.getInt(4);


               stokDat.setId(id);
               stokDat.setNamaBarang(nama);
               stokDat.setCurrentStok(stok);
               stokDat.setHargaSewa(harga);

                stoks.add(stokDat);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return stoks;
    }

    @Override
    public void add(stok stokDat) {
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO barang(nama,stok,harga) VALUES(?,?,?)";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,stokDat.getNamaBarang());
            preparedStatement.setInt(2,stokDat.getCurrentStok());
            preparedStatement.setInt(3,stokDat.getHargaSewa());


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
        String sqlStatement = "DELETE FROM barang WHERE id_barang = ?";
        Connection connection = database.getConnection();

        int id;

        ArrayList<stok>stoks = getStockList();

        if(num >= 0 && num < stoks.size()) {
            id = stoks.get(num).getId();
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
    public boolean edit(int num, String nama, int stok,int harga) {
        String sqlStatement = "UPDATE barang set nama = ?, stok = ?, harga = ? WHERE id_barang = ?";
        Connection conn = database.getConnection();

        int id;

        ArrayList<stok>stoks = getStockList();

        if(num >= 0 && num < stoks.size()) {
            id = stoks.get(num).getId();
        }else {
            return false;
        }

        System.out.println("id : "+id);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlStatement);

            preparedStatement.setString(1,nama);
            preparedStatement.setInt(2,stok);
            preparedStatement.setInt(3,harga);
            preparedStatement.setInt(4,id);


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
