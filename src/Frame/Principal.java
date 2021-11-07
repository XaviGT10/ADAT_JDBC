package Frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;
import Modelo.Modelo;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Controlador miControlador;
	private Modelo miModelo;
	private JTable table;
	private DefaultTableModel modelito;
	private JButton btnAñadirFila, btnEditar, btnEliminar;
	private JLabel lblNombrePiloto, lblNombreEscuderia;
	private JTextField txtNombrePiloto, txtNombreEscuderia;

	public Principal() {
		setTitle("App Pilotos F1");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/imgs/f1logo.png")));
		addWindowListener(new WindowAdapter() {
			// Llamada a carga de tabla
			@Override
			public void windowActivated(WindowEvent e) {
				table.setModel(miModelo.cargarModelito());
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 629);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(117, 66, 768, 257);
		contentPane.add(scrollPane);

		table = new JTable();
		this.modelito = new DefaultTableModel(new Object[][] { { null, null }, { null, null }, },
				new String[] { "New column", "New column" });
		scrollPane.setViewportView(table);

		btnAñadirFila = new JButton("A\u00F1adir");
		btnAñadirFila.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnAñadirFila.setBackground(Color.RED);
		btnAñadirFila.setForeground(Color.WHITE);
		btnAñadirFila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Llamada a añadirInfo
				miControlador.addInfo(getTxtNombrePiloto(), getTxtNombreEscuderia());
				// Intento recragar tabla
				table.setModel(miModelo.cargarModelito());
			}
		});
		btnAñadirFila.setBounds(92, 359, 126, 39);
		contentPane.add(btnAñadirFila);

		btnEditar = new JButton("Editar");
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnEditar.setBackground(Color.RED);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Llamada a editar Info
				miControlador.editInfo(getTxtNombrePiloto(), getTxtNombreEscuderia());
				// Intento recragar tabla
				table.setModel(miModelo.cargarModelito());
			}
		});
		btnEditar.setBounds(437, 359, 126, 39);
		contentPane.add(btnEditar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(Color.RED);
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Llamada a eliminar info
				miControlador.deleteInfo();
				// Intento recragar tabla
				table.setModel(miModelo.cargarModelito());
			}
		});
		btnEliminar.setBounds(762, 359, 137, 39);
		contentPane.add(btnEliminar);

		lblNombrePiloto = new JLabel("Nombre del piloto:");
		lblNombrePiloto.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNombrePiloto.setBounds(61, 496, 193, 31);
		contentPane.add(lblNombrePiloto);

		txtNombrePiloto = new JTextField();
		txtNombrePiloto.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtNombrePiloto.setBounds(256, 498, 170, 35);
		contentPane.add(txtNombrePiloto);
		txtNombrePiloto.setColumns(10);

		lblNombreEscuderia = new JLabel("Nombre escuderia:");
		lblNombreEscuderia.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNombreEscuderia.setBounds(498, 498, 208, 27);
		contentPane.add(lblNombreEscuderia);

		txtNombreEscuderia = new JTextField();
		txtNombreEscuderia.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		txtNombreEscuderia.setColumns(10);
		txtNombreEscuderia.setBounds(688, 498, 170, 35);
		contentPane.add(txtNombreEscuderia);
	}

	public String getTxtNombreEscuderia() {
		return txtNombreEscuderia.getText();
	}

	public void setTxtNombreEscuderia(String txtNombreEscuderia) {
		this.txtNombreEscuderia.setText(txtNombreEscuderia);
	}

	public String getTxtNombrePiloto() {
		return txtNombrePiloto.getText();
	}

	public void setTxtNombrePiloto(String txtNombrePiloto) {
		this.txtNombrePiloto.setText(txtNombrePiloto);
	}

	public JTable getTable() {
		return table;
	}

	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public DefaultTableModel getModelito() {
		return modelito;
	}

	public void setModelito(DefaultTableModel modelito) {
		this.modelito = modelito;
	}

	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}
}
