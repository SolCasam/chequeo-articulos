package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.DAO.CategoriaDAO;
import estructuraTP.DAO.ChequeoDAO;
import estructuraTP.DAO.PotencialExplicacionDAO;
import estructuraTP.modelo.Chequeo;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.DefaultComboBoxModel;

public class PantallaPotencialExplicacion extends JPanel {

	private DefaultTableModel modeloDeTabla;
	private JTable table;

	public PantallaPotencialExplicacion(JFrame m) {
		setLayout(null);

		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(35, 35, 74, 14);
		add(lblCategoria);

		JComboBox<String> comboBox_Categorias = new JComboBox<String>();
		comboBox_Categorias.setBounds(119, 32, 89, 20);
		CategoriaDAO catdao = new CategoriaDAO();
		ArrayList<String> items = catdao.todos();
		for (String cat : items)
			comboBox_Categorias.addItem(cat);
		add(comboBox_Categorias);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 84, 380, 127);
		add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		Object[] columnas = new Object[] { "Noticia", "Cantidad de Chequeos" };
		modeloDeTabla = new DefaultTableModel(columnas, 0);
		table.setModel(modeloDeTabla);

		JButton btnCrear = new JButton("Crear");
		btnCrear.setBounds(35, 235, 89, 23);
		add(btnCrear);
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String link = table.getValueAt(table.getSelectedRow(), 0).toString();
				PotencialExplicacionDAO peDAO = new PotencialExplicacionDAO();
				HashMap<String, ArrayList<Chequeo>> potencialesExps = peDAO.potencialesExplicaciones(comboBox_Categorias.getSelectedItem().toString().trim());
				m.setContentPane(new PantallaAMExplicacion(m, potencialesExps.get(link), null));
				m.validate();
			}
		});

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(326, 235, 89, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaExplicacion(m));
				m.validate();
			}
		});

		JButton btnMostrarChequeos = new JButton("Mostrar Chequeos");
		btnMostrarChequeos.setBounds(147, 235, 156, 23);
		add(btnMostrarChequeos);
		btnMostrarChequeos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String link = table.getValueAt(table.getSelectedRow(), 0).toString().trim();
					PotencialExplicacionDAO peDAO = new PotencialExplicacionDAO();
					String categoria=comboBox_Categorias.getSelectedItem().toString().trim();
					HashMap<String, ArrayList<Chequeo>> potencialesExps = peDAO.potencialesExplicaciones(categoria);
					
					m.setContentPane(new PantallaListaDeChequeos(m,potencialesExps.get(link)));
					m.validate();
				} finally {
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		add(menuBar);

		JLabel lblMenPotencialesExplicaciones = new JLabel("Men\u00FA Potenciales Explicaciones");
		menuBar.add(lblMenPotencialesExplicaciones);

		comboBox_Categorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modeloDeTabla.setRowCount(0);
				String categoria = comboBox_Categorias.getSelectedItem().toString();
				PotencialExplicacionDAO peDAO = new PotencialExplicacionDAO();
				HashMap<String, ArrayList<Chequeo>> potencialesExps = peDAO
						.potencialesExplicaciones(comboBox_Categorias.getSelectedItem().toString().trim());
				for (String l : potencialesExps.keySet()) {
					if (potencialesExps.get(l).size() > 1) {
						modeloDeTabla.addRow(new Object[] { l, potencialesExps.get(l).size() });
					}
				}
			}
		});

	}
}
