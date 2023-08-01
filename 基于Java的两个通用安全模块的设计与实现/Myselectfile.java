import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
public class Myselectfile extends JFrame {          
          public String path;
          public String fileName;
          public String full;
          public int id;
          public String inpassword;

          private JLabel FileLabel ;
          private JTextField FileField;
          private JButton OpenButton ;
	  private JButton SubmmitButton ;
	  private JButton cancelButton ;
          JPanel regPanel = new JPanel();
          TitledBorder titledBorder1;  
          GridBagLayout gridBagLayout1 = new GridBagLayout();//ע����岼�� 
          GridBagLayout layout = new GridBagLayout();//���岼��

//���췽��
   public Myselectfile(int userid,String password) 
   {
          id = userid;
          inpassword = password;
	  setTitle("�����ļ�����");
    	  setSize(500,300);
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
                jbcreat();
              }
             catch(Exception e){
             e.printStackTrace();
            } 
       }//���췽����� 

private void jbcreat() throws Exception{

          java.awt.Color mycolor=new Color(200,0,0);
            titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                                             white, new Color(165, 163, 151)), 
                                             "�ļ�����",TitledBorder.CENTER,
                                             TitledBorder.TOP,
                                             new Font("SansSerif",Font.BOLD,20),mycolor); 

                  regPanel.setLayout(gridBagLayout1);
                    regPanel.setBorder(titledBorder1);
                    this.getContentPane().setLayout(layout);

                     this.getContentPane().add(regPanel,
                                                new GridBagConstraints(0, 0, 3, 2, 1.0, 1.0
                                                , GridBagConstraints.NORTH, 
                                                 GridBagConstraints.BOTH,
                                                 new Insets(10, 10, 0, 10), 0, 0)); 

                  JPanel buttonPanel = createButtonPanel(); // sets global loginButton
                       this.getContentPane().add(buttonPanel,
                                                new GridBagConstraints(0, 5, 3, 1, 1.0, 1.0
                                                , GridBagConstraints.SOUTH, 
                                                GridBagConstraints.BOTH,
                                                new Insets(6, 85, 6, 6), 0, 0)); 

        //�ļ����ı���
        FileField = new JTextField(); // needed below
        FileField.setPreferredSize(new Dimension(300, 15));
        FileLabel = new JLabel();
        FileLabel.setFont(new java.awt.Font("����", 0, 15));
        FileLabel.setText("ѡ���ļ� : ");

    regPanel.add(FileLabel,
                     new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
                                            , GridBagConstraints.WEST, 
                                             GridBagConstraints.NONE,
                                             new Insets(12, 20, 0, 10), 8, 13));

        regPanel.add(FileField,                           //�û����ı�
                       new GridBagConstraints(0,2, 1, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(0, 50, 20, 15), 30, 11));

        //�򿪰�ť
         OpenButton = new JButton();
         OpenButton.setPreferredSize(new Dimension(50, 15));
         OpenButton.setFont(new java.awt.Font("����", 0, 12));
         OpenButton.setText("� ��");

  regPanel.add(OpenButton,
                  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
                  , GridBagConstraints.WEST, 
                  GridBagConstraints.NONE,
                  new Insets(0, 0, 20, 10), 8, 13));

              OpenButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                OpenFile();
            }
        });
System.out.println(id);
 }

    private JPanel createButtonPanel() 
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 0));
        SubmmitButton = new JButton();
        SubmmitButton.setFont(new java.awt.Font("����", 0, 12));
        SubmmitButton.setText("��  ��");
              SubmmitButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent event) 
            {
                SentFile();
            }
        });
        
        panel.add(SubmmitButton);
        panel.add(Box.createRigidArea(new Dimension(200,0)));

        cancelButton = new JButton();
        cancelButton.setFont(new java.awt.Font("����", 0, 12));
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
//���ļ��Ի��򷽷�
  private void OpenFile()
      {
       try{
            FileDialog dia=new FileDialog(Myselectfile.this);
            dia.setVisible(true);
            fileName=dia.getFile();
            if(fileName==null)
            FileField.setText("");
            else
             FileField.setText(dia.getDirectory()+fileName);
             path = dia.getDirectory();
             full = path.concat(fileName);
             }catch(Exception e){                
            }
           }
//�����ļ�����
private void SentFile()
      {
      if(FileField.getText().trim().equals(""))
           {
   	      JOptionPane.showMessageDialog( this,"��ѡ��Ҫ�����ļ�!" ); 
         	return; 	
   		}
       else
           {
              MySSLClientFile myfile = new MySSLClientFile(id,inpassword,path,fileName);
               if(myfile.sentf == 1)
                {
   	         JOptionPane.showMessageDialog( this,"�ļ�����ɹ�!" );
                 FileField.setText(""); 
         	 return; 	
   		 }
                 else
                 {
                   JOptionPane.showMessageDialog( this,"�ļ�����ʧ��!" );
                   return; 
                  }
               }
         
          }

}      

