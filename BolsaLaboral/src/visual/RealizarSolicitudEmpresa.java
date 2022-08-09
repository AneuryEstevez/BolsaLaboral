package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Recursos.Control;
import Recursos.Empresa;
import Recursos.Obrero;
import Recursos.Persona;
import Recursos.SolicitudEmpresa;
import Recursos.SolicitudPersona;
import Recursos.Tecnico;
import Recursos.Universitario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class RealizarSolicitudEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JTextField txtTitulo;
	private JTextField txtSueldo;
	private JComboBox cbxCategoria;
	private JComboBox cbxProvincia;
	private JRadioButton rdbtnTecnico;
	private JRadioButton rdbtnUniversitario;
	private JRadioButton rdbtnObrero;
	private JComboBox cbxTipoTrabajo;
	private JSpinner spnCantidad;
	private JSpinner spnExperiencia;
	private JSpinner spnEdad;
	private JRadioButton rdbtnSiLicencia;
	private JRadioButton rdbtnNoLicencia;
	private JCheckBox chckbxEspanol;
	private JCheckBox chckbxIngles;
	private JCheckBox chckbxFrances;
	private JLabel lblTitulo;
	private JLabel lblNewLabel_4;
	private JComboBox cbxMunicipio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RealizarSolicitudEmpresa dialog = new RealizarSolicitudEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RealizarSolicitudEmpresa() {
		setTitle("Solicitud Empresa");
		setBounds(100, 100, 499, 519);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setLayout(null);
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			{
				JLabel lblId = new JLabel("ID:");
				lblId.setBounds(12, 11, 61, 16);
				panel.add(lblId);
			}
			{
				txtId = new JTextField();
				txtId.setColumns(10);
				txtId.setBounds(41, 6, 130, 26);
				panel.add(txtId);
			}
			{
				JButton btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Empresa emp = Control.getInstance().getCon().SelectEmpresaRnc(txtId.getText());
						if (emp != null) {
							txtNombre.setText(emp.getNombreEmpresa());
							cbxCategoria.setSelectedItem(emp.getCategoria());
							txtTelefono.setText(emp.getTelefonoEmpresa());
							txtDireccion.setText(emp.getDireccionEmpresa());
							cbxProvincia.setSelectedItem(Control.getInstance().getCon().SelectProvincia(emp.getMunicipio()));
							cbxMunicipio.setSelectedItem(emp.getMunicipio());
							cbxMunicipio.setEnabled(false);
						} else {
							cleanBuscar();
						}
						enableSolicitud();
					}
				});
				btnBuscar.setBounds(172, 6, 91, 29);
				panel.add(btnBuscar);
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setLayout(null);
				panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(6, 39, 477, 177);
				panel.add(panel_1);
				{
					JLabel lblNewLabel_1 = new JLabel("Nombre:");
					lblNewLabel_1.setBounds(6, 38, 61, 16);
					panel_1.add(lblNewLabel_1);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setEditable(false);
					txtNombre.setColumns(10);
					txtNombre.setBounds(79, 33, 392, 26);
					panel_1.add(txtNombre);
				}
				{
					JLabel lblNewLabel_2 = new JLabel("Teléfono:");
					lblNewLabel_2.setBounds(6, 66, 72, 16);
					panel_1.add(lblNewLabel_2);
				}
				{
					txtTelefono = new JTextField();
					txtTelefono.setEditable(false);
					txtTelefono.setColumns(10);
					txtTelefono.setBounds(79, 61, 392, 26);
					panel_1.add(txtTelefono);
				}
				{
					JLabel lblNewLabel_5 = new JLabel("Dirección:");
					lblNewLabel_5.setBounds(6, 94, 72, 16);
					panel_1.add(lblNewLabel_5);
				}
				{
					txtDireccion = new JTextField();
					txtDireccion.setEditable(false);
					txtDireccion.setColumns(10);
					txtDireccion.setBounds(79, 89, 392, 26);
					panel_1.add(txtDireccion);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Categoria:");
					lblNewLabel_3.setBounds(6, 10, 72, 16);
					panel_1.add(lblNewLabel_3);
				}
				{
					JLabel lblNewLabel_7 = new JLabel("Provincia:");
					lblNewLabel_7.setBounds(6, 122, 61, 16);
					panel_1.add(lblNewLabel_7);
				}
				{
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
					cbxProvincia.setEnabled(false);
					cbxProvincia.setBounds(79, 117, 172, 27);
					panel_1.add(cbxProvincia);
				}
				
				cbxCategoria = new JComboBox();
				cbxCategoria.setEnabled(false);
				cbxCategoria.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Turismo", "Salud", "Educación", "Comercio", "Industrial", "Finanzas", "Telecomunicaciones", "Transporte"}));
				cbxCategoria.setBounds(79, 6, 172, 27);
				panel_1.add(cbxCategoria);
				{
					lblNewLabel_4 = new JLabel("Municipio:");
					lblNewLabel_4.setBounds(6, 150, 72, 16);
					panel_1.add(lblNewLabel_4);
				}
				{
					cbxMunicipio = new JComboBox();
					cbxMunicipio.setEnabled(false);
					cbxMunicipio.setBounds(79, 146, 172, 27);
					panel_1.add(cbxMunicipio);
				}
			}
			{
				JPanel panel_2 = new JPanel();
				panel_2.setLayout(null);
				panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_2.setBounds(6, 228, 477, 208);
				panel.add(panel_2);
				{
					JLabel lblNewLabel_8 = new JLabel("Años de experiencia:");
					lblNewLabel_8.setBounds(6, 124, 136, 16);
					panel_2.add(lblNewLabel_8);
				}
				{
					spnExperiencia = new JSpinner();
					spnExperiencia.setEnabled(false);
					spnExperiencia.setBounds(144, 119, 81, 26);
					panel_2.add(spnExperiencia);
				}
				{
					JLabel lblNewLabel_9 = new JLabel("Sueldo Máximo:");
					lblNewLabel_9.setBounds(292, 92, 107, 16);
					panel_2.add(lblNewLabel_9);
				}
				{
					JLabel lblNewLabel_10 = new JLabel("Tipo de trabajo:");
					lblNewLabel_10.setBounds(6, 64, 136, 16);
					panel_2.add(lblNewLabel_10);
				}
				{
					cbxTipoTrabajo = new JComboBox();
					cbxTipoTrabajo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tiempo completo", "Medio tiempo"}));
					cbxTipoTrabajo.setEnabled(false);
					cbxTipoTrabajo.setBounds(144, 60, 136, 27);
					panel_2.add(cbxTipoTrabajo);
				}
				{
					JLabel lblNewLabel_11 = new JLabel("Licencia de conducir:");
					lblNewLabel_11.setBounds(6, 152, 136, 16);
					panel_2.add(lblNewLabel_11);
				}
				{
					rdbtnSiLicencia = new JRadioButton("Si");
					rdbtnSiLicencia.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							rdbtnSiLicencia.setSelected(true);
							rdbtnNoLicencia.setSelected(false);
						}
					});
					rdbtnSiLicencia.setSelected(true);
					rdbtnSiLicencia.setEnabled(false);
					rdbtnSiLicencia.setBounds(144, 149, 48, 23);
					panel_2.add(rdbtnSiLicencia);
				}
				{
					rdbtnNoLicencia = new JRadioButton("No");
					rdbtnNoLicencia.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							rdbtnSiLicencia.setSelected(false);
							rdbtnNoLicencia.setSelected(true);
						}
					});
					rdbtnNoLicencia.setEnabled(false);
					rdbtnNoLicencia.setBounds(237, 149, 50, 23);
					panel_2.add(rdbtnNoLicencia);
				}
				{
					JLabel lblNewLabel_13 = new JLabel("Idiomas:");
					lblNewLabel_13.setBounds(6, 180, 61, 16);
					panel_2.add(lblNewLabel_13);
				}
				{
					chckbxEspanol = new JCheckBox("Español");
					chckbxEspanol.setSelected(true);
					chckbxEspanol.setEnabled(false);
					chckbxEspanol.setBounds(144, 177, 81, 23);
					panel_2.add(chckbxEspanol);
				}
				{
					chckbxIngles = new JCheckBox("Inglés");
					chckbxIngles.setEnabled(false);
					chckbxIngles.setBounds(237, 177, 70, 23);
					panel_2.add(chckbxIngles);
				}
				{
					chckbxFrances = new JCheckBox("Francés");
					chckbxFrances.setEnabled(false);
					chckbxFrances.setBounds(330, 177, 81, 23);
					panel_2.add(chckbxFrances);
				}
				{
					JLabel lblNewLabel_14 = new JLabel("Tipo de empleado:");
					lblNewLabel_14.setBounds(6, 8, 116, 16);
					panel_2.add(lblNewLabel_14);
				}
				{
					txtTitulo = new JTextField();
					txtTitulo.setEditable(false);
					txtTitulo.setColumns(10);
					txtTitulo.setBounds(144, 31, 329, 26);
					panel_2.add(txtTitulo);
				}
				{
					txtSueldo = new JTextField();
					txtSueldo.setEditable(false);
					txtSueldo.setColumns(10);
					txtSueldo.setBounds(395, 87, 76, 26);
					panel_2.add(txtSueldo);
				}
				
				JLabel lblNewLabel = new JLabel("Cantidad:");
				lblNewLabel.setBounds(6, 92, 61, 16);
				panel_2.add(lblNewLabel);
				
				spnCantidad = new JSpinner();
				spnCantidad.setEnabled(false);
				spnCantidad.setBounds(144, 87, 81, 26);
				panel_2.add(spnCantidad);
				
				rdbtnObrero = new JRadioButton("Obrero");
				rdbtnObrero.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnTecnico.setSelected(false);
						rdbtnObrero.setSelected(true);
						rdbtnUniversitario.setSelected(false);
						lblTitulo.setText("Skill:");
					}
				});
				rdbtnObrero.setEnabled(false);
				rdbtnObrero.setBounds(392, 5, 81, 23);
				panel_2.add(rdbtnObrero);
				
				rdbtnUniversitario = new JRadioButton("Universitario");
				rdbtnUniversitario.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rdbtnTecnico.setSelected(false);
						rdbtnObrero.setSelected(false);
						rdbtnUniversitario.setSelected(true);
						lblTitulo.setText("Título:");
					}
				});
				rdbtnUniversitario.setEnabled(false);
				rdbtnUniversitario.setBounds(255, 5, 112, 23);
				panel_2.add(rdbtnUniversitario);
				
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
				rdbtnTecnico.setEnabled(false);
				rdbtnTecnico.setBounds(144, 5, 81, 23);
				panel_2.add(rdbtnTecnico);
				
				lblTitulo = new JLabel("Área:");
				lblTitulo.setBounds(6, 36, 61, 16);
				panel_2.add(lblTitulo);
				
				JLabel lblNewLabel_6 = new JLabel("Edad Mínima:");
				lblNewLabel_6.setBounds(292, 124, 84, 16);
				panel_2.add(lblNewLabel_6);
				
				spnEdad = new JSpinner();
				spnEdad.setEnabled(false);
				spnEdad.setBounds(395, 119, 76, 26);
				panel_2.add(spnEdad);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Realizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tipoEmpleado = "";
						if (rdbtnTecnico.isSelected()) {
							tipoEmpleado = "Tecnico";
						} else if (rdbtnUniversitario.isSelected()) {
							tipoEmpleado = "Universitario";
						} else if (rdbtnObrero.isSelected()) {
							tipoEmpleado = "Obrero";
						}
						String titulo = txtTitulo.getText();
						String tipo = cbxTipoTrabajo.getSelectedItem().toString();
						int cantidad = (int) spnCantidad.getValue();
						int experiencia = (int) (spnExperiencia.getValue());
						float sueldo = new Float (txtSueldo.getText());
						int edad = (int) spnEdad.getValue();
						String licencia = "";
						if (rdbtnSiLicencia.isSelected()) {
							licencia = "Si";
						} else if (rdbtnNoLicencia.isSelected()) {
							licencia = "No";
						}
						ArrayList<String> idiomas = new ArrayList<>();
						if (chckbxEspanol.isSelected()) {
							idiomas.add("Español");
						}
						if (chckbxIngles.isSelected()) {
							idiomas.add("Ingles");
						}
						if (chckbxFrances.isSelected()) {
							idiomas.add("Frances");
						}
						Empresa aux = Control.getInstance().getCon().SelectEmpresaRnc(txtId.getText());
						if (aux != null) {
							SolicitudEmpresa soli = new SolicitudEmpresa(aux, cantidad, experiencia, sueldo, tipo, licencia, tipoEmpleado, titulo, edad, idiomas);
							Control.getInstance().getCon().InsertSolicitudEmpresa(soli);
//							aux.getSolicitudes().add(soli);
//							Control.getInstance().getSoliEmpresas().add(soli);
						} else {
							String id = txtId.getText();
							String categoria = cbxCategoria.getSelectedItem().toString();
							String nombre = txtNombre.getText();
							String telefono = txtTelefono.getText();
							String direccion = txtDireccion.getText();
							String provincia = cbxProvincia.getSelectedItem().toString();
							
							aux = new Empresa(id, nombre, categoria, direccion, telefono, provincia);
							Control.getInstance().RegistrarEmpresa(aux);
							SolicitudEmpresa soli = new SolicitudEmpresa(aux, cantidad, experiencia, sueldo, tipo, licencia, tipoEmpleado, titulo, edad, idiomas);
							Control.getInstance().getCon().InsertSolicitudEmpresa(soli);
//							aux.getSolicitudes().add(soli);
//							Control.getInstance().getSoliEmpresas().add(soli);
						}
						JOptionPane.showMessageDialog(null, "Realizado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						txtId.setText("");
						cleanRealizar();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void cleanBuscar() {
		txtNombre.setText("");
		cbxCategoria.setSelectedIndex(0);
		txtTelefono.setText("");
		txtDireccion.setText("");
		cbxProvincia.setEnabled(true);
		cbxProvincia.setSelectedIndex(0);
		txtNombre.setEditable(true);
		txtTelefono.setEditable(true);
		txtDireccion.setEditable(true);
		cbxProvincia.setEnabled(true);
		cbxCategoria.setEnabled(true);
	}
	
	private void enableSolicitud() {
		rdbtnTecnico.setEnabled(true);
		rdbtnObrero.setEnabled(true);
		rdbtnUniversitario.setEnabled(true);
		txtTitulo.setEditable(true);
		cbxTipoTrabajo.setEnabled(true);
		spnCantidad.setEnabled(true);
		spnExperiencia.setEnabled(true);
		txtSueldo.setEditable(true);
		spnEdad.setEnabled(true);
		rdbtnSiLicencia.setEnabled(true);
		rdbtnNoLicencia.setEnabled(true);
		chckbxEspanol.setEnabled(true);
		chckbxFrances.setEnabled(true);
		chckbxIngles.setEnabled(true);
	}
	
	private void cleanRealizar() {
		cbxCategoria.setSelectedIndex(0);
		txtNombre.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtSueldo.setText("");
		spnEdad.setValue(0);
		spnCantidad.setValue(0);
		spnCantidad.setEnabled(false);
		rdbtnTecnico.setEnabled(false);
		rdbtnObrero.setEnabled(false);
		rdbtnUniversitario.setEnabled(false);
		cbxProvincia.setEnabled(false);
		cbxProvincia.setSelectedIndex(0);
		txtNombre.setEditable(false);
		cbxCategoria.setEnabled(false);
		spnEdad.setEnabled(false);
		txtTelefono.setEditable(false);
		txtDireccion.setEditable(false);
		txtTitulo.setText("");
		txtTitulo.setEditable(false);
		cbxTipoTrabajo.setSelectedIndex(0);
		cbxTipoTrabajo.setEnabled(false);
		spnExperiencia.setValue(0);
		spnExperiencia.setEnabled(false);
		txtSueldo.setText("");
		txtSueldo.setEditable(false);
		rdbtnSiLicencia.setEnabled(false);
		rdbtnNoLicencia.setEnabled(false);
		chckbxEspanol.setSelected(true);
		chckbxEspanol.setEnabled(false);
		chckbxFrances.setSelected(false);
		chckbxFrances.setEnabled(false);
		chckbxIngles.setSelected(false);
		chckbxIngles.setEnabled(false);
		rdbtnTecnico.setSelected(true);
		lblTitulo.setText("Árbol:");
		rdbtnObrero.setSelected(false);
		rdbtnUniversitario.setSelected(false);
	}
}
