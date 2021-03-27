package estructuraTP;

import javax.swing.JFrame;

import estructuraTP.vistas.PantallaInicio;

public class App {

	public static void main(String[] args) {
		JFrame m = new JFrame();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
		m.setBounds(0,0, 800, 600);
		m.setTitle("Repaso");
		m.setContentPane(new PantallaInicio(m));
		m.validate();

	}

}
