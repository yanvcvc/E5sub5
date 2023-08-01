import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import mypackage.*;

class LoginFrame extends JFrame 
{
   private JButton loginButton ;
   private Connection loginconnection;
   private Statement loginstatement; 	
   private ResultSet loginresultSet; 
    
   private JTextField userNameTextField;
   private JPasswordField passwordField;
   private  Choice choicetype;

   String logname;
   String logpass;
   int logpasslen;
   int times;
   int choiceIndex;

   private int login = 0 ;
   int userId;
   String insertpass;
   String loginupdatequery;

   public LoginFrame() 
   {
	setTitle("登录窗口");
    	setSize(320,220);
      	setResizable(false);
      	addWindowListener(new WindowAdapter() 
      	{
        	public void windowClosing(WindowEvent e) 
        	{
            	System.exit(0);
        	}
      	});

        setLocation(600,150);
      	GridBagLayout layout = new GridBagLayout();
        Container contents = getContentPane();
        contents.setLayout(layout);      
        GridBagConstraints constraints = new GridBagConstraints();
        
        userNameTextField = new JTextField(); 
        userNameTextField.setPreferredSize(new Dimension(130, 25));
        // 用户名标签
        JLabel userNameLabel = new JLabel(); 
        userNameLabel.setText("用户名:");
        userNameLabel.setFont(new java.awt.Font("宋体", 0, 12));
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(0, 15, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        contents.add(userNameLabel, constraints);
        // 用户名框
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 30);
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        contents.add(userNameTextField, constraints);
 
        passwordField = new JPasswordField(); // needed below
        passwordField.setPreferredSize(new Dimension(130, 25));

        // 密码标签
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("口  令:");
        passwordLabel.setFont(new java.awt.Font("宋体", 0, 12));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 15, 0, 0);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        contents.add(passwordLabel, constraints);

        // 密码框       
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0 , 0, 0, 30);
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        contents.add(passwordField, constraints);
        //用户类型选框
        JLabel choicetypeLabel = new JLabel();
        choicetypeLabel.setText("类  型:");
        choicetypeLabel.setFont(new java.awt.Font("宋体", 0, 12));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 15, 0, 0);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        contents.add(choicetypeLabel, constraints);

        String[] description = {"默认口令","一次一密"};
        choicetype = new Choice();
        for(int i=0; i<2; i++)
          {choicetype.addItem(description[i]);}
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 120);
        contents.add(choicetype, constraints);

        JPanel buttonPanel = createButtonPanel(); // sets global loginButton
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 0, 0, 0);
        constraints.gridwidth = 4;
        constraints.gridheight = 1;
        contents.add(buttonPanel, constraints);
   }
   
    private JPanel createButtonPanel() 
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));

        // 确定按钮
        loginButton = new JButton();
        loginButton.setText("确  定");
        loginButton.setFont(new java.awt.Font("宋体", 0, 12));
       // 确定按钮事件
        loginButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
               	logindispose();
               	if(login == 1)
               	{
          Myselectfile sentframe=new Myselectfile(userId,insertpass);
               sentframe.show();
           dispose(); 
               	}
            }
        });
       
         panel.add(loginButton);

        // space
        panel.add(Box.createRigidArea(new Dimension(2,0)));
        // 更改按钮
        JButton newpassButton = new JButton();
        newpassButton.setText("改口令");
        newpassButton.setFont(new java.awt.Font("宋体", 0, 12));

        //口令更改事件
        newpassButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) {
             choiceIndex = choicetype.getSelectedIndex();
               if (choiceIndex == 0) 
               { initChangepass();}
               else
               { onekeychange();}
            }
        });
        
        panel.add(newpassButton);
        // space
        panel.add(Box.createRigidArea(new Dimension(2,0)));

        // 注册按钮
        JButton helpButton = new JButton();
        helpButton.setText("注  册");
        helpButton.setFont(new java.awt.Font("宋体", 0, 12));
        //注册事件
        helpButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) {
           choiceIndex = choicetype.getSelectedIndex();
                 if (choiceIndex == 0) 
                    { initAboutDialog();}
                 else
                    {keyButtonRegi();}
            }
        });
        
        panel.add(helpButton);
        // space
        panel.add(Box.createRigidArea(new Dimension(2,0)));
        // 取消按钮
        JButton cancelButton = new JButton();
        cancelButton.setText("取  消");
        cancelButton.setFont(new java.awt.Font("宋体", 0, 12));
        cancelButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                dispose();
                System.exit(0);
            }
        });
         panel.add(cancelButton);

        return panel;
    } // createButtonPanel()   

    //注册处理方法
     private void initAboutDialog()
     {
	   RegisterFrame regframe=new RegisterFrame();
           regframe.show();
           dispose();     
     }
    //一次一密注册处理方法
     private void keyButtonRegi()
     {
	   RegisterFrame1 regframe1=new RegisterFrame1();
           regframe1.show();
           dispose();     
     }

//更改口令按钮
     private void initChangepass()
     {
	   ChangeFrame chaframe=new ChangeFrame ();
           chaframe.show();
           dispose();     
     }

     private void onekeychange()
          {
	   ChangeFrame1 chaframe1=new ChangeFrame1 ();
           chaframe1.show();
           dispose();     
     }

//确定按钮处理方法
 private void logindispose()
    {
      if(userNameTextField.getText().trim().equals(""))
           {
   	      JOptionPane.showMessageDialog( this,"请输入用户名!警告" ); 
         	return; 	
   		}
      if(passwordField.getText().trim().equals(""))
           {
   	      JOptionPane.showMessageDialog( this,"请输入口令!警告" ); 
         	return; 	
   		}

      choiceIndex = choicetype.getSelectedIndex();
      logname = userNameTextField.getText().trim();
      logpass = passwordField.getText().trim();

     System.out.println("choiceIndex"+choiceIndex);
//输入口令处理
      
      ProductKey its = new MyProductKey();
if (choiceIndex == 0)
    {
      String uu[] = its.DigestKey(logpass); 
      insertpass = uu[0];       
     }
else
    {
      logpasslen = logpass.length();
      String uu[] = its.DigestKey(logpass,logpasslen,1);
      insertpass = uu[0];
     }
    
//连接数据库
   String url="jdbc:odbc:Person";
    try 
      	{ 
        	Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
        	loginconnection = DriverManager.getConnection( url); 
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
     		String loginquery;            
                String logtimesquery;         	
         	loginquery = "select * from Person where (NAME='"+logname +
   							  "' and	PASSWORD = '"+insertpass+"')";
   							  
   		loginstatement = loginconnection.createStatement(); 
        	loginresultSet = loginstatement.executeQuery( loginquery );
                 
        	boolean Records = loginresultSet.next();
        	if ( ! Records ) 
      		{ 
        		JOptionPane.showMessageDialog( this,"登录失败" ); 
        		return; 
      	 	} 
	        if (  Records ) 
      		{ 
        	userId = loginresultSet.getInt(1);
                   if (choiceIndex == 1)
                     {
                       times = loginresultSet.getInt(5)-1;
                       loginupdatequery = "Update Person set PASSWORD ='"+logpass+"',TIMES = "+times+"Where NAME = '"+logname+
                                                                                           "'and	PASSWORD = '"+insertpass+"'";
                       int update = loginstatement.executeUpdate( loginupdatequery );

                      }

                       login = 1 ;
      		}
      		loginconnection.close();
        }
     	catch(SQLException sqlex)
     	{
     		sqlex.printStackTrace();
     	}     
     }   
  public static void main(String s[]){
   LoginFrame frame=new LoginFrame();
               frame.show();
}
}