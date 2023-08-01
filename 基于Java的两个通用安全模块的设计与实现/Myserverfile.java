import mypackage.*;
import java.io.*;
import java.lang.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

class Myserverfile extends JFrame {
          public String path;
          public String fileName;
          public String RecevefileN;
          public String sfileN;
          public String spathN;
          public String sfullpath;
          public int flag = 0;
          public String ofileN;
          public String opathN;
          public String ofullpath;

          public JLabel FilesaveLabel = new JLabel();
          public JLabel FileopenLabel = new JLabel();
          public JLabel mingwenLabel =  new JLabel();   
          public JTextField Filesavetext = new JTextField();
          public JTextField Fileopentext = new JTextField();
          public JTextArea Filecontenttext = new JTextArea(5,20);      
          public JButton saveButton = new JButton();
          public JButton openButton = new JButton();


//面板定义
JPanel recivedPanel = new JPanel();
JPanel jiemiPanel = new JPanel();

TitledBorder titledBorder1;
TitledBorder titledBorder2;

GridBagLayout gridBagLayout1 = new GridBagLayout();//整体面板布局
GridBagLayout gridBagLayout2 = new GridBagLayout();//接收面板布局
GridBagLayout gridBagLayout3 = new GridBagLayout();//解密面板布局
     
     public Myserverfile()
      {
        setTitle("服务器接收窗口");
    	setSize(800,600);
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
                jbInit();
              }
             catch(Exception e){
             e.printStackTrace();
            } 
       }

private void jbInit() throws Exception{

           java.awt.Color mycolor=new Color(200,0,0);
            titledBorder1 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                 white, new Color(165, 163, 151)), "密文接收",TitledBorder.CENTER,
                 TitledBorder.TOP,new Font("SansSerif",Font.BOLD,20),mycolor);             

            titledBorder2 = new TitledBorder(BorderFactory.createEtchedBorder(Color.
                 white, new Color(165, 163, 151)), "密文解密",TitledBorder.CENTER,
                 TitledBorder.TOP,new Font("SansSerif",Font.BOLD,20),mycolor);      

          FilesaveLabel.setText("文件保存路径：");
           FilesaveLabel.setFont(new java.awt.Font("宋体", 0, 12));
            
          FileopenLabel.setText("解密文件路径：");
          FileopenLabel.setFont(new java.awt.Font("宋体", 0, 12));
            
         mingwenLabel.setText("明 文：");
           mingwenLabel.setFont(new java.awt.Font("宋体", 1, 18));
           java.awt.Color my_color=new Color(0,85,13); 
           mingwenLabel.setForeground(my_color);

          Filesavetext.setText("");
          Fileopentext.setText("");
         saveButton.setText("浏览…");
         saveButton.setFont(new java.awt.Font("宋体", 0, 12));
         saveButton.setEnabled(false);
     saveButton.addActionListener(new ActionListener() 
       {
           public void actionPerformed(ActionEvent event) 
            {
               saveFile();
            }
        });
 
         openButton.setText("浏览…");
           openButton.setFont(new java.awt.Font("宋体", 0, 12));

      openButton.addActionListener(new ActionListener() 
       {
            public void actionPerformed(ActionEvent event) 
           {
              OpenFile();
           }
       });

         recivedPanel.setLayout(gridBagLayout2);
         recivedPanel.setBorder(titledBorder1);

        jiemiPanel.setLayout(gridBagLayout3);
         jiemiPanel.setBorder(titledBorder2);
        
        this.getContentPane().setLayout(gridBagLayout1);
        
         this.getContentPane().add(recivedPanel,
                         new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0
                                                , GridBagConstraints.NORTH, 
                                                GridBagConstraints.BOTH,
                                                new Insets(6, 6, 6, 6), 0, 0));    
        
         this.getContentPane().add(jiemiPanel,
                         new GridBagConstraints(0, 2, 5, 1, 1.0, 1.0
                                                , GridBagConstraints.SOUTH, 
                                                GridBagConstraints.BOTH,
                                                new Insets(6, 6, 6, 6), 0, 240)); 

             recivedPanel.add(FilesaveLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
                                     , GridBagConstraints.WEST, GridBagConstraints.NONE,
                                       new Insets(0, 10, 2, 2), 0, 0));

               recivedPanel.add(Filesavetext,                           
                       new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
                                              ,GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(2,90,3,10), 0, 0));

               recivedPanel.add(saveButton,                          
                       new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.EAST,
                                              GridBagConstraints.NONE,
                                              new Insets(2, 10, 3, 10), 0, 0));

        jiemiPanel.add(FileopenLabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
                                     , GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
                                       new Insets(2, 10, 2, 2),0, 0));


              jiemiPanel.add(Fileopentext,                           
                       new GridBagConstraints(1, 1, 2, 1, 1.0, 0.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.HORIZONTAL,
                                              new Insets(2, 90, 2, 10), 0, 0));

               jiemiPanel.add(openButton,                          
                       new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
                                              , GridBagConstraints.EAST,
                                              GridBagConstraints.NONE,
                                              new Insets(2, 10, 2,10), 0, 0));

        jiemiPanel.add(mingwenLabel, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
                                     , GridBagConstraints.CENTER, GridBagConstraints.NONE,
                                       new Insets(2,365, 2, 2),0, 0));


               jiemiPanel.add(new JScrollPane(Filecontenttext),                          
                       new GridBagConstraints(1, 3, 3, 7, 1.0, 1.0
                                              , GridBagConstraints.CENTER,
                                              GridBagConstraints.BOTH,
                                              new Insets(30, 60, 10, 60), 0,0));

     }

      public void OpenFile()
        {
          Filecontenttext.setEditable(true);
          Filecontenttext.setText("");
         try{  FileDialog dia=new FileDialog(Myserverfile.this);
               dia.setVisible(true);
               ofileN=dia.getFile();
           if(ofileN==null)
               Fileopentext.setText("");
          
           else
          Fileopentext.setText(dia.getDirectory()+ofileN);
           opathN = dia.getDirectory();
           ofullpath = opathN.concat(ofileN); 
            File filsecopen = new File(opathN,ofileN);     
             File fillminsave = new File("c:\\SSLfile",ofileN);       
           MysaveEnc_Decfile decAndsave = new MysaveEnc_Decfile();
           decAndsave.readsecwenAndSaveminwen(filsecopen, fillminsave); 
                
           InputStream readminwen = new FileInputStream(fillminsave); 
                        int avai=readminwen.available();
                         byte buf[]=new byte[1024];
                         while ((avai=readminwen.read(buf))>=0){
                         System.out.println("num="+avai+",");
                          String temp =new String(buf,0,avai);

                          Filecontenttext.append(temp);
                        }
                   Filecontenttext.setEditable(false);

              }catch(Exception e){     
            }
       }   
 
     public void saveFile()
         {
           try{ FileDialog dia=new FileDialog(Myserverfile.this,"",FileDialog.SAVE);
                    dia.setFile(RecevefileN) ;
                        dia.setVisible(true);
                String sfileN=dia.getFile();
                if(sfileN==null){
                flag = 0;
                Filesavetext.setText("");}
                else
                {Filesavetext.setText(dia.getDirectory()+sfileN);
                spathN = dia.getDirectory();
                sfullpath = spathN.concat(sfileN); 
                 flag = 1;}}catch(Exception e){
                 }           
            }

     public void message(){
         	      JOptionPane.showMessageDialog( this,"文件保存成功!" ); 
         	return; 
       }

}




