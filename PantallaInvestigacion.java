package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import estructuraTP.DAO.InvestigacionDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Investigacion;
import estructuraTP.modelo.Sugerencia;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class PantallaInvestigacion extends JPanel {
	
	private JTable table;
	private JTextField Buscartxt;
	private DefaultTableModel modeloDeTabla;

	/**
	 * Create the panel.
	 */
	public PantallaInvestigacion(JFrame m) {
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 631, 21);
		add(menuBar);
		
		JMenu mnMen = new JMenu("Men\u00FA Investigaciones");
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 452, 206);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		Object[] columnas = new Object[] {"Id","Categoria", "Tema", "Palabra Clave", "Titulo", "Epigrafe", "Contenido", "Fecha"};
		modeloDeTabla = new DefaultTableModel(columnas,0);
		table.setModel(modeloDeTabla);
		
		InvestigacionDAO iDAO = new InvestigacionDAO();
		ArrayList<Investigacion> Investigaciones = iDAO.todasLasInvestigaciones();
		for(Investigacion i : Investigaciones) {
			modeloDeTabla.addRow(new Object[] {i.getId(),i.getCategoria(),i.getTema(),i.getPalabraClave(),i.getTitulo(),i.getEpigrafe(),i.getContenido(),i.getFecha()});
		}
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(492, 86, 91, 23);
		add(btnCrear);
		btnCrear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaAMInvestigacion(m,null));
				m.validate();
			}
		});
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(492, 120, 91, 23);
		add(btnActualizar);
		btnActualizar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
				int id= Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				String categoria= table.getValueAt(table.getSelectedRow(), 1).toString();
				String tema= table.getValueAt(table.getSelectedRow(), 2).toString();
				String palabra_clave= table.getValueAt(table.getSelectedRow(), 3).toString();
				String titulo= table.getValueAt(table.getSelectedRow(), 4).toString();
				String epigrafe= table.getValueAt(table.getSelectedRow(), 5).toString();
				String contenido= table.getValueAt(table.getSelectedRow(), 6).toString();
				LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 7).toString());
				
				Investigacion i=new Investigacion(id, categoria, tema, palabra_clave, titulo, epigrafe, contenido, fecha);
				
				
				m.setContentPane(new PantallaAMInvestigacion(m,i));
				m.validate();
				}else {
					JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado un campo");
				}
			}
		});
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(492, 154, 91, 23);
		add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InvestigacionDAO iDAO = new InvestigacionDAO();
				if(table.getSelectedRow() != -1) {
					int id=Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					String categoria=table.getValueAt(table.getSelectedRow(),1).toString();
					String tema=table.getValueAt(table.getSelectedRow(),2).toString();
					String palabra_clave=table.getValueAt(table.getSelectedRow(),3).toString();
					String titulo=table.getValueAt(table.getSelectedRow(),4).toString();
					String epigrafe=table.getValueAt(table.getSelectedRow(),5).toString();
					String contenido=table.getValueAt(table.getSelectedRow(),6).toString();
					LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 7).toString());
					Investigacion i = new Investigacion(id, categoria, tema, palabra_clave, titulo, epigrafe, contenido, fecha);
					iDAO.eliminacion(i);
					
					}else {
						JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado un campo");
					}
				modeloDeTabla.setRowCount(0);
				ArrayList<Investigacion> Investigaciones = iDAO.todasLasInvestigaciones();
				for(Investigacion i : Investigaciones) {
					modeloDeTabla.addRow(new Object[] {i.getId(),i.getCategoria(),i.getTema(),i.getPalabraClave(),i.getTitulo(),i.getEpigrafe(),i.getContenido(),i.getFecha()});
				}
			}	
		});
		
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(492, 188, 91, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInicio(m));
				m.validate();
			}
		});
		
		Buscartxt = new JTextField();
		Buscartxt.setBounds(10, 46, 172, 20);
		add(Buscartxt);
		Buscartxt.setColumns(10);
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(203, 45, 83, 23);
		add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				modeloDeTabla.setRowCount(0);
				InvestigacionDAO iDAO=new InvestigacionDAO();
				String tema=Buscartxt.getText();
				ArrayList<Investigacion> investigaciones=iDAO.buscar(tema);
				for(Investigacion i:investigaciones) {
					modeloDeTabla.addRow(new Object[] {i.getId(),i.getTema(),i.getPalabraClave(),i.getTitulo(),i.getEpigrafe(),i.getEpigrafe(),i.getContenido(),i.getFecha()});
					}
			}
		});
	}
}
