package estructuraTP.vistas;

import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.ExplicacionDAO;
import estructuraTP.DAO.InvestigacionDAO;
import estructuraTP.modelo.Explicacion;
import estructuraTP.modelo.Investigacion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;

public class PantallaInicio extends JPanel{
	
	private DefaultTableModel modeloDeTabla;
	private JTable table;
	
	public PantallaInicio(JFrame m) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 105, 291, 127);
		add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	
	Object[] columnas = new Object[] {"Título","Epígrafe","Contenido","Fecha"};
	modeloDeTabla = new DefaultTableModel(columnas,0);
	table.setModel(modeloDeTabla);
	
	InvestigacionDAO iDAO = new InvestigacionDAO();
	ArrayList<Investigacion> Investigaciones = iDAO.Investigaciones();
	for(Investigacion i : Investigaciones) {
		modeloDeTabla.addRow(new Object[] {i.getTitulo(),i.getEpigrafe(),i.getContenido(),i.getFecha()});
	}
	
	ExplicacionDAO eDAO = new ExplicacionDAO();
	ArrayList<Explicacion> Explicaciones = eDAO.Explicaciones();
	for(Explicacion e : Explicaciones) {
		modeloDeTabla.addRow(new Object[] {e.gettitulo(), e.getepigrafe(), e.getcontenido(), e.getfecha()});
	}
	
	JLabel lblArtculoAltoImpacto = new JLabel("Art\u00EDculo Alto Impacto");
	lblArtculoAltoImpacto.setBounds(162, 86, 188, 14);
	add(lblArtculoAltoImpacto);
	
	JMenuBar menuBar_1 = new JMenuBar();
	menuBar_1.setBounds(0, 0, 450, 21);
	add(menuBar_1);
	
	JMenu mnMen = new JMenu("Men\u00FA Inicio");
	menuBar_1.add(mnMen);
	
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
	
	JButton btnSalir = new JButton("Salir");
	btnSalir.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			m.dispose();
		}
	});
	btnSalir.setBounds(174, 253, 89, 23);
	add(btnSalir);
	mntmInvestigaciones.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			m.setContentPane(new PantallaInvestigacion(m));
			m.validate();
		}
	});
	
	}
}

