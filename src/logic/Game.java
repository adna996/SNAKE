package logic;

import gui.Canvas;
import gui.Grid;
import gui.Square;
import gui.Text;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game implements KeyListener {
	 private static final String GAME_TITLE = "Snake Game";
	    private static final int SQUARE_SIZE = 50;
	    private JFrame frame;
	    private Canvas canvas;
	    private Grid grid;
	    private List<Square> snake;
	    private Square food;
	    public enum  movement {
	        left,right,down,up,stop
	    }
	    private List<Text> pontuation;
	    private movement direction = movement.stop;
	    Random rand = new Random();
	    Square auxsquare = new Square(SQUARE_SIZE, Color.GREEN);
	    Text text = new Text("Points",25,25);
	    int points = 0;
	    Text textx = new Text (Integer.toString(points),25,40);


	    public Game(int width, int height) {

	    	//kreiramo prozor sa odgovarajucim svojistima
	    	this.frame = new JFrame(GAME_TITLE);
	        this.frame.getContentPane().setPreferredSize(new Dimension(width, height));
	        this.frame.pack();
	        this.frame.setLocationRelativeTo(null);
	        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.frame.setResizable(false);
	        this.frame.addKeyListener(this);
	        this.frame.setVisible(true);

	        

	        //pravimo prozor velicine prozora
	        this.canvas = new Canvas(frame.getWidth(), frame.getHeight());

	        //Objektiza iscrtavanje grid-aa 
	        this.grid = new Grid(frame.getWidth(), frame.getHeight());

	        //Stavar se ono sto ce predstavljati hranu
	        this.food = new Square(SQUARE_SIZE, Color.RED);

	        food.setX(rand.nextInt(10)+1);
	        food.setY(rand.nextInt(10)+1);

	        //lista objekata koja ce ciniti zmiju
	        this.snake = new ArrayList<>();
	        Square square = new Square(SQUARE_SIZE, Color.black);
	        square.setY(3);
	        square.setX(3);
	        snake.add(square);
	        
	        this.pontuation = new ArrayList<>();
	        this.pontuation.add(text);
	        this.pontuation.add(textx);
	        
	        
	        //dodaje canvas na "platno"
	        this.frame.add(canvas);

	    }

	    public void start() {



	        while (true) { 

	        	//Objekat za crtanje
	        	Graphics2D g = canvas.getBuffer().createGraphics();
	            
	        	//Ciscenje ekrana
	        	g.setBackground(Color.white);
	            g.clearRect(0,0,frame.getWidth(),frame.getHeight());
	            

	            //Cratamo resetku za igranje
	            grid.draw(g);

	            //Crtamo hranu za zmiju
	            food.draw(g);
	            
	            for (int i=0;i< pontuation.size(); i++)
	            {
	            	pontuation.get(i).draw(g);
	            }

	            //Crtaj zmiju
	            for (int i = 0; i < snake.size(); i++) {
	                snake.get(i).draw(g);
	            }
	            
	            moveSnake();
	            getDefeat();
	            verifyCollision();


	            canvas.repaint();
	            

	            try {
	                Thread.sleep(250);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	        }

	    }

	    
	    public void getFood()
	    {
	        food.setX(rand.nextInt(10)+1);
	        food.setY(rand.nextInt(10)+1);
	    }
	    public void verifyCollision(){

	        if(snake.get(0).getX() == food.getX() && snake.get(0).getY() == food.getY())
	        {
	            Square auxxsquare = new Square(SQUARE_SIZE, Color.GREEN );
	            auxxsquare.setY(12);
	            auxxsquare.setX(12);
	            this.snake.add(auxxsquare);
	            points += 10;
	            pontuation.get(1).setText(Integer.toString(points));
	            
	            

	            getFood();
	        }
	        
	        
	        for(int i = 1 ; i<= snake.size() -1 ;i++)
	        {
	        	if(snake.get(0).getX()== snake.get(i).getX() && snake.get(0).getY() == snake.get(i).getY() )
	        	{
	        		resetGame();
	        	}

	        }

	    }


	    public void moveSnake()
	    {
	        for (int z = snake.size() - 1; z > 0; z--) {
	            snake.get(z).setX(snake.get(z - 1).getX());
	            snake.get(z).setY(snake.get(z - 1).getY());

	        }

	        switch(direction){
	            case up:
	                snake.get(0).setY(snake.get(0).getY() -1 );
	                break;
	            case down:
	                snake.get(0).setY(snake.get(0).getY() +1 );
	                break;
	            case left:
	                snake.get(0).setX(snake.get(0).getX() - 1 );
	                break;
	            case right:
	                snake.get(0).setX(snake.get(0).getX() +1 );
	                break;
	            case stop:
	            	break;
	            	
	        }




	    }

	    public void getDefeat()
	    {
	        if(snake.get(0).getX() < 1 || snake.get(0).getX() >= 11 || snake.get(0).getY() <= 0 || snake.get(0).getY() >=11) {
	            resetGame();
	        }

	    }
	    public void resetGame()
	    {
	        snake.clear();
	        Square square = new Square(SQUARE_SIZE, Color.black);
	        square.setY(3);
	        square.setX(3);
	        points = 0;
	        pontuation.get(1).setText(Integer.toString(points));
	        snake.add(square);
	        direction = movement.stop;

	    }
	    public void keyPressed(KeyEvent keyEvent) {

	        switch (keyEvent.getKeyCode()) {

	            case KeyEvent.VK_LEFT:
	            	if(direction != movement.right)
	                direction = movement.left;
	                break;

	            case KeyEvent.VK_RIGHT:
	            	if(direction != movement.left)
	                direction = movement.right;
	                break;

	            case KeyEvent.VK_UP:
	            	if(direction != movement.down)
	                direction = movement.up;
	                break;

	            case KeyEvent.VK_DOWN:
	            	if(direction != movement.up)
	                direction = movement.down;
	                break;

	        }

	    }

	    public void keyReleased(KeyEvent keyEvent) {

	    }

	    public void keyTyped(KeyEvent keyEvent) {

	    }


	}
