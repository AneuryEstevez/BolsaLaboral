package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import Recursos.Control;
import Recursos.Obrero;
import Recursos.Persona;
import Recursos.SolicitudPersona;
import Recursos.Tecnico;
import Recursos.Universitario;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class RealizarSolicitudPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;
	private JTextField txtNombre;
	private JComboBox cbxSexo;
	private JRadioButton rdbtnTecnico;
	private JRadioButton rdbtnUniversitario;
	private JRadioButton rdbtnObrero;
	private JSpinner spnEdad;
	private JTextField txtTelefono;
	private JTextField txtDireccion;
	private JComboBox cbxProvincia;
	private JTextField txtTitulo;
	private JComboBox cbxTipoTrabajo;
	private JSpinner spnExperiencia;
	private JRadioButton rdbtnSiLicencia;
	private JRadioButton rdbtnNoLicencia;
	private JRadioButton rdbtnSiMovilidad;
	private JRadioButton rdbtnNoMovilidad;
	private JCheckBox chckbxEspanol;
	private JCheckBox chckbxIngles;
	private JCheckBox chckbxFrances;
	private int cantIdiomas = 0;
	private JTextField txtSueldo;
	private JLabel lblTitulo;
	SimpleDateFormat sm = new SimpleDateFormat("yyyy-MMM-dd");
	private JComboBox cbxMunicipio;
	private JTextField txtEdad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RealizarSolicitudPersona dialog = new RealizarSolicitudPersona();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RealizarSolicitudPersona() {
		setTitle("Solicitud Persona");
		setBounds(100, 100, 450, 522);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Cédula:");
			lblNewLabel.setBounds(12, 11, 61, 16);
			panel.add(lblNewLabel);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(61, 6, 130, 26);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Persona p = Control.getInstance().BuscarPersona(txtCedula.getText());
					if (p != null) {
						if (p instanceof Tecnico) {
							rdbtnTecnico.setSelected(true);
							rdbtnObrero.setSelected(false);
							rdbtnUniversitario.setSelected(false);
							lblTitulo.setText("Área:");
							txtTitulo.setText(((Tecnico) p).getArea());
						} else if (p instanceof Universitario) {
							rdbtnUniversitario.setSelected(true);
							rdbtnObrero.setSelected(false);
							rdbtnTecnico.setSelected(false);
							lblTitulo.setText("Título:");
							txtTitulo.setText(((Universitario) p).getTitulo());
						} else if (p instanceof Obrero){
							rdbtnObrero.setSelected(true);
							rdbtnUniversitario.setSelected(false);
							rdbtnTecnico.setSelected(false);
							lblTitulo.setText("Skill:");
							txtTitulo.setText(((Obrero) p).getSkills());
						}
						txtNombre.setText(p.getNombre());
						cbxSexo.setSelectedItem(p.getSexo());
						spnEdad.setVisible(false);
						txtEdad.setVisible(true);
						txtEdad.setText(p.getFecha_nacimiento());
//						spnEdad.setValue(p.getFecha_nacimiento());
						txtTelefono.setText(p.getTelefono());
						txtDireccion.setText(p.getDireccion());
						cbxProvincia.setSelectedItem(Control.getInstance().getCon().SelectProvincia(p.getMunicipio()));
						cbxMunicipio.setSelectedItem(p.getMunicipio());
						cbxMunicipio.setEnabled(false);
					} else {
						cleanBuscar();
					}
					enableSolicitud();
				}

			});
			btnBuscar.setBounds(195, 5, 91, 29);
			panel.add(btnBuscar);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(6, 39, 428, 209);
			panel.add(panel_1);
			
			JLabel lblNewLabel_1 = new JLabel("Nombre:");
			lblNewLabel_1.setBounds(6, 38, 61, 16);
			panel_1.add(lblNewLabel_1);
			
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(79, 33, 329, 26);
			panel_1.add(txtNombre);
			
			JLabel lblNewLabel_2 = new JLabel("Teléfono:");
			lblNewLabel_2.setBounds(6, 94, 72, 16);
			panel_1.add(lblNewLabel_2);
			
			txtTelefono = new JTextField();
			txtTelefono.setEditable(false);
			txtTelefono.setColumns(10);
			txtTelefono.setBounds(79, 89, 329, 26);
			panel_1.add(txtTelefono);
			
			JLabel lblNewLabel_5 = new JLabel("Dirección:");
			lblNewLabel_5.setBounds(6, 122, 72, 16);
			panel_1.add(lblNewLabel_5);
			
			txtDireccion = new JTextField();
			txtDireccion.setEditable(false);
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(79, 117, 329, 26);
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
			rdbtnTecnico.setEnabled(false);
			rdbtnTecnico.setSelected(true);
			rdbtnTecnico.setBounds(79, 7, 81, 23);
			panel_1.add(rdbtnTecnico);
			
			rdbtnUniversitario = new JRadioButton("Universitario");
			rdbtnUniversitario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnUniversitario.setSelected(true);
					rdbtnObrero.setSelected(false);
					rdbtnTecnico.setSelected(false);
					lblTitulo.setText("Título:");
				}
			});
			rdbtnUniversitario.setEnabled(false);
			rdbtnUniversitario.setBounds(190, 6, 112, 23);
			panel_1.add(rdbtnUniversitario);
			
			rdbtnObrero = new JRadioButton("Obrero");
			rdbtnObrero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnObrero.setSelected(true);
					rdbtnTecnico.setSelected(false);
					rdbtnUniversitario.setSelected(false);
					lblTitulo.setText("Skill:");
				}
			});
			rdbtnObrero.setEnabled(false);
			rdbtnObrero.setBounds(327, 7, 81, 23);
			panel_1.add(rdbtnObrero);
			
			JLabel lblNewLabel_4 = new JLabel("Sexo:");
			lblNewLabel_4.setBounds(6, 66, 61, 16);
			panel_1.add(lblNewLabel_4);
			
			cbxSexo = new JComboBox();
			cbxSexo.setEnabled(false);
			cbxSexo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "M", "F"}));
			cbxSexo.setBounds(79, 61, 142, 27);
			panel_1.add(cbxSexo);
			
			
			JLabel lblNewLabel_6 = new JLabel("Edad:");
			lblNewLabel_6.setBounds(259, 66, 61, 16);
			panel_1.add(lblNewLabel_6);
			
			Date today = new Date();
			spnEdad = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
			JSpinner.DateEditor editor = new JSpinner.DateEditor(spnEdad, "dd/MM/yy");
			spnEdad.setEditor(editor);
			spnEdad.setEnabled(false);
			spnEdad.setBounds(308, 61, 100, 26);
			panel_1.add(spnEdad);
			
			JLabel lblNewLabel_7 = new JLabel("Provincia:");
			lblNewLabel_7.setBounds(6, 150, 61, 16);
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
			cbxProvincia.setEnabled(false);
			ArrayList<String> p = new ArrayList<>();
			p.add("<Seleccione>");
			p.addAll(Control.getInstance().getProvincias());
			cbxProvincia.setModel(new DefaultComboBoxModel(p.toArray()));
			cbxProvincia.setMaximumRowCount(5);
			cbxProvincia.setBounds(79, 145, 168, 27);
			panel_1.add(cbxProvincia);
			
			JLabel lblNewLabel_14 = new JLabel("Municipio:");
			lblNewLabel_14.setBounds(6, 178, 72, 16);
			panel_1.add(lblNewLabel_14);
			
			cbxMunicipio = new JComboBox();
			cbxMunicipio.setEnabled(false);
			cbxMunicipio.setBounds(79, 174, 168, 27);
			panel_1.add(cbxMunicipio);
			
			txtEdad = new JTextField();
			txtEdad.setEditable(false);
			txtEdad.setVisible(false);
			txtEdad.setBounds(308, 61, 100, 26);
			panel_1.add(txtEdad);
			txtEdad.setColumns(10);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(6, 255, 428, 179);
			panel.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel_8 = new JLabel("Años de experiencia:");
			lblNewLabel_8.setBounds(6, 64, 136, 16);
			panel_2.add(lblNewLabel_8);
			
			spnExperiencia = new JSpinner();
			spnExperiencia.setEnabled(false);
			spnExperiencia.setBounds(144, 59, 62, 26);
			panel_2.add(spnExperiencia);
			
			JLabel lblNewLabel_9 = new JLabel("Sueldo Mínimo:");
			lblNewLabel_9.setBounds(237, 64, 97, 16);
			panel_2.add(lblNewLabel_9);
			
			SpinnerNumberModel model = new SpinnerNumberModel(1.0, null, null, 1.0);
			
			JLabel lblNewLabel_10 = new JLabel("Tipo de trabajo:");
			lblNewLabel_10.setBounds(6, 36, 136, 16);
			panel_2.add(lblNewLabel_10);
			
			cbxTipoTrabajo = new JComboBox();
			cbxTipoTrabajo.setEnabled(false);
			cbxTipoTrabajo.setModel(new DefaultComboBoxModel(new String[] {"<Seleccione>", "Tiempo completo", "Medio tiempo"}));
			cbxTipoTrabajo.setBounds(144, 31, 136, 27);
			panel_2.add(cbxTipoTrabajo);
			
			JLabel lblNewLabel_11 = new JLabel("Licencia de conducir:");
			lblNewLabel_11.setBounds(6, 92, 136, 16);
			panel_2.add(lblNewLabel_11);
			
			JLabel lblNewLabel_12 = new JLabel("Dispuesto a mudarse?");
			lblNewLabel_12.setBounds(6, 120, 143, 16);
			panel_2.add(lblNewLabel_12);
			
			rdbtnSiLicencia = new JRadioButton("Si");
			rdbtnSiLicencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiLicencia.setSelected(true);
					rdbtnNoLicencia.setSelected(false);
				}
			});
			rdbtnSiLicencia.setEnabled(false);
			rdbtnSiLicencia.setSelected(true);
			rdbtnSiLicencia.setBounds(144, 89, 48, 23);
			panel_2.add(rdbtnSiLicencia);
			
			rdbtnNoLicencia = new JRadioButton("No");
			rdbtnNoLicencia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiLicencia.setSelected(false);
					rdbtnNoLicencia.setSelected(true);
				}
			});
			rdbtnNoLicencia.setEnabled(false);
			rdbtnNoLicencia.setBounds(237, 89, 50, 23);
			panel_2.add(rdbtnNoLicencia);
			
			rdbtnSiMovilidad = new JRadioButton("Si");
			rdbtnSiMovilidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiMovilidad.setSelected(true);
					rdbtnNoMovilidad.setSelected(false);
				}
			});
			rdbtnSiMovilidad.setEnabled(false);
			rdbtnSiMovilidad.setSelected(true);
			rdbtnSiMovilidad.setBounds(144, 117, 62, 23);
			panel_2.add(rdbtnSiMovilidad);
			
			rdbtnNoMovilidad = new JRadioButton("No");
			rdbtnNoMovilidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnSiMovilidad.setSelected(false);
					rdbtnNoMovilidad.setSelected(true);
				}
			});
			rdbtnNoMovilidad.setEnabled(false);
			rdbtnNoMovilidad.setBounds(237, 117, 55, 23);
			panel_2.add(rdbtnNoMovilidad);
			
			JLabel lblNewLabel_13 = new JLabel("Idiomas:");
			lblNewLabel_13.setBounds(6, 148, 61, 16);
			panel_2.add(lblNewLabel_13);
			
			chckbxEspanol = new JCheckBox("Español");
			chckbxEspanol.setEnabled(false);
			chckbxEspanol.setSelected(true);
			chckbxEspanol.setBounds(144, 145, 81, 23);
			panel_2.add(chckbxEspanol);
			
			chckbxIngles = new JCheckBox("Inglés");
			chckbxIngles.setEnabled(false);
			chckbxIngles.setBounds(237, 145, 70, 23);
			panel_2.add(chckbxIngles);
			
			chckbxFrances = new JCheckBox("Francés");
			chckbxFrances.setEnabled(false);
			chckbxFrances.setBounds(330, 145, 81, 23);
			panel_2.add(chckbxFrances);
			
			lblTitulo = new JLabel("Área:");
			lblTitulo.setBounds(6, 8, 61, 16);
			panel_2.add(lblTitulo);
			
			txtTitulo = new JTextField();
			txtTitulo.setEditable(false);
			txtTitulo.setBounds(144, 3, 264, 26);
			panel_2.add(txtTitulo);
			txtTitulo.setColumns(10);
			
			txtSueldo = new JTextField();
			txtSueldo.setEditable(false);
			txtSueldo.setBounds(346, 59, 65, 26);
			panel_2.add(txtSueldo);
			txtSueldo.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Realizar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String tipo = cbxTipoTrabajo.getSelectedItem().toString();
						int experiencia = (int) (spnExperiencia.getValue());
						float sueldo = new Float (txtSueldo.getText());
						String licencia = "";
						if (rdbtnSiLicencia.isSelected()) {
							licencia = "Si";
						} else if (rdbtnNoLicencia.isSelected()) {
							licencia = "No";
						}
						String movilidad = "";
						if (rdbtnSiMovilidad.isSelected()) {
							movilidad = "Si";
						} else if (rdbtnNoMovilidad.isSelected()) {
							movilidad = "No";
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
						
						Persona aux = Control.getInstance().BuscarPersona(txtCedula.getText());
						if (aux != null) {
							
							SolicitudPersona soli = new SolicitudPersona(aux, experiencia, sueldo, tipo, licencia, movilidad, idiomas);
							Control.getInstance().getCon().InsertSolicitudPersona(soli);
//							aux.getMisSolicitudes().add(soli);
//							Control.getInstance().getSoliPersonas().add(soli);
						} else {
							String cedula = txtCedula.getText();
							String nombre = txtNombre.getText();
							String sexo = cbxSexo.getSelectedItem().toString();
							String edad = sm.format(spnEdad.getValue());
							String telefono = txtTelefono.getText();
							String direccion = txtDireccion.getText();
							String municipio = cbxMunicipio.getSelectedItem().toString();
							String titulo = txtTitulo.getText();
							
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
							
							SolicitudPersona soli = new SolicitudPersona(aux, experiencia, sueldo, tipo, licencia, movilidad, idiomas);
							Control.getInstance().getCon().InsertSolicitudPersona(soli);
//							aux.getMisSolicitudes().add(soli);
//							Control.getInstance().getSoliPersonas().add(soli);
						}
						JOptionPane.showMessageDialog(null, "Realizado satisfactoriamente", "Información", JOptionPane.INFORMATION_MESSAGE);
						txtCedula.setText("");
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
		cbxSexo.setSelectedIndex(0);
//		spnEdad.setValue(0);
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtSueldo.setText("");
		rdbtnTecnico.setEnabled(true);
		rdbtnObrero.setEnabled(true);
		rdbtnUniversitario.setEnabled(true);
		cbxProvincia.setEnabled(true);
		cbxProvincia.setSelectedIndex(0);
		txtNombre.setEditable(true);
		cbxSexo.setEnabled(true);
		spnEdad.setEnabled(true);
		txtTelefono.setEditable(true);
		txtDireccion.setEditable(true);
		cbxProvincia.setEnabled(true);
		cbxMunicipio.setEnabled(false);
	}
	
	private void cleanRealizar() {
		txtNombre.setText("");
		cbxSexo.setSelectedIndex(0);
		spnEdad.setValue(new Date());
		txtEdad.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtSueldo.setText("");
		rdbtnTecnico.setEnabled(false);
		rdbtnObrero.setEnabled(false);
		rdbtnUniversitario.setEnabled(false);
		cbxProvincia.setEnabled(false);
		cbxProvincia.setSelectedIndex(0);
		txtNombre.setEditable(false);
		cbxSexo.setEnabled(false);
		spnEdad.setEnabled(false);
		txtTelefono.setEditable(false);
		txtDireccion.setEditable(false);
		cbxProvincia.setEnabled(false);
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
		rdbtnSiMovilidad.setEnabled(false);
		rdbtnNoMovilidad.setEnabled(false);
		chckbxEspanol.setSelected(true);
		chckbxEspanol.setEnabled(false);
		chckbxFrances.setSelected(false);
		chckbxFrances.setEnabled(false);
		chckbxIngles.setSelected(false);
		chckbxIngles.setEnabled(false);
		rdbtnTecnico.setSelected(true);
		lblTitulo.setText("Área:");
		rdbtnObrero.setSelected(false);
		rdbtnUniversitario.setSelected(false);
	}
	
	private void enableSolicitud() {
		txtTitulo.setEditable(true);
		cbxTipoTrabajo.setEnabled(true);
		spnExperiencia.setEnabled(true);
		txtSueldo.setEditable(true);
		rdbtnSiLicencia.setEnabled(true);
		rdbtnNoLicencia.setEnabled(true);
		rdbtnSiMovilidad.setEnabled(true);
		rdbtnNoMovilidad.setEnabled(true);
		chckbxEspanol.setEnabled(true);
		chckbxFrances.setEnabled(true);
		chckbxIngles.setEnabled(true);
	}
}
