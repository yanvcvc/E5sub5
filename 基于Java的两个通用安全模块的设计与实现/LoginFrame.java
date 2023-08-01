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
	setTitle("��¼����");
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
        // �û�����ǩ
        JLabel userNameLabel = new JLabel(); 
        userNameLabel.setText("�û���:");
        userNameLabel.setFont(new java.awt.Font("����", 0, 12));
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(0, 15, 0, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        contents.add(userNameLabel, constraints);
        // �û�����
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0, 0, 0, 30);
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        contents.add(userNameTextField, constraints);
 
        passwordField = new JPasswordField(); // needed below
        passwordField.setPreferredSize(new Dimension(130, 25));

        // �����ǩ
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("��  ��:");
        passwordLabel.setFont(new java.awt.Font("����", 0, 12));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 15, 0, 0);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        contents.add(passwordLabel, constraints);

        // �����       
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(0 , 0, 0, 30);
        constraints.gridwidth = 2;
        constraints.gridheight = 1;
        contents.add(passwordField, constraints);
        //�û�����ѡ��
        JLabel choicetypeLabel = new JLabel();
        choicetypeLabel.setText("��  ��:");
        choicetypeLabel.setFont(new java.awt.Font("����", 0, 12));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.insets = new Insets(0, 15, 0, 0);
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        contents.add(choicetypeLabel, constraints);

        String[] description = {"Ĭ�Ͽ���","һ��һ��"};
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

        // ȷ����ť
        loginButton = new JButton();
        loginButton.setText("ȷ  ��");
        loginButton.setFont(new java.awt.Font("����", 0, 12));
       // ȷ����ť�¼�
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
        // ���İ�ť
        JButton newpassButton = new JButton();
        newpassButton.setText("�Ŀ���");
        newpassButton.setFont(new java.awt.Font("����", 0, 12));

        //��������¼�
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

        // ע�ᰴť
        JButton helpButton = new JButton();
        helpButton.setText("ע  ��");
        helpButton.setFont(new java.awt.Font("����", 0, 12));
        //ע���¼�
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
        // ȡ����ť
        JButton cancelButton = new JButton();
        cancelButton.setText("ȡ  ��");
        cancelButton.setFont(new java.awt.Font("����", 0, 12));
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

    //ע�ᴦ����
     private void initAboutDialog()
     {
	   RegisterFrame regframe=new RegisterFrame();
           regframe.show();
           dispose();     
     }
    //һ��һ��ע�ᴦ����
     private void keyButtonRegi()
     {
	   RegisterFrame1 regframe1=new RegisterFrame1();
           regframe1.show();
           dispose();     
     }

//���Ŀ��ť
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

//ȷ����ť������
 private void logindispose()
    {
      if(userNameTextField.getText().trim().equals(""))
           {
   	      JOptionPane.showMessageDialog( this,"�������û���!����" ); 
         	return; 	
   		}
      if(passwordField.getText().trim().equals(""))
           {
   	      JOptionPane.showMessageDialog( this,"���������!����" ); 
         	return; 	
   		}

      choiceIndex = choicetype.getSelectedIndex();
      logname = userNameTextField.getText().trim();
      logpass = passwordField.getText().trim();

     System.out.println("choiceIndex"+choiceIndex);
//��������
      
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
    
//�������ݿ�
   String url="jdbc:odbc:Person";
    try 
      	{ 
        	Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
        	loginconnection = DriverManager.getConnection( url); 
      	}
//����������������쳣 
      	catch ( ClassNotFoundException cnfex ) 
      	{ 
        	System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�" ); 
        	cnfex.printStackTrace(); 
         	System.exit( 1 );  // terminate program 
      	} 
//�����������ݿ��쳣
      	catch ( SQLException sqlex ) 
      	{ 
         	System.err.println( "�޷��������ݿ�" ); 
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
        		JOptionPane.showMessageDialog( this,"��¼ʧ��" ); 
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