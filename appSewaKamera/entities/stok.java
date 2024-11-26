package appSewaKamera.entities;

public class stok {
    private String namaBarang;
    private int hargaSewa;
    private int currentStok;
    private int id;

    public stok() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }

    public int getCurrentStok() {
        return currentStok;
    }

    public void setCurrentStok(int currentStok) {
        this.currentStok = currentStok;
    }
}
