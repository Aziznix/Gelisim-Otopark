package model;

import kontrol.kullanicikontrol;
import kontrol.postaonay;
import kullanici.bakiye;
import kullanici.session;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fonks.hızlıödeme;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;

public class gui extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField getParola;
    private JTextField getKullaniciAdi;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                gui frame = new gui();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public gui() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\xampp\\htdocs\\internetprogramcılığıproje\\images\\icon.png"));
        setTitle("Gelişim Otopark");

        // Kullanıcı kontrol sınıfı
        kullanicikontrol kullanicikontrol = new kullanicikontrol();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 652, 422);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(140, 207, 247));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        getKullaniciAdi = new JTextField();
        getKullaniciAdi.setBounds(33, 104, 176, 37);
        contentPane.add(getKullaniciAdi);

        JLabel txtKullanıcıad = new JLabel("Kullanıcı Adı:");
        txtKullanıcıad.setForeground(new Color(255, 255, 255));
        txtKullanıcıad.setFont(new Font("Poppins", Font.BOLD, 18));
        txtKullanıcıad.setBounds(33, 86, 176, 16);
        contentPane.add(txtKullanıcıad);

        getParola = new JTextField();
        getParola.setBounds(33, 190, 176, 37);
        contentPane.add(getParola);

        JLabel lblNewLabel = new JLabel("Parola:");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel.setBounds(33, 175, 160, 16);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Kayıt Ol");
        btnNewButton.addActionListener(e -> {
            String Kullaniciadi = textField.getText();
            String Parola = textField_1.getText();
            String Eposta = textField_2.getText();

            if (kullanicikontrol.kayit(Kullaniciadi, Parola, Eposta)) {
                System.out.println("Kayıt Başarılı");
                kontrol.postaonay postaonay = new postaonay();
                postaonay.setVisible(true);
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
                currentFrame.dispose();
                
            } else {
            	 JOptionPane.showMessageDialog(btnNewButton, "Bu Kullanıcı Adı Kullanılmakta!", "Hata", JOptionPane.ERROR_MESSAGE);
                System.out.println("Kayıt Yapılamadı");
            }
        });
        btnNewButton.setBounds(392, 250, 97, 25);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Giriş Yap");
        btnNewButton_1.addActionListener(e -> {
            String Kullaniciadi = getKullaniciAdi.getText();
            String Parola = getParola.getText();

            if (kullanicikontrol.giris(Kullaniciadi, Parola)) {
            	 JOptionPane.showMessageDialog(btnNewButton_1, "Giriş Başarılı Hoşgeldin "+Kullaniciadi);
                // Ana sayfa GUI'yi aç
                anasayfa.gui gui = new anasayfa.gui();
                gui.setVisible(true);

                // Login ekranını kapat
                session session = new session();
                System.out.println("Giriş Yapan Kullanici id: "+session.getkullanicid());
                hızlıödeme sorgu = new hızlıödeme();
                bakiye bakiye = new bakiye();
                int güncelbakiye=sorgu.borcisleme(session.getkullanicid());
                bakiye.setbakiye(güncelbakiye);
                System.out.println("Kullanıcının Güncel Borcu: "+bakiye.getbakiye());
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
                if (currentFrame != null) {
                    currentFrame.dispose();
                }
            } else {
                System.out.println("Kullanıcı Bulunamadı");
             	JOptionPane.showMessageDialog(btnNewButton_1, "Lütfen Şifrenizi veya Kullanıcı Adınızı Kontrol Ediniz!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnNewButton_1.setBounds(69, 250, 97, 25);
        contentPane.add(btnNewButton_1);
        
        textField = new JTextField();
        textField.setBounds(357, 76, 176, 37);
        contentPane.add(textField);
        
        JLabel txtKullanıcıad_1 = new JLabel("Kullanıcı Adı:");
        txtKullanıcıad_1.setForeground(new Color(255, 255, 255));
        txtKullanıcıad_1.setFont(new Font("Poppins", txtKullanıcıad_1.getFont().getStyle() | Font.BOLD, 18));
        txtKullanıcıad_1.setBounds(357, 58, 176, 16);
        contentPane.add(txtKullanıcıad_1);
        
        textField_1 = new JTextField();
        textField_1.setBounds(357, 139, 176, 37);
        contentPane.add(textField_1);
        
        JLabel lblNewLabel_1 = new JLabel("Parola:");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel_1.setBounds(357, 124, 175, 16);
        contentPane.add(lblNewLabel_1);
        
        textField_2 = new JTextField();
        textField_2.setBounds(357, 202, 176, 37);
        contentPane.add(textField_2);
        
        JLabel lblNewLabel_1_1 = new JLabel("E-Posta");
        lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1_1.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(357, 187, 175, 16);
        contentPane.add(lblNewLabel_1_1);
    }
}
