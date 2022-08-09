package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Recursos.Control;
import Recursos.Obrero;
import Recursos.Persona;
import Recursos.SolicitudEmpresa;
import Recursos.SolicitudPersona;
import Recursos.Tecnico;
import Recursos.Universitario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RealizarMatch extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object rows[];
	private SolicitudEmpresa selected = null;
	private SolicitudPersona selectedPerson = null;
	private JTable table;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RealizarMatch dialog = new RealizarMatch();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RealizarMatch() {
		setTitle("Matching");
		setBounds(100, 100, 740, 430);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(6, 6, 718, 341);
			panel.add(scrollPane);
			{
				model = new DefaultTableModel();
				String headers[] = {"Empresa", "Solicitud", "Título", "Empleado","Cédula", "Solicitud Persona", "Porcentaje"};
				model.setColumnIdentifiers(headers);
				loadtable(0);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = -1;
						index = table.getSelectedRow();
						if (index != -1) {
							btnEliminar.setEnabled(true);
							int id = (int)(model.getValueAt(index, 1));
							int idPerson = (int) model.getValueAt(index, 5);
							String cedula = (String) model.getValueAt(index, 4);
							selected = Control.getInstance().BuscarSoliEmpresa(id);
							selectedPerson = Control.getInstance().BuscarSoliPersona(idPerson, cedula);
						}
					}
				});
				table.setModel(model);
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Realizar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "Desea realizar la siguiente solicitud "+ selected.getIdSolicitud(), "Completar Solicitud Empresa", JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							Control.getInstance().completarSolicitud(selected);
							selectedPerson.setEstado("Completada");
							Control.getInstance().getCon().UpdateSoliPersona(selectedPerson);
							Control.getInstance().changeToEmpleado(selectedPerson.getPerson());
							Control.getInstance().getCon().InsertMatchSolicitudes(selected.getIdSolicitud(), selectedPerson.getId());
							loadtable(0);
							btnEliminar.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Solicitud completada!", "Información", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				});
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
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

	private static void loadtable(int selection) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		
		for (SolicitudEmpresa se: Control.getInstance().getCon().SelectSolicitudEmpresas()) {
			if (se.isEstado()) {
				SolicitudPersona p = Control.getInstance().match(se);
				if (p != null) {
					rows[0] = se.getMiEmpresa().getNombreEmpresa();
					rows[1] = se.getIdSolicitud();
					rows[2] = se.getTituloEmpleado();
					rows[3] = p.getPerson().getNombre();
					rows[4] = p.getPerson().getId();
					rows[5] = p.getId();
					rows[6] = Control.getInstance().porcentaje(p, se);
					model.addRow(rows);
				} else {
					rows[0] = se.getMiEmpresa().getNombreEmpresa();
					rows[1] = se.getIdSolicitud();
					rows[2] = "Sin resultado";
					rows[3] = "Sin resultado";
					rows[4] = "Sin resultado";
					rows[5] = "Sin resultado";
					rows[6] = "Sin resultado";
					model.addRow(rows);
				}
			}
		}
	}
}
