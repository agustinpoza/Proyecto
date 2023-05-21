import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Auxiliares.Entry;
import Auxiliares.Par;
import Auxiliares.Position;
import Excepciones.DatoInvalidoException;


import javax.swing.JTextArea;
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

import TDALista.PositionList;


public class GUI {

	private JFrame frmMalditoFrame;
	private JLabel lblMateria;
	private JLabel lblLU;
	private JLabel lblNota;
	private JTextField txtfLu;
	private JTextField txtfNota;
	private JButton btnCargar;
	private JTextField txtBuscar;
	private JLabel lblPromedio;
	private JButton btnPromedio;
	private JLabel lblNotaMin;
	private JButton btnNotaMin;
	private JTable table;
	private JScrollPane scrollPane;
	private Programa p;
	private JButton btnAprobados;
	private JButton btnBuscar;
	private JButton btnDesaprobados;
	private JButton btnNotaMax;
	private JButton btnBuscarNota;
	private JTextField textNota1;
	
	
	
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
		
		lblLU = new JLabel("LU: ");
		lblLU.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblLU.setBounds(10, 60, 46, 14);
		frmMalditoFrame.getContentPane().add(lblLU);
		
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
		

		JLabel lblNewLabel_1 = new JLabel("Alumnos\r\n");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(688, 61, 70, 32);
		frmMalditoFrame.getContentPane().add(lblNewLabel_1);
		
		btnAprobados = new JButton("New button");
		btnAprobados.setBounds(623, 94, 89, 23);
		frmMalditoFrame.getContentPane().add(btnAprobados);
		btnAprobados.setAction(Aprobados);
		
		btnDesaprobados = new JButton("Desaprobados");
		btnDesaprobados.setBounds(722, 94, 101, 23);
		frmMalditoFrame.getContentPane().add(btnDesaprobados);
		btnDesaprobados.setAction(Desaprobados);

		lblPromedio = new JLabel("");
		lblPromedio.setBounds(345, 109, 46, 14);
		frmMalditoFrame.getContentPane().add(lblPromedio);
		
		btnPromedio = new JButton("Promedio");
		btnPromedio.setAction(promedio);
		btnPromedio.setBounds(230, 109, 103, 20);
		frmMalditoFrame.getContentPane().add(btnPromedio);

		btnBuscarNota = new JButton("buscarNota");
		btnBuscarNota.setAction(buscarNota);
		btnBuscarNota.setBounds(108, 219, 100, 20);
		frmMalditoFrame.getContentPane().add(btnBuscarNota);
		
		JLabel lblNota_1 = new JLabel("Nota: ");
		lblNota_1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lblNota_1.setBounds(10, 219, 60, 20);
		frmMalditoFrame.getContentPane().add(lblNota_1);
		
		textNota1 = new JTextField();
		textNota1.setColumns(10);
		textNota1.setBounds(58, 219, 40, 20);
		frmMalditoFrame.getContentPane().add(textNota1);
		
		btnNotaMin = new JButton("Nota Minima");
		btnNotaMin.setAction(notaMinima);
		btnNotaMin.setBounds(230, 140, 103, 20);
		frmMalditoFrame.getContentPane().add(btnNotaMin);
		
		lblNotaMin = new JLabel("");
		lblNotaMin.setBounds(345, 140, 46, 14);
		frmMalditoFrame.getContentPane().add(lblNotaMin);
		
