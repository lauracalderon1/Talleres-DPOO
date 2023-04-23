package uniandes.dpoo.taller4.vista;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("serial")
public class Statistics extends JPanel {

	private JLabel[] values;
	private String playername;
	private int play = 0;

	public Statistics(String playerName) {
		this.playername = playerName;
		setBorder(new EmptyBorder(0, 7, 0, 7));
		setBackground(Design.BASECOL);
		setLayout(new GridLayout(1, 0, 0, 0));

		JLabel[] keys = new JLabel[2];
		String[] keysLbl = { "Jugadas", "Jugador" };
		values = new JLabel[2];
		String[] valuesLbls = new String[] { "" + play, playername };

		for (int i = 0; i < 2; i++) {
			keys[i] = new JLabel(keysLbl[i]);
			keys[i].setForeground(Color.WHITE);
			keys[i].setFont(Design.FONT);
			values[i] = new JLabel(valuesLbls[i]);
			values[i].setForeground(Color.WHITE);
			values[i].setFont(Design.FONT);
			add(keys[i]);
			add(values[i]);
		}
	}

	public void setJugadas(int i) {
		this.play = i;
		values[0].setText("" + i);
	}

	public void setPlayer(String name) {
		values[1].setText(name);
	}

	public int getJugadas() {
		return this.play;
	}
}
