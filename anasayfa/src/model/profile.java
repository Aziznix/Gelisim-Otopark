package model;

public class profile {
    private int id;  // id ekleniyor
    private String ad;
    private String soyad;
    private String telefon;
    private String plaka;
    private String krediKarti;

    // Constructor
    public profile(int id, String ad, String soyad, String telefon, String plaka, String krediKarti) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.plaka = plaka;
        this.krediKarti = krediKarti;
    }

    // Getter ve Setter metodları
    public int getId() {  
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public String getKrediKartı() {
        return krediKarti;
    }

    public void setKrediKartı(String krediKartı) {
        this.krediKarti = krediKartı;
    }
}