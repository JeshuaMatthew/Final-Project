package services;

import entities.stok;

import java.util.ArrayList;

public interface StokServices {
    ArrayList<stok> getAll();
    boolean addStok(String newStockName, int newRentPrice, int newStockAmmount);
    void subtractStok(int subtractAmmount, int stockIndex);
    boolean deleteStokDat(int stockIndex);
    int editStok(String newStockName, int newRentPrice, int newStockAmmount, int stockIndex);
    int isOrderValid(int subtractAmmount, int stockIndex);
}
