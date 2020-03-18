package Ventanas;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import utilidades.ElegirFichero;
import codigo.ListIt;
import sun.awt.geom.AreaOp.AddOp;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTable;

public class VentanaPrincipal {

	private JFrame frame;
	private JTextField textField;
	private String fileElegido;
	private JLabel labelResultado;
	private JTextField textfield_palabra;
	private JButton btnbusqueda;
	private JTextPane textArea_resultado; 

	private DefaultTableModel tablaModel;

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
		frame.setBounds(100, 100, 523, 422);
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
		
		JButton btnRun = new JButton("Ejecutar Análisis");
		btnRun.setBounds(172, 100, 151, 41);
		panel_analisis.add(btnRun);
		
		labelResultado = new JLabel("");
		labelResultado.setBounds(34, 140, 455, 25);
		panel_analisis.add(labelResultado);
		
		tablaModel= new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		tablaModel.addColumn("Palabra");
		tablaModel.addColumn("Frecuencia");

		JTable table = new JTable(tablaModel);
		table.setBounds(38, 166, 439, 187);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setBounds(38, 166, 439, 187);
		panel_analisis.add(scroll);

		
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
				textArea_resultado.setText("");
				
				if (ListIt.diccionario==null) {
					
					textArea_resultado.setText("Primero debes ejecutar el análisis");
					textArea_resultado.setForeground(Color.RED);
				}
				else {
					textArea_resultado.setForeground(Color.BLACK);
					if (ListIt.diccionario.get(textfield_palabra.getText())!=null) {
							textArea_resultado.setText("La palabra aparece: ");
						 	StyledDocument doc = textArea_resultado.getStyledDocument();
						    Style style = textArea_resultado.addStyle("I'm a Style", null);
						    StyleConstants.setForeground(style, Color.green);
						    StyleConstants.setBold(style, true);

						    try {
								doc.insertString(doc.getLength(), Integer.toString((Integer)ListIt.diccionario.get(textfield_palabra.getText())), style);
							} catch (BadLocationException e) {}

						    StyleConstants.setForeground(style, Color.black);
						    StyleConstants.setBold(style, false);

						    try {
								doc.insertString(doc.getLength(), " veces", style);
							} catch (BadLocationException e) {}
						
					}
					else textArea_resultado.setText("Esta palabra no existe");
				}
				
			}
		});
		btnbusqueda.setBounds(390, 45, 102, 25);
		panel_busqueda.add(btnbusqueda);
		
		JLabel lblIntroduceLaPalabra = new JLabel("Introduce la palabra que desees consultar:");
		lblIntroduceLaPalabra.setBounds(23, 18, 335, 15);
		panel_busqueda.add(lblIntroduceLaPalabra);
		
		textArea_resultado = new JTextPane();
		textArea_resultado.setEditable(false);
		textArea_resultado.setBounds(23, 109, 469, 225);
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
				if (!fileElegido.equals("")){
					try {
						int retorno=ListIt.analyze(fileElegido);
						if (retorno==0) {
							resultadoCorrecto("Los archivos se han analizado correctamente");
							actualizarTabla();
						}
						if (retorno==1) {
							resultadoCorrecto("La ruta ya se ha analizado hace menos de 10 minutos");
							actualizarTabla();
						}
						if (retorno==-1) resultadoError("La ruta "+fileElegido+" no puede ser analizada");
					} catch (Exception e1) {
						resultadoError("La ruta "+fileElegido+" no puede ser analizada");
					}
				}
				else resultadoError("Debe especificar una ruta");

				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileElegido=ElegirFichero.elegir();
				if (!fileElegido.equals("")) textField.setText(fileElegido);
			}
		});
	}
	public void actualizarTabla() {
		if (tablaModel.getRowCount() > 0) {
		    for (int i = tablaModel.getRowCount() - 1; i > -1; i--) {
		    	tablaModel.removeRow(i);
		    }
		}
		Map <String, Integer>diccionario=ListIt.resultadoAnalisis();
		Iterator it = diccionario.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			String[] fila= {key,Integer.toString(diccionario.get(key))};
			tablaModel.addRow(fila);		
		}
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
