package ttt;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tictactoe extends JFrame implements ActionListener {

	public static int BOARD_SIZE = 3;

	public static enum Gamestatus {
		Incomplete, xwins, zwins, tie

	}

	private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
	boolean CrossTurn = true;

	public Tictactoe() {
		super.setTitle("Tictactoe");
		super.setSize(800, 800);
		GridLayout grid = new GridLayout(BOARD_SIZE, BOARD_SIZE);
		super.setLayout(grid);
		Font font = new Font("Comic Sans", 1, 150);
		for (int row = 0; row < BOARD_SIZE; row++) {
			for (int col = 0; col < BOARD_SIZE; col++) {
				JButton button = new JButton("");
				buttons[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
	}

	// private JButton[][] buttons=new JButton[BOARD_SIZE][BOARD_SIZE];
	// boolean CrossTurn=true;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton clickedbutton = (JButton) e.getSource();
		makemove(clickedbutton);
		Gamestatus gs = this.getGamestatus();
		if(gs==Gamestatus.Incomplete) {
			return;
		}
		declarewinner(gs);
		int choice=JOptionPane.showConfirmDialog(this,"Do you want to restart the GAME");
		if(choice==JOptionPane.YES_OPTION) {
			for (int row = 0; row < BOARD_SIZE; row++) {
				for (int col = 0; col < BOARD_SIZE; col++) {
				buttons[row][col].setText("");	
				}
				
				}CrossTurn=true;
		}else {
			super.dispose();//terminate the program
		}
	}

	private void declarewinner(Gamestatus gs) {
		if(gs==Gamestatus.xwins) {
			JOptionPane.showMessageDialog(this, "X got WINNER WINNER CHICKEN DINNER");
		}else if(gs==Gamestatus.zwins) {
			JOptionPane.showMessageDialog(this, "ZERO got WINNER WINNER CHICKEN DINNER ");
		}else {
			JOptionPane.showMessageDialog(this, "IT IS A TIE");
		}
		
	}

	private Gamestatus getGamestatus() {
		// TODO Auto-generated method stub
		String text1 = "", text2 = "";
		int row = 0, col = 0;
		// text inside rows
		row = 0;
		while (row < BOARD_SIZE) {
			col = 0;
			while (col < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row][col + 1].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				col++;
			}
			if(col==BOARD_SIZE-1) {
				if(text1.contentEquals("X")) {
					return Gamestatus.xwins;
				}else {
					return Gamestatus.zwins;
				}
			}
			row++;
		}
		//text inside cols
	col = 0;
		while (col < BOARD_SIZE) {
			row = 0;
			while (row < BOARD_SIZE - 1) {
				text1 = buttons[row][col].getText();
				text2 = buttons[row+1][col].getText();
				if (!text1.equals(text2) || text1.length() == 0) {
					break;
				}
				row++;
			}
			if(row==BOARD_SIZE-1) {
				if(text1.contentEquals("X")) {
					return Gamestatus.xwins;
				}else {
					return Gamestatus.zwins;
				}
			}
			col++;
		}
		//text inside diagonals1
		row=0;
		col=0;
		while(row<BOARD_SIZE-1) {
			text1=buttons[row][col].getText();
			text2=buttons[row+1][col+1].getText();
			if(!text1.equals(text2)||text1.length()==0) {
				break;
			}row++;
			col++;
		}
		if(row==BOARD_SIZE-1) {
			if(text1.equals("X")) {
				return Gamestatus.xwins;
				
			}else {
				return Gamestatus.zwins;
			}
		}
		//text in second diagonal
		row=BOARD_SIZE-1;
		col=0;
		while(row>0) {
			text1=buttons[row][col].getText();
			text2=buttons[row-1][col+1].getText();
			if(!text1.equals(text2)||text1.length()==0) {
				break;
			}row--;
			col++;
		}
		if(row==0) {
			if(text1.equals("X")) {
				return Gamestatus.xwins;
				
			}else {
				return Gamestatus.zwins;
			}
		}
		//no wins
		String txt="";
		for(row=0;row<BOARD_SIZE;row++) {
			for(col=0;col<BOARD_SIZE;col++) {
				txt=buttons[row][col].getText();
				if(txt.length()==0) {
					return Gamestatus.Incomplete;
				}
			}
		}
		return Gamestatus.tie;
	}

	private void makemove(JButton clickedbutton) {
		// TODO Auto-generated method stub
		String btn = clickedbutton.getText();
		if (btn.length() > 0) {
			JOptionPane.showMessageDialog(this, "invalid move");

		} else {
			if (CrossTurn) {
				clickedbutton.setText("X");
			} else {
				clickedbutton.setText("0");
			}
			CrossTurn = !CrossTurn;

		}
	}

}
