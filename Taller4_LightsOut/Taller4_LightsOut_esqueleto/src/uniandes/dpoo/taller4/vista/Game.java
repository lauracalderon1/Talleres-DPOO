package uniandes.dpoo.taller4.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

@SuppressWarnings("serial")
public class Game extends JFrame {

	private JPanel contentPane;
	private String playerName;
	private Framework game;
	private Statistics stats;
	private Menu buttons;
	private Size_difficulty header;
	private Tablero model;
	private Top10 top10;
	private static final File DATA = new File("data/top10.csv");

	/**
	 * Create the frame.
	 * 
	 * @param top
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Design.BASECOL);
		contentPane.setBorder(new EmptyBorder(5, 0, 5, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		this.top10 = new Top10();
		top10.cargarRecords(DATA);

		header = new Size_difficulty();
		contentPane.add(header, BorderLayout.NORTH);

		JPanel container = new JPanel();
		container.setBackground(Design.BASECOL);
		contentPane.add(container, BorderLayout.SOUTH);
		container.setLayout(new BorderLayout(0, 0));

		buttons = new Menu(this);
		container.add(buttons, BorderLayout.NORTH);

		this.playerName = getUser();
		stats = new Statistics(this.playerName);
		container.add(stats, BorderLayout.SOUTH);

		game = new Framework(this, 1);
		contentPane.add(game, BorderLayout.CENTER);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					top10.salvarRecords(DATA);
				} catch (Exception e) {
				}
			}
		});

		pack();
		setLocationRelativeTo(null);
	}
	//Se pide al usuario que ingrese el nombre para identificarlo como jugador
	private String getUser() {
		String t = JOptionPane.showInputDialog(null, "Ingrese su nombre", "Lights Out Name",
				JOptionPane.PLAIN_MESSAGE);

		return t == null || t.isBlank() ? "Kvothe" : t.replaceAll("\\s", "");
	}

	public boolean isOn(int i, int j) {
		if (game.getSquare() == 1)
			return true;
		return model.darTablero()[i][j];
	}

	public void newGame() {
		int cell = header.getSizeGrid();
		int diff = header.getDifficultyGame();

		model = new Tablero(cell);
		game.setSquare(cell);

		model.desordenar(diff);
		game.repaint();
	}

	public void restart() {
		model.reiniciar();
		game.repaint();
	}

	public void top10() {
		new Best10(top10.darRegistros().stream().toArray(RegistroTop10[]::new));
	}

	public void change() {
		stats.setJugadas(0);
		stats.setPlayer(getUser());
		newGame();
	}
	
	//A continuación se muestra 

	public void play(int[] cells) {
		if (model != null) {
			model.jugar(cells[0], cells[1]);
			stats.setJugadas(model.darJugadas());
			if (model.tableroIluminado()) {
				int puntaje = model.calcularPuntaje();
				String lbl = "Haz ganado, tu puntaje fue de:  " + puntaje;
				if (top10.esTop10(puntaje)) {
					lbl += "\n Felicitaciones, perteneces a los mejores 10 jugadores";
					top10.agregarRegistro(playerName, puntaje);
				}
				JOptionPane.showMessageDialog(null, lbl);
			}
		}
	}

}