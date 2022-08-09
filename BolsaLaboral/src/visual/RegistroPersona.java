package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Recursos.Control;
import Recursos.Obrero;
import Recursos.Persona;
import Recursos.Tecnico;
import Recursos.Universitario;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;

public class RegistroPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	 static ArrayList<Persona> personas;//
	 private JTextField txtNombre;
	 private JTextField txtTelefono;
	 private JTextField txtDireccion;
	 private JTextField txtCedula;
	 private JRadioButton rdbtnUniversitario;
	 private JRadioButton rdbtnObrero;
	 private JRadioButton rdbtnTecnico;
	 private JTextField txtTitulo;
	 private JLabel lblTitulo;
	 private JComboBox cbxProvincia;
	 private JComboBox cbxSexo;
	 private JSpinner spnEdad;
	 private JComboBox cbxMunicipio;
	 SimpleDateFormat sm = new SimpleDateFormat("yyyy-MMM-dd");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistroPersona dialog = new RegistroPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public RegistroPersona() {
		
		personas = new ArrayList();//
		setTitle("Registro persona");
		setBounds(100, 100, 450, 361);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(6, 76, 61, 16);
		panel_1.add(lblNewLabel_1);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(79, 71, 329, 26);
		panel_1.add(txtNombre);
		
		JLabel lblNewLabel_2 = new JLabel("Teléfono:");
		lblNewLabel_2.setBounds(6, 166, 72, 16);
		panel_1.add(lblNewLabel_2);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(79, 161, 329, 26);
		panel_1.add(txtTelefono);
		
		JLabel lblNewLabel_5 = new JLabel("Dirección:");
		lblNewLabel_5.setBounds(6, 194, 72, 16);
		panel_1.add(lblNewLabel_5);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(79, 189, 329, 26);
		panel_1.add(txtDireccion);
		
		JLabel lblNewLabel_3 = new JLabel("Tipo:");
		lblNewLabel_3.setBounds(6, 10, 61, 16);
		panel_1.add(lblNewLabel_3);
		
		rdbtnTecnico = new JRadioButton("Técnico");
		rdbtnTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTecnico.setSelected(true);
				rdbtnObrero.setSelected(false);
				rdbtnUniversitario.setSelected(false);
				lblTitulo.setText("Área:");
			}
		});
		rdbtnTecnico.setSelected(true);
		rdbtnTecnico.setBounds(79, 7, 81, 23);
		panel_1.add(rdbtnTecnico);
		
		rdbtnUniversitario = new JRadioButton("Universitario");
		rdbtnUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTecnico.setSelected(false);
				rdbtnObrero.setSelected(false);
				rdbtnUniversitario.setSelected(true);
				lblTitulo.setText("Título:");
			}
		});
		rdbtnUniversitario.setBounds(190, 6, 112, 23);
		panel_1.add(rdbtnUniversitario);
		
		rdbtnObrero = new JRadioButton("Obrero");
		rdbtnObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnTecnico.setSelected(false);
				rdbtnObrero.setSelected(true);
				rdbtnUniversitario.setSelected(false);
				lblTitulo.setText("Skill:");
			}
		});
		rdbtnObrero.setBounds(327, 7, 81, 23);
		panel_1.add(rdbtnObrero);
		
		JLabel lblNewLabel_4 = new JLabel("Sexo:");
		lblNewLabel_4.setBounds(6, 138, 61, 16);
		panel_1.add(lblNewLabel_4);
		
		cbxSexo = new JComboBox();
		cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Masculino", "Femenino"}));
		cbxSexo.setBounds(79, 133, 142, 27);
		panel_1.add(cbxSexo);
		
		JLabel lblNewLabel_6 = new JLabel("Edad:");
		lblNewLabel_6.setBounds(257, 138, 61, 16);
		panel_1.add(lblNewLabel_6);
		
