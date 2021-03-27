package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.CategoriaDAO;
import estructuraTP.DAO.ExplicacionDAO;
import estructuraTP.modelo.Explicacion;

import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;

public class PantallaExplicacion extends JPanel {
	private DefaultTableModel modeloTabla;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PantallaExplicacion(JFrame m) {
		setLayout(null);
	
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 740, 21);
		add(menuBar);
		
		JMenu mnMenu = new JMenu("Men\u00FA Explicaciones");
		menuBar.add(mnMenu);
		
		JMenuItem mntmInicio = new JMenuItem("Inicio");
		mnMenu.add(mntmInicio);
		mntmInicio.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInicio(m));
				m.validate();
			}
		});

		JMenuItem mntmChequeos = new JMenuItem("Chequeos");
		mnMenu.add(mntmChequeos);
		mntmChequeos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaChequeo(m));
				m.validate();
			}
		});
		
		JMenuItem mntmExplicaciones = new JMenuItem("Explicaciones");
		mnMenu.add(mntmExplicaciones);
		mntmExplicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaExplicacion(m));
				m.validate();
			}
		});
		
		JMenuItem mntmInvestigaciones = new JMenuItem("Investigaciones");
		mnMenu.add(mntmInvestigaciones);
		mntmInvestigaciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInvestigacion(m));
				m.validate();
			}
		});
		
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(493, 84, 105, 23);
		add(btnCrear);
		btnCrear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaPotencialExplicacion(m));
				m.validate();
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(493, 118, 105, 23);
		add(btnModificar);
		btnModificar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(table.getValueAt(table.getSelectedRow(),0).toString());
				String titulo=table.getValueAt(table.getSelectedRow(), 1).toString();
				String epigrafe=table.getValueAt(table.getSelectedRow(), 2).toString();
				String contenido=table.getValueAt(table.getSelectedRow(), 3).toString();
				LocalDate fecha=LocalDate.parse(table.getValueAt(table.getSelectedRow(), 4).toString());
				Explicacion ex=new Explicacion(id, titulo, epigrafe, contenido, fecha);
				m.setContentPane(new PantallaAMExplicacion(m,null, ex));
				m.validate();
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(493, 152, 105, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInicio(m));
				m.validate();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 452, 245);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		Object[] columnas=new Object[] {"ID","Titulo","Epigrafe","Contenido","Fecha"};
		 modeloTabla = new DefaultTableModel(columnas,0);
		
		 table.setModel(modeloTabla);
		 
		 ExplicacionDAO eDAO=new ExplicacionDAO();
		 ArrayList<Explicacion> explicaciones=eDAO.todes();
		for(Explicacion ex:explicaciones) {
			modeloTabla.addRow(new Object[] {ex.getId(),ex.gettitulo(),ex.getepigrafe(),ex.getcontenido(),ex.getfecha()});
		}

	}
}
