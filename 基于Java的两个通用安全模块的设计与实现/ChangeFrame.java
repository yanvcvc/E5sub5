import java.sql.*; 
import javax.swing.*; 
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import mypackage.*;

public class ChangeFrame extends JFrame
{
	private Connection Insertconnection; 
   	private Statement Insertstatement; 
   	private ResultSet InsertresultSet; 
   
      
    //GUI变量定义	
	private JLabel NameLabel ;
        private JLabel oldpasswordLabel ;
	private JLabel passwordLabel ;
	private JLabel repasswordLabel ;
	
	
   	private JTextField NameField;
        private JPasswordField oldpasswordField;
   	private JPasswordField passwordField;
   	private JPasswordField repasswordField;
   	
	private JButton SubmmitButton ;
	private JButton cancelButton ;
        JPanel regPanel = new JPanel();
        TitledBorder titledBorder1;  
        GridBagLayout gridBagLayout1 = new GridBagLayout();//注册面板布局 
        GridBagLayout layout = new GridBagLayout();//整体布局

	private String  regname;
        private String oldpass;
        private String  regpass;
        private  String chagepass;

   public ChangeFrame() 
   {
	setTitle("口令更改");
    	setSize(600,450);
      	setResizable(false);
      	addWindowListener(new WindowAdapter() 
      	{
        	public void windowClosing(WindowEvent e) 
        	{
            	System.exit(0);
        	}
      	});
      	setLocation(200,100);    
    	GridBagLayout layout = new GridBagLayout();
        Container contents = getContentPane();
        contents.setLayout(layout);
        GridBagConstraints constraints = new GridBagConstraints();

        addWindowListener(new WindowAdapter () 
        {
            public void windowClosing(WindowEvent event) 
            {
                setVisible(false);
        		dispose();
            }
        });
             try{
                jbchange();
              }
             catch(Exception e){
             e.printStackTrace();
            } 
       }//构造方法完成 

private void jbchange() throws Exception{

          java.awt.Color mycolor=new Color(200,0,0);
            titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                                             white, new Color(165, 163, 151)), 
                                             "默认口令更改",TitledBorder.CENTER,
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
                                                new Insets(6, 130, 6, 6), 0, 0)); 
                   
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

        //2用 户  口 令
        oldpasswordField = new JPasswordField(); // needed below
        oldpasswordField.setPreferredSize(new Dimension(150, 20));
        oldpasswordLabel = new JLabel();
        oldpasswordLabel.setFont(new java.awt.Font("宋体", 1, 15));
        oldpasswordLabel.setText("登 录 口 令 ：");
        
     regPanel.add(oldpasswordLabel,
                  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                  , GridBagConstraints.WEST, 
                  GridBagConstraints.NONE,
                  new Insets(13, 20, 13, 10), 8, 13));

    regPanel.add(oldpasswordField, 
                 new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                 , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(13, 15, 13, 20), 30, 11));

        //3用 户 新 口 令
        passwordField = new JPasswordField(); // needed below
        passwordField.setPreferredSize(new Dimension(150, 20));
        passwordLabel = new JLabel();
        passwordLabel.setFont(new java.awt.Font("宋体", 1, 15));
        passwordLabel.setText("新 的 口 令 ：");

     regPanel.add( passwordLabel,
                  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                 , GridBagConstraints.WEST, 
                  GridBagConstraints.NONE,
                  new Insets(13, 20, 13, 10), 8, 13));

     regPanel.add(passwordField, 
                 new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(13, 15, 13, 20), 30, 11));

	//4重 输 口 令
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

        // 确定按钮
        SubmmitButton = new JButton();
        SubmmitButton.setFont(new java.awt.Font("宋体", 1, 15));
        SubmmitButton.setText("确  定");
        
        SubmmitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                RenewfoInput();
            }
        });
        panel.add(SubmmitButton);

        // space
        panel.add(Box.createRigidArea(new Dimension(200,0)));

        // 返回按钮
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
 
    private void RenewfoInput()
    {    

   	  if(                       
   		   NameField.getText().equals( "" )|| 
   		   passwordField.getText().equals( "" )||
   		   repasswordField.getText().equals( "" ))
   		{
   			JOptionPane.showMessageDialog( this,"请将各项内容填完整!" ); 
         	return; 	
   		}
           if( oldpasswordField.getText().equals( "" ))
                {
   			JOptionPane.showMessageDialog( this,"请输入原口令验证身份!" ); 
         	return;                     
                  } 
           if(!passwordField.getText().equals(repasswordField.getText()))
   		{
   			JOptionPane.showMessageDialog( this,"新口令不一致!" ); 
         	return; 	
   		}        
           
               regname = NameField.getText().trim();
               oldpass = oldpasswordField.getText().trim();
               regpass = passwordField.getText().trim();

              if(regpass.length()<6 ||regpass.length()>12) 
                     		{
   			JOptionPane.showMessageDialog( this,"口令长度为6-12之间!!" ); 
         	return; 	
   		}
             //处理旧口令
                   ProductKey its = new MyProductKey();
                   String w[] = its.DigestKey(oldpass);
                   String checkpass = w[0];   

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
      		 String CheckQuery = "select * from Person where (NAME='"+regname +
   							  "' and	PASSWORD = '"+checkpass+"')";				     		
         	 InsertresultSet = Insertstatement.executeQuery( CheckQuery );   
         	 boolean Records = InsertresultSet.next();     
         	 if ( ! Records ) 
      		 { 
         		JOptionPane.showMessageDialog( this,"对不起，没有此用户，请重新输入" ); 
         		Insertconnection.close(); 
         		return; 
      		 }

    //生成验证口令 
              ProductKey it = new MyProductKey();
              String u[] = it.DigestKey(regpass);
              chagepass = u[0];

               String searchmore = "select * from Person where PASSWORD = '"+chagepass+"'";
	        InsertresultSet = Insertstatement.executeQuery( searchmore );  
         	 
         	 boolean moreRecords = InsertresultSet.next();     
         	 
         	 if ( moreRecords ) 
      		 { 
         		JOptionPane.showMessageDialog( this,"对不起，口令无效，请重新输入" ); 
         		
         		Insertconnection.close(); 
         		return; 
      		 }               
        
      		 String InsertInput = "Update Person set PASSWORD ='"+chagepass+"'Where PASSWORD = '"+checkpass+"'";
  
      		 int insert = Insertstatement.executeUpdate( InsertInput );//executeUpdate返回一个整型值 
      		 if (insert == 1)
      		 {
      		 	JOptionPane.showMessageDialog( this,"口令更改成功！");
      		 	        NameField.setText("");
                                oldpasswordField.setText(""); 
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