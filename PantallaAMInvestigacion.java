package estructuraTP.vistas;

import javax.swing.JPanel;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JTextField;

import estructuraTP.DAO.CategoriaDAO;
import estructuraTP.DAO.InvestigacionDAO;
import estructuraTP.modelo.Investigacion;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PantallaAMInvestigacion extends JPanel{
	
	private JTextField txtTitulo;
	private JTextField txtEpigrafe;
	private JTextField txtFecha;
	private JTextField txtContenido;
	private JTextField txtTema;
	private JTextField txtPalabraClave;
	
	public PantallaAMInvestigacion(JFrame m,Investigacion i) {
		setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 450, 21);
		add(menuBar);
		
		JLabel lblMenCreacinDe = new JLabel("Men\u00FA Creaci\u00F3n de Investigaci\u00F3n");
		menuBar.add(lblMenCreacinDe);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setBounds(10, 32, 46, 14);
		add(lblTtulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 57, 233, 20);
		add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblEpgrafe = new JLabel("Ep\u00EDgrafe");
		lblEpgrafe.setBounds(10, 88, 46, 14);
		add(lblEpgrafe);
		
		txtEpigrafe = new JTextField();
		txtEpigrafe.setBounds(10, 113, 233, 20);
		add(txtEpigrafe);
		txtEpigrafe.setColumns(10);
		
		JLabel lblFechaDeCreacin = new JLabel("Fecha de Creaci\u00F3n (A\u00F1o-Mes-D\u00EDa)");
		lblFechaDeCreacin.setBounds(10, 144, 167, 14);
		add(lblFechaDeCreacin);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(10, 169, 116, 20);
		add(txtFecha);
		txtFecha.setColumns(10);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(197, 144, 63, 14);
		add(lblCategora);
		
		JComboBox<String> comboBox_Categorias = new JComboBox<String>();
		comboBox_Categorias.setBounds(197, 169, 86, 20);
		CategoriaDAO catdao = new CategoriaDAO();
		ArrayList<String> items = catdao.todos();
		for(String cat : items)
			comboBox_Categorias.addItem(cat);			
		add(comboBox_Categorias);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(35, 372, 89, 23);
		add(btnGuardar);
		btnGuardar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				i.setCategoria(comboBox_Categorias.getSelectedItem().toString());
				i.setTema(txtTema.getText());
				i.setPalabraClave(txtPalabraClave.getText());
				i.setTitulo(txtTitulo.getText());
				i.setEpigrafe(txtEpigrafe.getText());
				i.setContenido(txtContenido.getText());
				i.setFecha(LocalDate.parse(txtFecha.getText()));
				
				InvestigacionDAO iDAO = new InvestigacionDAO();
				iDAO.insercion(i);
				
				m.setContentPane(new PantallaInvestigacion(m));
				m.validate();
			}
		});
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(197, 372, 89, 23);
		add(btnVolver);
		
		JLabel lblContenido = new JLabel("Contenido");
		lblContenido.setBounds(10, 262, 86, 14);
		add(lblContenido);
		
		txtContenido = new JTextField();
		txtContenido.setBounds(9, 287, 326, 74);
		add(txtContenido);
		txtContenido.setColumns(10);
		
		txtTema = new JTextField();
		txtTema.setBounds(90, 231, 245, 20);
		add(txtTema);
		txtTema.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setBounds(10, 237, 46, 14);
		add(lblTema);
		
		JLabel lblNewLabel = new JLabel("Palabra Clave");
		lblNewLabel.setBounds(10, 207, 86, 14);
		add(lblNewLabel);
		
		txtPalabraClave = new JTextField();
		txtPalabraClave.setBounds(90, 200, 246, 20);
		add(txtPalabraClave);
		txtPalabraClave.setColumns(10);
		btnVolver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				m.setContentPane(new PantallaInvestigacion(m));
				m.validate();
			}
		});
		
		if(i!=null) {
			txtTitulo.setText(i.getTitulo());
			txtEpigrafe.setText(i.getEpigrafe());
			txtContenido.setText(i.getContenido());
			txtFecha.setText(i.getFecha().toString());
			
		}
		
		
	}
}
