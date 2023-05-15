import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Auxiliares.Entry;
import Excepciones.AlumnoRepetidoException;
import Excepciones.DatoInvalidoException;
import Excepciones.InvalidEntryException;
import Excepciones.InvalidKeyException;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import TDADiccionario.Dictionary;
import TDADiccionario.DiccionarioHash;

public class GUI {

	private JFrame frmMalditoFrame;
	private JLabel lblMateria;
	private JLabel lblNewLabel;
	private JLabel lblNota;
	private JTextField txtfLu;
	private JTextField txtfNota;
	private JButton btnCargar;
	private JTextField txtBuscar;
	//private Action subirAlumno = new SubirAlumno();
	
	private String Materia;
	private JTable table;
	private JScrollPane scrollPane;
	protected Programa p;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMalditoFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */
	private void initialize() {
		frmMalditoFrame = new JFrame();
		frmMalditoFrame.getContentPane().setLayout(null);
		frmMalditoFrame.setTitle("Maldito Frame");
		frmMalditoFrame.setBounds(100, 100, 1116, 657);
		frmMalditoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lblMateria = new JLabel();
		lblMateria.setForeground(new Color(255, 255, 255));
		lblMateria.setOpaque(true);
		lblMateria.setHorizontalAlignment(SwingConstants.CENTER);
		lblMateria.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 43));
		lblMateria.setBackground(new Color(0, 0, 0));
		lblMateria.setBounds(0, 0, 1100, 50);
		frmMalditoFrame.getContentPane().add(lblMateria);
		p = new Programa();
		
		inicio();
		
		
		
		lblNewLabel = new JLabel("LU: ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 60, 46, 14);
		frmMalditoFrame.getContentPane().add(lblNewLabel);
		
		lblNota = new JLabel("Nota: ");
		lblNota.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNota.setBounds(165, 60, 60, 20);
		frmMalditoFrame.getContentPane().add(lblNota);
		
		txtfLu = new JTextField();
		txtfLu.setBounds(40, 60, 100, 20);
		frmMalditoFrame.getContentPane().add(txtfLu);
		txtfLu.setColumns(10);
		
		txtfNota = new JTextField();
		txtfNota.setBounds(213, 60, 40, 20);
		frmMalditoFrame.getContentPane().add(txtfNota);
		txtfNota.setColumns(10);
		
		btnCargar = new JButton("agregar");
		btnCargar.setName("agregar");
		btnCargar.setAction(subirAlumno);
		btnCargar.setBounds(263, 60, 70, 20);
		frmMalditoFrame.getContentPane().add(btnCargar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(854, 61, 236, 546);
		frmMalditoFrame.getContentPane().add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("LU");
		model.addColumn("NOTA");
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"LU", "Nota"
			}
		));
		
		JLabel lblLuBuscar = new JLabel("LU: ");
		lblLuBuscar.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblLuBuscar.setBounds(10, 103, 46, 14);
		frmMalditoFrame.getContentPane().add(lblLuBuscar);
		
		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(40, 103, 100, 20);
		frmMalditoFrame.getContentPane().add(txtBuscar);
		
		JButton btnEliminar = new JButton("eliminar");
		btnEliminar.setAction(eliminarAlumno);
		btnEliminar.setSize(70, 20);
		btnEliminar.setLocation(150, 140);
		frmMalditoFrame.getContentPane().add(btnEliminar);
		
		btnBuscar = new JButton("New button");
		btnBuscar.setAction(buscarAlumno);
		btnBuscar.setBounds(150, 103, 70, 20);
		frmMalditoFrame.getContentPane().add(btnBuscar);
		
		
		
	}
	
	
	
	protected void inicio(){
		p.setMateria(JOptionPane.showInputDialog("Ingrese el nombre de la materia: "));
		lblMateria.setText(p.getMateria());
		
	}
	Action eliminarAlumno = new AbstractAction("eliminar") {
	public void actionPerformed(ActionEvent e) {
			try {
				if(p.eliminarAlumnoLu(Integer.parseInt(txtBuscar.getText()))) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				boolean encontre = false;
				for(int fila = 0; fila<model.getRowCount() && encontre == false; fila++) {
					Integer auxLu = (Integer) model.getValueAt(fila, 0);
					if(Integer.parseInt(txtfLu.getText()) == auxLu) {
						model.removeRow(fila);
						encontre = true;
					}
				}
			}
				else JOptionPane.showMessageDialog(null, "El alumno no esta en el sistema", "ERROR", 0);
			}
			catch (NumberFormatException e1) {}	
		}
	};
	
	Action buscarAlumno = new AbstractAction("buscar") {
		public void actionPerformed(ActionEvent e) {
			Integer i = p.getAlumnoLu(Integer.parseInt(txtBuscar.getText())).getKey();
			if(i == null) {
				JOptionPane.showMessageDialog(null, "El alumno no esta en el sistema", "ERROR", 0);
			}
			else JOptionPane.showMessageDialog(null, "La nota del alumno es: "+i, "Congratulation", 1);
		}
	};
	
	Action subirAlumno = new AbstractAction("agregar") {
		public void actionPerformed(ActionEvent e) {
			try {
			Par alumno = new Par();
			alumno.setLu(Integer.parseInt(txtfLu.getText()));
			alumno.setNota(Integer.parseInt(txtfNota.getText()));
			if(p.setAlumno(alumno)) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Integer[] {(Integer) alumno.getValue(), (Integer)alumno.getKey()});
			}
			else JOptionPane.showMessageDialog(null, "Datos Ingresados Incorrectos", "ERROR", 0);
			}
			catch (DatoInvalidoException | NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Datos Ingresados Incorrectos", "ERROR", 0);
			}
		}
	};
	private JButton btnBuscar;
	
}
