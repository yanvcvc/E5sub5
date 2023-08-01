import java.sql.*; 
import javax.swing.*; 
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*; 
import java.awt.event.*; 
import java.lang.*;
import java.util.*; 
import mypackage.*;

public class RegisterFrame1 extends JFrame
{
	private Connection Insertconnection; 
   	private Statement Insertstatement; 
   	private ResultSet InsertresultSet; 
   	
      
    //GUI��������	
	private JLabel NameLabel ;
	private JLabel passwordLabel ;
	private JLabel repasswordLabel ;
	private JLabel passlenLabel ;
	private JLabel passnumLabel ;
   	private JTextField NameField;
   	private JPasswordField passwordField;
   	private JPasswordField repasswordField;
   	private JTextField passlenField;
   	private JTextField passnumField;
	private JButton SubmmitButton ;
	private JButton cancelButton ;
        JPanel regPanel = new JPanel();
        TitledBorder titledBorder1; 
        GridBagLayout layout = new GridBagLayout();//���岼�� 
        GridBagLayout gridBagLayout1 = new GridBagLayout();//ע����岼��

	String  regname;
        String  regpass;
        String first;
        int  passlen;
        int  passnum;
        String keyfull;
 
      
   public RegisterFrame1() 
   {
	setTitle("�û�ע�ᴰ��");
    	setSize(550,400);
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
                jbrcreat();
              }
             catch(Exception e){
             e.printStackTrace();
            } 
       }//���췽�����         
        
       private void jbrcreat() throws Exception{

          java.awt.Color mycolor=new Color(200,0,0);
            titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                                             white, new Color(165, 163, 151)), 
                                             "һ��һ���û�ע��",TitledBorder.CENTER,
                                             TitledBorder.TOP,
                                             new Font("SansSerif",Font.BOLD,22),mycolor);

                    regPanel.setLayout(gridBagLayout1);
                    regPanel.setBorder(titledBorder1);
                    this.getContentPane().setLayout(layout);

                     this.getContentPane().add(regPanel,
                                                new GridBagConstraints(0, 0, 3, 5, 1.0, 1.0
                                                , GridBagConstraints.NORTH, 
                                                 GridBagConstraints.BOTH,
                                                 new Insets(20, 20, 0, 20), 0, 0)); 
                    JPanel buttonPanel = createButtonPanel(); // sets global loginButton
                       this.getContentPane().add(buttonPanel,
                                                new GridBagConstraints(0, 5, 3, 1, 1.0, 1.0
                                                , GridBagConstraints.SOUTH, 
                                                GridBagConstraints.BOTH,
                                                new Insets(10, 90, 10, 10), 0, 0));
       
        //1�û���
        NameField = new JTextField(); // needed below
        NameField.setPreferredSize(new Dimension(150, 20));
        NameLabel = new JLabel();
 NameLabel.setFont(new java.awt.Font("����", 1, 15));
        NameLabel.setText("�� �� �� �� ��");
        
       regPanel.add(NameLabel,
                     new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
                                            , GridBagConstraints.WEST, 
                                             GridBagConstraints.NONE,
                                             new Insets(5, 40, 5, 0), 8, 13));

        regPanel.add(NameField,                           //�û����ı�
                       new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(5, 10, 5, 40), 30, 11));

        //2�� �� �� ��
        passwordField = new JPasswordField(); // needed below
        passwordField.setPreferredSize(new Dimension(150, 20));
        passwordLabel = new JLabel();
        passwordLabel.setFont(new java.awt.Font("����", 1, 15));
        passwordLabel.setText("�� �� �� �� ��");
        
     regPanel.add(passwordLabel,
                  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                  , GridBagConstraints.WEST, 
                  GridBagConstraints.NONE,
                  new Insets(5, 40, 5, 0), 8, 13));

    regPanel.add(passwordField, 
                 new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0
                 , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(5, 10, 5, 40), 30, 11));

	//3�� �� �� ��
	repasswordField = new JPasswordField(); // needed below
        repasswordField.setPreferredSize(new Dimension(150, 20));
        repasswordLabel = new JLabel();
        repasswordLabel.setFont(new java.awt.Font("����", 1, 15));
        repasswordLabel.setText("ȷ �� �� �� ��");
        
   regPanel.add(repasswordLabel,
                 new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
                , GridBagConstraints.WEST, 
                 GridBagConstraints.NONE,
                 new Insets(5, 40, 5, 0), 8, 13));
       
    regPanel.add(repasswordField, 
                 new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(5, 10, 5, 40), 30, 11));

        //4�����
        passlenField = new JTextField(); // needed below
        passlenField.setPreferredSize(new Dimension(150, 20));
        passlenLabel = new JLabel();
        passlenLabel.setFont(new java.awt.Font("����", 1, 15));
        passlenLabel.setText("�� �� �� �ȣ�        ");
   regPanel.add(passlenLabel,
                 new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
                , GridBagConstraints.WEST, 
                 GridBagConstraints.NONE,
                 new Insets(5, 40, 5, 0), 8, 13));

    regPanel.add(passlenField, 
                 new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(5, 10, 5, 40), 30, 11));       

	//5�������
	passnumField = new JTextField(); // needed below
        passnumField.setPreferredSize(new Dimension(150, 20));
        passnumLabel = new JLabel();
     passnumLabel.setFont(new java.awt.Font("����", 1, 15));
        passnumLabel.setText("�� �� �� �� ��");
   regPanel.add(passnumLabel,
                 new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
                , GridBagConstraints.WEST, 
                 GridBagConstraints.NONE,
                 new Insets(5, 40, 5, 0), 8, 13));

    regPanel.add(passnumField, 
                 new GridBagConstraints(1, 4, 1, 1, 1.0, 0.0
                , GridBagConstraints.CENTER, 
                 GridBagConstraints.HORIZONTAL,
                 new Insets(5, 10, 5, 40), 30, 11));  
	}
      
     private JPanel createButtonPanel() 
     {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
 
        // login button (global variable)
        SubmmitButton = new JButton();
       SubmmitButton.setFont(new java.awt.Font("����", 1, 15));
        SubmmitButton.setText("ȷ  ��");
        
        SubmmitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                RegInfoInput();
            }
        });
        panel.add(SubmmitButton);

        // space
        panel.add(Box.createRigidArea(new Dimension(200,0)));

        // cancel button
        JButton cancelButton = new JButton();
        cancelButton.setFont(new java.awt.Font("����", 1, 15));
        cancelButton.setText(" ��  ��");
        
        cancelButton.addActionListener(new ActionListener() 
       {
           public void actionPerformed(ActionEvent event) 
           {      LoginFrame frame=new LoginFrame();
                  frame.show();
                  dispose();
           }
       });
        panel.add(cancelButton);
        return panel;
    } // createButtonPanel()
 
    private void RegInfoInput()
    {    

   	  if(
   		   NameField.getText().equals( "" )||
   		   passwordField.getText().equals( "" )||
   		   repasswordField.getText().equals( "" )||
		   passlenField.getText().equals( "" )||
   		   passnumField.getText().equals( "" ))
   		{
   			JOptionPane.showMessageDialog( this,"�뽫��������������!" ); 
         	return; 	
   		}
           if(!passwordField.getText().equals(repasswordField.getText()))
   		{
   			JOptionPane.showMessageDialog( this,"���һ��!" ); 
         	return; 	
   		}        

               regname = NameField.getText().trim();
               regpass = passwordField.getText().trim();
               passlen = Integer.parseInt(passlenField.getText().trim());
               passnum = Integer.parseInt(passnumField.getText().trim());
             if(passlen<6 ||passlen>10)
                     		{
   			JOptionPane.showMessageDialog( this,"�����Ϊ6-10֮��!" ); 
         	return; 	
   		}
             if(passnum<=0 ||passnum>10)
                     		{
   			JOptionPane.showMessageDialog( this,"������Ч����Ϊ1-10��֮��!" ); 
         	return; 	
   		}
              if(regpass.length() != passlen) 
                     		{
   			JOptionPane.showMessageDialog( this,"����Ŀ���ĳ��ȸ�������Ŀ���Ȳ�һ��!" ); 
         	return; 	
   		}   
             //���ɿ���
              ProductKey it = new MyProductKey();
              String u[] = it.DigestKey(regpass, passlen , passnum);

              first = u[passnum-1];
              StringBuffer seck=new StringBuffer();
              for(int i=0;i<u.length-1;i++)
               {
                  seck.append(u[u.length-i-2]+",");
                }

   	String url="jdbc:odbc:Person";
    	// ���ݿ�	

    try 
      	{ 
        	Class.forName( "sun.jdbc.odbc.JdbcOdbcDriver" ); 
        	Insertconnection = DriverManager.getConnection( url); 
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
                 Insertstatement = Insertconnection.createStatement();
      		 String InsertQuery = "select * from Person where PASSWORD = '"+first+"'";
      		 String InsertInput = "insert into Person(NAME,PASSWORD,TIMES) values ('"
      		 					+ regname+"','"+first+"',"+passnum+")";   		 					     		
      		 
         	 InsertresultSet = Insertstatement.executeQuery( InsertQuery );  
         	 
         	 boolean moreRecords = InsertresultSet.next();     
         	 
         	 if ( moreRecords ) 
      		 { 
         		JOptionPane.showMessageDialog( this,"�Բ��𣬿�����Ч������������" ); 
         		
         		Insertconnection.close(); 
         		return; 
      		 }  
      		 
      		 int insert = Insertstatement.executeUpdate( InsertInput );//executeUpdate����һ������ֵ
      		 keyfull = new String(seck);
                 keyfull = keyfull.concat(regpass);
      		 if (insert == 1)
      		 {        
                              JDialog dialog = new keyDialog(this,true,keyfull);
                               dialog.show();
                                NameField.setText("");
   		   		passwordField.setText("");
   		   		repasswordField.setText("");
   		   		passlenField.setText("");
   		   		passnumField.setText("");
      		 } 
      	} 
     	catch ( SQLException sqlex ) 
      	{ 
        	sqlex.printStackTrace(); 
      	}
           
     }

}