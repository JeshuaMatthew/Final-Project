package appSewaKamera.repositories;


import appSewaKamera.config.Database;
import appSewaKamera.entities.pelanggan;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

@Component
public class PelangganRepositoriesImpl implements PelangganRepositories {
    private final Database database;


    public PelangganRepositoriesImpl(Database database){
        this.database = database;
    }


    @Override
    public ArrayList<pelanggan> getListPelanggan() {
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT pelanggan.id_pengguna, pelanggan.nama, pelanggan.alamat, pelanggan.tel_num, barang.nama, pelanggan.jumlah , pelanggan.tgl_pesan,pelanggan.tgl_kembali FROM pelanggan LEFT JOIN barang ON pelanggan.id_barang = barang.id_barang";
        ArrayList<pelanggan> pelangganList = new ArrayList<>();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                pelanggan pelangganDat = new pelanggan();


                int id = resultSet.getInt(1);
                String nama = resultSet.getString(2);
                String alamat = resultSet.getString(3);
                String telNum = resultSet.getString(4);
                String namaBrg = resultSet.getString(5);
                int jumlah = resultSet.getInt(6);
                String tglPesan = resultSet.getString(7);
                String tglKembali = resultSet.getString(8);

                pelangganDat.setId(id);
                pelangganDat.setNama(nama);
                pelangganDat.setAlamat(alamat);
                pelangganDat.setNoHp(telNum);
                pelangganDat.setNamaBarang(namaBrg);
                pelangganDat.setRentAmmount(jumlah);
                pelangganDat.setTanggalPemesanan(tglPesan);
                pelangganDat.setTanggalPengembalian(tglKembali);

                pelangganList.add(pelangganDat);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return pelangganList;
    }

    @Override
    public ArrayList<pelanggan> passPelangganList() {
        return getListPelanggan();
    }

    @Override
    public void add(pelanggan pelangganDat) {
        int id = getIdBrg(pelangganDat.getNamaBarang());
        Connection connection = database.getConnection();
        String sqlStatement = "INSERT INTO pelanggan(nama, alamat, tel_num, id_barang, tgl_pesan, tgl_kembali, jumlah) VALUES(?,?,?,?,?,?,?)";


        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,pelangganDat.getNama());
            preparedStatement.setString(2, pelangganDat.getAlamat());
            preparedStatement.setString(3,pelangganDat.getNoHp());
            preparedStatement.setInt(4,id);
            preparedStatement.setString(5,pelangganDat.getTanggalPemesanan());
            preparedStatement.setString(6,pelangganDat.getTanggalPengembalian());
            preparedStatement.setInt(7,pelangganDat.getRentAmmount());


            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert successful !");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private int getIdBrg(String namaBrg){
        Connection connection = database.getConnection();
        String sqlStatement = "SELECT id_barang FROM barang WHERE nama = ?";
        int id = -1;

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1,namaBrg);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.first();
            id = resultSet.getInt(1);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return id;
    }

}
