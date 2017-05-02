package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool{
	private double x,y;
	private File file;
	private ClipImage ci;
	
	public ToolImage(File file){
		this.file = file;
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x = e.getX();
		y = e.getY();
		ci = new ClipImage(x, y, file);
		
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		Board br = i.getBoard();
		ci.move(x, y);
		br.addClip(ci);
		
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		Board br = i.getBoard();
		br.removeClip(ci);
		br.addClip(new ClipImage(x, y, file));
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		i.getBoard().draw(gc);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Filled Image tool";
	}

	@Override
	public void setCouleur(Color c) {
		// TODO Auto-generated method stub
		
	}

}
