package appSewaKamera.services;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TransactionServiceImpl implements TransactionService{

    private StokServicesImpl stokServicesImpl;
    private PelangganServices pelangganServicesImpl;

    public TransactionServiceImpl(StokServicesImpl stokServicesImpl, PelangganServices pelangganServicesImpl) {
        this.stokServicesImpl = stokServicesImpl;
        this.pelangganServicesImpl = pelangganServicesImpl;
    }



    Scanner input = new Scanner(System.in);

    @Override
    public void sewaKamera(){
        System.out.print("Masukan Nomor Baris Kamera / Lensa Yang Ingin Di Sewa : ");
        int indexDataKamera = input.nextInt() - 1;
        System.out.print("Masukan Jumlah Yang Ingin Anda Sewa : ");
        int rentAmmount = input.nextInt();

        String[] orderErrMsg = {"Maaf Stok tidak Mencukupi","Jumlah Yang Anda Masukan Tidak Sesuai !!","Pilihan anda tidak tersedia !!"};

        int orderErrTypes = stokServicesImpl.isOrderValid(rentAmmount,indexDataKamera);
        if(orderErrTypes != 0){
            System.out.println(orderErrMsg[orderErrTypes -1]);
        }else {

            if(simpanDataPelanggan(rentAmmount,stokServicesImpl.getAll().get(indexDataKamera).getNamaBarang())){
                stokServicesImpl.subtractStok(rentAmmount,indexDataKamera);
            }
        }

    }


    private boolean simpanDataPelanggan(int rentAmmount,String namaBarang){
        System.out.print("Isi Form Berikut : \n\n");
        System.out.print("Masukan Nama Anda : ");
        input.nextLine();
        String nama = input.nextLine();

        System.out.print("Masukan Alamat Anda : ");
        String alamat = input.nextLine();

        System.out.print("Masukan Nomor Telephone Anda : ");
        String noHp = input.next();
        System.out.print("Masukan Jumlah Hari Anda Akan Meminjam Barang : ");
        int rentDays = input.nextInt();

        return pelangganServicesImpl.savePelangganData(alamat,nama,noHp,namaBarang,rentDays,rentAmmount);

    }
}
