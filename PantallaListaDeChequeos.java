package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import estructuraTP.modelo.Chequeo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JLabel;

public class PantallaListaDeChequeos extends JPanel{
	
	private DefaultTableModel modeloDeTabla;
	private JTable table;
	
	public PantallaListaDeChequeos(JFrame m, ArrayList<Chequeo> chequeos) {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 32, 428, 257);
		add(scrollPane);
	
	table = new JTable();
	scrollPane.setViewportView(table);
	
	Object[] columnas = new Object[] {"Frase", "Fecha", "Medio", "Palabra Clave", "Verificacion", "Categoria"};
	modeloDeTabla = new DefaultTableModel(columnas,0);
	table.setModel(modeloDeTabla);
	
	JButton btnVolver = new JButton("Volver");
	btnVolver.setBounds(184, 299, 89, 23);
	add(btnVolver);
	btnVolver.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			m.setContentPane(new PantallaPotencialExplicacion(m));
			m.validate();
		}
	});
	
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 450, 21);
	add(menuBar);
	
	JLabel lblListaDeChequeos = new JLabel("Lista de Chequeos");
	menuBar.add(lblListaDeChequeos);
		
	
	for(Chequeo c:chequeos) {
		modeloDeTabla.addRow(new Object[] {c.getFrase(),c.getFecha(),c.getMedio(),c.getPalabraClave(),c.getVerificacion(),c.getCategoria()});
	}
	
}}
