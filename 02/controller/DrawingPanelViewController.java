package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.MouseInputListener;

import controller.listener.CircleListener;
import controller.listener.PointListener;
import controller.listener.LineListener;
import controller.listener.PolygonListener;
import controller.listener.RectangleListener;
import controller.listener.AntiAliasedLineListener;
import model.drawables.DrawableObject;
import view.DrawingPanelView;

/**
 * Der Controller, der eine DrawingPanelView verwaltet und das dazugehörige
 * Model in Form einer Liste von DrawableObjects. Das Model des Controllers ist
 * hart mit dem der View verbunden. Änderungen werden also direkt in der View
 * passieren. Ggf. muss die View aufgefordert werden sich neu zu zeichnen.
 * 
 * @author Nicolas Neubauer
 * 
 */
public class DrawingPanelViewController implements DrawableObjectProcessing {

	// View
	private DrawingPanelView drawingPanelView;

	// Model
	private LinkedList<DrawableObject> drawableObjectsModel;
	private DrawableObject temporaryObject;

	// Listener für die einzelnen grafischen Objekte
	private PointListener pointListener;			//Punkt
	private LineListener lineListener;				//Linie
	private RectangleListener rectangleListener;	//Rechteck
	private AntiAliasedLineListener aalListener;	//AALine
	private PolygonListener polygonListener;		//Polygon
	private CircleListener circleListener;			//Kreis
	
	
	/**
	 * Der Konstruktor initialisiert die View und legt die Listener an.
	 */
	public DrawingPanelViewController() {

		drawableObjectsModel = new LinkedList<DrawableObject>();
		drawingPanelView = new DrawingPanelView(640, 480, drawableObjectsModel);

		// das UI anpassen
		drawingPanelView.getButton().setText("Clear");

		// welche Objekte sollen gezeichnet werden können?
		String[] objectArray = { "Punkt", "Linie", "Anti-Aliased Linie",
				"Rechteck", "Polygon", "Kreis" };
		drawingPanelView.getComboBox().setModel(
				new DefaultComboBoxModel(objectArray));

		// Listener für die einzelnen Grafischen Objekte anlegen.
		pointListener = new PointListener(this);
		lineListener = new LineListener(this);
		rectangleListener = new RectangleListener(this);
		aalListener = new AntiAliasedLineListener(this);
		polygonListener = new PolygonListener(this);
		circleListener = new CircleListener(this);
		
		// Event-Listener fuer Button
		ActionListener a = new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				// Das Model leeren und die View neu zeichnen
				drawableObjectsModel.clear();												//Nur bei Clear button alles l�schen
				clearAll();																	//Und die other Objekte neu setzten
				drawingPanelView.getDrawingPanel().repaint();
			}

		};

		// ChangeListener um festzustellen, was gezeichnet werden soll
		ActionListener c = new ActionListener() {

			/**
			 * Hängt den passenden Listener an die Zeichenfläche
			 */
			public void actionPerformed(ActionEvent e) {
				// Hänge das aktuell gewählte Objekt als Listener an die
				// View

				JComboBox cb = (JComboBox) e.getSource();
					clearAll();																//Und die other Objekte neu setzten
				if (cb.getSelectedItem().equals("Punkt")){
					changeMouseInputListenerTo(pointListener);
				}
				if (cb.getSelectedItem().equals("Linie")){
					changeMouseInputListenerTo(lineListener);
				}
				if (cb.getSelectedItem().equals("Rechteck")){
					changeMouseInputListenerTo(rectangleListener);
				}
				if (cb.getSelectedItem().equals("Anti-Aliased Linie")){
					changeMouseInputListenerTo(aalListener);
				}
				if (cb.getSelectedItem().equals("Polygon")){
					changeMouseInputListenerTo(polygonListener);
				}
				if (cb.getSelectedItem().equals("Kreis")){
					changeMouseInputListenerTo(circleListener);
				}
				
			}

			/**
			 * Convenience-Methode zum Umsetzen des aktuellen MouseListeners an
			 * der Zeichenfläche der verwalteten View
			 * 
			 * @param newListener
			 *            der zu setzende Listener, alle anderen werden
			 *            ausgehängt
			 */
			private void changeMouseInputListenerTo(
					MouseInputListener newListener) {

				// InputListeners
				MouseListener[] currentInputListeners = drawingPanelView
						.getDrawingPanel().getMouseListeners();

				for (MouseListener curListener : currentInputListeners)
					drawingPanelView.getDrawingPanel().removeMouseListener(
							curListener);

				// MotionListeners
				MouseMotionListener[] currentMotionListners = drawingPanelView
						.getDrawingPanel().getMouseMotionListeners();
				for (MouseMotionListener curListener : currentMotionListners)
					drawingPanelView.getDrawingPanel()
							.removeMouseMotionListener(curListener);

				drawingPanelView.getDrawingPanel()
						.addMouseListener(newListener);
				drawingPanelView.getDrawingPanel().addMouseMotionListener(
						newListener);
			}

		};

		// Anhängen der Listener
		drawingPanelView.getButton().addActionListener(a);
		drawingPanelView.getComboBox().addActionListener(c);

		// Event einmal auslösen, damit der korrekte Listener angehängt wird
		drawingPanelView.getComboBox().setSelectedItem("Punkt");

	}

	/**
	 * Gibt die verwaltete View zurück
	 * 
	 * @return die View
	 */
	public DrawingPanelView getView() {
		return drawingPanelView;
	}

	/**
	 * Fügt das DrawableObject in das Model ein und fordert die View zum
	 * Neuzeichnen auf.
	 */
	public void processDrawableObject(DrawableObject drawableObject) {

		drawableObjectsModel.add(drawableObject);
		drawingPanelView.getDrawingPanel().repaint();

	}

	/**
	 * Löscht das temporäre DrawableObject aus dem Model und fordert die View
	 * zum Neuzeichnen auf.
	 */
	public void clearTemporaryDrawableObject() {

		drawableObjectsModel.remove(temporaryObject);
		drawingPanelView.getDrawingPanel().repaint();
		temporaryObject = null;

	}

	/**
	 * Fügt ein temporäres Objekt in das Model ein und fordert die View zum
	 * Neuzeichnen auf. Das Objekt kann mit clearTemporaryDrawableObject wieder
	 * aus dem Model entfernt werden.
	 */
	public void setTemporaryDrawableObject(DrawableObject drawableObject) {

		// Erst löschen
		if (temporaryObject != null)
			this.clearTemporaryDrawableObject();

		temporaryObject = drawableObject;
		this.processDrawableObject(temporaryObject);

	}
	
	public void clearAll(){
		
		lineListener.clearLPoint();
		rectangleListener.clearLPoint();
		aalListener.clearLPoint();
		polygonListener.clearLPoint();
		circleListener.clearLPoint();
	}

}
