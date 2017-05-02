package pobj.pinboard.document;

import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip {
	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public AbstractClip (double left, double top, double right, double bottom,
			Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}
	
	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return left;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return bottom;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		this.left =left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}

	@Override
	public void move(double x, double y) {
		left += x;
		right += x;
		top += y;
		bottom +=y;
	}
	

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		color =c ;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return (left <= x) && (x <= right) && (top <= y) && (y <= bottom);
	}


}
