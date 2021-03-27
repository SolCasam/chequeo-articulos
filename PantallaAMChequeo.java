package estructuraTP.vistas;

import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import estructuraTP.DAO.CategoriaDAO;
import estructuraTP.DAO.ChequeoDAO;
import estructuraTP.DAO.SugerenciaDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Sugerencia;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JMenuBar;
import javax.swing.DefaultComboBoxModel;

public class PantallaAMChequeo extends JPanel {
	private JTextField txtFrase;
	private JTextField txtMedio;
	private JTextField txtPalabraClave;
	private JTextField txtFecha;

	String timezone = "&serverTimezone=UTC";

	public PantallaAMChequeo(JFrame m, Chequeo c, int id) {
		setLayout(null);

		txtFrase = new JTextField();
		txtFrase.setBounds(24, 65, 373, 38);
		add(txtFrase);
		txtFrase.setColumns(10);

		JLabel lblAfirmacionFrase = new JLabel("Afirmacion frase");
		lblAfirmacionFrase.setBounds(24, 46, 189, 14);
		add(lblAfirmacionFrase);

		JLabel lblMedioQueFue = new JLabel("Medio que fue Publicada");
		lblMedioQueFue.setBounds(24, 114, 129, 14);
		add(lblMedioQueFue);

		txtMedio = new JTextField();
		txtMedio.setHorizontalAlignment(SwingConstants.CENTER);
		txtMedio.setBounds(24, 139, 373, 20);
		add(txtMedio);
		txtMedio.setColumns(10);

		JLabel lblFechaDeLa = new JLabel("Fecha de la frase (A\u00F1o-Mes-D\u00EDa)");
		lblFechaDeLa.setBounds(24, 170, 179, 14);
		add(lblFechaDeLa);

		JLabel lblPalabraClave = new JLabel("Palabra Clave");
		lblPalabraClave.setBounds(24, 226, 86, 14);
		add(lblPalabraClave);

		txtPalabraClave = new JTextField();
		txtPalabraClave.setHorizontalAlignment(SwingConstants.CENTER);
		txtPalabraClave.setBounds(24, 251, 249, 20);
		add(txtPalabraClave);
		txtPalabraClave.setColumns(10);

		JLabel lblResultadoDeVerificacion = new JLabel("Resultado de Verificacion:");
		lblResultadoDeVerificacion.setBounds(34, 282, 179, 14);
		add(lblResultadoDeVerificacion);

		JComboBox<String> comboBox_Verificacion = new JComboBox<String>();
		comboBox_Verificacion.setBounds(187, 278, 86, 22);
		CategoriaDAO catDao = new CategoriaDAO();
		ArrayList<String> Items = catDao.verificacion();
		for (String Cat : Items)
			comboBox_Verificacion.addItem(Cat);
		add(comboBox_Verificacion);

		txtFecha = new JTextField();
		txtFecha.setBounds(24, 195, 115, 20);
		add(txtFecha);
		txtFecha.setColumns(10);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		add(menuBar);

		JLabel lblMenCreacinDe = new JLabel("Men\u00FA Creaci\u00F3n de Chequeo");
		menuBar.add(lblMenCreacinDe);

		JComboBox<String> comboBox_Categorias = new JComboBox<String>();
		comboBox_Categorias.setBounds(210, 194, 102, 22);
		CategoriaDAO catdao = new CategoriaDAO();
		ArrayList<String> items = catdao.todos();
		for (String cat : items)
			comboBox_Categorias.addItem(cat);
		add(comboBox_Categorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(210, 170, 63, 14);
		add(lblCategora);

		if (id == 0) {
			txtPalabraClave.setText(c.getPalabraClave());
			txtFrase.setText(c.getFrase());
			txtMedio.setText(c.getMedio());
			txtFecha.setText(c.getFecha().toString());

			txtFrase.setEditable(false);
			txtMedio.setEditable(false);
			txtFecha.setEditable(false);
		} else {
			txtFrase.setText(c.getFrase());
			txtMedio.setText(c.getMedio());
			txtFecha.setText(c.getFecha().toString());
		}

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(52, 318, 115, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String enlace = c.getEnlace();
				c.setFrase(txtFrase.getText());
				c.setFecha(LocalDate.parse(txtFecha.getText()));
				c.setMedio(txtMedio.getText());
				c.setPalabraClave(txtPalabraClave.getText());
				c.setVerificacion(comboBox_Verificacion.getSelectedItem().toString());
				c.setCategoria(comboBox_Categorias.getSelectedItem().toString());
				
				ChequeoDAO cDAO = new ChequeoDAO();
				cDAO.registrarChequeo(c);
				if (id != 0) {
					SugerenciaDAO sDAO = new SugerenciaDAO();
					sDAO.delete(id);
				}
				m.setContentPane(new PantallaChequeo(m));
				m.validate();
			}
		});

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(246, 318, 109, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaChequeo(m));
				m.validate();
			}
		});

	}
}
