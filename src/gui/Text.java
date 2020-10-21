package gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class Text implements Drawable{
	private String text;
	private int x;
	private int y;
	
	
	public Text(String text,int x,int y){
		
		this.text = text;
		this.x = x;
		this.y = y;
		
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y )
	{
		this.y = y;
	}
	
	public String getText()
	{
		return text;
	}
	
	public int getX()
	{
		return x;
		
	}
	
	public int getY()
	{
		return y;
	}
	
	
	@Override
	public void draw(Graphics2D g){
	    g.setColor(Color.black);
		g.drawString(this.text, this.x, this.y);
		
	}

}
