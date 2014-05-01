package controller.listener;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

import model.drawables.GestrichelteLine;
import model.drawables.Point;
import model.drawables.Line;
import controller.DrawableObjectProcessing;

/**
 * Ein Listener, der Points durch MouseEvents erzeugt und an ein Objekt, das
 * DrawableObjectProcessing implementiert Ã¼bergibt.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class PolygonListener extends MouseInputAdapter {//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
	private Point other = null;
	private Point start = null;		//Startpunkt init.
	private int abweichung = 5;
	private DrawableObjectProcessing delegate;
	
	public PolygonListener(DrawableObjectProcessing delegate) {
		
		this.delegate = delegate;
	}

	public void clearLPoint(){//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		other = null;
		delegate.clearTemporaryDrawableObject();
	}
	
	public void mouseMoved(MouseEvent e){//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		if(other!=null){
			Point x = new Point(e.getX(), e.getY());
			GestrichelteLine l = new GestrichelteLine(other, x);
			delegate.setTemporaryDrawableObject(l);
		}		
	}
	
	
	
	/**
	 * Erzeuge einen neuen Point bei den Klick-Koordinaten und Ã¼bergebe ihn an
	 * das Delegate.
	 */
	public void mouseClicked(MouseEvent e) {//Siehe Dokumentation von AntiAliasedLineListener, oder CircleListener
		if(other==null){
			other = new Point(e.getX(), e.getY());
			start = new Point(e.getX(), e.getY());//Speicher den Startpunkt
		}
		else{
			Point a = new Point(e.getX(), e.getY());
			Line l = new Line(other, a);
			if(a.x>=(start.x-abweichung) && a.x<=(a.x+abweichung)){		//Sofern ein nächste Punkt in der 5*5 Kästchen nähe sich befinded
				if(a.y>=(start.y-abweichung) && a.y<=(start.y+abweichung)){	//in x als auch in y richtung
					l = new Line(other, start);			//Zeichne stattdessen die Linie zum Startpunkt zurrück
				}
			}
			delegate.clearTemporaryDrawableObject();
			delegate.processDrawableObject(l);		//Zeichne die Linie
			other = a;								//Fange neue Linie ab "a" an
			if(a.x>=(start.x-abweichung) && a.x<=(a.x+abweichung)){		//Waren wir aber in Startpunkt nähe
				if(a.y>=(start.y-abweichung) && a.y<=(start.y+abweichung)){
					other = null;					//So beende das Polygon
				}
			}
		}

	}

}
