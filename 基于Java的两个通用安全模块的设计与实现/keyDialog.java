import javax.swing.*; 
import java.io.*;
import java.awt.*; 
import java.awt.event.*; 
import java.util.*; 
import java.lang.*;
public class keyDialog extends JDialog 
{	    
    //GUI��������
	private JFrame parent; 
	private JPanel TextPanel, ButtonPanel;//��������������
        private JButton saveButton;
	private JButton OkButton;   
	private JTextArea HelpArea; 
        FileOutputStream wf;  
        private String strkey ; 

        private String keyf;
        private String keys;
        private int keynum;
	
        StringBuffer tmp=new StringBuffer();
        StringBuffer temp=new StringBuffer();

    public keyDialog(JFrame parent, boolean modal,String key) 
    {      
        super(parent, modal);
        this.parent = parent;
        pack();
        strkey = key;
       	GridBagConstraints constraints = new GridBagConstraints();      	
        OkButton = new JButton( "ȷ  ��" );
        OkButton.setPreferredSize(new Dimension(70, 25));
    	OkButton.addActionListener( 
         new ActionListener() 
         { 
            public void actionPerformed( ActionEvent e ) 
            { 
               	setVisible(false);
               	dispose();
            } 
         }   
        ); 

        saveButton = new JButton( "ճ  ��" );
        saveButton.setPreferredSize(new Dimension(70, 25));
    	saveButton.addActionListener( 
         new ActionListener() 
         { 
            public void actionPerformed( ActionEvent e ) 
            { 
               	
               	save();
            } 
         }   
        ); 
        String[] keyarray = strkey.split(",");
        keynum = keyarray.length;
        if (keynum < 7)
           {keyf = strkey;
            keys = ""; }
        else
           {
             for(int i=0; i<5; i++)
                 {tmp.append(keyarray[i]+",");}
             keyf = new String(tmp);
             for(int i=5; i<keynum-1; i++)
                 {temp.append(keyarray[i]+",");} 
             temp.append(keyarray[keynum-1]);
             keys = new String(temp);}
       
    	HelpArea = new JTextArea();
    	HelpArea.setText("\n"+"	            һ��һ�ܿ���              \n\n "+
    					 "     �����ɹ���              \n"+
                                         "     ���п���ÿ���ɹ���¼һ�μ���Ч,������ʹ������Ŀ��\n"+
    					        keyf  +"\n"+
                                                 keys  +"\n"
    				     );
    	        TextPanel = new JPanel();
		TextPanel.setLayout( new BorderLayout());
		TextPanel.setBorder(BorderFactory.createEtchedBorder());
		TextPanel.add(HelpArea,BorderLayout.CENTER);
		
		ButtonPanel = new JPanel();
		TextPanel.setLayout( new GridBagLayout());
		constraints.weightx = 100;
                constraints.weighty = 100;
                constraints.gridx = 0;
                constraints.gridy = 0;
                constraints.gridwidth = 2;
                constraints.gridheight = 3;
                ButtonPanel.add(OkButton, constraints);

                constraints.gridx = 2;
                constraints.gridy = 0;
                ButtonPanel.add(saveButton, constraints);
	
 		Container c = getContentPane(); 
      	c.setLayout( new BorderLayout());      	
      	c.add( TextPanel ,BorderLayout.CENTER);
      	c.add( ButtonPanel ,BorderLayout.SOUTH);
      	setTitle("About");
      	setSize( 350, 250 );
      	setResizable(false);
      	setLocation(300,200);      	
    }
 private void save(){
      try{  wf = new FileOutputStream("c:\\Documents and Settings\\Administrator\\����\\key.txt");
        int le = strkey.length();
        byte[] keybyte = new byte[le];
        keybyte = strkey.getBytes("UTF8");
        wf.write(keybyte);
        wf.close();}catch(Exception e){}
        }  
}