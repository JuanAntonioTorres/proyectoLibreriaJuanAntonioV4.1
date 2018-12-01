package vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;

public class PanelTemas extends JPanel{
	private JTextField textFieldModificar;
	private JTextField textFieldNuevoTema;
	private JButton btnAccionAdmin;
	private String tipo;
	private JLabel accionActualTitulo;
	private JLabel lblmodificar;
	
	public PanelTemas() {
		setBackground(new Color(255, 255, 255));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 199, 225, 0, 0};
		gridBagLayout.rowHeights = new int[]{33, 0, 44, 0, 42, 39, 37, 31, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		accionActualTitulo = new JLabel(this.tipo);
		accionActualTitulo.setMaximumSize(new Dimension(400, 50));
		accionActualTitulo.setForeground(new Color(0, 0, 128));
		accionActualTitulo.setFont(new Font("Segoe Script", Font.BOLD, 25));
		GridBagConstraints gbc_accionActualTitulo = new GridBagConstraints();
		gbc_accionActualTitulo.fill = GridBagConstraints.BOTH;
		gbc_accionActualTitulo.gridwidth = 2;
		gbc_accionActualTitulo.insets = new Insets(0, 0, 5, 5);
		gbc_accionActualTitulo.gridx = 1;
		gbc_accionActualTitulo.gridy = 0;
		add(accionActualTitulo, gbc_accionActualTitulo);
		
		lblmodificar = new JLabel("tema modificado");
		lblmodificar.setMaximumSize(new Dimension(200, 50));
		lblmodificar.setForeground(Color.ORANGE);
		lblmodificar.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblmodificar.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNombreTema = new GridBagConstraints();
		gbc_lblNombreTema.fill = GridBagConstraints.BOTH;
		gbc_lblNombreTema.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreTema.gridx = 1;
		gbc_lblNombreTema.gridy = 2;
		add(lblmodificar, gbc_lblNombreTema);
		
		textFieldModificar = new JTextField();
		textFieldModificar.setMaximumSize(new Dimension(200, 50));
		textFieldModificar.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.ORANGE, null, null, null));
		textFieldModificar.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 2;
		add(textFieldModificar, gbc_textField);
		textFieldModificar.setColumns(10);
		
		JLabel nuevoTema = new JLabel("nuevo tema");
		nuevoTema.setMaximumSize(new Dimension(200, 50));
		nuevoTema.setForeground(new Color(0, 100, 0));
		nuevoTema.setFont(new Font("Segoe Print", Font.BOLD, 18));
		nuevoTema.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_nuevoTema = new GridBagConstraints();
		gbc_nuevoTema.fill = GridBagConstraints.VERTICAL;
		gbc_nuevoTema.insets = new Insets(0, 0, 5, 5);
		gbc_nuevoTema.gridx = 1;
		gbc_nuevoTema.gridy = 4;
		add(nuevoTema, gbc_nuevoTema);
		
		textFieldNuevoTema = new JTextField();
		textFieldNuevoTema.setMaximumSize(new Dimension(200, 50));
		textFieldNuevoTema.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 100, 0), null, null, null));
		textFieldNuevoTema.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNuevoTema.setColumns(10);
		GridBagConstraints gbc_textFieldNuevoTema = new GridBagConstraints();
		gbc_textFieldNuevoTema.fill = GridBagConstraints.BOTH;
		gbc_textFieldNuevoTema.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNuevoTema.gridx = 2;
		gbc_textFieldNuevoTema.gridy = 4;
		add(textFieldNuevoTema, gbc_textFieldNuevoTema);
		
		btnAccionAdmin = new JButton(tipo);
		btnAccionAdmin.setMaximumSize(new Dimension(400, 50));
		btnAccionAdmin.setForeground(new Color(0, 51, 0));
		btnAccionAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAccionAdmin.setBackground(new Color(204, 255, 204));
		GridBagConstraints gbc_btnAccionAdmin = new GridBagConstraints();
		gbc_btnAccionAdmin.fill = GridBagConstraints.BOTH;
		gbc_btnAccionAdmin.gridwidth = 2;
		gbc_btnAccionAdmin.insets = new Insets(0, 0, 5, 5);
		gbc_btnAccionAdmin.gridx = 1;
		gbc_btnAccionAdmin.gridy = 6;
		add(btnAccionAdmin, gbc_btnAccionAdmin);
		
		configurarCamposSegunTipo();
	}

	public void configurarCamposSegunTipo() {
		if(tipo != null && !tipo.equals("modificar")) {
			lblmodificar.setVisible(false);
			textFieldModificar.setVisible(false);
		}
	}

	public JButton getBtnAccionAdmin() {
		return btnAccionAdmin;
	}

	public JTextField getTextFieldModificar() {
		return textFieldModificar;
	}

	public JTextField getTextFieldNuevoTema() {
		return textFieldNuevoTema;
	}

	public void setTipo(String tipo) {
		this.tipo = new String(tipo);
	}

	public JLabel getAccionActualTitulo() {
		return accionActualTitulo;
	}
	
}
