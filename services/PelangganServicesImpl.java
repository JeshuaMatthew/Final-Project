package services;

import entities.pelanggan;
import repositories.PelangganRepositoriesImpl;

import java.time.LocalDate;
import java.util.ArrayList;

public class PelangganServicesImpl implements PelangganServices {

    private PelangganRepositoriesImpl pelangganRepositoriesImpl;

    public PelangganServicesImpl(PelangganRepositoriesImpl pelangganRepositoriesImpl) {
        this.pelangganRepositoriesImpl = pelangganRepositoriesImpl;
    }

    @Override
    public ArrayList<pelanggan> getAll() {
        return pelangganRepositoriesImpl.passPelangganList();
    }

    @Override
    public boolean savePelangganData(String alamat, String nama, String noHp, String stockName, int rentDays, int rentAmmount){

        if(isDataValid(alamat,nama,noHp,rentDays)){
            pelanggan dataPelangganBaru = new pelanggan();
            dataPelangganBaru.setAlamat(alamat);
            dataPelangganBaru.setNama(nama);
            dataPelangganBaru.setNoHp(noHp);
            dataPelangganBaru.setTanggalPemesanan(getCurrentDate());
            dataPelangganBaru.setTanggalPengembalian(getReturnDate(rentDays));
            dataPelangganBaru.setNamaBarang(stockName);
            dataPelangganBaru.setRentAmmount(rentAmmount);

            pelangganRepositoriesImpl.add(dataPelangganBaru);


            return true;
        }


        return false;
    }


    private boolean isDataValid(String alamat, String nama, String noHp, int rentDays) {
        if(!isAddressValid(alamat)) {
            System.out.println("Panjang Alamat Harus Lebih Dari 3 Karakter !!");
            return false;
        } else if (!isNameValid(nama)) {
            System.out.println("Anda Harus Mengisi Nama Anda !!");
            return false;
        }else if(!isPhoneNumValid(noHp)){
            System.out.println("Nomor Telepon Yang Anda Masukan Tidak Sesuai !!");
            return false;
        } else if(!isPhoneNumLengthValid(noHp)) {
            System.out.println("Panjang Nomor Telephone Harus Lebih Dari 10 Angka !!");
            return false;
        } else if (isRentDaysValid(rentDays)) {
            System.out.println("Anda Tidak Dapat Meminjam Lebih Dari 60 Hari !!");
            return false;
        }

        return true;
    }

    private boolean isAddressValid(String alamat){
        return !alamat.isBlank() && alamat.length() >= 3;
    }
    private boolean isNameValid(String nama){
        return !nama.isBlank();
    }
    private boolean isPhoneNumLengthValid(String number){
        return !number.isBlank() && number.length() >= 10;
    }

    private boolean isPhoneNumValid(String number){

        for (int i = 0; i < number.length(); i++){
            int numAsciiVal = number.charAt(i);
            if(numAsciiVal < 48 || numAsciiVal > 57){
                return false;
            }
        }

        return true;
    }

    private boolean isRentDaysValid(int daysNum){
        return daysNum > 60;
    }

    private String getCurrentDate(){
        return LocalDate.now().toString();
    }

    private String getReturnDate(int rentDays){
        return LocalDate.now().plusDays(rentDays).toString();
    }

}
