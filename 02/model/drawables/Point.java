package model.drawables;

import java.awt.Graphics;

/**
 * Repräsentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class Point extends DrawableObject {

	//X , Y Koordinate
	public int x;
	public int y;

	/**
	 * Konstruktor, der ein Punktobjekt aus einem Koordinatenpaar errechnet
	 * 
	 * @param x
	 *            x-Koordinate des Punktes
	 * @param y
	 *            y-Koordinate des Punktes
	 */
	public Point(int x, int y) { //Werte setzen
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param copy
	 *            Orginal, das kopiert werden soll
	 */

	public Point(Point copy) {
		this.x = copy.x;
		this.y = copy.y;
	}

	/**
	 * Paint-Methode der Pointklasse. Zeichnet einen Pixel
	 * 
	 * @param g
	 *            der Graphikkontext, in den das Objekt gezeichnet werden soll
	 */
	public void paint(Graphics g) {	//Punkt zeichnen mit 1,1
		setPixel(x, y, g);
	}

}
