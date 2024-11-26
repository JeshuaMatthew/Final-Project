package appSewaKamera.services;

import appSewaKamera.entities.pelanggan;

import java.util.ArrayList;

public interface PelangganServices {
    ArrayList<pelanggan> getAll();
    boolean savePelangganData(String alamat, String nama, String noHp, String stockName,int rentDays, int rentAmmount);
}
