package model.drawables;

import java.awt.Graphics;

/**
 * ReprÃ¤sentiert einen (2D-)Punkt
 * 
 * @author Nicolas Neubauer
 * 
 */
public class AntiAliasedLine extends DrawableObject {

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
//	public AntiAliasedLine(int x, int y, int x1, int y1) {
//		this.x1 = x;
//		this.y1 = y;
//		this.x2 = x1;
//		this.y2 = y1;
//
//	}

	public AntiAliasedLine(Point a, Point b) {//Setzte x,y aus zwei Punkten
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

	public AntiAliasedLine(AntiAliasedLine copy) {//Copy befehl
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
		//Bresenham Algorythmus zum zeichnen einer Linie, modifiziert mit einem AntiAliasing Verfahren
		int x, y, error, delta, schritt, dx, dy, inc_x, inc_y;
		
		
													//BRESENHAM ALGO			//ANTI ALIASING
		
		
		float bright = 0.0f;													//Anfangsintensität

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
			error = -Math.abs(dx); 						// Fehler bestimmen
			delta = 2*Math.abs(dy);                    // Delta bestimmen
			schritt = 2*error;                         // Schwelle bestimmen
			while(x != x2) {                           // Fuer jede x-Koordina
				setPixel(x,y,bright,g);                       // setze Pixel			//Geminderte Intensität
				x += inc_x;                              // naechste x-Koordinate
				bright = 0.0f;															//Volle Farbe
				error = error + delta;                   // Fehler aktualisieren
				if (error > 0) {                         // neue Spalte erreicht?
					bright=((float)error)/((float)delta);								//Geminderste Intensität berechnen
					setPixel(x,y,1.0f-bright,g);										//setzten dieser Intensität
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
				setPixel(x,y,bright,g);                       // setze Pixel			//Geminderte Intensität
				y += inc_y;									//Incementieren
				bright=0.0f;															//VOlle Farbintensität
				error = error + delta;                   // Fehler aktualisieren
				if (error > 0) {                         // neue Zeile erreicht?
					bright=((float)error)/((float)delta);								//Geminderste Intensität berechnen
					setPixel(x,y,1.0f-bright,g);										//setzten dieser Intensität
					x += inc_x;                            // x-Koord. aktualisieren
					error += schritt;                      // Fehler aktualisieren
				}
			}
		}
		setPixel(x2, y2,bright,g);                        // letztes Pixel hier setzen, //Geminderte Intensität
	}                                              // falls (x1==x2) & (y1==y2)
}
