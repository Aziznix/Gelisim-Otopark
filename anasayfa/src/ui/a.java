package ui;

import busniess.ProfileService;
import fonks.parkandquit;
import kullanici.bakiye;
import kullanici.session;
import model.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import java.awt.event.ActionEvent;

public class a extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtAd;
    private JTextField txtSoyad;
    private JTextField txtTelefon;
    private JTextField txtPlaka;
    private JPasswordField txtParola; // Parola girişi için JPasswordField kullanıyoruz.

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    a frame = new a();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public a() {
        setTitle("Kullanıcı Profil Sayfası");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 350);
        contentPane = new JPanel();
        contentPane.setLayout(null); // Serbest yerleşim
        setContentPane(contentPane);

        // Ad Label ve TextField
        JLabel lblAd = new JLabel("Ad:");
        lblAd.setBounds(27, 13, 47, 25);
        contentPane.add(lblAd);

        txtAd = new JTextField();
        txtAd.setBounds(27, 38, 150, 25);
        contentPane.add(txtAd);
        txtAd.setColumns(10);

        // Soyad Label ve TextField
        JLabel lblSoyad = new JLabel("Soyad:");
        lblSoyad.setBounds(199, 13, 47, 25);
        contentPane.add(lblSoyad);

        txtSoyad = new JTextField();
        txtSoyad.setBounds(199, 38, 150, 25);
        contentPane.add(txtSoyad);
        txtSoyad.setColumns(10);

        // Telefon Label ve TextField
        JLabel lblTelefon = new JLabel("Telefon:");
        lblTelefon.setBounds(27, 76, 80, 25);
        contentPane.add(lblTelefon);

        txtTelefon = new JTextField();
        txtTelefon.setBounds(27, 100, 150, 25);
        contentPane.add(txtTelefon);
        txtTelefon.setColumns(10);

        // Plaka Label ve TextField
        JLabel lblPlaka = new JLabel("Plaka:");
        lblPlaka.setBounds(199, 76, 80, 25);
        contentPane.add(lblPlaka);

        txtPlaka = new JTextField();
        txtPlaka.setBounds(199, 102, 150, 25);
        contentPane.add(txtPlaka);
        txtPlaka.setColumns(10);

        // Parola Label ve TextField (Parola alanı için JPasswordField kullanıyoruz)
        JLabel lblParola = new JLabel("Kredi Kartı:");
        lblParola.setBounds(27, 137, 80, 25);
        contentPane.add(lblParola);

        txtParola = new JPasswordField(); // JPasswordField kullanıldı.
        txtParola.setBounds(27, 161, 321, 25);
        contentPane.add(txtParola);

        // Kaydet Butonu
        JButton btnKaydet = new JButton("Kaydet");
        btnKaydet.setBounds(77, 199, 100, 30);
        contentPane.add(btnKaydet);

        // ProfileService ile profil bilgilerini çekiyoruz
        ProfileService service = new ProfileService();
        model.profile profile = service.getProfileById(session.getkullanicid());  // ID'yi alıyoruz
        System.out.println(session.getkullanicid() + " Nolu Kullanıcının profili");

        // Null kontrolü yapıyoruz
        if (profile != null) {
            txtAd.setText(profile.getAd());
            txtSoyad.setText(profile.getSoyad());
            txtTelefon.setText(profile.getTelefon());
            txtPlaka.setText(profile.getPlaka());
            txtParola.setText(profile.getKrediKartı()); // Parola alınıyor
        } else {
            System.out.println("Kullanıcı Profili oluşturuluyor");
            service.insertProfile(session.getkullanicid());  // Profil oluşturuluyor
        }
        

        // Kaydet butonuna tıklama işlemi
        btnKaydet.addActionListener(e -> {
            // Profil verisini güncellemeden önce null kontrolü yapıyoruz
            if (profile == null) {
                JOptionPane.showMessageDialog(this, "Profil oluşturulamadı. Lütfen tekrar deneyin.");
                return;
            }

            // Kullanıcı bilgilerini güncellerken boş alanları kontrol edelim
            if (txtAd.getText().isEmpty() || txtSoyad.getText().isEmpty() || txtTelefon.getText().isEmpty() || txtPlaka.getText().isEmpty() || txtParola.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun.");
                return;
            }

            // Güncel profile objesi oluşturuluyor
            model.profile updatedProfile = new model.profile(
                    profile.getId(), // ID'yi alıyoruz
                    txtAd.getText(),
                    txtSoyad.getText(),
                    txtTelefon.getText(),
                    txtPlaka.getText(),
                    new String(txtParola.getPassword()) // Parola'yı alıyoruz
            );

            if (service.updateProfile(updatedProfile)) {
                JOptionPane.showMessageDialog(this, "Bilgiler başarıyla güncellendi.");
            } else {
                JOptionPane.showMessageDialog(this, "Bilgiler güncellenemedi.");
            }
            
        });
     // Ana Menü Butonu
        JButton btnAnaMenu = new JButton("Anasayfa");
        btnAnaMenu.setBounds(199, 199, 100, 30); // Butonu yerleştir
        contentPane.add(btnAnaMenu);

        // Ana menü butonuna tıklama işlemi
        btnAnaMenu.addActionListener(e -> {
        	anasayfa.gui gui = new anasayfa.gui();
            gui.setVisible(true);
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
            if (currentFrame != null) {
                currentFrame.dispose();
            }
        });
        JButton btnNewButton = new JButton("Park Et");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		fonks.parkandquit park = new fonks.parkandquit();
        		
        		if(park.parket()) {
        			JOptionPane.showMessageDialog(btnNewButton,"Başarıyla Park Edildi");
        		}
        		else {
        			JOptionPane.showMessageDialog(btnNewButton, "Aracınız Park Halinde", "Hata", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
        btnNewButton.setBounds(240, 242, 109, 25);
        contentPane.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("Park'tan Çık ");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fonks.parkandquit parkandquit = new fonks.parkandquit();
                bakiye bakiye = new bakiye();
                
                if (parkandquit.parkçıkar()) {
                    String metinString = "Toplam Geçirilen Süre: " + (bakiye.getString() + 1) +
                                         "\nToplam Ücret: " + ((bakiye.getString() + 1) * 200) + " TL";
                    JOptionPane.showMessageDialog(btnNewButton_1, metinString);
                } else {
                    JOptionPane.showMessageDialog(btnNewButton_1, "Önce Aracı Park Etmeniz Gerekli!.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
                
                // Sayfayı yenilemek
                SwingUtilities.invokeLater(() -> {
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnNewButton_1); // Mevcut pencereyi al
                    currentFrame.dispose(); // Mevcut pencereyi kapat
                    new a().setVisible(true); // Ana çerçeveyi yeniden başlat
                });
            }
        });
        btnNewButton_1.setBounds(240, 273, 109, 25);
        contentPane.add(btnNewButton_1);
        
        JLabel OtoparkNameLabel = new JLabel("");
        OtoparkNameLabel.setBounds(37, 251, 191, 16);
       // OtoparkNameLabel.setText();
        contentPane.add(OtoparkNameLabel);
        busniess.profilpark profilParkService = new busniess.profilpark();
        String otoparkAdi = profilParkService.getOtoparkYeri();  // Kullanıcıya ait park yeri bilgisi

        if (otoparkAdi != null) {
            OtoparkNameLabel.setText("Otopark: " + otoparkAdi);  // JLabel'e park yerini yazıyoruz
        } else {
            OtoparkNameLabel.setText("Lütfen önce herhangi bir");  // Eğer park yeri yoksa alternatif mesaj
            btnNewButton.setVisible(false);
            btnNewButton_1.setVisible(false);
            
        }
        
        
        JLabel ParkYerLabel = new JLabel("");
        ParkYerLabel.setBounds(37, 273, 191, 16);
        contentPane.add(ParkYerLabel);
        String YerAdi = profilParkService.getParkYeri();
        
        if(YerAdi!= null) {
        	ParkYerLabel.setText("Park Yeri: "+YerAdi);
        }
        else {
        	ParkYerLabel.setText("park işlemi yapınız");
        }
    }
}
