package controller.listener;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import model.drawables.GestricheltRectangle;
import model.drawables.Point;
import model.drawables.Rectangle;
import controller.DrawableObjectProcessing;

/**
 * Ein Listener, der Points durch MouseEvents erzeugt und an ein Objekt, das
 * DrawableObjectProcessing implementiert übergibt.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class RectangleListener extends MouseInputAdapter {
	private Point other = null;
	private DrawableObjectProcessing delegate;
	
	public RectangleListener(DrawableObjectProcessing delegate) {
		
		this.delegate = delegate;
	}

	public void clearLPoint(){//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		other = null;
		delegate.clearTemporaryDrawableObject();
	}
	
	public void mouseMoved(MouseEvent e){//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		if(other!=null){
			Point x = new Point(e.getX(), e.getY());
			GestricheltRectangle l = new GestricheltRectangle(other, x);//zeichne Gesticheltes Rechteck
			delegate.setTemporaryDrawableObject(l);
		}		
	}
	/**
	 * Erzeuge einen neuen Point bei den Klick-Koordinaten und übergebe ihn an
	 * das Delegate.
	 */
	public void mouseClicked(MouseEvent e) {//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		if(other==null){
			other = new Point(e.getX(), e.getY());
		}
		else{
			Point a = new Point(e.getX(), e.getY());
			Rectangle r = new Rectangle(other, a);
			other = null;
			delegate.clearTemporaryDrawableObject();
			delegate.processDrawableObject(r);
		}

	}

}
