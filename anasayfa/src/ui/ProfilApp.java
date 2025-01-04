package ui;

import model.profile;

import javax.swing.*;

import busniess.ProfileService;
import kullanici.session;

import java.awt.*;

public class ProfilApp extends JFrame {

    public static void main(String[] args) {
        JFrame frm = new JFrame("Kullanıcı Profil Sayfası");
        frm.setSize(400, 300);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblAd = new JLabel("Ad:");
        JTextField txtAd = new JTextField(20);

        JLabel lblSoyad = new JLabel("Soyad:");
        JTextField txtSoyad = new JTextField(20);

        JLabel lblTelefon = new JLabel("Telefon:");
        JTextField txtTelefon = new JTextField(20);

        JLabel lblPlaka = new JLabel("Plaka:");
        JTextField txtPlaka = new JTextField(20);

        JLabel lblKrediKarti = new JLabel("Kredi Kartınızı giriniz:");
        JTextField txtKrediKarti = new JTextField(20);

        JButton btnKaydet = new JButton("Kaydet");

        gbc.gridx = 0;
        gbc.gridy = 0;
        frm.add(lblAd, gbc);
        gbc.gridx = 1;
        frm.add(txtAd, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frm.add(lblSoyad, gbc);
        gbc.gridx = 1;
        frm.add(txtSoyad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        frm.add(lblTelefon, gbc);
        gbc.gridx = 1;
        frm.add(txtTelefon, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        frm.add(lblPlaka, gbc);
        gbc.gridx = 1;
        frm.add(txtPlaka, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        frm.add(lblKrediKarti, gbc);
        gbc.gridx = 1;
        frm.add(txtKrediKarti, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        frm.add(btnKaydet, gbc);

        // ProfileService ile profil bilgilerini çekiyoruz
        ProfileService service = new ProfileService();
        session session = new session();
        profile profile = service.getProfileById(session.getkullanicid()); 
        System.out.println(session.getkullanicid()+" Nolu Kullanıcının Profili");

        // Null kontrolü yapıyoruz
        if (profile != null) {
            txtAd.setText(profile.getAd());
            txtSoyad.setText(profile.getSoyad());
            txtTelefon.setText(profile.getTelefon());
            txtPlaka.setText(profile.getPlaka());
            txtKrediKarti.setText(profile.getKrediKartı());
        } else {
            JOptionPane.showMessageDialog(frm, "Kullanıcı bulunamadı.");
        }

        btnKaydet.addActionListener(e -> {
            if (profile != null) {
                // Güncel profile objesi oluşturuluyor
                profile updatedProfile = new profile(
                        profile.getId(), // ID'yi alıyoruz
                        txtAd.getText(),
                        txtSoyad.getText(),
                        txtTelefon.getText(),
                        txtPlaka.getText(),
                        txtKrediKarti.getText()
                );

                if (service.updateProfile(updatedProfile)) {
                    JOptionPane.showMessageDialog(frm, "Bilgiler başarıyla güncellendi.");
                } else {
                    JOptionPane.showMessageDialog(frm, "Bilgiler güncellenemedi.");
                }
            } else {
                JOptionPane.showMessageDialog(frm, "Profil verisi mevcut değil.");
            }
        });

        frm.setVisible(true);
    }
}