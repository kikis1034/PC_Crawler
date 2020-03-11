package Ventanas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Objects.MetadataAnalisis;
import utilidades.CargarObjeto;
import utilidades.ElegirFichero;
import utilidades.SalvarObjeto;
import codigo.ListIt;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private String fileElegido;
	private JLabel labelResultado;
	private JTextField textfield_palabra;
	private JButton btnbusqueda;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		fileElegido="";
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setEnabled(false);
		frame.setTitle("PC-Crawler");
		frame.setBounds(100, 100, 523, 268);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_analisis = new JPanel();
		tabbedPane.addTab("Análisis", null, panel_analisis, null);
		panel_analisis.setLayout(null);
		
		JButton btnNewButton = new JButton("Elegir");
		btnNewButton.setBounds(379, 63, 98, 25);
		panel_analisis.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(25, 65, 342, 22);
		panel_analisis.add(textField);
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setColumns(10);
		
		JLabel lblEligeUnDirectorio = new JLabel("Elige un directorio o archivo:");
		lblEligeUnDirectorio.setBounds(28, 38, 202, 15);
		panel_analisis.add(lblEligeUnDirectorio);
		
		JLabel lblEjecutarElAnlisis = new JLabel("Ejecutar el análisis:");
		lblEjecutarElAnlisis.setBounds(149, 111, 139, 15);
		panel_analisis.add(lblEjecutarElAnlisis);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(196, 142, 61, 25);
		panel_analisis.add(btnRun);
		
		labelResultado = new JLabel("");
		labelResultado.setBounds(28, 174, 455, 25);
		panel_analisis.add(labelResultado);
		
		JPanel panel_busqueda = new JPanel();
		tabbedPane.addTab("Búsqueda", null, panel_busqueda, null);
		panel_busqueda.setLayout(null);
		
		textfield_palabra = new JTextField();
		textfield_palabra.setBounds(23, 45, 356, 25);
		textfield_palabra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnbusqueda.doClick();
			}
		});
		panel_busqueda.add(textfield_palabra);
		textfield_palabra.setColumns(10);
		
		btnbusqueda = new JButton("Buscar");
		btnbusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO: Acción buscar en el fichero. Controlar erroes
			}
		});
		btnbusqueda.setBounds(390, 45, 102, 25);
		panel_busqueda.add(btnbusqueda);
		
		JLabel lblIntroduceLaPalabra = new JLabel("Introduce la palabra que desees consultar:");
		lblIntroduceLaPalabra.setBounds(23, 18, 335, 15);
		panel_busqueda.add(lblIntroduceLaPalabra);
		
		JTextArea textArea_resultado = new JTextArea();
		textArea_resultado.setEditable(false);
		textArea_resultado.setBounds(23, 109, 469, 67);
		panel_busqueda.add(textArea_resultado);
		
		JLabel lblResultado = new JLabel("Resultado:");
		lblResultado.setBounds(23, 82, 102, 15);
		panel_busqueda.add(lblResultado);
		labelResultado.setVisible(false);
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelResultado.setVisible(false);
				labelResultado.setText("");
				if (!fileElegido.equals(""))
					try {
						ListIt.analyze(fileElegido);
						MetadataAnalisis meta = new MetadataAnalisis(fileElegido);
						SalvarObjeto.salvarMetadata(meta);
						resultadoCorrecto("Los archivos se han analizado correctamente");
						CargarObjeto.cargarMetadata();
					} catch (Exception e1) {
						resultadoError("La ruta "+fileElegido+" no puede ser analizada");
					}
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileElegido=ElegirFichero.elegir();
				if (!fileElegido.equals("")) textField.setText(fileElegido);
			}
		});
	}
	
	public void resultadoError(String codigo) {
		labelResultado.setVisible(true);
		labelResultado.setForeground(Color.red);
		labelResultado.setText(codigo);
	}
	
	public void resultadoCorrecto(String codigo) {
		labelResultado.setVisible(true);
		labelResultado.setForeground(Color.green);
		labelResultado.setText(codigo);
	}
}
