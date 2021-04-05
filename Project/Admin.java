import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

class myframe extends JFrame implements ActionListener
{
    Container c;
    JLabel lb1;
    JLabel lb2;
    JLabel lb3;
    JLabel lb;
    JLabel lb4;
    JTextField tf1;
    JTextField tf2;
    JTextField tf3;
    JButton b1;
	public static String ip;
	public static String uname;
	public static int prt;
	Font font = new Font("times new roman", Font.BOLD, 20);

    myframe(){		
        setTitle("Network monitoring");
		ImageIcon webIcon = new ImageIcon("C:\\Users\\Invictus\\Desktop\\Group 8 - Network Monitoring\\eyes.png");
		setIconImage(webIcon.getImage());
        setSize(400,340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        c= getContentPane();
        c.setLayout(null);
        lb = new JLabel("Network Monitoring System ");
        lb.setBounds(60, 30, 290, 30);

        c.add(lb);
        lb.setFont(font);

        lb1 = new JLabel("IP Address : ");
        lb2 = new JLabel("Port No  : ");
        lb3 = new JLabel("User Name  : ");

        lb1.setBounds(50, 80, 120, 30);
        lb2.setBounds(50, 130, 120, 30);
        lb3.setBounds(50, 180, 120, 30);

        c.add(lb1);
        c.add(lb2);
        c.add(lb3);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();

        tf1.setBounds(180, 80, 150, 30);
        tf2.setBounds(180, 130, 150, 30);
        tf3.setBounds(180, 180, 150, 30);

        c.add(tf1);
        c.add(tf2);
        c.add(tf3);

        b1 = new JButton("Connect");
        b1.setBounds(130,240,100,30);
        c.add(b1);

        b1.addActionListener(this);
        setVisible(true);
    }
    
        public void actionPerformed(ActionEvent e)
		{        
			if(tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("") || !tf2.getText().matches("[0-9]+") || !tf1.getText().matches("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"))
			{
				JOptionPane.showMessageDialog(this,"Invalid Credentials","Error!",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				try
				{
					Registry reg = LocateRegistry.getRegistry(tf1.getText(),Integer.parseInt(tf2.getText()));
					Adder ad = (Adder)reg.lookup("myserver");	
					ip=tf1.getText();
					prt=Integer.parseInt(tf2.getText());
					uname=tf3.getText();
					dispose();
					myframe1 frame = new myframe1();
				}
				catch(NotBoundException | RemoteException ex)
				{
					lb4=new JLabel();
					c.add(lb4);
					lb4.setBounds(10,280,400,30);
					lb4.setText("Connection Error!");
					System.out.println("Exception : " + ex);
				}
			}	
        } 
}

class myframe1 extends JFrame implements ActionListener{
    Container c;
	JLabel lb;
    JLabel lb1;
	JLabel lb2;
	
	// Temp
	JLabel lb3;
	JLabel lb4;
	JLabel lb5;
	JLabel lb6;
	JLabel lb7;
	// Temp End
	
    JRadioButton jr1;
    JRadioButton jr2;
    JRadioButton jr3;
	JRadioButton jr4;
    JButton b1;
    ButtonGroup g1;
	Font font3 = new Font("times new roman", Font.BOLD, 20);
    JButton jb1 = new JButton("Send Warning");
	JButton jb2 = new JButton("Kill Process");
	JTextArea tf1=new JTextArea();
	
	JTextArea tf2=new JTextArea();
	
    myframe1(){
		ImageIcon webIcon = new ImageIcon("C:\\Users\\Invictus\\Desktop\\Group 8 - Network Monitoring\\eyes.png");
		setIconImage(webIcon.getImage());
		lb1 = new JLabel("Enter Warning :-");
		lb2 = new JLabel("Enter Process ID :-");
		
		// Temp
		lb7 = new JLabel("Connection Details:- ");
		lb3 = new JLabel("Host IP Address: ");
		lb4 = new JLabel("Host Username: ");
		lb5 = new JLabel(myframe.ip);
		lb6 = new JLabel(myframe.uname);
		// Temp End
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
        setTitle("Network monitoring");
        setSize(500,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        c=getContentPane();
        c.setLayout(null);
		Font font2 = new Font("times new roman", Font.BOLD, 25);
		Font font3 = new Font("times new roman", Font.BOLD, 20); 		
        lb = new JLabel("Operations");
		lb.setFont(font2);
		lb3.setFont(font3);
		lb4.setFont(font3);
		lb5.setFont(font3);
		lb6.setFont(font3);
		lb7.setFont(font3);
        lb.setBounds(170, 30, 170, 30);
		lb3.setBounds(20, 400, 200, 30);
		lb4.setBounds(20, 440, 200, 30);
		lb5.setBounds(170, 400, 200, 30);
		lb6.setBounds(170, 440, 200, 30);
		lb7.setBounds(20, 350, 200, 30);
		lb7.setForeground(Color.GREEN.darker());
		lb3.setForeground(Color.GREEN.darker());
		lb4.setForeground(Color.GREEN.darker());
		lb5.setForeground(Color.GREEN.darker());
		lb6.setForeground(Color.GREEN.darker());
        c.add(lb);
		c.add(lb7);
		c.add(lb3);
		c.add(lb4);
		c.add(lb5);
		c.add(lb6);
		
        jr1 = new JRadioButton("Monitor");
        jr2 = new JRadioButton("Warning");
        jr3 = new JRadioButton("ShutDown");
		jr4 = new JRadioButton("Kill Process");

        jr1.setBounds(20, 80, 100, 30);
        jr2.setBounds(118, 80, 100, 30);
		jr4.setBounds(220, 80, 129, 30);
		jr3.setBounds(345, 80, 120, 30);
		jr1.setFont(font3);
		jr2.setFont(font3);
		jr3.setFont(font3);
		jr4.setFont(font3);
        g1 = new ButtonGroup();

        g1.add(jr1);
        g1.add(jr2);
        g1.add(jr3);
		g1.add(jr4);
        add(jr1); 
        add(jr2);
        add(jr3);
		add(jr4);

        b1 = new JButton("Execute");
        b1.setBounds(180,130,100,30);
        b1.addActionListener(this); 

        c.add(b1);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){ 
	String s;
	String ip;
	int port; 
	s=myframe.uname;
	ip=myframe.ip;
	port=myframe.prt;
	
	try{
	Registry reg = LocateRegistry.getRegistry(ip,port);
	Adder ad = (Adder)reg.lookup("myserver");
	
		
		if(jr1.isSelected())
		{  
           JFrame frame = new JFrame("Process List"); 
		   Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Invictus\\Desktop\\Group 8 - Network Monitoring\\eyes.png");
			frame.setIconImage(icon);
		   JTextArea area=new JTextArea(); 
		   JScrollPane scroll = new JScrollPane(area, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		   frame.add(scroll);
		   frame.setVisible(true);	
	       frame.setSize(500,200);
		   frame.setLocation(10,250);			
			
			try{
			area.setText(ad.add(s));
			}catch(Exception exe){
				System.out.println(exe);
			}
			
           lb1.setVisible(false);
		   lb2.setVisible(false);
		   jb1.setVisible(false);
		   tf1.setVisible(false);
		   jb2.setVisible(false);
		   tf2.setVisible(false);
        }    
        else if(jr2.isSelected()){  
			Font font = new Font("times new roman", Font.BOLD, 20); 
			lb1.setBounds(30,180,400,30);
			lb1.setFont(font); 
			c.add(lb1);
            tf1.setBounds(30,215,360,30);
            tf1.setFont(font); 
            c.add(tf1);
            tf1.setSize(425,30);
            jb1.setBounds(155,270,150,30);
            c.add(jb1);
            lb1.setVisible(true);
            tf1.setVisible(true);
            jb1.setVisible(true);
			
            lb2.setVisible(false);
            tf2.setVisible(false);		
			jb2.setVisible(false);
			setVisible(true);            
        } 
        else if(jr3.isSelected()){ 
            jb1.setVisible(false);   
			tf1.setVisible(false);
			lb1.setVisible(false);
			jb2.setVisible(false);   
			tf2.setVisible(false);
			lb2.setVisible(false);
			ad.shutdown();
            JOptionPane.showMessageDialog(this,"Shutted down Successfully!");    
        }
		else if(jr4.isSelected()){ 
			Font font = new Font("times new roman", Font.BOLD, 20); 
			lb2.setBounds(30,180,400,30);
            tf2.setBounds(30,215,400,30);
            lb2.setFont(font);
            tf2.setFont(font);
            c.add(lb2); 
            c.add(tf2);
            tf2.setSize(430,30);
            jb2.setBounds(155,270,150,30);
            c.add(jb2);
			lb2.setVisible(true);
			tf2.setVisible(true);
            jb2.setVisible(true);
			setVisible(true);
            lb1.setVisible(false);
			tf1.setVisible(false);
            jb1.setVisible(false);
		}
		
		if(e.getSource()==jb1)
		{
			System.out.println("Sending Warning...");
			ad.sendwarning(tf1.getText());
			JOptionPane.showMessageDialog(this,"Warning Sent");  
		}
		
		if(e.getSource()==jb2)
		{
			System.out.println("Killing Process...");
			ad.killprocess(Integer.parseInt(tf2.getText()));
			JOptionPane.showMessageDialog(this,"Process Killed Successfully.");  
		}
        }catch(Exception exeee){}
		tf1.setText("");
		tf2.setText("");
    }
    
}

public class Admin 
{
        public static void main(String[] args) 
		{
            myframe frame = new myframe();
        }
}