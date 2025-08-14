package brickBreaking;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.json.simple.JSONObject;

public class GameWindow extends JFrame
{
	JPanel p1; JFrame f=this; JButton pdal;int x1=635,y1=461,jmp=10,i,sp=22;
	ImageIcon pi;JButton brik[];int n=1;Rectangle bound[];Timer t1;
   public GameWindow()
   {
	   super("GAME STARTED");
	  
//	   setResizable(false);
	   setSize(Toolkit.getDefaultToolkit().getScreenSize());
	   
	  setDefaultCloseOperation(EXIT_ON_CLOSE);
	   p1=new JPanel() {
		   @Override
		   public void paintComponent(Graphics g)
		   {
			  g.drawImage(BrickImageBox.GAME_WINDOW, 0, 0, getWidth(), getHeight(), this);
			  g.setColor(Color.white);
			  g.fillOval(x1, y1, 20, 20);
		   }
	   }; 
	  pi=new ImageIcon(BrickImageBox.PADEL);
      setContentPane(p1);
      pdal=new JButton(pi);
      pdal.setBackground(Color.red);
      pdal.setPreferredSize(new Dimension(151,27));
      pdal.setBounds(531, 672, 151, 27);
      add(pdal);
      brik=new JButton[18];
 	 bound=new Rectangle[18];
 	 int lx=0,ly=0,ly1=0,clr=1;
 	 for(i=0;i<=17;i++)
 	 {
 		 brik[i]=new JButton();
 		 bound[i]=new Rectangle(lx+185,ly1+122,100,54);
 		 lx=lx+180;
 		 ly+=1;
 		 if(clr==1)
 		 {
 			 brik[i].setBackground(Color.green);
 		 }
 		 else if(clr==2)
 		 {
 			 brik[i].setBackground(Color.orange);
 		 }
 		 else
 		 {
 			 brik[i].setBackground(Color.BLUE);
 		 }
 		 if(ly==6)
 		 {
 			 ly=0;
 			 ly1=ly1+70;
 			 lx=0;
 			 clr+=1;
 		 }
 		 brik[i].setBounds(bound[i]);
 		 add(brik[i]);
 	 }
      addMouseListener(new MouseAdapter() {
//    	  @Override
//    	  public void mouseClicked (MouseEvent me)
//    	  {
//    		  System.out.println(me.getX()+","+me.getY());
//    	  }
    	  @Override
    	  public void mouseEntered (MouseEvent me)
    	  {
    		  if(me.getX()<=(f.getWidth()-151))
    		  pdal.setLocation(me.getX(), 672);
    		  else
    	     pdal.setLocation(f.getWidth()-151, 672);  
    	  }
      });
      addMouseMotionListener(new MouseMotionAdapter()
      {
    	  @Override
    	  public void mouseMoved(MouseEvent me)
    	  {
    		  if(me.getX()<=(f.getWidth()-151))
        		  pdal.setLocation(me.getX(), 672);
    		  else if(me.getX()<151)
    			  pdal.setLocation(0, 672);
        		  else
        	     pdal.setLocation(f.getWidth()-151, 672);
    	  }
      });
      t1=new Timer(70, new ActionListener() 
      {
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int px=x1,py=y1;
			if(n==1)
			{
				x1+=sp;	
				y1-=sp;
			}
			else if(n==2)
			{
				y1+=sp;
				x1+=sp;
			}
			else if(n==3)
			{
				x1-=sp;
				y1-=sp;
			}
			else if(n==4)
			{
			y1+=sp;
			x1-=sp;
			}
			else if(n==5)
			{
				y1-=sp;
			}
			else if(n==6)
			{
				y1+=sp;
			}
			else if(n==7)
			{
				x1+=sp;
			}
			else if(n==8)
			{
				x1-=sp;
			}
				if(x1<0 && y1<0)
				{
					x1=0;
					y1=0;
					n=2;
					repaint();
				}
				else if(y1<0 && x1>(f.getWidth()-1))
				{
					y1=0;
					x1=f.getWidth()-1;
					n=4;
					repaint();
				}
				else if(x1<0 &&  y1>(f.getHeight()-1))
				{
					x1=0;
					y1=f.getHeight()-1;
					n=1;
					repaint();
				}
				else if(x1>(f.getWidth()-1) && y1>(f.getHeight()-1))
				{
					x1=f.getWidth()-1;
					y1=f.getHeight()-1;
					n=3;
					repaint();
				}
				if((x1==0 && y1!=0) || x1<0 )
				{
					if(x1<0)
					{
					  x1=0;
					  repaint();
					}
					if(px>x1 && py>y1)
					{
						n=1;
					}
					else if(px>x1 && py<y1)
					{
						n=2;
					}
					else if(px>x1 && py==y1)
					{
						n=7;
					}
				}
				else if((x1!=0 &&y1==0) || y1<0)
				{
					if(y1<0)
					{
						y1=0;
						repaint();
					}
					if(px<x1 && py>y1)
					{
						n=2;
					}
					else if(x1==px && py>y1)
					{
						n=6;
					}
					else if(px>x1 && py>y1)
					{
						n=4;
					}
				}
				else if((x1==(f.getWidth()-1) && (y1>=0 && y1<=(f.getHeight()-1))) || x1>(f.getWidth()-1))
				{
					if(x1>(f.getWidth()-1))
					{
						x1=(f.getWidth()-1);
						repaint();
					}
					if(px<x1 && py<y1)
					{
						n=4;
					}
					else if(px<x1 && py>y1)
					{
						n=3;
					}
					else if(py==y1 && px<x1)
					{
						n=8;
					}
				}
				else if((y1==(f.getHeight()-1) && (x1>=0 && x1<=(f.getWidth()-1))) || y1>(f.getHeight()-1))
				{
					stop();
					dispose();
				}
				Point pdll=pdal.getLocation();
				if(((px>=pdll.x && px<=(pdll.x+(pdal.getWidth()-1))) && py<pdll.y) && y1>pdll.y)
				{
					 if(px>x1 && py<y1)
					{
						 int mm=0;
						for(;mm!=1;)
						{
							y1-=1;
							x1+=1;
							if(y1==pdll.y)
							{
								mm=1;
								break;
							}
						}
					}
					 else if(x1>px && y1>py)
					 {
						 int mm=0;
							for(;mm!=1;)
							{
								y1-=1;
								x1-=1;
								if(y1==pdll.y)
								{
									mm=1;
									break;
								}
							} 
					 }
					 else if(x1==px && py<y1)
					 {
						 int mm=0;
							for(;mm!=1;)
							{
								y1-=1;
								if(y1==pdll.y)
								{
									mm=1;
									break;
								}
							}
					 }
				}
				else if((px<pdll.x && py<pdll.y) && (x1>pdll.x && y1>pdll.y))
				{
					x1=pdll.x;
					y1=pdll.y;
					n=3;
					repaint();
				}
				else if((py<pdll.y && px>pdll.x+(pdal.getWidth()-1)) && (y1>pdll.y && x1<pdll.x+(pdal.getWidth()-1)))
				{
					y1=pdll.y;
					x1=pdll.x+(pdal.getWidth()-1);
					n=1;
					repaint();
				}
				else if(((py>=pdll.y && py<(pdll.y+(pdal.getHeight()-1))) && (px<pdll.x || px>pdll.x+(pdal.getWidth()-1))) && (x1>=pdll.x && x1<=pdll.x+(pdal.getWidth()-1)))
				{
					if(x1>px && y1>py)
					{
						 int mm=x1-pdll.x;
						 x1=pdll.x;
						 y1-=mm;
						 repaint();
							n=4;
					}
					else if(x1>px && py>y1)
					{
					
						int mm=0;
						for(;mm!=1;)
						{
							y1+=1;
							x1-=1;
							if(y1==bound[i].y || x1==bound[i].x)
							{
								mm=1;
								break;
							}
						}
						repaint();
						n=3;
					}
					else if(px>x1 && py>y1)
					{
						int mm=0;
						for(;mm!=1;)
						{
							y1+=1;
							x1+=1;
							if(y1==bound[i].y || x1==bound[i].x)
							{
								mm=1;
								break;
							}
						}
						repaint();
						n=1;
					}
					else if(px>x1 && py<y1)
					{
						 int mm=0;
							for(;mm!=1;)
							{
								y1-=1;
								x1+=1;
								if(y1==bound[i].y || x1==bound[i].x)
								{
									mm=1;
									break;
								}
							}
							repaint();
							n=2;
					}
					else if(px<x1 && y1==py)
					{
						int mm=0;
						for(;mm!=1;)
						{
							
							x1-=1;
							if(x1==pdll.x)
							{
								mm=1;
								break;
							}
						}
						repaint();	
					}
					else if(py==y1 && px>x1)
					{
						int mm=0;
						for(;mm!=1;)
						{
							
							x1+=1;
							if(x1==pdll.x)
							{
								mm=1;
								break;
							}
						}
						repaint();
					}
				}
				if(y1==pdll.y && (x1>=pdll.x && x1<=pdll.x+(pdal.getWidth()-1)))
				{
					if(px<x1 && py<y1)
					{
						n=1;
					}
					else if(x1==px && py<y1)
					{
						n=5;
					}
					else if(py<y1 && px>x1)
					{
						n=3;
					}
				}
				else if(x1==pdll.x && (y1>=pdll.y && y1<=pdll.y+(pdal.getHeight()-1)))
				{
					if(px<x1 && py<y1)
					{
						n=4;
					}
					else if(px<x1 && py>y1)
					{
						n=3;
					}
					else if(py==y1 && px<x1)
					{
						n=8;
					}
				}
				else if(x1==pdll.x+(pdal.getWidth()-1) && (y1>=pdll.y && y1<=pdll.y+(pdal.getHeight()-1)))
				{
					if(px>x1 && py>y1)
					{
						n=1;
					}
					else if(px>x1 && py<y1)
					{
						n=2;
					}
					else if(px>x1 && py==y1)
					{
						n=7;
					}
				}
				for(i=0;i<=17;i++)
				{
					if(bound[i].x==-1)
						continue;
					if(((px>=bound[i].x && px<=(bound[i].x+(bound[i].width-1))) && py<bound[i].y) && y1>bound[i].y && (n==4 || n==2 || n==6))
					{
						 if(px>x1 && py<y1)
						{
							 int mm=0;
							for(;mm!=1;)
							{
								y1-=1;
								x1+=1;
								if(y1==bound[i].y)
								{
									mm=1;
									break;
								}
							}
						}
						 else if(x1>px && y1>py)
						 {
							 int mm=0;
								for(;mm!=1;)
								{
									y1-=1;
									x1-=1;
									if(y1==bound[i].y)
									{
										mm=1;
										break;
									}
								} 
						 }
						 else if(x1==px && py<y1)
						 {
							 int mm=0;
								for(;mm!=1;)
								{
									y1-=1;
									if(y1==bound[i].y)
									{
										mm=1;
										break;
									}
								}
						 }
					}
					else if((px<bound[i].x && py<bound[i].y) && (x1>bound[i].x && y1>bound[i].y))
					{
						x1=bound[i].x;
						y1=bound[i].y;
						repaint();
		                n=3;
	                	f.remove(brik[i]);
	                	bound[i].x=-1;
		                f.revalidate();
					}
					else if((py<bound[i].y && px>bound[i].x+(bound[i].width-1)) && (y1>bound[i].y && x1<bound[i].x+(bound[i].width-1)))
					{
						y1=bound[i].y;
						x1=bound[i].x+(bound[i].width-1);
						repaint();
		                n=1;
	                	f.remove(brik[i]);
	                	bound[i].x=-1;
		                f.revalidate();
					}
					else if((px<bound[i].x && py> bound[i].y+(bound[i].height-1)) && (x1>bound[i].x &&  y1< bound[i].y+(bound[i].height-1)))
					{
						x1=bound[i].x;
						y1=bound[i].y+(bound[i].height-1);
						n=4;
	                	f.remove(brik[i]);
	                	bound[i].x=-1;
		                f.revalidate();
					}
					else if((px>bound[i].x+(bound[i].width-1) && py> bound[i].y+(bound[i].height-1) )&& (x1<bound[i].x+(bound[i].width-1) && y1< bound[i].y+(bound[i].height-1)))
					{
						x1=bound[i].x+(bound[i].width-1);
						y1= bound[i].y+(bound[i].height-1);
						n=2;
	                	f.remove(brik[i]);
	                	bound[i].x=-1;
		                f.revalidate();
						
					}
					else if(((py>=bound[i].y && py<(bound[i].y+(bound[i].height-1))) && (px<bound[i].x || px>bound[i].x+(bound[i].width-1))) && (x1>=bound[i].x && x1<=bound[i].x+(bound[i].width-1)))
					{
						if(x1>px && y1>py)
						{
							 int mm=0;
								for(;mm!=1;)
								{
									y1-=1;
									x1-=1;
									if(y1==bound[i].y || x1==bound[i].x)
									{
										mm=1;
										break;
									}
								}
								repaint();
				                n=4;
			                	f.remove(brik[i]);
			                	bound[i].x=-1;
				                f.revalidate();
				             
						}
						else if(x1>px && py>y1)
						{
							int mm= bound[i].x-x1;
							y1+=mm;
							x1=bound[i].x;
							repaint();
                            n=3;
                           f.remove(brik[i]);
                           bound[i].x=-1;
                           f.revalidate();
                         
						}
						else if(px>x1 && py>y1)
						{
							int mm=bound[i].x-x1;
							y1+=mm;
							x1=bound[i].x+(bound[i].width-1);
							repaint();
                           n=1;
                         f.remove(brik[i]);
                         bound[i].x=-1;
                         f.revalidate();
                        
						}
						else if(px>x1 && py<y1)
						{
							 int mm=0;
								for(;mm!=1;)
								{
									y1-=1;
									x1+=1;
									if(y1==bound[i].y || x1==bound[i].x)
									{
										mm=1;
										break;
									}
								}
								repaint();
                               n=2;
                              f.remove(brik[i]);
                              bound[i].x=-1;
                              f.revalidate();
                          
						}
						else if(px<x1 && y1==py)
						{
							int mm=0;
							for(;mm!=1;)
							{
								
								x1-=1;
								if(x1==bound[i].x)
								{
									mm=1;
									break;
								}
							}
							repaint();	
						}
						else if(py==y1 && px>x1)
						{
							int mm=0;
							for(;mm!=1;)
							{
								
								x1+=1;
								if(x1==bound[i].x)
								{
									mm=1;
									break;
								}
							}
							repaint();
						}
					}
					if(((px>=bound[i].x && px<=(bound[i].x+(bound[i].width-1))) && py>bound[i].y+(bound[i].height-1)) && y1<bound[i].y+(bound[i].height-1) && (n==1 || n==3 || n==5))
					{
						 if(px<x1 && py>y1)
						{
							 int mm=0;
							for(;mm!=1;)
							{
								y1+=1;
								x1-=1;
								if(y1==bound[i].y+(bound[i].height-1))
								{
									mm=1;
									break;
								}
							}
							repaint();
							n=4;
							bound[i].x=-1;
							f.remove(brik[i]);
							f.revalidate();
						}
						 else if(x1<px && y1<py)
						 {
							 int mm=0;
								for(;mm!=1;)
								{
									y1+=1;
									x1+=1;
									if(y1==bound[i].y+(bound[i].height-1))
									{
										mm=1;
										break;
									}
								} 
								repaint();
								n=2;
								bound[i].x=-1;
								f.remove(brik[i]);
								f.revalidate();
						 }
						 else if(x1==px && py>y1)
						 {
							 int mm=0;
								for(;mm!=1;)
								{
									y1+=1;
									if(y1==bound[i].y+(bound[i].height-1))
									{
										mm=1;
										break;
									}
								}
								repaint();
								n=6;
								bound[i].x=-1;
								f.remove(brik[i]);
								f.revalidate();
						 }
					}
					
					if(y1==bound[i].y && (x1>=bound[i].x && x1<=bound[i].x+(bound[i].width-1)))
					{
						if(px<x1 && py<y1)
						{
							n=1;
							bound[i].x=-1;
							f.remove(brik[i]);
							f.revalidate();
							
						}
						else if(x1==px && py<y1)
						{
							n=5;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
						else if(py<y1 && px>x1)
						{
							n=3;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
					}
					else if(x1==bound[i].x && (y1>=bound[i].y && y1<=bound[i].y+(bound[i].height-1)))
					{
						if(px<x1 && py<y1)
						{
							n=4;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
						
						}
						else if(px<x1 && py>y1)
						{
							n=3;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
						else if(py==y1 && px<x1)
						{
							n=8;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
					}
					else if(x1==bound[i].x+(bound[i].width-1) && (y1>=bound[i].y && y1<=bound[i].y+(bound[i].height-1)))
					{
						if(px>x1 && py>y1)
						{
							n=1;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
						else if(px>x1 && py<y1)
						{
							n=2;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
						else if(px>x1 && py==y1)
						{
							n=7;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
					}
					 if(x1>=bound[i].x && x1<=(bound[i].x+(bound[i].width-1)) && y1==bound[i].height-1)
					{
						if(px>x1 && py>y1)
						{
							n=1;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
						else if(x1==px && py>y1)
						{
							n=5;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
						else if(py>y1 && px<x1)
						{
							n=3;
							f.remove(brik[i]);
							bound[i].x=-1;
							f.revalidate();
							
						}
					}
				}
				for(i=0;i<=17;i++)
				{
			 if(bound[i].x!=-1)
			 {
				 break;
			 }
				}
				if(i>17)
				{
					stop();
					dispose();
				}
			  repaint();
		}   
	   }); 
      t1.start();
	   setLayout(null);
	   setVisible(true);
	   revalidate();
	   p1.repaint();
   }
   public void stop()
   {
	   t1.stop();
   }
   public static void main(String args[])
   {
	   new GameWindow();
   }
}
