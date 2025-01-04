package otopark;

import list.parklar;
import list.yerler;

import java.util.List;

import fonks.parket;
import fonks.rezerv;
import javax.swing.JOptionPane;



public class servis {
    private final parklar parklar;
    private final yerler yerler;
    private final rezerv rezerv;
    private final parket parket;

    public servis() {
        this.parklar = new parklar();
        this.yerler = new yerler();
        this.rezerv = new rezerv();
        this.parket = new parket();
    }

    // Parklar tablosundan kolon verilerini döndürür
    public List<String> getColumnData() {
        return parklar.fetchColumnData();
    }

    // Belirli bir otopark adına göre boş park yerlerini döndürür
    public List<String> getParkYerleri(String parkAdi) {
        return yerler.fetchParkYerleri(parkAdi);
    }
    
    public void setRezerv(String parkYeri, String ParkId) {
        boolean success = rezerv.rezerv(parkYeri, ParkId); // rezerv metodundan başarılı olup olmadığını alıyoruz
        if (success) {
            // Başarılı mesajı gösteriyoruz
            JOptionPane.showMessageDialog(null, "Rezervasyon Başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Hata mesajı gösteriyoruz
            JOptionPane.showMessageDialog(null, "Rezervasyon Başarısız. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void setParkEt(String parkYeri, String ParkId) {
        boolean success = parket.parket(parkYeri, ParkId); // rezerv metodundan başarılı olup olmadığını alıyoruz
        if (success) {
            // Başarılı mesajı gösteriyoruz
            JOptionPane.showMessageDialog(null, "Park Başarılı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Hata mesajı gösteriyoruz
            JOptionPane.showMessageDialog(null, "Park Başarısız. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

}
