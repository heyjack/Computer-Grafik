package model.drawables;

import java.awt.Graphics;

/**
 * ReprÃ¤sentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class GestrichelteLine extends DrawableObject {

	//Startpunkt
	public int x1;
	public int y1;
	
	//Endpunkt
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
	public GestrichelteLine(int x, int y, int x1, int y1) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x1;
		this.y2 = y1;
		
	}
	
	public GestrichelteLine(Point a, Point b) {
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

	public GestrichelteLine(GestrichelteLine copy) {
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
	public void paint(Graphics g) {
		//Bresenham Algorythmus
		
		int x, y, error, delta, schritt, dx, dy, inc_x, inc_y;
		int abstand = 4;														//abstand der Punkte
	    int draw = abstand;
		
														//BRESENHAM ALGO			//Gestrichelt
	    
	    
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
		      if(draw == abstand){
		    	  setPixel(x,y,g);					// setze Pixel
		    	  draw = 0;
		      }
		      else{
		    	  draw++;
		      }
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
		    	if(draw == abstand){													//Falls genug abstand, zeichne
			    	  setPixel(x,y,g);					// setze Pixel
			    	  draw = 0;															//Setze abstand zurrück
			      }
			      else{
			    	  draw++;															//Falls nicht genug abstand, erhöhe
			      }                     // setze Pixel
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
