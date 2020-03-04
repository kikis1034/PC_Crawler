package Ventanas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import utilidades.ElegirFichero;
import codigo.ListIt;

import javax.swing.JLabel;
import java.awt.Color;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private String fileElegido;
	private JLabel labelResultado;

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
		frame.setBounds(100, 100, 521, 256);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Elegir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileElegido=ElegirFichero.elegir();
				if (!fileElegido.equals("")) textField.setText(fileElegido);
			}
		});
		btnNewButton.setBounds(313, 75, 129, 27);
		frame.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(49, 75, 252, 28);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEligeUnDirectorio = new JLabel("Elige un directorio o archivo:");
		lblEligeUnDirectorio.setBounds(49, 48, 437, 15);
		frame.getContentPane().add(lblEligeUnDirectorio);
		
		JLabel lblEjecutarElAnlisis = new JLabel("Ejecutar el análisis:");
		lblEjecutarElAnlisis.setBounds(172, 125, 160, 15);
		frame.getContentPane().add(lblEjecutarElAnlisis);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				labelResultado.setVisible(false);
				labelResultado.setText("");
				if (!fileElegido.equals(""))
					try {
						ListIt.analyze(fileElegido);
						resultadoCorrecto("Los archivos se han analizado correctamente");
					} catch (Exception e1) {
						resultadoError("La ruta "+fileElegido+" no puede ser analizada");
					}
				
			}
		});
		btnRun.setBounds(172, 152, 129, 34);
		frame.getContentPane().add(btnRun);
		
		labelResultado = new JLabel("");
		labelResultado.setVisible(false);
		labelResultado.setBounds(51, 192, 392, 15);
		frame.getContentPane().add(labelResultado);
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