		btnNotaMax = new JButton("Nota Maxima");
		btnNotaMax.setAction(notaMaxima);
		btnNotaMax.setBounds(230, 170, 145, 20);
		frmMalditoFrame.getContentPane().add(btnNotaMax);
		
		
	}
	
	
	protected void inicio(){
		p.setMateria(JOptionPane.showInputDialog("Ingrese el nombre de la materia: "));
		lblMateria.setText(p.getMateria());
		
	}
	
	private void mostrarAlumnos(PositionList<Par<Integer, Integer>> alumnos,String titulo) {
	    // mostrar en una ventana emergente
	    StringBuilder sb = new StringBuilder();
	    sb.append(titulo).append(":\n");
	    for (Par<Integer, Integer> alumno : alumnos) {
	        sb.append("LU: ").append(alumno.getValue()).append(", Nota: ").append(alumno.getKey()).append("\n");
	    }
	    JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Notas", JOptionPane.INFORMATION_MESSAGE);
	}
	
	@SuppressWarnings("serial")
	Action Desaprobados = new AbstractAction("Desaprobados") {
		public void actionPerformed(ActionEvent e) {
			PositionList<Par<Integer, Integer>> desaprobados = p.getAlumnosDesaprobados();
	        mostrarAlumnos(desaprobados,"Alumnos Desaprobados");

		}
	};
	
	@SuppressWarnings("serial")
	Action Aprobados = new AbstractAction("Aprobados") {
		public void actionPerformed(ActionEvent e) {
			PositionList<Par<Integer, Integer>> aprobados = p.getAlumnosAprobados();
	        mostrarAlumnos(aprobados,"Alumnos Aprobados");

		}
		
	};
	
	
	@SuppressWarnings("serial")
	Action eliminarAlumno = new AbstractAction("eliminar") {
		public void actionPerformed(ActionEvent e) {
			try {
				int lu = Integer.parseInt(txtBuscar.getText());
				if (p.eliminarAlumnoLu(lu)) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int cantFila = model.getRowCount(); //tamaño de filas
					for (int fila = 0; fila < cantFila; fila++) {
						Integer auxLu = (Integer) model.getValueAt(fila, 0);
						if (lu == auxLu) {
							model.removeRow(fila);
							break;
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "El alumno no está en el sistema", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para el LU", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
	};

	@SuppressWarnings("serial")
	Action buscarAlumno = new AbstractAction("buscar") {
	    public void actionPerformed(ActionEvent e) {
	        Integer lu = Integer.parseInt(txtBuscar.getText());
	        Par<Integer, Integer> alumno = p.getAlumnoLu(lu);
	        if (alumno == null) {
	            JOptionPane.showMessageDialog(null, "El alumno no está en el sistema", "ERROR", JOptionPane.ERROR_MESSAGE);
	        } else {
	            int nota = alumno.getKey(); 
	            JOptionPane.showMessageDialog(null, "La nota del alumno es: " + nota, "Congratulation", JOptionPane.INFORMATION_MESSAGE);
	        }
	    }
	};
	
	@SuppressWarnings("serial")
	Action subirAlumno = new AbstractAction("agregar") {
		@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	@SuppressWarnings("serial")
	Action promedio = new AbstractAction("Promedio") {
		public void actionPerformed(ActionEvent e) {
			int prom = p.calcularPromedio();
			lblPromedio.setText(String.valueOf(prom));
		}
	};
	
	@SuppressWarnings("serial")
	Action buscarNota = new AbstractAction("buscar nota") {
		public void actionPerformed(ActionEvent e) {
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Comic Sans", Font.PLAIN, 12));
			for(Entry<Integer,Integer> i : p.alumnosConNota(Integer.parseInt(textNota1.getText()))){
				textArea.append(i.getValue() + "\n");
			}
			JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(50, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "Los alumnos con la nota "+textNota1.getText(), 1);
		}
	};
	
	@SuppressWarnings("serial")
	Action notaMaxima = new AbstractAction("Nota de mayor a menor") {
		public void actionPerformed(ActionEvent e) {
			JTextArea textArea = new JTextArea();
			textArea.setFont(new Font("Comic Sans", Font.PLAIN, 12));
			for(Position<Entry<Integer, Integer>> i : p.NotaMayorMenor().positions()){
				textArea.append("Alumno: "+i.element().getValue()+" Nota: "+i.element().getKey()+ "\n");
			}
			JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(50, 300));
            JOptionPane.showMessageDialog(null, scrollPane, "" , 1);
		}
	};
	
	@SuppressWarnings("serial")
	Action notaMinima = new AbstractAction("Nota Minima") {
		public void actionPerformed(ActionEvent e) {
			int notaMin = p.NotaMinima();
			lblNotaMin.setText(String.valueOf(notaMin));
			
		}
	};
	
	
	
}
