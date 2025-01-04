package anasayfa;

import java.awt.EventQueue;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.im.InputContext;
import java.security.PublicKey;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import fonks.hızlıödeme;
import kullanici.session;
import otopark.servis;

public class gui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
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
	public gui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\xampp\\htdocs\\internetprogramcılığıproje\\images\\icon.png"));
		setTitle("Gelişim Otopark");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 451);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(140, 207, 247));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Profil ve Oturum Kapat Butonları
		JButton btnProfil = new JButton("Profil");
		btnProfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ui.a guiApp = new ui.a();
				guiApp.setVisible(true);
				
				JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
                if (currentFrame != null) {
                    currentFrame.dispose();
                }
				
			}
		});
		btnProfil.setFont(new Font("Poppins", Font.BOLD, 11));
		btnProfil.setBounds(403, 28, 89, 23);
		contentPane.add(btnProfil);

		JButton btnClose = new JButton("Oturum Kapat");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.gui gui = new model.gui();
				 gui.setVisible(true);
				 
				 
				 session session = new session();
	                System.out.println("Çıkış Yapan Kullanici id: "+session.getkullanicid());
	                session.setkullanid(0);
	                System.out.println("Güncel id: "+session.getkullanicid());
	                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
	                if (currentFrame != null) {
	                    currentFrame.dispose();
	                }
			}
		});
		btnClose.setFont(new Font("Poppins", Font.BOLD, 11));
		btnClose.setBounds(497, 28, 111, 23);
		contentPane.add(btnClose);

		// Otopark Seçimi
		JLabel lblNewLabel = new JLabel("Mevcut Otoparklar");
		lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 24));
		lblNewLabel.setBounds(27, 140, 258, 44);
		contentPane.add(lblNewLabel);

		JComboBox comboBoxOtopark = new JComboBox();
		comboBoxOtopark.setFont(new Font("Poppins", Font.BOLD, 11));
		comboBoxOtopark.setBounds(27, 175, 222, 22);
		contentPane.add(comboBoxOtopark);

		// Otopark Adlarını Veritabanından Çek
		servis servis = new servis();
		List<String> dataList = servis.getColumnData();
		comboBoxOtopark.addItem("Otopark Seçiniz");
		for (String data : dataList) {
			comboBoxOtopark.addItem(data);
		}

		// Müsait Yerler Başlık
		JLabel lblNewLabel_1 = new JLabel("Müsait Yerler");
		lblNewLabel_1.setFont(new Font("Poppins", Font.BOLD, 20));
		lblNewLabel_1.setBounds(27, 208, 232, 23);
		contentPane.add(lblNewLabel_1);

		JComboBox comboBoxYerler = new JComboBox();
		comboBoxYerler.setFont(new Font("Poppins", Font.BOLD, 11));
		comboBoxYerler.setBounds(27, 231, 222, 22);
		contentPane.add(comboBoxYerler);

		// Otopark Seçimi Değiştiğinde Müsait Yerleri Güncelle
		comboBoxOtopark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ParkAdi = (String) comboBoxOtopark.getSelectedItem();
				List<String> yerList = servis.getParkYerleri(ParkAdi);

				// Önceden eklenmiş yerleri temizle
				comboBoxYerler.removeAllItems();

				// Yeni yerleri ekle
				for (String data : yerList) {
					comboBoxYerler.addItem(data);
				}
			}
		});

		JButton btnRezerve = new JButton("Rezerve Et");
		btnRezerve.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // servis nesnesini oluşturuyoruz
		        servis servisInstance = new servis();
		        
		        // ComboBox'lardan seçilen değerleri alıyoruz ve rezervasyonu yapıyoruz
		        servisInstance.setRezerv(comboBoxOtopark.getSelectedItem().toString(), comboBoxYerler.getSelectedItem().toString());
		        
		        comboBoxYerler.removeAllItems();
		        String ParkAdi = (String) comboBoxOtopark.getSelectedItem();
				List<String> yerList = servis.getParkYerleri(ParkAdi);

				// Yeni yerleri ekle
				for (String data : yerList) {
					comboBoxYerler.addItem(data);
				}
		    }
		});

		btnRezerve.setFont(new Font("Poppins", Font.BOLD, 11));
		btnRezerve.setBounds(27, 296, 117, 44);
		contentPane.add(btnRezerve);

		// Park Et Butonu
		JButton btnParkEt = new JButton("Park Et");
		btnParkEt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			     servis servisInstance = new servis();
			        
			        // ComboBox'lardan seçilen değerleri alıyoruz ve rezervasyonu yapıyoruz
			        servisInstance.setParkEt(comboBoxOtopark.getSelectedItem().toString(),comboBoxYerler.getSelectedItem().toString());
			        
			        comboBoxYerler.removeAllItems();
			        String ParkAdi = (String) comboBoxOtopark.getSelectedItem();
					List<String> yerList = servis.getParkYerleri(ParkAdi);

					// Yeni yerleri ekle
					for (String data : yerList) {
						comboBoxYerler.addItem(data);
					}
			    }
		});
		btnParkEt.setFont(new Font("Poppins", Font.BOLD, 11));
		btnParkEt.setBounds(154, 296, 117, 44);
		contentPane.add(btnParkEt);

		// Hızlı Ödeme Başlık
		JLabel lblNewLabel_2 = new JLabel("Hızlı Ödeme");
		lblNewLabel_2.setFont(new Font("Poppins", Font.BOLD, 24));
		lblNewLabel_2.setBounds(403, 145, 181, 35);
		contentPane.add(lblNewLabel_2);

		// Araç Plakası Alanı
		textField = new JTextField();
        textField.setFont(new Font("Poppins", Font.BOLD, 11));
        textField.setBounds(403, 211, 151, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        // Borç Alanı
        JLabel lblNewLabel_4 = new JLabel("Borcunuz:");
        lblNewLabel_4.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel_4.setBounds(403, 245, 111, 14);
        contentPane.add(lblNewLabel_4);

        // Borç etiketini oluştur
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setFont(new Font("Poppins", Font.BOLD, 18));
        lblNewLabel_5.setForeground(new Color(0, 255, 0));
        lblNewLabel_5.setBounds(508, 242, 100, 18); 
        contentPane.add(lblNewLabel_5);

      
        fonks.hızlıödeme hızlıödeme = new fonks.hızlıödeme();

        
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaka = textField.getText(); 
                String borc = hızlıödeme.BorcSorgu(plaka); 
                if (borc != null) {
                    lblNewLabel_5.setText(borc + " TL"); 
                } else {
                    lblNewLabel_5.setText(""); 
                }
            }
        });

		// Ödeme Yap Butonu
		JButton btnNewButton = new JButton("Ödeme Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonks.hızlıödeme hızlıödeme = new fonks.hızlıödeme();
				
				if(hızlıödeme.borcödeme(session.getkullanicid())) {
					JOptionPane.showMessageDialog(btnNewButton,"Ödeme Başarılı!");
					
					lblNewLabel_5.setText(""); 
				}else {
					 JOptionPane.showMessageDialog(btnNewButton, "Ödeme Sırasında Bir Hata Oluştu \nLütfen Daha Sonra Tekrar Deneyiniz", "Ödeme Hatası", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("Poppins", Font.BOLD, 11));
		btnNewButton.setBounds(419, 356, 119, 23);
		contentPane.add(btnNewButton);

		// Kart Seçimi Alanı
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Poppins", Font.BOLD, 11));
		comboBox.setBounds(403, 307, 151, 22);
		contentPane.add(comboBox);
		busniess.ProfileService profileService = new busniess.ProfileService();
		int userId = session.getkullanicid();
		List<String> krediKartlari = profileService.getKrediKartlariByUserId(userId);
		comboBox.addItem("Kart Seçiniz");
		for (String kart : krediKartlari) {
		    comboBox.addItem(kart);
		}

		// Kart Seçimi Başlık
		JLabel lblNewLabel_3_1 = new JLabel("Kart Seçiniz:");
		lblNewLabel_3_1.setFont(new Font("Poppins", Font.BOLD, 18));
		lblNewLabel_3_1.setBounds(403, 281, 151, 18);
		contentPane.add(lblNewLabel_3_1);

		// Logo
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\xampp\\htdocs\\internetprogramcılığıproje\\images\\logo.png"));
		lblNewLabel_6.setBounds(-60, 0, 444, 118);
		contentPane.add(lblNewLabel_6);

		// Arka Plan Resmi
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\Aziz\\Downloads\\istockphoto-896263238-170667a-Photoroom.png"));
		lblNewLabel_7.setBounds(10, 146, 557, 266);
		contentPane.add(lblNewLabel_7);
		
			
	}
	
}
