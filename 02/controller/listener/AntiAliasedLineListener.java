package controller.listener;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import model.drawables.AntiAliasedLine;
import model.drawables.GestrichelteLine;
import model.drawables.Point;
import controller.DrawableObjectProcessing;

/**
 * Ein Listener, der Points durch MouseEvents erzeugt und an ein Objekt, das
 * DrawableObjectProcessing implementiert Ã¼bergibt.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class AntiAliasedLineListener extends MouseInputAdapter {
	private Point other = null;									//Erster Punkt, als extra Speicher
	private DrawableObjectProcessing delegate;
	
	public AntiAliasedLineListener(DrawableObjectProcessing delegate) {
		
		this.delegate = delegate;
	}

	public void clearLPoint(){		//Die Clear Methode, um bei einem Wechsel der ComboBox Fehler zu vermeiden
		other = null;		//Ersten Punkt zurrück setzen
		delegate.clearTemporaryDrawableObject();
	}
	
	public void mouseMoved(MouseEvent e){
		if(other!=null){		//Nur sobald der erste Punkt gesetzt wurde
			Point x = new Point(e.getX(), e.getY());	//zweiten Punkt immer neu setzten
			GestrichelteLine l = new GestrichelteLine(other, x);	//Gestrichelte Linie zeichnen
			delegate.setTemporaryDrawableObject(l);		//als temp objekt definieren
		}		
	}
	
	/**
	 * Erzeuge einen neuen Point bei den Klick-Koordinaten und Ã¼bergebe ihn an
	 * das Delegate.
	 */
	public void mouseClicked(MouseEvent e) {
		if(other==null){		//Falls der erste Punkt noch nicht gesetzt wurde
			other = new Point(e.getX(), e.getY());	//ersten Punkt setzen
		}
		else{					//Startpunkt ist festgelegt
			Point a = new Point(e.getX(), e.getY());	//zweiten Punkt setzten
			AntiAliasedLine l = new AntiAliasedLine(other, a);	//AALine setzen
			other = null;		//Ersten Punkt zurrück setzten
			delegate.clearTemporaryDrawableObject();	//Gestrichelte Linie löschen
			delegate.processDrawableObject(l);			//AALinie zeichnen
		}

	}

}
