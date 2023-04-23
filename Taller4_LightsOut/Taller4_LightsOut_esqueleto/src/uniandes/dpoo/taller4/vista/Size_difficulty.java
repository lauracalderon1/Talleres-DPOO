package uniandes.dpoo.taller4.vista;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


@SuppressWarnings("serial")
public class Size_difficulty extends JPanel {

	private JComboBox<String> sizeBox;
	private ButtonGroup group;

	public Size_difficulty() {
		setBackground(Design.BASECOL);

		JLabel sizeLbl = new JLabel("Tamanio:");
		sizeLbl.setForeground(Color.WHITE);
		sizeLbl.setFont(Design.FONT);
		add(sizeLbl);

		sizeBox = new JComboBox<String>();
		sizeBox.setModel(
				new DefaultComboBoxModel<String>(new String[] { "5x5", "7x7", "9x9", "11x11", "13x13", "15x15" }));
		sizeBox.setSelectedIndex(0);
		add(sizeBox);

		JLabel diffLbl = new JLabel("Dificultad:");
		diffLbl.setForeground(Color.WHITE);

		group = new ButtonGroup();

		diffLbl.setFont(Design.FONT);
		add(diffLbl);

		String[] labels = { "Facil", "Medio", "Dificil", "Muy dificil" };
		JRadioButton[] groupedRadioButtons = new JRadioButton[labels.length];
		for (int i = 0; i < labels.length; i++) {
			groupedRadioButtons[i] = new JRadioButton(labels[i]);
			groupedRadioButtons[i].setActionCommand("" + i);
			groupedRadioButtons[i].setBackground(Design.BASECOL);
			groupedRadioButtons[i].setForeground(Color.WHITE);
			groupedRadioButtons[i].setFont(Design.FONT);
			group.add(groupedRadioButtons[i]);
			add(groupedRadioButtons[i]);
		}
		groupedRadioButtons[0].setSelected(true);

	}

	public int getSizeGrid() {
		return 2 * sizeBox.getSelectedIndex() + 5;
	}

	public int getDifficultyGame() {
		int dif = Integer.parseInt(group.getSelection().getActionCommand()) + 1;
		return dif * 10 << dif;
	}

}
