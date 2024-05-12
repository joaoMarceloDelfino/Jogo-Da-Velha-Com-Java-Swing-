package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import logica.Logica;

@SuppressWarnings("serial")
public class Tabuleiro extends JFrame {
	private JButton botoes[][] = new JButton[3][3];
	private Logica engine = new Logica();

	public Tabuleiro() {

		Inicializar();
		eventosListener();

	}


	private void Inicializar() {
		setTitle("Jogo da velha");
		setVisible(true);
		setLayout(null);
		setBounds(100, 200, 600, 500);		 
		int x = 50, y = 50;

		for (int i = 0; i < 3; i++) {
			if (i >= 1) {
				y += 105;
				x = 50;
			}
			for (int j = 0; j < 3; j++) {
				botoes[i][j] = new JButton();
				add(botoes[i][j]);
				botoes[i][j].setBounds(x, y, 100, 100);
				x += 105;
				botoes[i][j].setText("");
			}
		}

	}

	private void eventosListener() {

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				final int I = i;
				final int J = j;

				botoes[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
	 		 
						if (engine.getControle_Rodada() % 2 == 0) {
							botoes[I][J].setText("O");
						} else {
							botoes[I][J].setText("X");
						}
						engine.Jogar();
						botoes[I][J].setEnabled(false);
						detectaVencedor();

					}
				});
			}
		}

	}
	private void resetBotoes() {
		
		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				botoes[i][j].setText("");
				botoes[i][j].setEnabled(true);
			}
			}
	}

	public void detectaVencedor() {

		for (int c = 0; c < 3; c++) {                                                          //Detecta vitória na vertical
			 
			if (!botoes[0][c].getText().equals("")) {
				if (botoes[0][c].getText().equals(botoes[1][c].getText())
						&& botoes[0][c].getText().equals(botoes[2][c].getText())) {
					JOptionPane.showConfirmDialog(null, "Parabens"+" "+ botoes[0][c].getText()+".Você venceu!");
					resetBotoes();
				}
			}
			
			
			if (!botoes[c][0].getText().equals("")) {                                           //Detecta vitória na horizontal
				if (botoes[c][0].getText().equals(botoes[c][1].getText())
						&& botoes[c][0].getText().equals(botoes[c][2].getText())) {
					JOptionPane.showConfirmDialog(null,"Parabens"+" "+ botoes[c][0].getText()+".Você venceu!");
					resetBotoes();
				}
			}

		}//fim do laço for
		
		if (!botoes[0][0].getText().equals("")) {
			if (botoes[0][0].getText().equals(botoes[1][1].getText())
					&& botoes[0][0].getText().equals(botoes[2][2].getText())) {                 //Detecta vitória na diagonal(superior esquerdo a inferior direito)
				JOptionPane.showConfirmDialog(null, "Parabens"+" "+ botoes[0][0].getText()+".Você venceu!");
				resetBotoes();
			}
		}
		
		if (!botoes[0][2].getText().equals("")) {
			if (botoes[0][2].getText().equals(botoes[1][1].getText())
					&& botoes[0][2].getText().equals(botoes[2][0].getText())) {                //Detecta vitória na diagonal(superior direito a inferior esquerdo)
				JOptionPane.showConfirmDialog(null, "Parabens"+" "+ botoes[0][2].getText()+".Você venceu!");
				resetBotoes();
			}
		}
	}
}
