package controller.listener;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import model.drawables.GestrichelteLine;
import model.drawables.Point;
import model.drawables.Line;
import controller.DrawableObjectProcessing;

/**
 * Ein Listener, der Points durch MouseEvents erzeugt und an ein Objekt, das
 * DrawableObjectProcessing implementiert übergibt.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class LineListener extends MouseInputAdapter {
	private Point other = null;
	private DrawableObjectProcessing delegate;
	
	public LineListener(DrawableObjectProcessing delegate) {	
		this.delegate = delegate;
	}

	public void clearLPoint(){
		other = null;
		delegate.clearTemporaryDrawableObject();
	}
	
	public void mouseMoved(MouseEvent e){
		if(other!=null){		//Nur sobald der erste Punkt gesetzt wurde
			Point x = new Point(e.getX(), e.getY());	//zweiten Punkt immer neu setzten
			GestrichelteLine l = new GestrichelteLine(other, x);	//Linie vorzeichnen
			delegate.setTemporaryDrawableObject(l);
		}		
	}
	
	
	
	/**
	 * Erzeuge einen neuen Point bei den Klick-Koordinaten und übergebe ihn an
	 * das Delegate.
	 */
	public void mouseClicked(MouseEvent e) {		//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		if(other==null){
			other = new Point(e.getX(), e.getY());
		}
		else{
			Point a = new Point(e.getX(), e.getY());
			Line l = new Line(other, a);
			other = null;
			delegate.clearTemporaryDrawableObject();
			delegate.processDrawableObject(l);
		}

	}

}
