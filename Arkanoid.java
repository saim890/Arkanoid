/**
 * Arkanoid.java
 * Saim Abbasi & Hamza Muhammad
 * January 18th, 2017
 * Simple Game Assignment - User controls a platform that directs a ball to destroy several bricks
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;    //Import libraries
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class Arkanoid extends JFrame implements ActionListener{ //Runs the game loop
	Timer myTimer;
	GamePanel game;

	public Arkanoid(){ //Constructor
		super("Arkanoid!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Ends running the program when the window is closed
		setSize(800,700); //set screen size to 800 x 650
		myTimer = new Timer (10,this); //Triggers ever 10 ms

		game = new GamePanel(this);
		add(game);

		setVisible(true);
	}
	public void start(){ //Starts the timer
		myTimer.start();
	}
	public void actionPerformed(ActionEvent e){
		game.move();
		game.repaint();
	}
	public static void main(String[]args){
		Arkanoid frame = new Arkanoid();
	}
}
class GamePanel extends JPanel implements KeyListener{
	private int brickx, bricky;
	private boolean[]keys;
	private Image back;
	private Arkanoid mainFrame;

	public GamePanel(Arkanoid m){
		keys = new boolean[KeyEvent.KEY_LAST+1];
		back = new ImageIcon("arkanoidbg.png").getImage(); //Arkanoid Background picture
		mainFrame = m;
		brickx = 400;
		bricky = 625;
		setSize(800,700);
		addKeyListener(this);
	}
	public void addNotify(){
		super.addNotify();
		requestFocus();
		mainFrame.start();
	}
	public void move(){
		if(keys[KeyEvent.VK_RIGHT]){
			brickx += 5;
			checkOOB();
		}
		if(keys[KeyEvent.VK_LEFT]){
			brickx -= 5;
			checkOOB();
		}
		//if(keys[KeyEvent.VK_UP]){
			//bricky -= 5;
		//}
		//if(keys[KeyEvent.VK_DOWN]){
			//bricky += 5;
		//}
	}
	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e){
		keys[e.getKeyCode()] = true;
	}
	public void keyReleased(KeyEvent e){
		keys[e.getKeyCode()] = false;
	}
	public void paintComponent(Graphics g){
		g.drawImage(back,0,0,this);
		g.setColor(Color.red);
		g.fillRect(brickx, bricky,80,10);
	}
	private void checkOOB() {
        if(brickx < 0) brickx = 0;
        if(brickx > 710) brickx = 710;
    }
}