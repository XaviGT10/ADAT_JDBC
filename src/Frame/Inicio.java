package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.Modelo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class Inicio extends JFrame {

	private JPanel contentPane;
	private Modelo miModelo;
	private Controlador miControlador;
	private JButton btnCambiaPag;

	public Inicio() {
		setTitle("App Pilotos F1");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/imgs/f1logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 971, 621);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnCambiaPag = new JButton("");
		btnCambiaPag.setIcon(new ImageIcon(Inicio.class.getResource("/imgs/start-action-valtteri-bottas-m.jpg")));
		btnCambiaPag.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Crea conexión a la base de datos
				miModelo.conexionBD();
				// Cambia de ventana onclick
				miControlador.changeWindow();
				// Carga tabla de la base de datos
				miModelo.loadDBTable();
			}
		});
		btnCambiaPag.setBounds(0, 0, 962, 602);
		contentPane.add(btnCambiaPag);
	}

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

}
