package game_hash;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Color;

public class game_hash {

	private JFrame frame;
	private Button[][] buttons;
	private String[][] matriz = new String[3][3];
	private TicTacToeAI tictactoe = new TicTacToeAI();
	int count;
	public boolean winner;
	JLabel lblNewLabel = new JLabel("Player 1");
	Panel panel = new Panel();
	JToolBar toolBar = new JToolBar();
	private final Component horizontalGlue = Box.createHorizontalGlue();
	private final JButton btnNewButton = new JButton("Reniciar");

	private String players[] = { "X", "O" };
	private int numberPlayer = 0;
	private final JButton btnNewButton_1 = new JButton("Reset");

	private TicTacToeAI ai;
	private final Box verticalBox_1 = Box.createVerticalBox();
	private final JLabel lblNewLabel_1 = new JLabel("Player 1: 0");
	private final Component verticalGlue = Box.createVerticalGlue();
	private final JLabel lblNewLabel_2 = new JLabel("Player 2: 0");
	private final JLabel lblNewLabel_3 = new JLabel("Emaptes: 0");
	private final Component verticalGlue_1 = Box.createVerticalGlue();
	private final JButton button = new JButton("Começe jogando");

	public game_hash() {
		initialize();
		ai = new TicTacToeAI();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					game_hash window = new game_hash();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		matriz = new String[3][3];

		Box verticalBox = Box.createVerticalBox();
		frame.getContentPane().add(verticalBox);

		verticalBox.add(toolBar);

		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		toolBar.add(lblNewLabel);

		toolBar.add(horizontalGlue);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});

		verticalBox.add(panel);
		panel.setLayout(new GridLayout(3, 3));

		frame.getContentPane().add(verticalBox_1);

		verticalBox_1.add(verticalGlue_1);

		lblNewLabel_1.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		lblNewLabel_2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		lblNewLabel_3.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));

		verticalBox_1.add(lblNewLabel_1);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));

		verticalBox_1.add(lblNewLabel_2);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));

		verticalBox_1.add(lblNewLabel_3);

		verticalBox_1.add(verticalGlue);

		buttons = new Button[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j] = new Button(" ");
				buttons[i][j].setFont(new Font("Tahoma", Font.BOLD, 24));
				;
				panel.add(buttons[i][j]);
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						marker(e);
					}
				});
			}
		}
		IaMove();
	}

	private void whoWin() {
		lblNewLabel.setText("Empate!!");
		insertButton();
		char lastChar = lblNewLabel_3.getText().charAt(lblNewLabel_3.getText().length() - 1);
		lblNewLabel_3.setText("Empates: " + Integer.toString(Character.getNumericValue(lastChar) + 1));
	}

	private void alterMatriz() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				matriz[i][j] = buttons[i][j].getLabel();
			}
		}
	}

	private void IaMove() {
		alterMatriz();
		activateDeactivate(false, false);

		count++;
		ai = new TicTacToeAI();
		int[] bestMove = ai.minimax(matriz, "X");
		if (bestMove != null && bestMove.length == 2) {
			int row = bestMove[0];
			int col = bestMove[1];

			buttons[row][col].setLabel("X");
			buttons[row][col].setEnabled(false);

		}
		alterMatriz();
		activateDeactivate(false, true);

		numberPlayer = 1;
	}

	private void activateDeactivate(boolean b, boolean format) {
		if (format) {
			for (Button[] butttonAr : buttons) {
				for (Button button : butttonAr) {
					if (button.getLabel().equals("X") || button.getLabel().equals("O"))
						button.setEnabled(b);
					else
						button.setEnabled(b ? false : true);
				}
			}
		} else {
			for (Button[] butttonAr : buttons) {
				for (Button button : butttonAr) {
					button.setEnabled(b);
				}
			}
		}

	}

	private void marker(ActionEvent e) {
		Button buttonClicked = (Button) e.getSource();
		buttonClicked.setEnabled(false);
		buttonClicked.setLabel(players[numberPlayer]);
		numberPlayer = (numberPlayer + 1) % 2;
		lblNewLabel.setText("Player " + (numberPlayer + 1));
		count++;

		if (!winner) {
			IaMove();
		}

		String check = tictactoe.checkWinner(matriz);
		if (check.equals("T"))
			whoWin();
		if (check.equals("X") || check.equals("O"))
			win();
	}

	private void insertButton() {
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		toolBar.add(btnNewButton_1);
		toolBar.add(button);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset(e);
			}
		});
	}

	private void win() {
		lblNewLabel.setText("Player " + (numberPlayer == 1 ? 1 : 2) + " Ganhou!!");
		if (numberPlayer == 1) {
			String text = lblNewLabel_1.getText();
			char lastChar = text.charAt(text.length() - 1);
			int num = Character.getNumericValue(lastChar) + 1;
			lblNewLabel_1.setText("Player 1:" + Integer.toString(num));
		} else {
			String text = lblNewLabel_2.getText();
			char lastChar = text.charAt(text.length() - 1);
			int num = Character.getNumericValue(lastChar) + 1;
			lblNewLabel_2.setText("Player 2:" + Integer.toString(num));
		}

		insertButton();
		winner = true;

		activateDeactivate(!winner, false);
	}

	private void reset(ActionEvent e) {
		JButton buttonClicked = (JButton) e.getSource();
		toolBar.remove(btnNewButton_1);
		toolBar.remove(button);
		toolBar.revalidate();

		count = 0;
		lblNewLabel.setText("Player 1");
		winner = false;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				buttons[i][j].setEnabled(true);
				buttons[i][j].setLabel(" ");
				matriz[i][j] = " ";
			}
		}
		
		if (buttonClicked.getLabel().equals("Reset")) {
			numberPlayer = 0;
			IaMove();
		} else {
			numberPlayer = 1;
		}
	}

}
