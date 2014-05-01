package controller.listener;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

import model.drawables.GestricheltKreis;
import model.drawables.Kreis;
import model.drawables.Point;
import controller.DrawableObjectProcessing;

/**
 * Ein Listener, der Points durch MouseEvents erzeugt und an ein Objekt, das
 * DrawableObjectProcessing implementiert Ã¼bergibt.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class CircleListener extends MouseInputAdapter {
	private Point other = null;						//Erster Punkt, als extra Speicher
	private DrawableObjectProcessing delegate;
	
	public CircleListener(DrawableObjectProcessing delegate) {	
		this.delegate = delegate;
	}

	public void clearLPoint(){
		other = null;
		delegate.clearTemporaryDrawableObject();
	}
	
	public void mouseMoved(MouseEvent e){
		if(other!=null){		//Nur sobald der erste Punkt gesetzt wurde
			Point x = new Point(e.getX(), e.getY());	//zweiten Punkt immer neu setzten
			GestricheltKreis l = new GestricheltKreis(other, x);	//kreis vorzeichnen
			delegate.setTemporaryDrawableObject(l);		//temp objekt zeichnen
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
			Kreis l = new Kreis(other, a);		//kreis malen
			other = null;		//Ersten Punkt zurrück setzten
			delegate.clearTemporaryDrawableObject();	//Gestrichelte Linie löschen
			delegate.processDrawableObject(l);			//AALinie zeichnen
		}

	}

}
