package appSewaKamera.repositories;

import appSewaKamera.entities.stok;
import java.util.ArrayList;

public interface StokRepositories {
    ArrayList<stok> passStokDat();
    ArrayList<stok> getStockList();
    void add(stok stokDat);
    boolean delete(int num);
    boolean edit(int num, String nama, int stok,int harga);
}
