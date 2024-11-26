package appSewaKamera.repositories;

import appSewaKamera.entities.pelanggan;
import java.util.ArrayList;

public interface PelangganRepositories {
    ArrayList<pelanggan> getListPelanggan();
    ArrayList<pelanggan> passPelangganList();
    void add(pelanggan pelangganDat);

}
