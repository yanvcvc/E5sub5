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
   
      
    //GUI��������	
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
        GridBagLayout gridBagLayout1 = new GridBagLayout();//ע����岼�� 
        GridBagLayout layout = new GridBagLayout();//���岼��

	private String  regname;
        private String oldpass;
        private String  regpass;
        private  String chagepass;

   public ChangeFrame() 
   {
	setTitle("�������");
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
       }//���췽����� 

private void jbchange() throws Exception{

          java.awt.Color mycolor=new Color(200,0,0);
            titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                                             white, new Color(165, 163, 151)), 
                                             "Ĭ�Ͽ������",TitledBorder.CENTER,
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
                                             new Insets(12, 20, 12, 10), 8, 13));
        regPanel.add(NameField,                           //�û����ı�
                       new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(13, 15, 13, 20), 30, 11));

        //2�� ��  �� ��
        oldpasswordField = new JPasswordField(); // needed below
        oldpasswordField.setPreferredSize(new Dimension(150, 20));
        oldpasswordLabel = new JLabel();
        oldpasswordLabel.setFont(new java.awt.Font("����", 1, 15));
        oldpasswordLabel.setText("�� ¼ �� �� ��");
        
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

        //3�� �� �� �� ��
        passwordField = new JPasswordField(); // needed below
        passwordField.setPreferredSize(new Dimension(150, 20));
        passwordLabel = new JLabel();
        passwordLabel.setFont(new java.awt.Font("����", 1, 15));
        passwordLabel.setText("�� �� �� �� ��");

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

	//4�� �� �� ��
	repasswordField = new JPasswordField(); // needed below
        repasswordField.setPreferredSize(new Dimension(150, 20));
        repasswordLabel = new JLabel();
        repasswordLabel.setFont(new java.awt.Font("����", 1, 15));
        repasswordLabel.setText("ȷ �� �� �� ��");
        
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

        // ȷ����ť
        SubmmitButton = new JButton();
        SubmmitButton.setFont(new java.awt.Font("����", 1, 15));
        SubmmitButton.setText("ȷ  ��");
        
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

        // ���ذ�ť
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
   			JOptionPane.showMessageDialog( this,"�뽫��������������!" ); 
         	return; 	
   		}
           if( oldpasswordField.getText().equals( "" ))
                {
   			JOptionPane.showMessageDialog( this,"������ԭ������֤���!" ); 
         	return;                     
                  } 
           if(!passwordField.getText().equals(repasswordField.getText()))
   		{
   			JOptionPane.showMessageDialog( this,"�¿��һ��!" ); 
         	return; 	
   		}        
           
               regname = NameField.getText().trim();
               oldpass = oldpasswordField.getText().trim();
               regpass = passwordField.getText().trim();

              if(regpass.length()<6 ||regpass.length()>12) 
                     		{
   			JOptionPane.showMessageDialog( this,"�����Ϊ6-12֮��!!" ); 
         	return; 	
   		}
             //����ɿ���
                   ProductKey its = new MyProductKey();
                   String w[] = its.DigestKey(oldpass);
                   String checkpass = w[0];   

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
      		 String CheckQuery = "select * from Person where (NAME='"+regname +
   							  "' and	PASSWORD = '"+checkpass+"')";				     		
         	 InsertresultSet = Insertstatement.executeQuery( CheckQuery );   
         	 boolean Records = InsertresultSet.next();     
         	 if ( ! Records ) 
      		 { 
         		JOptionPane.showMessageDialog( this,"�Բ���û�д��û�������������" ); 
         		Insertconnection.close(); 
         		return; 
      		 }

    //������֤���� 
              ProductKey it = new MyProductKey();
              String u[] = it.DigestKey(regpass);
              chagepass = u[0];

               String searchmore = "select * from Person where PASSWORD = '"+chagepass+"'";
	        InsertresultSet = Insertstatement.executeQuery( searchmore );  
         	 
         	 boolean moreRecords = InsertresultSet.next();     
         	 
         	 if ( moreRecords ) 
      		 { 
         		JOptionPane.showMessageDialog( this,"�Բ��𣬿�����Ч������������" ); 
         		
         		Insertconnection.close(); 
         		return; 
      		 }               
        
      		 String InsertInput = "Update Person set PASSWORD ='"+chagepass+"'Where PASSWORD = '"+checkpass+"'";
  
      		 int insert = Insertstatement.executeUpdate( InsertInput );//executeUpdate����һ������ֵ 
      		 if (insert == 1)
      		 {
      		 	JOptionPane.showMessageDialog( this,"������ĳɹ���");
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