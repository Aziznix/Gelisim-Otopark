package kontrol;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import kullanici.session;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class postaonay extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    postaonay frame = new postaonay();
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
    public postaonay() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\xampp\\htdocs\\internetprogramcılığıproje\\images\\icon.png"));
        setTitle("Kayıt Başarılı!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 370);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Kayıt Başarılı");
        lblNewLabel.setBounds(155, 49, 124, 28);
        lblNewLabel.setFont(new Font("Poppins", Font.BOLD, 18));
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Lütfen E-Posta adresinize gönderilen 4 haneli onay kodunu giriniz");
        lblNewLabel_1.setFont(new Font("Poppins", Font.BOLD, 11));
        lblNewLabel_1.setBounds(36, 88, 361, 14);
        contentPane.add(lblNewLabel_1);

        textField = createLimitedTextField();
        textField.setBounds(71, 136, 48, 76);
        contentPane.add(textField);

        textField_1 = createLimitedTextField();
        textField_1.setBounds(155, 136, 48, 76);
        contentPane.add(textField_1);

        textField_2 = createLimitedTextField();
        textField_2.setBounds(239, 136, 48, 76);
        contentPane.add(textField_2);

        textField_3 = createLimitedTextField();
        textField_3.setBounds(323, 136, 48, 76);
        contentPane.add(textField_3);
        
        JLabel lblNewLabel_2 = new JLabel("Eğer e-postanızı onaylamazsanız tekrar kayıt olmanız gerekmektedir.");
        lblNewLabel_2.setBounds(22, 292, 434, 44);
        contentPane.add(lblNewLabel_2);
        
        JButton btnNewButton = new JButton("ONAYLA");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		session session = new session();
        		kontrol.onaykod postaonay = new kontrol.onaykod();
        		
        		String kod1= textField.getText();
        		String kod2= textField_1.getText();
        		String kod3= textField_2.getText();
        		String kod4= textField_3.getText();
        		
        		String OnayKodu = kod1+kod2+kod3+kod4;
        		
        		if (postaonay.kullaniciOnayla(session.getkullanicid(), OnayKodu)) {
        			
        			JOptionPane.showMessageDialog(btnNewButton, "Hesabınız Onaylandı Giriş Yapabilirsiniz");
        			model.gui gui = new model.gui();
                    gui.setVisible(true);
                    JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(contentPane);
                    if (currentFrame != null) {
                        currentFrame.dispose();
                    }
					
				}
        		else {
        			JOptionPane.showMessageDialog(btnNewButton, "Onay Kodu Hatalı!","Hata", JOptionPane.ERROR_MESSAGE);
        		}
        		
        		
        	}
        });
        btnNewButton.setFont(new Font("Poppins", Font.BOLD, 18));
        btnNewButton.setBounds(129, 244, 176, 37);
        contentPane.add(btnNewButton);
    }

    /**
     * Creates a JTextField with a maximum character limit of 1.
     */
    private JTextField createLimitedTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Poppins", Font.BOLD, 60));
        textField.setColumns(1);

        // Apply a DocumentFilter to limit input to 1 character
        AbstractDocument document = (AbstractDocument) textField.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 1) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= 1) {
                    super.insertString(fb, offset, string, attr);
                }
            }
        });

        return textField;
    }
}
