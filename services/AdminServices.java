package services;

import entities.admin;
import entities.pelanggan;
import entities.stok;

import java.util.ArrayList;

public interface AdminServices {
    void editCurrentAccount();
    void registerNewAccount();
    boolean enterAccount();
    boolean deleteAccount();

    void editStock();
    void deleteStock();
    void addStock();


    ArrayList<pelanggan> passPelangganData();
    ArrayList<admin> passAdminData();
    ArrayList<stok> passStokData();
}
