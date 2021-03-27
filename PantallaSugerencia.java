package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.InvestigacionDAO;
import estructuraTP.DAO.SugerenciaDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Investigacion;
import estructuraTP.modelo.Sugerencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

public class PantallaSugerencia extends JPanel {
	private DefaultTableModel modeloDeTabla;
	private JTable table;

	public PantallaSugerencia(JFrame m) {
		setLayout(null);
		
		Object[] columnas = new Object[] {"Id","Frase", "Medio", "Enlace", "Fecha"};
		modeloDeTabla = new DefaultTableModel(columnas,0);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 311, 147);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		table.setModel(modeloDeTabla);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 467, 21);
		add(menuBar);
		
		JLabel lblMenSugerencias = new JLabel("Men\u00FA Sugerencias");
		menuBar.add(lblMenSugerencias);
		
		SugerenciaDAO sDAO = new SugerenciaDAO();
		ArrayList<Sugerencia> sugerencias = sDAO.mostrarTodo(); 
		for(Sugerencia s:sugerencias) {
		modeloDeTabla.addRow(new Object[] {s.getId(),s.getFrase(),s.getMedio(),s.getEnlace(),s.getFecha()});
		}
		
		
		JButton btnChequeo = new JButton("Chequeo");
		btnChequeo.setBounds(343, 59, 91, 23);
		add(btnChequeo);
		btnChequeo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String frase= table.getValueAt(table.getSelectedRow(), 1).toString();
				String medio =table.getValueAt(table.getSelectedRow(), 2).toString();
				String link=table.getValueAt(table.getSelectedRow(), 3).toString();
				LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 4).toString());
				Chequeo c=new Chequeo(frase, medio, link, fecha);
				
				m.setContentPane(new PantallaAMChequeo(m, c,id));
				m.validate();
			}
		});
		
		
		JButton btnDescartar = new JButton("Descartar");
		btnDescartar.setBounds(343, 124, 92, 23);
		add(btnDescartar);
		btnDescartar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				SugerenciaDAO sDAO = new SugerenciaDAO();
				if(table.getSelectedRow() != -1) {
					int id=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					String frase=table.getValueAt(table.getSelectedRow(),1).toString();
					String medio=table.getValueAt(table.getSelectedRow(),2).toString();
					String enlace=table.getValueAt(table.getSelectedRow(),3).toString();
					LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 4).toString());
					Sugerencia s = new Sugerencia(id, frase, medio, enlace, fecha);
					sDAO.eliminarSugerencia(s);
					
					}else {
						JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado un campo");
					}
				modeloDeTabla.setRowCount(0);
				ArrayList<Sugerencia> sugerencias = sDAO.mostrarTodo(); 
				for(Sugerencia s:sugerencias) {
				modeloDeTabla.addRow(new Object[] {s.getId(),s.getFrase(),s.getMedio(),s.getEnlace(),s.getFecha()});
				}
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(343, 183, 92, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaChequeo(m));
				m.validate();
			}
		});

	}
}
