package estructuraTP.vistas;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import estructuraTP.DAO.ExplicacionDAO;
import estructuraTP.modelo.Chequeo;
import estructuraTP.modelo.Explicacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;

public class PantallaAMExplicacion extends JPanel {

	private JTextField txtTitulo;
	private JTextField txtEpigrafe;
	private JTextField txtFecha;
	private JTextField txtContenido;

	public PantallaAMExplicacion(JFrame m, ArrayList<Chequeo> chequeos, Explicacion ex) {
		setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		add(menuBar);

		JLabel lblMenCreacinDe = new JLabel("Men\u00FA Creaci\u00F3n de Explicaci\u00F3n");
		menuBar.add(lblMenCreacinDe);

		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 43, 46, 14);
		add(lblTtulo);

		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 68, 226, 20);
		add(txtTitulo);
		txtTitulo.setColumns(10);

		JLabel lblEpgrafe = new JLabel("Ep\u00EDgrafe");
		lblEpgrafe.setBounds(10, 99, 72, 14);
		add(lblEpgrafe);

		txtEpigrafe = new JTextField();
		txtEpigrafe.setBounds(10, 124, 226, 20);
		add(txtEpigrafe);
		txtEpigrafe.setColumns(10);

		JLabel lblFecha = new JLabel("Fecha de Creaci\u00F3n (A\u00F1o-Mes-D\u00EDa)");
		lblFecha.setBounds(10, 155, 226, 14);
		add(lblFecha);

		txtFecha = new JTextField();
		txtFecha.setBounds(10, 180, 115, 20);
		add(txtFecha);
		txtFecha.setColumns(10);

		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(10, 211, 72, 14);
		add(lblContenido);

		txtContenido = new JTextField();
		txtContenido.setBounds(10, 236, 394, 57);
		add(txtContenido);
		txtContenido.setColumns(10);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(92, 318, 89, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titulo = txtTitulo.getText();
				String epigrafe = txtEpigrafe.getText();
				LocalDate fecha = LocalDate.parse(txtFecha.getText());
				String contenido = txtContenido.getText();
				Explicacion ex = new Explicacion(titulo, epigrafe, contenido, fecha);
				ExplicacionDAO eDAO = new ExplicacionDAO();
				eDAO.insert(ex);
				eDAO.insertchequeo_explicacion(eDAO.conseguirIdExplicacion(ex), chequeos);

				m.setContentPane(new PantallaExplicacion(m));
				m.validate();
			}
		});

		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(244, 318, 89, 23);
		add(btnVolver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaExplicacion(m));
				m.validate();
			}
		});
		
		if (ex != null) {
			txtTitulo.setText(ex.gettitulo());
			txtEpigrafe.setText(ex.getepigrafe());
			txtContenido.setText(ex.getcontenido());
			txtFecha.setText(ex.getfecha().toString());
		} 

	}
}
