package pobj.pinboard.editor;
import java.util.ArrayList;
import java.util.List;


import pobj.pinboard.document.Clip;
public final class Clipboard {
	private static Clipboard cb;
	private List<Clip> lc;
	private List<Clipboardlistener> lcl;
	
	private Clipboard(){
		lc = new ArrayList<>();
		lcl = new ArrayList<>();
	}
	
	public static Clipboard getInstance(){
		if(cb == null){
			cb = new Clipboard();
		}
		return cb;
	}

	public void copyToClipboard(List<Clip> clips){
		for(Clip c : clips){
			lc.add(c.copy());
		}
	}
	public List<Clip> copyFromClipboard(){
		List<Clip> l = new ArrayList<>();
		for(Clip c : lc){
			l.add(c.copy());
		}
		return l;
	}
	public void clear(){
		lc.clear();
	}
	
	public boolean isEmpty(){
		return lc.isEmpty();
	}
	
	public void addListener(Clipboardlistener ls){
		lcl.add(ls);
	}
	public void removeListener(Clipboardlistener ls){
		lcl.remove(ls);
	}
	
	public List<Clipboardlistener> getLcl(){
		return lcl;
	}
}
