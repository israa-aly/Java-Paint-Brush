import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class Paint extends Applet{
	int x1;
	int y1;
	int x2;
	int y2;
	int w,h;
	int x,y;
	char shape=' ';
	boolean solid =false;
	boolean flagg=false;
	boolean isClear =false;
	boolean undoo=false;
	Color color= Color.BLACK;
	
	Button rect;
	Button oval;
	Button line;
	Button eraser;
	Button pencile;
	Button green;
	Button red;
	Button blue;
	Button clear;
	Button undo;
	Checkbox solid2;
	ArrayList<Shape> list = new ArrayList<>();
	ArrayList<Shape> draft = new ArrayList<>();
	public void init(){
		rect =new Button("Rectangle");
		oval =new Button("Oval");
		line =new Button("Line");
		eraser =new Button("Eraser");
		pencile =new Button("Pencil");
		red =new Button("Red");
		green =new Button("Green");
		blue =new Button("Blue");
		solid2= new Checkbox("Solid");
		clear =new Button("Clear All");
		undo =new Button("Undo");
		addMouseListener(new Mouse());
		addMouseMotionListener(new Mouse());
		rect.addActionListener(new RectListener());
		add(rect);
		oval.addActionListener(new OvalListener());
		add(oval);
		line.addActionListener(new LineListener());
		add(line);
		eraser.addActionListener(new EraserListener());
		add(eraser);
		pencile.addActionListener(new PencileListener());
		add(pencile);
		red.addActionListener(new RedListener());
		add(red);
		green.addActionListener(new GreenListener());
		add(green);
		blue.addActionListener(new BlueListener());
		add(blue);
		solid2.addItemListener(new SolidListener());
		add(solid2);
		clear.addActionListener(new ClearListener());
		add(clear);
		undo.addActionListener(new UndoListener());
		add(undo);
		
		
	}
	public void paint(Graphics g){
		for(int i=0; i<list.size();i++){
			switch(shape){
				case 'r':
				list.get(i).draw(g);
				break;
				case 'o':
				list.get(i).draw(g);
				break;
				case 'l':
				list.get(i).draw(g);
				break;
				case 'e':
				list.get(i).draw(g);
				break;
				case 'p':
				list.get(i).draw(g);
				break;
				
			}
		}
		
		if(flagg==false){
			switch(shape){
				case 'r':
				
				if(x2<x1 && y2<y1){
					x=x2;
					y=y2;
					w = x1-x2;
					h=y1-y2;
				}
				else if(x2>x1 && y2<y1){
					x=x1;
					y=y2;
					w=x2-x1;
					h=y1-y2;
				}
				else if(x2<x1 && y2>y1){
					x=x2;
					y=y1;
					w=x1-x2;
					h=y2-y1;
					
				}
				else{
					x=x1;
					y=y1;
					w=x2-x1;
					h=y2-y1;
				}
				Rectangle r =new Rectangle(x,y,w,h,color,solid);
				r.draw( g );
				break;
				case 'o':
				if(x2<x1 && y2<y1){
					x=x2;
					y=y2;
					w = x1-x2;
					h=y1-y2;
				}
				else if(x2>x1 && y2<y1){
					x=x1;
					y=y2;
					w=x2-x1;
					h=y1-y2;
				}
				else if(x2<x1 && y2>y1){
					x=x2;
					y=y1;
					w=x1-x2;
					h=y2-y1;
					
				}
				else{
					x=x1;
					y=y1;
					w=x2-x1;
					h=y2-y1;
				}
				
				Oval o =new Oval(x,y,w,h,color,solid);
				o.draw( g );
				break;
				case 'l':
				Linee l =new Linee(x1,y1,x2,y2,color,solid);
				l.draw( g );
				break;
				case 'e':
				Eraser e =new Eraser(x1,y1);
				e.draw( g );
				break;
				case 'p':
				Pencile p =new Pencile(x1,y1,color);
				p.draw( g );
				break;
			}
		}
		
	}
	
	
	class Mouse implements MouseListener,MouseMotionListener{
		public void mousePressed(MouseEvent me){
			x1= me.getX();
			y1= me.getY();
			x2=x1;
			y2=y1;
			
			
		}
		
		public void mouseReleased(MouseEvent me){
			
			if (shape=='l' || shape=='e' || shape=='p'){
			x2= me.getX();
			y2= me.getY();
			}
			else if(x2<x1 && y2<y1){
					x=x2;
					y=y2;
					w = x1-x2;
					h=y1-y2;
				}
				else if(x2>x1 && y2<y1){
					x=x1;
					y=y2;
					w=x2-x1;
					h=y1-y2;
				}
				else if(x2<x1 && y2>y1){
					x=x2;
					y=y1;
					w=x1-x2;
					h=y2-y1;
					
				}
				else{
					x=x1;
					y=y1;
					w=x2-x1;
					h=y2-y1;
				}
			
			switch(shape){
				case 'r':
				list.add(new Rectangle  (x,y,w,h,color,solid));
				break;
				case 'o':
				list.add(new Oval  (x,y,w,h,color,solid));
				break;
				case 'l':
				list.add(new Linee  (x1,y1,x2,y2,color,solid));
				break;
				
			}
			
			repaint() ;
		}
		public void mouseDragged (MouseEvent e){
			flagg=false;
			if (shape=='l'){
			x2= e.getX();
			y2= e.getY();
			}
			else if(shape=='e'){
					x1 = e.getX();
					y1 = e.getY();
					list.add(new Eraser (x1,y1));
					repaint() ;
				}
				else if(shape=='p'){
					x1 = e.getX();
					y1 = e.getY();
					list.add(new Pencile (x1,y1,color));
					repaint() ;
				}
				else{
				x2= (e.getX());
				y2= (e.getY());
				}
			repaint() ;
		}
		public void mouseClicked (MouseEvent e){};
		public void mouseEntered (MouseEvent e){};
		public void mouseExited (MouseEvent e){};
		public void mouseMoved(MouseEvent e){};
			
	}
	class RectListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			shape='r';	
		}
	}
	class OvalListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			shape='o';	
		}
	}
	class LineListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			shape='l';	
		}
	}
	class EraserListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			shape='e';	
		}
	}
	class PencileListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			shape='p';	
		}
	}
	class RedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			color = Color.RED;
			
		}
	}
	class GreenListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			color = Color.GREEN;
			
		}
	}
	class BlueListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			color = Color.BLUE;
			
		}
	}
	class SolidListener implements ItemListener{
		public void itemStateChanged(ItemEvent e) {
			if(solid)
				solid=false;
			else
				solid=true;
		}
	}
	class ClearListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			shape=' ' ;
			for(int i=0;i<list.size();i++){
				draft.add(list.get(i));
			}
			list.clear();
			repaint();
			
			
		}
	}
	class UndoListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			flagg=true;
			if (list.size() > 0 ){
				int last = list.size()-1;
				list.remove(last);
				repaint();
				
				
			}	
		}
	}
}
abstract  class Shape{
	private int x1 =0;
	private int y1= 0;
	private int x2;
	private int y2;
	private Color color;
	private boolean solid ;
	public Shape(int x1,int y1,int x2,int y2, Color color,boolean solid){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
		this.color=color;
		this.solid=solid;
	}
	public Shape(int x1,int y1){
		this.x1=x1;
		this.y1=y1;
		
	}
	public Shape(int x1,int y1,Color color){
		this.x1=x1;
		this.y1=y1;
		this.color=color;
		
	}
	public int getX1(){return x1;}
	public int getY1(){return y1;}
	public int getX2(){return x2;}
	public int getY2(){return y2;}
	public boolean getSolid(){return solid;}
	public Color getColor(){return color;}
	abstract void draw(Graphics g);
	
}
 class Rectangle extends Shape{
	public Rectangle (int x1,int y1,int x2,int y2,Color color,boolean solid){
		super(x1,y1,x2,y2,color,solid);
	}
	public void draw(Graphics g){
			if (super.getSolid()){
			g.setColor(getColor());
			g.fillRect(getX1(),getY1(),getX2(),getY2());
			g.drawRect(getX1(),getY1(),getX2(),getY2());
			}
			else{
				g.setColor(getColor());
				g.drawRect(getX1(),getY1(),getX2(),getY2());
			}
		}
		
}
 class Oval extends Shape{
	public Oval (int x1,int y1,int x2,int y2,Color color,boolean solid){
		super(x1,y1,x2,y2,color,solid);
	}
	public void draw(Graphics g){
			if (super.getSolid()){
			g.setColor(getColor());
			g.fillOval(getX1(),getY1(),getX2(),getY2());
			g.drawOval(getX1(),getY1(),getX2(),getY2());
			}
			else{
				g.setColor(getColor());
				
				g.drawOval(getX1(),getY1(),getX2(),getY2());
				
			}
			
		}
		
}
class Linee extends Shape{
	public Linee (int x1,int y1,int x2,int y2,Color color,boolean solid){
		super(x1,y1,x2,y2,color,solid);
	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.drawLine(getX1(),getY1(),getX2(),getY2());		
	}
			
		
		
}
class Eraser extends Shape{
	public Eraser (int x1,int y1){
		super(x1,y1);
	}
	public void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect((getX1()-5),(getY1()-5),10,10);
		g.drawRect((getX1()-5),(getY1()-5),10,10);		
	}		
}
class Pencile extends Shape{
	public Pencile (int x1,int y1,Color color){
		super(x1,y1,color);
	}
	public void draw(Graphics g){
		g.setColor(getColor());
		g.fillOval((getX1()-5),(getY1()-5),5,5);
		g.drawOval((getX1()-5),(getY1()-5),5,5);		
	}		
}

