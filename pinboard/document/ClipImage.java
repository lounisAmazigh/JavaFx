package pobj.pinboard.document;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ClipImage implements Clip{
	private double left, top;
	private Image image;
	private File filename;
	
	
	public ClipImage(double left , double top , File filename){
		this.left = left;
		this.top = top; 
		this.filename = filename ;
		image = new Image("file://" + filename.getAbsolutePath());
	}
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.drawImage(image, left, top);
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
		return top + image.getHeight();
	}

	@Override
	public double getRight() {
		return left + image.getWidth();
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
	}

	@Override
	public void move(double x, double y) {
		left += x;
		top += y;
	}

	@Override
	public boolean isSelected(double x, double y) {
		return (left <= x) && (x <= getRight()) && (top <= y) && (y <= getBottom());
	}

	@Override
	public void setColor(Color c) {
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public Clip copy() {
		return new ClipImage(left, top, filename);
	}
}