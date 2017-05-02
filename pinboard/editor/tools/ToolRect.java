package pobj.pinboard.editor.tools;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool{

	private double x,y;
	private Clip cr = null;
	private Color c = Color.TRANSPARENT;
	private Color c_draw = Color.BROWN;
	
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
		cr = new ClipRect(x, y, e.getX(), e.getY(),c);
		br.addClip(cr);
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		Board br = i.getBoard();
		br.removeClip(cr);
		Clip cr1 = new ClipRect(cr.getLeft(),cr.getTop(),cr.getRight() ,cr.getBottom() ,c_draw);
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
		return "Filled rectangle tool";
	}

}
