package model.drawables;

import java.awt.Graphics;

/**
 * Repräsentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class Kreis extends DrawableObject {		//Siehe GestrichelterKreis

	public int x0;
	public int y0;

	public int a;
	public int b;

	private int radius;


	/**
	 * Konstruktor, der ein Punktobjekt aus einem Koordinatenpaar errechnet
	 * 
	 * @param x
	 *            x-Koordinate des Punktes
	 * @param y
	 *            y-Koordinate des Punktes
	 */

	public Kreis(Point a, Point b) {			//Siehe GestrichelterKreis

		this.x0 = a.x;
		this.y0 = a.y;
		this.a = b.x;
		this.b = b.y;
		this.radius = (int) Math.sqrt(Math.pow((this.a-this.x0), 2.0)+Math.pow((this.b-this.y0), 2.0));


	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param copy
	 *            Orginal, das kopiert werden soll
	 */

	public Kreis(Kreis copy) {			//Siehe GestrichelterKreis
		this.x0 = copy.x0;
		this.y0 = copy.y0;
		this.a = copy.a;
		this.b = copy.b;
		this.radius = copy.radius;
	}

	/**
	 * Paint-Methode der Pointklasse. Zeichnet einen Pixel
	 * 
	 * @param g
	 *            der Graphikkontext, in den das Objekt gezeichnet werden soll
	 */
	public void paint(Graphics g) { 				//Siehe GestrichelterKreis
		int f = 1 - radius;
		int ddF_x = 0;
		int ddF_y = -2 * radius;
		int x = 0;
		int y = radius;

		setPixel(x0, y0 + radius,g);
		setPixel(x0, y0 - radius,g);
		setPixel(x0 + radius, y0,g);
		setPixel(x0 - radius, y0,g);

		while(x < y)
		{
			if(f >= 0)
			{
				y--;
				ddF_y += 2;
				f += ddF_y;
			}
			x++;
			ddF_x += 2;
			f += ddF_x + 1;
			setPixel(x0 + x, y0 + y,g);
			setPixel(x0 - x, y0 + y,g);
			setPixel(x0 + x, y0 - y,g);
			setPixel(x0 - x, y0 - y,g);
			setPixel(x0 + y, y0 + x,g);
			setPixel(x0 - y, y0 + x,g);
			setPixel(x0 + y, y0 - x,g);
			setPixel(x0 - y, y0 - x,g);	

		}
	} 
}
