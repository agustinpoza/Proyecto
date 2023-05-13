

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class asdqwrqasdas extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					asdqwrqasdas frame = new asdqwrqasdas();
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
	public asdqwrqasdas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 522);
		getContentPane().setLayout(null);
		
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("AAAAA");
	}
}
