package pobj.pinboard.editor;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.document.Clip;


public class Selection {
	private List<Clip> lc = new ArrayList<>();
	
	public void select(Board board , double x , double y){
		List<Clip> l = new ArrayList<>();
		lc.clear();
		l = board.getContents();
		
		for(Clip c : l){
			if(c.isSelected(x, y)){
				System.out.println("je suis la ");
				lc.add(c);
				break;
			}
		}
	}
	
	public void toogleSelect(Board board , double x , double y){
		List<Clip> l = new ArrayList<>();
		l = board.getContents();
		for(Clip c : l){
			
			System.out.println("c : left "+c.getLeft()+" right "+c.getRight()+ " top: "+c.getTop()+" btoom "+c.getBottom() );
			System.out.println("is Selected"+c.isSelected(x, y));
			if(c.isSelected(x, y)){
				System.out.println("is contains"+lc.contains(c));
				if(lc.contains(c)){
					lc.remove(c);
				}
				else{
					lc.add(c);
				}
			}
		}
		
	}
	public void clear(){
		lc.clear();
	}
	
	public List<Clip> getContents(){
		return lc;
	}
	
	public void drawFeedback(GraphicsContext gc){
		for(Clip c : lc){
			c.draw(gc);
		}
		
	}


}
