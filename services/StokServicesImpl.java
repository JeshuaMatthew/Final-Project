package services;

import entities.stok;
import repositories.StokRepositoriesImpl;

import java.util.ArrayList;

public class StokServicesImpl implements StokServices{

    private StokRepositoriesImpl stokRepositoriesImpl;

    public StokServicesImpl(StokRepositoriesImpl stokRepositoriesImpl) {
        this.stokRepositoriesImpl = stokRepositoriesImpl;
    }


    @Override
    public ArrayList<stok> getAll() {
        return stokRepositoriesImpl.passStokDat();
    }

    @Override
    public boolean addStok(String newStockName, int newRentPrice, int newStockAmmount){
        stok newStock = new stok();
        newStock.setNamaBarang(newStockName);
        newStock.setHargaSewa(newRentPrice);
        newStock.setCurrentStok(newStockAmmount);

        if(isStockDataValid(newStock)){
            stokRepositoriesImpl.add(newStock);
            return true;
        }
        return false;
    }

    private boolean isStockDataValid(stok stock){
        return isStockNameValid(stock.getNamaBarang()) &&
                isStockAmmountValid(stock.getCurrentStok()) &&
                isRentPriceValid(stock.getHargaSewa());
    }

    private boolean isStockNameValid(String stockName){
        return !stockName.isBlank();
    }

    private boolean isStockAmmountValid(int stockSum){
        return stockSum >= 1;
    }

    private boolean isRentPriceValid(int RentPrice){
        return RentPrice >= 1000;
    }

    @Override
    public void subtractStok(int subtractAmmount, int stockIndex){

        stok tmp = stokRepositoriesImpl.passStokDat().get(stockIndex);

        int currentStock = tmp.getCurrentStok() - subtractAmmount;

        stokRepositoriesImpl.edit(stockIndex,tmp.getNamaBarang(),currentStock,tmp.getHargaSewa());


    }

    @Override
    public int isOrderValid(int subtractAmmount, int stockIndex) {
        if(!isStockIndexValid(stockIndex)) return 3;
        if(!isSubtractAmmountValid(subtractAmmount)) return 2;
        if(!isStockAvaliable(subtractAmmount, stockIndex)) return 1;
        return 0;
    }

    private boolean isSubtractAmmountValid(int subtractAmmount){
        return subtractAmmount >= 1;
    }

    private boolean isStockAvaliable(int subtractAmmount, int stockIndex){
        return subtractAmmount <= stokRepositoriesImpl.passStokDat().get(stockIndex).getCurrentStok();
    }

    private boolean isStockIndexValid(int stockIndex){
        return stockIndex >=0 && stockIndex < stokRepositoriesImpl.passStokDat().size();
    }


    @Override
    public boolean deleteStokDat(int stockIndex){
        if(isStockIndexValid(stockIndex)){
            stokRepositoriesImpl.delete(stockIndex);
            return true;
        }
        return false;
    }

    @Override
    public int editStok(String newStockName, int newRentPrice, int newStockAmmount, int stockIndex){
        stok tmp = new stok();
        tmp.setNamaBarang(newStockName);
        tmp.setHargaSewa(newRentPrice);
        tmp.setCurrentStok(newStockAmmount);

        if(!isStockIndexValid(stockIndex)) return 2;
        if(!isStockDataValid(tmp)) return 1;

        stokRepositoriesImpl.edit(stockIndex,tmp.getNamaBarang(),tmp.getCurrentStok(),tmp.getHargaSewa());

        return 0;
    }

}
