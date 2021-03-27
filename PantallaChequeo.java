package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.ChequeoDAO;
import estructuraTP.modelo.Chequeo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PantallaChequeo extends JPanel{
	
	private JTextField txtBuscar;
	private DefaultTableModel modeloDeTabla;
	private JTable table;
	
	String timezone = "&serverTimezone=UTC";
	
	public PantallaChequeo(JFrame m) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 77, 450, 127);
		add(scrollPane);
	
		table = new JTable();
		scrollPane.setViewportView(table);
	
		Object[] columnas = new Object[] {"ID", "Frase", "Fecha", "Medio", 
		"Enlace", "Palabra Clave", "Verificación", "Categoría"};
		modeloDeTabla = new DefaultTableModel(columnas,0);
		table.setModel(modeloDeTabla);
		
		ChequeoDAO cDAO = new ChequeoDAO();
		ArrayList<Chequeo> todos = cDAO.todosLosChequeos();
		for(Chequeo ch : todos) {
			modeloDeTabla.addRow(new Object[] {ch.getId(), ch.getFrase(), ch.getFecha(), ch.getMedio(), ch.getEnlace(), ch.getPalabraClave(), ch.getVerificacion(), ch.getCategoria()});
		}
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		add(menuBar);
		
		JMenu mnMen = new JMenu("Men\u00FA Chequeos");
		menuBar.add(mnMen);
		
		JMenuItem mntmInicio = new JMenuItem("Inicio");
		mnMen.add(mntmInicio);
		mntmInicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInicio(m));
				m.validate();
			}
		});
		
		JMenuItem mntmChequeos = new JMenuItem("Chequeos");
		mnMen.add(mntmChequeos);
		mntmChequeos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaChequeo(m));
				m.validate();
			}
		});
		
		JMenuItem mntmExplicaciones = new JMenuItem("Explicaciones");
		mnMen.add(mntmExplicaciones);
		mntmExplicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaExplicacion(m));
				m.validate();
			}
		});
		
		JMenuItem mntmInvestigaciones = new JMenuItem("Investigaciones");
		mnMen.add(mntmInvestigaciones);
		mntmInvestigaciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInvestigacion(m));
				m.validate();
			}
		});
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(10, 33, 233, 20);
		add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(265, 32, 89, 23);
		add(btnBuscar);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloDeTabla.setRowCount(0);
				ChequeoDAO cDAO = new ChequeoDAO();
				String pc = txtBuscar.getText();
				ArrayList<Chequeo> todos = cDAO.buscarChequeos(pc);
				for(Chequeo c : todos) {
					modeloDeTabla.addRow(new Object[] {c.getId(), c.getFrase(), c.getFecha(), c.getMedio(), c.getEnlace(), c.getPalabraClave(), c.getVerificacion(), c.getCategoria()});
					table.setModel(modeloDeTabla);
				}
			}
		});
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(10, 239, 89, 23);
		add(btnCrear);
		btnCrear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaSugerencia(m));
				m.validate();
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(123, 239, 89, 23);
		add(btnModificar);
		btnModificar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
				int id=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String frase=table.getValueAt(table.getSelectedRow(),1).toString();
				LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 2).toString());
				String medio=table.getValueAt(table.getSelectedRow(),3).toString();
				String enlace=table.getValueAt(table.getSelectedRow(),4).toString();
				String palabra_clave=table.getValueAt(table.getSelectedRow(),5).toString();
				String verificacion=table.getValueAt(table.getSelectedRow(),6).toString();
				String categoria=table.getValueAt(table.getSelectedRow(),7).toString();
				Chequeo c = new Chequeo(id, frase, fecha, medio, enlace, palabra_clave, verificacion, categoria);
				
				m.setContentPane(new PantallaAMChequeo(m, c, 0));
				m.validate();
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado un campo");
				}
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(351, 239, 89, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInicio(m));
				m.validate();
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChequeoDAO cDAO = new ChequeoDAO();
				//int id=table.getValueAt(table.getSelectedRow(), 0);
				//cDAO.eliminarChequeo(c.getId);
				if(table.getSelectedRow() != -1) {
					int id=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					String frase=table.getValueAt(table.getSelectedRow(),1).toString();
					LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 2).toString());
					String medio=table.getValueAt(table.getSelectedRow(),3).toString();
					String enlace=table.getValueAt(table.getSelectedRow(),4).toString();
					String palabra_clave=table.getValueAt(table.getSelectedRow(),5).toString();
					String verificacion=table.getValueAt(table.getSelectedRow(),6).toString();
					String categoria=table.getValueAt(table.getSelectedRow(),7).toString();
					Chequeo c = new Chequeo(id, frase, fecha, medio, enlace, palabra_clave, verificacion, categoria);
					cDAO.eliminarChequeo(c);
					
					}else {
						JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado un campo");
					}
				modeloDeTabla.setRowCount(0);
				ArrayList<Chequeo> todos = cDAO.todosLosChequeos();
				for(Chequeo c : todos) {
					modeloDeTabla.addRow(new Object[] {c.getId(), c.getFrase(), c.getFecha(), c.getMedio(), c.getEnlace(), c.getPalabraClave(), c.getVerificacion(), c.getCategoria()});
				}
			}	
		});
		btnEliminar.setBounds(235, 239, 89, 23);
		add(btnEliminar);
	
		
	}
}
