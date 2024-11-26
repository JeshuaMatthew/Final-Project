package appSewaKamera.views;

import appSewaKamera.entities.admin;
import appSewaKamera.entities.pelanggan;
import appSewaKamera.entities.stok;
import appSewaKamera.services.AdminServicesImpl;
import appSewaKamera.services.TransactionServiceImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class TerminalView implements MenuView{

    private final AdminServicesImpl adminServicesImpl;
    private final TransactionServiceImpl transactionServiceImpl;
    Scanner input = new Scanner(System.in);

    public TerminalView(AdminServicesImpl adminServicesImpl, TransactionServiceImpl transactionServiceImpl) {
        this.adminServicesImpl = adminServicesImpl;
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @Override
    public void run() {
        mainMenu();
    }

    private void mainMenu(){
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Selamat Datang Di Aplikasi Penyewaan Kamera\n");
            System.out.println("1.Pelanggan");
            System.out.println("2.Admin");
            System.out.println("3.Register Akun Baru");
            System.out.println("4.Keluar Aplikasi");
            System.out.print("Masukan Pilihan Anda : ");
            int selectedMenu = input.nextInt();
            switch (selectedMenu) {
                case 1:
                    sewaKamera();
                    break;
                case 2:
                    adminMenu();
                    break;
                case 3:
                    menuRegisterNewAccount();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu dengan benar");
            }
        }
    }



    private void menuEditStock(){
        showDataStok();
        adminServicesImpl.editStock();
    }

    private void  menuDeleteStock(){
        showDataStok();
        adminServicesImpl.deleteStock();
    }

    private void  menuAddStock(){
        adminServicesImpl.addStock();
    }

    private void menuEditCurrentAccount(){
        adminServicesImpl.editCurrentAccount();
    }

    private void menuRegisterNewAccount(){
        adminServicesImpl.registerNewAccount();
    }

    private boolean login(){
        return adminServicesImpl.enterAccount();
    }

    private void adminMenu(){
        if(login()){
            while (true) {
                System.out.println("Selamat Datang Di Menu Admin\n");
                System.out.println("1.Edit Akun");
                System.out.println("2.Hapus Akun");
                System.out.println("3.Edit Stok Data");
                System.out.println("4.Delete Stok Data");
                System.out.println("5.Tambah Stok Baru");
                System.out.println("6.Tampilkan Data Pelanggan");
                System.out.println("7.Tampilkan Data Stok");
                System.out.println("8.Tampilkan Data Admin");
                System.out.println("9.Keluar Menu");
                System.out.print("Masukan Pilihan Anda : ");
                int selectedMenu = input.nextInt();
                switch (selectedMenu) {
                    case 1:
                        menuEditCurrentAccount();
                        break;
                    case 2:
                        menuDeleteAccount();
                        break;
                    case 3:
                        menuEditStock();
                        break;
                    case 4:
                        menuDeleteStock();
                        break;
                    case 5:
                        menuAddStock();
                        break;
                    case 6:
                        showDataPelanggan();
                        break;
                    case 7:
                        showDataStok();
                        break;
                    case 8:
                        showAdminList();
                        break;
                    case 9:
                        return;
                    default:
                        System.out.println("Pilih menu dengan benar");
                }
            }
        }
    }

    private void sewaKamera(){
        System.out.println("Selamat Datang Ke Menu Pelanggan\n");
        showDataStok();
        transactionServiceImpl.sewaKamera();
    }

    private void showDataPelanggan(){
        ArrayList<pelanggan>listPelanggan = adminServicesImpl.passPelangganData();
        System.out.println("No.\tNama\tBarang\tJumlah\tTelp.\talamat\tPemesanan\tPengembalian");
        int counter = 1;
        for(pelanggan data : listPelanggan){
            System.out.println(counter + "\t" + data.getNama() + "\t" +data.getNamaBarang() + "\t" + data.getRentAmmount() + "\t" + data.getNoHp() + "\t" +data.getAlamat() + "\t" +data.getTanggalPemesanan() + "\t" +data.getTanggalPengembalian());
            counter++;
        }
    }

    private void showDataStok(){
        ArrayList<stok>listStok = adminServicesImpl.passStokData();
        System.out.println("No.\tNama\tHarga\tJumlah");
        int counter = 1;
        for(stok data : listStok){
            System.out.println(counter + "\t" + data.getNamaBarang() + "\t" +data.getHargaSewa() + "\t" + data.getCurrentStok());
            counter++;
        }
    }

    private void showAdminList(){
        ArrayList<admin>listStok = adminServicesImpl.passAdminData();
        System.out.println("No.\tNama\tEmail");
        int counter = 1;
        for(admin data : listStok){
            System.out.println(counter + "\t" + data.getUsername() + "\t" +data.getEmail());
            counter++;
        }
    }

    private void menuDeleteAccount(){
        showAdminList();
        adminServicesImpl.deleteAccount();
    }


}
