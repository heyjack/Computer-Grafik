package model.drawables;

import java.awt.Graphics;

/**
 * Repr√§sentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class GestricheltKreis extends DrawableObject {

	//Mittelpunkt
	public int x0;
	public int y0;

	//Punkt auf dem Kreis als Koordinate
	public int a;
	public int b;

	private int radius;		//radius


	/**
	 * Konstruktor, der ein Punktobjekt aus einem Koordinatenpaar errechnet
	 * 
	 * @param x
	 *            x-Koordinate des Punktes
	 * @param y
	 *            y-Koordinate des Punktes
	 */

	public GestricheltKreis(Point a, Point b) {

		//‹bergebene Punkte einsezten
		this.x0 = a.x;
		this.y0 = a.y;
		this.a = b.x;
		this.b = b.y;
		
		//Radius berechnen (Pythagoras)
		this.radius = (int) Math.sqrt(Math.pow((this.a-this.x0), 2.0)+Math.pow((this.b-this.y0), 2.0));


	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param copy
	 *            Orginal, das kopiert werden soll
	 */

	public GestricheltKreis(GestricheltKreis copy) {
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
	public void paint(Graphics g) { 

		//Siehe Gestrichelte Linie
		int abstand = 4;			
		int draw = 0;
		
		//Bresenham Algo, Kreis
		int f = 1 - radius;
		int ddF_x = 0;
		int ddF_y = -2 * radius;
		int x = 0;
		int y = radius;
		
		
		
		//Endpunkte (oben,unten,links,rechts)
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
			if(draw == abstand){				//Siehe Gestrichelte Linie
				setPixel(x0 + x, y0 + y,g);
				setPixel(x0 - x, y0 + y,g);
				setPixel(x0 + x, y0 - y,g);
				setPixel(x0 - x, y0 - y,g);
				setPixel(x0 + y, y0 + x,g);
				setPixel(x0 - y, y0 + x,g);
				setPixel(x0 + y, y0 - x,g);
				setPixel(x0 - y, y0 - x,g);					// setze Pixel
		    	  draw = 0;
		      }
		      else{								//Siehe Gestrichelte Linie
		    	  draw++;
		      }
			
		}
	} 
}
