package services;

import entities.admin;
import entities.pelanggan;
import entities.stok;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminServicesImpl implements AdminServices{

    private AdminAccountRegistrationImpl adminAccountRegistrationImpl;
    private StokServicesImpl stokServicesImpl;
    private PelangganServicesImpl pelangganServicesImpl;
    private String currentUsername = null;
    private Scanner input = new Scanner(System.in);

    public AdminServicesImpl(AdminAccountRegistrationImpl adminAccountRegistrationImpl, StokServicesImpl stokServicesImpl, PelangganServicesImpl pelangganServicesImpl) {
        this.adminAccountRegistrationImpl = adminAccountRegistrationImpl;
        this.stokServicesImpl = stokServicesImpl;
        this.pelangganServicesImpl = pelangganServicesImpl;
    }

    @Override
    public void editCurrentAccount(){
        System.out.print("Masukan Email Baru Anda : ");
        String newEmail = input.next();
        input.nextLine();
        System.out.print("Masukan Nama Baru Anda : ");
        String newUsername = input.nextLine();
        System.out.print("Masukan Password Baru Anda : ");
        String newPwd = input.nextLine();

        adminAccountRegistrationImpl.editCurrentAdminDat(currentUsername,newUsername,newEmail,newPwd);


    }

    @Override
    public void registerNewAccount(){
        System.out.print("Masukan Email : ");
        String email = input.next();
        System.out.print("Masukan Username : ");
        input.nextLine();
        String username = input.nextLine();
        System.out.print("Masukan Password : ");
        String pwd = input.nextLine();

        int errtypes = adminAccountRegistrationImpl.addAdminDat(username,email,pwd);

        if(errtypes != 0){
            String[] errMsg = {"Username telah terpakai !!","Email anda tidak valid !!", "Password anda tidak valid !!"};
            System.out.println(errMsg[errtypes -1]);
        }
    }

    @Override
    public boolean enterAccount() {
        System.out.print("Masukan Username : ");
        String username = input.nextLine();
        System.out.print("Masukan Password : ");
        String pwd = input.nextLine();

        if(!adminAccountRegistrationImpl.login(username,pwd)){
            System.out.println("Username Atau Password Yang Anda Masukan Salah !!");
            return false;
        }
        currentUsername = username;
        return true;
    }

    @Override
    public boolean deleteAccount() {
        System.out.print("Masukan Nomor Baris Admin Yang Ingin Di Hapus : ");
        int index = input.nextInt();
        return adminAccountRegistrationImpl.delete(index);
    }

    @Override
    public void editStock(){
        System.out.print("Masukan Nomor Stok Yang Ingin diedit : ");
        int stockIndex = input.nextInt() - 1;
        System.out.print("Masukan Nama Stok Baru : ");
        input.nextLine();
        String newStockName = input.nextLine();
        System.out.print("Masukan Harga Sewa Baru : ");
        int newRentPrice = input.nextInt();
        System.out.print("Masukan Jumlah Stok : ");
        int newStockAmmount = input.nextInt();
        stokServicesImpl.editStok(newStockName,newRentPrice,newStockAmmount,stockIndex);
    }

    @Override
    public void deleteStock() {
        System.out.print("Masukan Nomor Stok Yang Ingin dihapus : ");
        int stockIndex = input.nextInt() - 1;
        stokServicesImpl.deleteStokDat(stockIndex);
    }

    @Override
    public void addStock() {
        System.out.print("Masukan Nama Stok : ");
        input.nextLine();
        String newStockName = input.nextLine();
        System.out.print("Masukan Harga Sewa : ");
        int newRentPrice = input.nextInt();
        System.out.print("Masukan Jumlah Stok : ");
        int newStockAmmount = input.nextInt();
        stokServicesImpl.addStok(newStockName,newRentPrice,newStockAmmount);
    }

    @Override
    public ArrayList<pelanggan> passPelangganData() {
        return pelangganServicesImpl.getAll();
    }

    @Override
    public ArrayList<admin> passAdminData() {
        return adminAccountRegistrationImpl.passAdminData();
    }

    @Override
    public ArrayList<stok> passStokData() {
        return stokServicesImpl.getAll();
    }
}
