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

public class ListSolicitudEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static DefaultTableModel model;
	private static Object rows[];
	private SolicitudEmpresa selected = null;
	private JTable table;
	private JComboBox cbxTipo;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListSolicitudEmpresa dialog = new ListSolicitudEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListSolicitudEmpresa() {
		setTitle("Listado de Solicitudes");
		setBounds(100, 100, 740, 430);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Listado de Solicitudes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblNewLabel = new JLabel("Tipo de Solicitud:");
				lblNewLabel.setBounds(16, 29, 125, 16);
				panel.add(lblNewLabel);
			}
			{
				cbxTipo = new JComboBox();
				cbxTipo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selection = cbxTipo.getSelectedIndex();
						loadtable(selection);
					}
				});
				cbxTipo.setModel(new DefaultComboBoxModel(new String[] {"<Todas>", "Activas", "Completadas"}));
				cbxTipo.setBounds(131, 25, 115, 27);
				panel.add(cbxTipo);
			}
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(6, 57, 718, 290);
			panel.add(scrollPane);
			{
				model = new DefaultTableModel();
				String headers[] = {"Código", "Empresa", "Cantidad", "Tipo de Empleado", "Título", "Sueldo Máximo", "Estado"};
				model.setColumnIdentifiers(headers);
				loadtable(0);
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int index = -1;
						index = table.getSelectedRow();
						if (index != -1) {
//							btnEliminar.setEnabled(true);
//							String id = (String)(model.getValueAt(index, 0));
//							selected = Control.getInstance().BuscarSoliEmpresa(id);
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
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (selected.isEstado()) {
							int option = JOptionPane.showConfirmDialog(null, "Desea eliminar la solicitud seleccionada: "+ selected.getIdSolicitud(), "Eliminar Solicitud Empresa", JOptionPane.YES_NO_OPTION);
							if (option == JOptionPane.YES_OPTION) {
								Control.getInstance().getSoliEmpresas().remove(selected);
								loadtable(0);
								btnEliminar.setEnabled(false);
							}
						} else {
							JOptionPane.showMessageDialog(null, "Esta solicitud no puede ser eliminada", "Error", JOptionPane.ERROR_MESSAGE);
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
		switch (selection) {
		case 0:
			for (SolicitudEmpresa se: Control.getInstance().getCon().SelectSolicitudEmpresas()) {
				rows[0] = se.getIdSolicitud();
				rows[1] = se.getMiEmpresa().getNombreEmpresa();
				rows[2] = se.getCantidad();
				rows[3] = se.getTipoEmpleado();
				rows[4] = se.getTituloEmpleado();
				rows[5] = se.getSueldoMaximoSolicitud();
				if (se.isEstado()) {
					rows[6] = "Activa";
				} else {
					rows[6] = "Completada";
				}
				
				model.addRow(rows);
			}
			break;

		case 1:
			for (SolicitudEmpresa se: Control.getInstance().getCon().SelectSolicitudEmpresas()) {
				if (se.isEstado()) {
					rows[0] = se.getIdSolicitud();
					rows[1] = se.getMiEmpresa().getNombreEmpresa();
					rows[2] = se.getCantidad();
					rows[3] = se.getTipoEmpleado();
					rows[4] = se.getTituloEmpleado();
					rows[5] = se.getSueldoMaximoSolicitud();
					rows[6] = "activa";
					
					model.addRow(rows);
				}
			}
			break;
			
		case 2:
			for (SolicitudEmpresa se: Control.getInstance().getCon().SelectSolicitudEmpresas()) {
				if (!se.isEstado()) {
					rows[0] = se.getIdSolicitud();
					rows[1] = se.getMiEmpresa().getNombreEmpresa();
					rows[2] = se.getCantidad();
					rows[3] = se.getTipoEmpleado();
					rows[4] = se.getTituloEmpleado();
					rows[5] = se.getSueldoMaximoSolicitud();
					rows[6] = "Completada";
					
					model.addRow(rows);
				}
			}
			break;
		}
	}
}
