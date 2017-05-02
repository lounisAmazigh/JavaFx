package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool{
	private double x,y;
	private Clip bordure;
	private double min_l=500440,min_t=5558000,max_r=0,max_b=0;
	
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		x = e.getX();
		y = e.getY();
		if (e.isShiftDown()){
			i.getSelection().toogleSelect(i.getBoard(), x, y);
			
			
		} else {
			i.getSelection().select(i.getBoard(), x, y);
			
		}
		for(Clip c : i.getSelection().getContents()){
			setDimention(c);
		}
		bordure = new ClipRect(min_l, min_t, max_r, max_b, Color.TRANSPARENT);
		i.getBoard().addClip(bordure);
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		for(Clip c : i.getSelection().getContents())
			c.move(e.getX()-x, e.getY()-y);
		
		bordure.move(e.getX()-x, e.getY()-y);
		x = e.getX();
		y = e.getY();
			
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getSelection().getContents().remove(bordure);
		i.getBoard().getContents().remove(bordure);
		setDimentionNull();
		
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getBoard().draw(gc);
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName() {
		return "Zone selected";
	}

	@Override
	public void setCouleur(Color c) {
		// TODO Auto-generated method stub
		
	}
	
	public void setDimention(Clip c){
		if(c.getLeft() < min_l){
			min_l = c.getLeft(); 
		}
		if(c.getTop() < min_t){
			min_t = c.getTop();
		}
		if(c.getRight() > max_r)
			max_r = c.getRight();
		
		if(c.getBottom() > max_b)
			max_b = c.getBottom();
		
	}
	public void setDimentionNull(){
		min_l=500440;
		min_t=5558000;
		max_r=0;
		max_b=0;
	}

}
