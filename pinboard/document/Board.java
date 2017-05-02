package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board {
	
	List<Clip> ls;
	public Board(){
		ls = new ArrayList<>();
	}
	
	public List<Clip> getContents(){
		return ls;
	}
	
	public void addClip(Clip clip){
		ls.add(clip);
	}
	
	public void addClip(List<Clip> clip){
		for(Clip c : clip){
			addClip(c);
		}
	}
	
	public void removeClip(Clip clip){
		ls.remove(clip);
	}
	
	public void removeClip(List<Clip> clips){
		for(Clip c : clips){
			removeClip(c);
		}
	}
	
	public void draw(GraphicsContext gc){
		gc.setFill(Color.WHITE);
		gc.fillRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());
		for(Clip c : ls){
			c.draw(gc);
		}
	}
	

}
