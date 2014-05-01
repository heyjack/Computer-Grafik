package model.drawables;

import java.awt.Graphics;

/**
 * ReprÃ¤sentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class GestricheltRectangle extends DrawableObject {
	//übernommen aus Rectangle, hier nicht umbedingt in dieser Form nötig, einfach halts halber
	
	//Ein ecktpunkt
	public int x;
	public int y;
	
	//länge in x(a) und y(b)
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
	
	public GestricheltRectangle(Point a, Point c) {
		//Obere linke Ecke heraus finden
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
		
		//Länge berechnen
		this.a = Math.abs(c.x-a.x);
		this.b = Math.abs(c.y-a.y);

		
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param copy
	 *            Orginal, das kopiert werden soll
	 */

	public GestricheltRectangle(GestricheltRectangle copy) {
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
		
		//4 Eckpunkte berechnen und erstellen
		Point a = new Point(this.x, this.y);
		Point b = new Point(this.x, this.y+this.b);
		Point c = new Point(this.x+this.a, this.y+this.b);
		Point d = new Point(this.x+this.a, this.y);
		
		//Gestrichelte Linien zwischen den richtigen Punkten
		GestrichelteLine ab = new GestrichelteLine(a,b);
		GestrichelteLine bc = new GestrichelteLine(b,c);
		GestrichelteLine cd = new GestrichelteLine(c,d);
		GestrichelteLine da = new GestrichelteLine(d,a);
		
		//Linien zeichnen
		ab.paint(g);
		bc.paint(g);
		cd.paint(g);
		da.paint(g);
		} 
	}
