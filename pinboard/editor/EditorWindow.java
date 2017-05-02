package pobj.pinboard.editor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolSelection;
import pobj.pinboard.editor.Selection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.editor.Clipboard;

public class EditorWindow extends javafx.application.Application implements EditorInterface,Clipboardlistener{

	private Stage st;
	private Board board;
	private ClipImage ci;
	private Selection selection;
	private Label l;
	private MenuItem past;
	private Separator s;
	private Canvas canvas;
	private Button ellipse,img,select,box;
	private Button c1,c2,c3,c4;
	
	Tool tool = new ToolRect();	
	public EditorWindow(Stage stage){
		st = stage;
		board = new Board();
		selection = new Selection();
		l = new Label("Rectangle fill box");
		s = new Separator();
		canvas = new Canvas(600, 700);
		try {
			start(st);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
			
		st.setTitle("PinBoard");
				

		Clipboard.getInstance().addListener(this);
		setCanvas();
		
		/**
		 * ToolBar avec ses bouttons
		 */
		setBoutton(st);
		 setButtonCouleur(st);
		ToolBar tb = new ToolBar(box , ellipse , img , select);
		ToolBar tb2 = new ToolBar(c1,c2,c3,c4);
		
		/**
		 * MenuBar 
		 */
		 final Menu menu1 = new Menu("File");
		 MenuItem mi = new MenuItem("New");
		 MenuItem mc = new MenuItem("Close");
		 menu1.getItems().add(mi);
		 menu1.getItems().add(mc);
		 mi.setOnAction(e->{
				new EditorWindow(new Stage());
			
			
		});
		 
		 mc.setOnAction(e-> {
			Clipboard.getInstance().removeListener(this);
			st.close();
		});
		 
		 final Menu menu2 = new Menu("Edit");
		 MenuItem copy = new MenuItem("copy");
		 past = new MenuItem("past");
		 past.setDisable(true);
		 MenuItem delete = new MenuItem("delete");
		 
		 menu2.getItems().add(copy);
		 copy.setOnAction(e->{
				
			 Clipboard.getInstance().clear();
			 Clipboard.getInstance().copyToClipboard(selection.getContents());
			 for( Clipboardlistener c : Clipboard.getInstance().getLcl()){
					 c.clipboardChanged();
			 }
		 });
		 menu2.getItems().add(past);
		 past.setOnAction(e->{
			 if(!Clipboard.getInstance().isEmpty()){
				 List<Clip> list = new ArrayList<>();
				 list = Clipboard.getInstance().copyFromClipboard();
				 for(Clip c : list){
					 board.addClip(c);
				 }
					board.draw(canvas.getGraphicsContext2D());
			 } 
		 });
		 menu2.getItems().add(delete);
		 delete.setOnAction(e->{
			 Clipboard.getInstance().copyToClipboard(selection.getContents());
			 List<Clip> list = new ArrayList<>();
			 list = selection.getContents();
			 for(Clip c : list){
				 board.removeClip(c);
			 }
			 Clipboard.getInstance().clear();
			 for( Clipboardlistener c : Clipboard.getInstance().getLcl()){
				 c.clipboardChanged();
		 }
			 board.draw(canvas.getGraphicsContext2D());
			 
		 });
		 final Menu menu3 = new Menu("Tools");
		 
		 MenuBar menuBar = new MenuBar();
		 menuBar.getMenus().addAll(menu1, menu2, menu3);
		 
		
		 
		 
		 /**
		 * VBOX 
		 */
		VBox vbox = new VBox();
		vbox.getChildren().add(menuBar);
		vbox.getChildren().add(tb);
		vbox.getChildren().add(tb2);
		vbox.getChildren().add(canvas);
		vbox.getChildren().add(s);
		vbox.getChildren().add(l);
		st.setScene(new javafx.scene.Scene(vbox));
		board.draw(canvas.getGraphicsContext2D());
		st.show();
	}


	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}
	public Selection getSelection(){
		return selection;
	}
	
	

	@Override
	public void clipboardChanged() {
		// TODO Auto-generated method stub
		if(Clipboard.getInstance().isEmpty())
			past.setDisable(true);
		else
			past.setDisable(false);
	}
	
	public void setCanvas(){
		canvas.setOnMousePressed(e -> {
			tool.press(this, e);
			tool.drawFeedback(this, canvas.getGraphicsContext2D());
			
		});
		canvas.setOnMouseDragged((e) -> { tool.drag(this, e); tool.drawFeedback(this, canvas.getGraphicsContext2D());});
		canvas.setOnMouseReleased((e) -> { tool.release(this, e); tool.drawFeedback(this, canvas.getGraphicsContext2D()); l.setText(tool.getName());});
		
	}
	
	public void setBoutton(Stage stage){
		box = new Button("Box");
		box.setOnAction(e -> {tool = new ToolRect();});
	
	
		ellipse = new Button("Ellipse");
		ellipse.setOnAction(e -> { tool = new ToolEllipse();});
	
		img = new Button("Image");
		img.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Resource File");
			 File selectedFile = fileChooser.showOpenDialog(stage);
			 tool = new ToolImage(selectedFile);
			 
			 
		});
		select = new Button("Select");
		select.setOnAction(e -> {
			tool = new ToolSelection();
		});
	}
	public void setButtonCouleur(Stage stage){
	
		c1 = new Button ("blue", new Rectangle(20, 20, Paint.valueOf("blue")));
		c1.setOnAction(e->{
			tool.setCouleur(Color.BLUE);
			for(Clip c : selection.getContents()){
				c.setColor(Color.BLUE);
			}
		});
		c2 = new Button ("green", new Rectangle(20, 20, Paint.valueOf("green")));
		c2.setOnAction(e->{
			tool.setCouleur(Color.GREEN);
			for(Clip c : selection.getContents()){
				c.setColor(Color.GREEN);
			}
		});
	
		c3 = new Button ("yellow", new Rectangle(20, 20, Paint.valueOf("yellow")));
		c3.setOnAction(e->{
			tool.setCouleur(Color.YELLOW);
			for(Clip c : selection.getContents()){
				c.setColor(Color.YELLOW);
			}
		});
		
		c4 = new Button ("red", new Rectangle(20, 20, Paint.valueOf("red")));
		c4.setOnAction(e->{
			tool.setCouleur(Color.RED);
			for(Clip c : selection.getContents()){
				c.setColor(Color.RED);
			}
		});
	}
	

}
