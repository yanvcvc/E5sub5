import java.sql.*; 
import javax.swing.*; 
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import mypackage.*;

public class RegisterFrame extends JFrame
{
	private Connection Insertconnection; 
   	private Statement Insertstatement;  
   	private ResultSet InsertresultSet; 
      
       //GUI变量定义	
	private JLabel NameLabel ;
	private JLabel passwordLabel ;
	private JLabel repasswordLabel ;
   	private JTextField NameField;
   	private JPasswordField passwordField;
   	private JPasswordField repasswordField;
	private JButton SubmmitButton ;
	private JButton cancelButton ;
        JPanel regPanel = new JPanel();
        TitledBorder titledBorder1;  
        GridBagLayout gridBagLayout1 = new GridBagLayout();//注册面板布局 
        GridBagLayout layout = new GridBagLayout();//整体布局

	String  regname;
        String  regpass;
        String password;

   public RegisterFrame() 
   {
	setTitle("用户注册窗口");
    	setSize(500,350);
      	setResizable(false);
      	addWindowListener(new WindowAdapter() 
      	{
        	public void windowClosing(WindowEvent e) 
        	{
            	System.exit(0);
        	}
      	});
      	setLocation(200,100);  
        addWindowListener(new WindowAdapter () 
        {
            public void windowClosing(WindowEvent event) 
            {
                setVisible(false);
        		dispose();
            }
        });  
             try{
                jbcreat();
              }
             catch(Exception e){
             e.printStackTrace();
            } 
       }//构造方法完成  
   
private void jbcreat() throws Exception{

          java.awt.Color mycolor=new Color(200,0,0);
            titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                                             white, new Color(165, 163, 151)), 
                                             "默认口令注册",TitledBorder.CENTER,
                                             TitledBorder.TOP,
                                             new Font("SansSerif",Font.BOLD,22),mycolor); 

                    regPanel.setLayout(gridBagLayout1);
                    regPanel.setBorder(titledBorder1);
                    this.getContentPane().setLayout(layout);
      
                     this.getContentPane().add(regPanel,
                                                new GridBagConstraints(0, 0, 3, 5, 1.0, 1.0
                                                , GridBagConstraints.NORTH, 
                                                 GridBagConstraints.BOTH,
                                                 new Insets(10, 20, 0, 20), 0, 0)); 

                    JPanel buttonPanel = createButtonPanel(); // sets global loginButton
                       this.getContentPane().add(buttonPanel,
                                                new GridBagConstraints(0, 5, 3, 1, 1.0, 1.0
                                                , GridBagConstraints.SOUTH, 
                                                GridBagConstraints.BOTH,
                                                new Insets(6, 90, 6, 6), 0, 0)); 
    
              //1用户名
        NameField = new JTextField(); // needed below
        NameField.setPreferredSize(new Dimension(150, 20));
        NameLabel = new JLabel();
        NameLabel.setFont(new java.awt.Font("宋体", 1, 15));
        NameLabel.setText("用 户 姓 名 ：");
        
       regPanel.add(NameLabel,
                     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                            , GridBagConstraints.WEST, 
                                             GridBagConstraints.NONE,
                                             new Insets(12, 20, 12, 10), 8, 13));


        regPanel.add(NameField,                           //用户名文本
                       new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(13, 15, 13, 20), 30, 11));
        //2用 户 口 令
        passwordField = new JPasswordField(); // needed below
        passwordField.setPreferredSize(new Dimension(150, 20));
        passwordLabel = new JLabel();
        passwordLabel.setFont(new java.awt.Font("宋体", 1, 15));
        passwordLabel.setText("用 户 口 令 ：");
        
     regPanel.add(passwordLabel,
                  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                  , GridBagConstraints.WEST, 
                  GridBagConstraints.NONE,
                  new Insets(13, 20, 13, 10), 8, 13));
     
    regPanel.add(passwordField, 
                 new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                 , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(13, 15, 13, 20), 30, 11));
     
	//3重 输 口 令
	repasswordField = new JPasswordField(); // needed below
        repasswordField.setPreferredSize(new Dimension(150, 20));
        repasswordLabel = new JLabel();
        repasswordLabel.setFont(new java.awt.Font("宋体", 1, 15));
        repasswordLabel.setText("确 认 口 令 ：");
        
   regPanel.add(repasswordLabel,
                 new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                , GridBagConstraints.WEST, 
                 GridBagConstraints.NONE,
                 new Insets(13, 20, 13, 10), 8, 13));
       
    regPanel.add(repasswordField, 
                 new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(13, 15, 13, 20), 30, 11));          
	}
        
     private JPanel createButtonPanel() 
     {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        SubmmitButton = new JButton();
        SubmmitButton.setFont(new java.awt.Font("宋体", 1, 15));
        SubmmitButton.setText("确  定");
        SubmmitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                RegInfoInput();
            }
        });
        panel.add(SubmmitButton);

        // space
        panel.add(Box.createRigidArea(new Dimension(150,0)));

        // cancel button
        JButton cancelButton = new JButton();
        cancelButton.setFont(new java.awt.Font("宋体", 1, 15));
        cancelButton.setText(" 返  回");
        
        cancelButton.addActionListener(new ActionListener() 
       {
           public void actionPerformed(ActionEvent event) 
           {      LoginFrame frame=new LoginFrame();
                  frame.show();
                  dispose();
           }
       });
        panel.add(cancelButton);
        // space       
        return panel;
    } // createButtonPanel()
 
    private void RegInfoInput()
    {    

   	  if(
   		   NameField.getText().equals( "" )||
   		   passwordField.getText().equals( "" )||
   		   repasswordField.getText().equals( "" ))
   		{
   			JOptionPane.showMessageDialog( this,"请将各项内容填完整!" ); 
         	
         	return; 	
   		}
           if(!passwordField.getText().equals(repasswordField.getText()))
   		{
   			JOptionPane.showMessageDialog( this,"口令不一致!" ); 
         	
         	return; 	
   		}        

               regname = NameField.getText().trim();
               regpass = passwordField.getText().trim();

             if(regname.length()>10)
                     		{
   			JOptionPane.showMessageDialog( this,"用户名长度为小于10!" ); 
         	return; 	
   		}

             if(regpass.length()<6 ||regpass.length()>12)
                     		{
   			JOptionPane.showMessageDialog( this,"口令长度为6-12之间!" ); 
         	return; 	
   		}
   
             //生成口令
              ProductKey it = new MyProductKey();
              String u[] = it.DigestKey(regpass);
              password = u[0];

   	String url="jdbc:odbc:Person";
    	// 数据库	

    try 
      	{ 
        	Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
        	Insertconnection = DriverManager.getConnection( url); 
      	} 
      	//捕获加载驱动程序异常
      	catch ( ClassNotFoundException cnfex ) 
      	{ 
        	System.err.println("装载 JDBC/ODBC 驱动程序失败。" ); 
        	cnfex.printStackTrace(); 
         	System.exit( 1 );  // terminate program 
      	} 
     //捕获连接数据库异常
      	catch ( SQLException sqlex ) 
      	{ 
         	System.err.println( "无法连接数据库" ); 
         	sqlex.printStackTrace(); 
         	System.exit( 1 );  // terminate program 
      	}
      	try 
      	{ 
                 Insertstatement = Insertconnection.createStatement();
      		 String InsertQuery = "select * from Person where PASSWORD = '"+password+"'";
      		 String InsertInput = "insert into Person(NAME,PASSWORD) values ('"
      		 					+ regname+"','"+password+"')";   		 					     		
      		 
         	 InsertresultSet = Insertstatement.executeQuery( InsertQuery );  
         	 
         	 boolean moreRecords = InsertresultSet.next();     
         	 
         	 if ( moreRecords ) 
      		 { 
         		JOptionPane.showMessageDialog( this,"对不起，口令无效，请重新输入" ); 
         		
         		Insertconnection.close(); 
         		return; 
      		 }  
      		 int insert = Insertstatement.executeUpdate( InsertInput );//executeUpdate返回一个整型值
      		 
      		 if (insert == 1)
      		 {
      		 	JOptionPane.showMessageDialog( this,"注册成功！");
      		 	        NameField.setText("");
   		   		passwordField.setText("");
   		   		repasswordField.setText("");
      		 } 
      	} 
     	catch ( SQLException sqlex ) 
      	{ 
        	sqlex.printStackTrace(); 
      	}
           
     }

}