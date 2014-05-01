package model.drawables;

import java.awt.Graphics;

/**
 * Repräsentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class Line extends DrawableObject {

	//Siehe GestrichelteLinie
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	

	/**
	 * Konstruktor, der ein Punktobjekt aus einem Koordinatenpaar errechnet
	 * 
	 * @param x
	 *            x-Koordinate des Punktes
	 * @param y
	 *            y-Koordinate des Punktes
	 */
	public Line(int x, int y, int x1, int y1) {//Siehe GestrichelteLinie
		this.x1 = x;
		this.y1 = y;
		this.x2 = x1;
		this.y2 = y1;
		
	}
	
	public Line(Point a, Point b) {//Siehe GestrichelteLinie
		this.x1 = a.x;
		this.y1 = a.y;
		this.x2 = b.x;
		this.y2 = b.y;
	}

	/**
	 * Copy-Konstruktor
	 * 
	 * @param copy
	 *            Orginal, das kopiert werden soll
	 */

	public Line(Line copy) {//Siehe GestrichelteLinie
		this.x1 = copy.x1;
		this.y1 = copy.y1;
		this.x2 = copy.x2;
		this.y2 = copy.y2;
	}

	/**
	 * Paint-Methode der Pointklasse. Zeichnet einen Pixel
	 * 
	 * @param g
	 *            der Graphikkontext, in den das Objekt gezeichnet werden soll
	 */
	public void paint(Graphics g) {//Siehe GestrichelteLinie
		int x, y, error, delta, schritt, dx, dy, inc_x, inc_y;
	    
		  x = x1;                                      // Koordinaten retten
		  y = y1;                        

		  dy = y2 - y1;                                // Hoehenzuwachs
		  dx = x2 - x1;                                // Schrittweite

		  if(dx > 0)                                   // Linie nach rechts?
		    inc_x = 1;                                 // x inkrementieren
		  else                                         // Linie nach links
		    inc_x = -1;                                // x dekrementieren

		  if(dy > 0)                                   // Linie nach unten?
		    inc_y = 1;                                 // y inkrementieren
		  else                                         // Linie nach oben
		    inc_y = -1;                                // y dekrementieren

		  if(Math.abs(dy) < Math.abs(dx)) {            // flach nach oben oder unten
		    error = -Math.abs(dx);                     // Fehler bestimmen
		    delta = 2*Math.abs(dy);                    // Delta bestimmen
		    schritt = 2*error;                         // Schwelle bestimmen
		    while(x != x2) {                           // Fuer jede x-Koordinate
		      setPixel(x,y,g);                       // setze Pixel
		      x += inc_x;                              // naechste x-Koordinate
		      error = error + delta;                   // Fehler aktualisieren
		      if (error > 0) {                         // neue Spalte erreicht?
		        y += inc_y;                            // y-Koord. aktualisieren
		        error += schritt;                      // Fehler aktualisieren
		      }
		    }
		  }
		  else {                                       // steil nach oben oder unten
		    error = -Math.abs(dy);                     // Fehler bestimmen
		    delta = 2*Math.abs(dx);                    // Delta bestimmen
		    schritt = 2*error;                         // Schwelle bestimmen
		    while(y != y2) {                           // fuer jede y-Koordinate
		      setPixel(x,y,g);                       // setze Pixel
		      y += inc_y;                              // naechste y-Koordinate
		      error = error + delta;                   // Fehler aktualisieren
		      if (error > 0) {                         // neue Zeile erreicht?
		        x += inc_x;                            // x-Koord. aktualisieren
		        error += schritt;                      // Fehler aktualisieren
		      }
		    }
		  }
		  setPixel(x2, y2,g);                        // letztes Pixel hier setzen, 
		}                                              // falls (x1==x2) & (y1==y2)
	}
