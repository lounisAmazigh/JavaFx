package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolEllipse implements Tool{
	private double x,y;
	private Color c = Color.TRANSPARENT;
	private Color c_draw = Color.CORAL;
	private Clip cr = null;
	
	@Override
	public void setCouleur(Color c){
		c_draw = c;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		Board br = i.getBoard();
		br.removeClip(cr);
		cr = new ClipEllipse(x, y, e.getX(), e.getY(),c);
		br.addClip(cr);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		Board br = i.getBoard();
		br.removeClip(cr);
		Clip cr1 = new ClipEllipse(x, y, e.getX(), e.getY(),c_draw);
		br.addClip(cr1);
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		i.getBoard().draw(gc);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Filled ellipse tool";
	}

}
