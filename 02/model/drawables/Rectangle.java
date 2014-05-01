package model.drawables;

import java.awt.Graphics;

/**
 * Repr√§sentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class Rectangle extends DrawableObject {
										//Siehe GestricheltRectangle
	public int x;
	public int y;
	
	
	public int a;
	public int b;
	

	/**
	 * Konstruktor, der ein Punktobjekt aus einem Koordinatenpaar errechnet
	 * 
	 * @param x
	 *            x-Koordinate des Punktes
	 * @param y
	 *            y-Koordinate des Punktes
	 */
	
	public Rectangle(Point a, Point c) {					//Siehe GestricheltRectangle
		
		if(a.x < c.x){
			this.x = a.x;
		}
		else{
			this.x = c.x;
		}
		if(a.y < c.y){
			this.y = a.y;
		}
		else{
			this.y = c.y;
		}
		this.a = Math.abs(c.x-a.x);
		this.b = Math.abs(c.y-a.y);

		
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param copy
	 *            Orginal, das kopiert werden soll
	 */

	public Rectangle(Rectangle copy) {					//Siehe GestricheltRectangle
		this.x = copy.x;
		this.y = copy.y;
		this.a = copy.a;
		this.b = copy.b;
	}

	/**
	 * Paint-Methode der Pointklasse. Zeichnet einen Pixel
	 * 
	 * @param g
	 *            der Graphikkontext, in den das Objekt gezeichnet werden soll
	 */
	public void paint(Graphics g) {
		setPixel(x, y, a, b, g);					//Zeichne sogesehen, einen groﬂen Punkt (was auch ein rechteck ist)
		} 
	}