//		long millis=System.currentTimeMillis();  
		Date today = new Date();
		spnEdad = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spnEdad, "dd/MM/yy");
		spnEdad.setEditor(editor);
		spnEdad.setBounds(308, 133, 100, 26);
		panel_1.add(spnEdad);
		
		JLabel lblNewLabel_7 = new JLabel("Provincia:");
		lblNewLabel_7.setBounds(6, 222, 61, 16);
		panel_1.add(lblNewLabel_7);
		
		cbxProvincia = new JComboBox();
		cbxProvincia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> m = new ArrayList<>();
				m.add("<Seleccione>");
				m.addAll(Control.getInstance().getMunicipiosProvincia(cbxProvincia.getSelectedItem().toString()));
				cbxMunicipio.setModel(new DefaultComboBoxModel(m.toArray()));
				cbxMunicipio.setEnabled(true);
			}
		});
		ArrayList<String> p = new ArrayList<>();
		p.add("<Seleccione>");
		p.addAll(Control.getInstance().getProvincias());
		cbxProvincia.setModel(new DefaultComboBoxModel(p.toArray()));
		cbxProvincia.setMaximumRowCount(5);
		cbxProvincia.setBounds(79, 217, 160, 27);
		panel_1.add(cbxProvincia);
		
		JLabel lblNewLabel = new JLabel("Cédula:");
		lblNewLabel.setBounds(6, 43, 61, 16);
		panel_1.add(lblNewLabel);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(79, 38, 329, 26);
		panel_1.add(txtCedula);
		txtCedula.setColumns(10);
		
		lblTitulo = new JLabel("Área:");
		lblTitulo.setBounds(6, 105, 61, 16);
		panel_1.add(lblTitulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(79, 100, 329, 26);
		panel_1.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Municipio:");
		lblNewLabel_8.setBounds(6, 250, 72, 16);
		panel_1.add(lblNewLabel_8);
		
		
		cbxMunicipio = new JComboBox();
		cbxMunicipio.setEnabled(false);
		cbxMunicipio.setMaximumRowCount(5);
		cbxMunicipio.setBounds(79, 246, 160, 27);
		panel_1.add(cbxMunicipio);
//		if (cbxProvincia.getSelectedIndex() != 0) {
//			ArrayList<String> m = new ArrayList<>();
//			m.add("Seleccione");
//			m.addAll(Control.getInstance().getMunicipiosProvincia(cbxProvincia.getSelectedItem().toString()));
//			cbxMunicipio.setModel(new DefaultComboBoxModel(m.toArray()));
//			cbxMunicipio.setEnabled(true);
//		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				{
					JButton cancelButton = new JButton("Cancelar");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					JButton okButton = new JButton("Registrar");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Persona aux = null;
							
							String cedula = txtCedula.getText();
							String nombre = txtNombre.getText();
							String sexo = cbxSexo.getSelectedItem().toString();
							String edad = sm.format(spnEdad.getValue());
							String telefono = txtTelefono.getText();
							String direccion = txtDireccion.getText();
							String municipio = cbxMunicipio.getSelectedItem().toString();
							String titulo = txtTitulo.getText();
							
//							if (rdbtnTecnico.isSelected()) {
//								aux = new Tecnico(cedula, nombre, edad, sexo, telefono, direccion, municipio, titulo);
//							} else if (rdbtnUniversitario.isSelected()) {
//								aux = new Universitario(cedula, nombre, edad, sexo, telefono, direccion, municipio, titulo);
//							} else if (rdbtnObrero.isSelected()) {
//								aux = new Obrero(cedula, nombre, edad, sexo, telefono, direccion, municipio, titulo);
//							}
							
							if (check()) {
								if (rdbtnTecnico.isSelected()) {
									aux = new Tecnico(cedula, nombre, edad, sexo, telefono, direccion, municipio, titulo);
									Control.getInstance().getCon().InsertTecnico((Tecnico) aux);
								} else if (rdbtnUniversitario.isSelected()) {
									aux = new Universitario(cedula, nombre, edad, sexo, telefono, direccion, municipio, titulo);
									Control.getInstance().getCon().InsertUniversitario((Universitario) aux);
								} else if (rdbtnObrero.isSelected()) {
									aux = new Obrero(cedula, nombre, edad, sexo, telefono, direccion, municipio, titulo);
									Control.getInstance().getCon().InsertObrero((Obrero) aux);
								}
//								Control.getInstance().getCon().InsertPersona(aux);
//								Control.getInstance().getPersonas().add(aux);
								
								JOptionPane.showMessageDialog(null, "Registrado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
								clean();
							} else {
								JOptionPane.showMessageDialog(null, "Debe completar todos los campos!", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
	
	private void clean() {
		txtCedula.setText("");
		cbxSexo.setSelectedIndex(0);
		txtNombre.setText("");
		spnEdad.setValue(new Date());
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxProvincia.setSelectedIndex(0);
		cbxMunicipio.setEnabled(false);
		txtTitulo.setText("");
		rdbtnTecnico.setSelected(true);
		lblTitulo.setText("Área:");
		rdbtnObrero.setSelected(false);
		rdbtnUniversitario.setSelected(false);
	}
	
	private boolean check() {
		boolean completo = true;
		
		if (txtCedula.getText().equalsIgnoreCase("") || txtNombre.getText().equalsIgnoreCase("")) {
			completo = false;
		} else if (txtTelefono.getText().equalsIgnoreCase("") || txtDireccion.getText().equalsIgnoreCase("")) {
			completo = false;
		} else if (txtTitulo.getText().equalsIgnoreCase("")) {
			completo = false;
		} else if (cbxSexo.getSelectedIndex() == 0 || cbxProvincia.getSelectedIndex() == 0  || cbxMunicipio.getSelectedIndex() == 0) {
			completo = false;
		} else if (sm.format(spnEdad.getValue()).compareTo(sm.format(new Date())) >= 0) {
			completo = false;
		}
		return completo;
	}
}
//
