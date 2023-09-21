package Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Image;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.border.Border;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import org.eclipse.ui.internal.themes.FontDefinition;

import java.awt.Font;
import java.awt.FontFormatException;

import javax.swing.JButton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;

public class userInterfaceDesign {

	private JFrame frmYearbookApplication;
	private JFormattedTextField idInputBG;
	private Properties prop = new Properties();
	private Connection con = null;
	
	
//	THESE ARE THE CUSTOMIZATION VALUES!!!!!!!!
	public String schoolNameString = "HERRIMAN HIGH";
	
//	SAVE YOUR IMAGES AS PRIMARY IMAGE & SECONDARY IMAGE
//	public final String primaryImageAddress = "Images/primaryImage.png";
	public final String secondaryImageAddress = "Images/secondaryImage.png";
	
	public Color primaryColor = new Color(39, 68, 114);
	public Color secondaryColor = new Color(154, 38, 57);
	
	public Color lightColor = new Color(243, 239, 233);
	public Color darkColor = new Color(1, 23, 55);
	
	
	public Color successColor = new Color(0, 255, 3);
	public Color failColor = new Color(255, 0, 3);
	
	private static Font lightItalicFont = new Font("Verdana", Font.BOLD | Font.ITALIC, 14);
	private static Font lightFont = new Font("Verdana", Font.BOLD, 14);
	private static Font subtitleFont = new Font("Verdana", Font.BOLD, 24);
	private static Font titleFont = new Font("Verdana", Font.BOLD, 30);
	
	private JFormattedTextField studentIDInput;
	private JLabel hasYearbookPaid;
	private JLabel studId;
	private JLabel hasReceivedYearbook;
	private JLabel hasFees;
	private JLabel studentName;
	private JPanel yearbookIndicator;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		INITIALIZE CUSTOM FONTS
		try {
		     titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Raleway-Black.ttf"));
		     titleFont = titleFont.deriveFont(Font.BOLD, 40f);
		     
		     subtitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Aleo_Bold.ttf"));
		     subtitleFont = subtitleFont.deriveFont(Font.BOLD, 30f);
		     
//		     lightFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Montserrat.ttf"));
//		     lightFont = lightFont.deriveFont(Font.BOLD, 14f);
//		     
//		     lightItalicFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts/Montserrat.ttf"));
		     lightItalicFont = lightItalicFont.deriveFont(Font.BOLD | Font.ITALIC, 14f);
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
		
/*
		The code below will create a instance of this class and run it.
 */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userInterfaceDesign window = new userInterfaceDesign();
					window.frmYearbookApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public userInterfaceDesign() {
		try {
			initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the entire application backend
	 */
	private void initialize() throws IOException {
//		Creates/reads the config.properties file.
		properties();
		
//		Attempts to establish a connection to the mysql database set in the config.properties file.
        try {
			con = DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("IP") + ":" + prop.getProperty("Port") + "/" + prop.getProperty("Database"), prop.getProperty("User_Name") + "", prop.getProperty("Password") + "");
		} catch (SQLException e1) {
		}
//		
		frmYearbookApplication = new JFrame();
		frmYearbookApplication.getContentPane().setBackground(new Color(255, 255, 255));
		frmYearbookApplication.getContentPane().setLayout(null);
		
		frmYearbookApplication.setResizable(false);
		
		hasFees = new JLabel("FALSE");
		hasFees.setForeground(successColor);
		hasFees.setFont(lightItalicFont);
		hasFees.setBounds(1019, 348, 94, 31);
		frmYearbookApplication.getContentPane().add(hasFees);
		
		hasReceivedYearbook = new JLabel("FALSE");
		hasReceivedYearbook.setForeground(successColor);
		hasReceivedYearbook.setFont(lightItalicFont);
		hasReceivedYearbook.setBounds(1155, 369, 128, 21);
		frmYearbookApplication.getContentPane().add(hasReceivedYearbook);
		
		studId = new JLabel("0123456");
		studId.setForeground(lightColor);
		studId.setFont(lightFont);
		studId.setBounds(1074, 305, 144, 31);
		frmYearbookApplication.getContentPane().add(studId);
		
		hasYearbookPaid = new JLabel("TRUE");
		hasYearbookPaid.setForeground(successColor);
		hasYearbookPaid.setFont(lightItalicFont);
		hasYearbookPaid.setBounds(1105, 338, 144, 19);
		frmYearbookApplication.getContentPane().add(hasYearbookPaid);
		
//		JLabel schoolLogo_Left = new JLabel("");
//		schoolLogo_Left.setHorizontalAlignment(SwingConstants.CENTER);
//		schoolLogo_Left.setBounds(186, 117, 292, 177);
//		schoolLogo_Left.setIcon(iconLogo);
//		frmYearbookApplication.getContentPane().add(schoolLogo_Left);
		
		
//		THIS IS THE RIGHT IMAGE
		ImageIcon iconLogo = new ImageIcon(secondaryImageAddress);
		Image image = iconLogo.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iconLogo = new ImageIcon(newimg);  // transform it back
		
		JLabel schoolLogo_Right = new JLabel("");
		schoolLogo_Right.setHorizontalAlignment(SwingConstants.CENTER);
		schoolLogo_Right.setBounds(775, 279, 152, 147);
		schoolLogo_Right.setIcon(iconLogo);
		frmYearbookApplication.getContentPane().add(schoolLogo_Right);
		
//		THIS IS THE BUTTON THEY PRESS WHEN THEY GIVE THE YEARBOOK TO A STUDENT
		JButton giveButton = new JButton("Give");
		giveButton.setBackground(primaryColor);
		giveButton.setForeground(lightColor);
		giveButton.setBounds(972, 401, 121, 25);
		giveButton.setOpaque(false);
		giveButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		frmYearbookApplication.getContentPane().add(giveButton);
//		This will be the action when the button is clicked/pressed.

		
		JFormattedTextField giveBG = new JFormattedTextField();
		giveBG.setBounds(972, 401, 121, 25);
		giveBG.setBackground(lightColor);
		giveBG.setOpaque(false);
		giveBG.setBorder(new RoundedBorder(15, secondaryColor));
		frmYearbookApplication.getContentPane().add(giveBG);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBackground(primaryColor);
		searchButton.setForeground(lightColor);
		searchButton.setBounds(254, 425, 152, 31);
		searchButton.setOpaque(false);
		searchButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		frmYearbookApplication.getContentPane().add(searchButton);
		searchButton.addActionListener(e -> database());
		
		JFormattedTextField searchBG = new JFormattedTextField();
		searchBG.setBounds(254, 425, 152, 31);
		frmYearbookApplication.getContentPane().add(searchBG);
		searchBG.setBackground(lightColor);
		searchBG.setOpaque(false);
		searchBG.setBorder(new RoundedBorder(15, darkColor));
		
//		SCHOOL NAME! SAVE THE SCHOOL NAME THEY INPUT AND PUT THAT INSTEAD OF THE SECTION THAT SAYS HERRIMAN HIGH
		JLabel schoolName = new JLabel(schoolNameString);
		schoolName.setHorizontalAlignment(SwingConstants.CENTER);
		schoolName.setForeground(lightColor);
		schoolName.setFont(titleFont);
		schoolName.setBounds(0, 305, 670, 48);
		frmYearbookApplication.getContentPane().add(schoolName);
		
		
//		STUDENTS INPUT THEIR STUDENT ID HERE
		studentIDInput = new JFormattedTextField();
		studentIDInput.setText("Enter Student ID");
		studentIDInput.setToolTipText("Enter Student ID...");
		studentIDInput.setOpaque(false);
		studentIDInput.setHorizontalAlignment(SwingConstants.LEFT);
		studentIDInput.setBounds(216, 371, 223, 25);
		studentIDInput.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		frmYearbookApplication.getContentPane().add(studentIDInput);
//		Limits the amount of characters allowed in the text box to number below.
		studentIDInput.setDocument(new JTextFieldLimit(7));
		studentIDInput.addKeyListener(new KeyListener() {
//			This will submit the text in the text box when enter is typed.
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					database();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}

//			This will auto submit text in the text box when 7 characters are typed.
			@Override
			public void keyReleased(KeyEvent e) {
				if(studentIDInput.getText().length() == 7){
                    database();
                }
			}
		});
		

//		THIS PART IS IRRELEVANT TO ANYTHING, THIS IS JUST THAT PART THAT SAYS REQUIREMENT: 
//		(only change the other text fields these don't need to be changed)
		JLabel lblNewLabel_1_1_1 = new JLabel("RECEIVED YEARBOOK:");
		lblNewLabel_1_1_1.setForeground(lightColor);
		lblNewLabel_1_1_1.setFont(lightItalicFont);
		lblNewLabel_1_1_1.setBounds(962, 371, 190, 19);
		frmYearbookApplication.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("FEES:");
		lblNewLabel_1_1.setForeground(lightColor);
		lblNewLabel_1_1.setFont(lightItalicFont);
		lblNewLabel_1_1.setBounds(962, 351, 45, 25);
		frmYearbookApplication.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("YEARBOOK PAID:");
		lblNewLabel_1.setForeground(lightColor);
		lblNewLabel_1.setFont(lightItalicFont);
		lblNewLabel_1.setBounds(962, 335, 150, 25);
		frmYearbookApplication.getContentPane().add(lblNewLabel_1);
		
		studentName = new JLabel("STUDENT NAME");
		studentName.setForeground(lightColor);
		studentName.setFont(subtitleFont);
		studentName.setBounds(962, 279, 279, 31);
		frmYearbookApplication.getContentPane().add(studentName);
		
		JLabel lblNewLabel = new JLabel("STUDENT ID:");
		lblNewLabel.setForeground(lightColor);
		lblNewLabel.setBounds(962, 305, 178, 31);
		lblNewLabel.setFont(lightFont);
		frmYearbookApplication.getContentPane().add(lblNewLabel);
		
		JPanel pictureBGBox = new JPanel();
		pictureBGBox.setBackground(primaryColor);
		pictureBGBox.setBounds(775, 279, 150, 147);
		frmYearbookApplication.getContentPane().add(pictureBGBox);
		
		JPanel innerBox = new JPanel();
		innerBox.setBounds(749, 239, 538, 235);
		innerBox.setForeground(new Color(0, 0, 0));
		innerBox.setBackground(darkColor);
		frmYearbookApplication.getContentPane().add(innerBox);


		JPanel indicatorInnerOutline = new JPanel();
		indicatorInnerOutline.setBounds(748, 238, 540, 237);
		indicatorInnerOutline.setBackground(lightColor);
		frmYearbookApplication.getContentPane().add(indicatorInnerOutline);
		

//		THIS HERE IS WHAT INDICATES IF THEY CAN PICK UP THE YEARBOOK WITH JUST A GLANCE
//		IF THEY CAN SET THE Background COLOR GREEN (else RED)
		yearbookIndicator = new JPanel();
		yearbookIndicator.setBackground(successColor);
		yearbookIndicator.setForeground(new Color(0, 0, 0));
		yearbookIndicator.setBounds(741, 232, 553, 250);
		frmYearbookApplication.getContentPane().add(yearbookIndicator);
		
		JPanel indicatorOuterOutline = new JPanel();
		indicatorOuterOutline.setBackground(lightColor);
		indicatorOuterOutline.setBounds(739, 230, 557, 254);
		frmYearbookApplication.getContentPane().add(indicatorOuterOutline);
				
		idInputBG = new JFormattedTextField();
		idInputBG.setBounds(208, 364, 247, 40);
		frmYearbookApplication.getContentPane().add(idInputBG);
		idInputBG.setText("Enter Student ID...");
		idInputBG.setHorizontalAlignment(SwingConstants.CENTER);
		idInputBG.setToolTipText("Enter Student ID...");
		idInputBG.setBackground(lightColor);
		idInputBG.setOpaque(false);
		idInputBG.setBorder(new RoundedBorder(15, lightColor));
		frmYearbookApplication.setTitle("Yearbook Application");
		frmYearbookApplication.setBounds(100, 100, 1348, 766);
		
		JPanel outerBox = new JPanel();
		outerBox.setBackground(darkColor);
		outerBox.setBounds(725, 215, 586, 283);
		frmYearbookApplication.getContentPane().add(outerBox);

		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(secondaryColor);
		rightPanel.setBounds(670, 0, 771, 727);
		frmYearbookApplication.getContentPane().add(rightPanel);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(primaryColor);
		leftPanel.setBounds(-101, 0, 771, 727);
		frmYearbookApplication.getContentPane().add(leftPanel);		
		frmYearbookApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void database(){
//      This method handles most of the backend of the Program.

        try{
//        	Create a SQL stament that will execute SQL commands as needed.
            Statement statement = con.createStatement();

//			Allows us to get the querey results from the database.
            ResultSet rs = statement.executeQuery("Select * from userdata where ID = '" + studentIDInput.getText() + "'");

//			This will get the result from the result set.
            if(rs.next()){
//				The next 5 lines are just getting the data from the columns into jlabels.
                studentName.setText(rs.getString("Name"));
                studId.setText("" + rs.getInt("ID"));
                hasReceivedYearbook.setText(rs.getBoolean("Collected") +"");
                hasYearbookPaid.setText(rs.getBoolean("Paid") + "");
                hasFees.setText(rs.getBoolean("Fees") + "");

//				Will change the color of the text if has already picked up year book or not.
                if(hasReceivedYearbook.getText().equalsIgnoreCase("true")){
                    hasReceivedYearbook.setForeground(failColor);
                }else{
                    hasReceivedYearbook.setForeground(successColor);
                }

//				Will change the color of the text if has or hasn't purcahsed a yearbook.
                if(hasYearbookPaid.getText().equalsIgnoreCase("true")){
                    hasYearbookPaid.setForeground(successColor);
                }else{
                    hasYearbookPaid.setForeground(failColor);
                }

//				From the result set will get the has fees boolean.
                boolean fees = rs.getBoolean("Fees");

//				Will change the color of the text if has or doesn't have any fees.
                if(fees){
                    hasFees.setForeground(failColor);
                }else{
                    hasFees.setForeground(successColor);
                }
                
                hasFees.setText(fees + "");

//				Will set yearbook indicator green if all prvious colors are green
                if(hasYearbookPaid.getForeground() == successColor && hasReceivedYearbook.getForeground() == successColor && hasFees.getForeground() == successColor){
                	
                   //canGiveYearbookLabel.setText("You can give them a yearbook");
                	yearbookIndicator.setBackground(successColor);
                    //canGiveYearbookLabel.setForeground(Color.green);
                   /* PreparedStatement ps = con.prepareStatement("update data.userdata set Collected = ? where ID = ? ");
                    ps.setBoolean(1,true);
                    ps.setInt(2, Integer.parseInt(studentIDInput.getText()));
                    ps.executeUpdate();*/
                    
                }else{
                	yearbookIndicator.setBackground(failColor);
                }

            }else{
            	//IN the event that they number or thing they entered is not in the database
                studentName.setText("Doesn't exist in the database.");
                studId.setText("Doesn't exist in the database.");
                hasReceivedYearbook.setText("Doesn't exist in the database.");
                hasYearbookPaid.setText("Doesn't exist in the database.");
                hasFees.setText("Doesn't exist in the database.");

                hasReceivedYearbook.setForeground(failColor);
                hasYearbookPaid.setForeground(failColor);
                hasFees.setForeground(failColor);
                yearbookIndicator.setBackground(failColor);
            }

//			Rests text box, repaints UI, cloeses the result set and statement.
            studentIDInput.setText("");
            frmYearbookApplication.repaint();
            rs.close();
            statement.close();
        }catch(SQLException ex){
//			Incase the application can't connect to the database
        	studentName.setText("Couldn't connect to database");
            studId.setText("Couldn't connect to database");
            hasReceivedYearbook.setText("Couldn't connect to database");
            hasYearbookPaid.setText("Couldn't connect to database");
            hasFees.setText("Couldn't connect to database");

            hasReceivedYearbook.setForeground(failColor);
            hasYearbookPaid.setForeground(failColor);
            hasFees.setForeground(failColor);
            yearbookIndicator.setBackground(failColor);
        }
//		Forces the textbox to be selected
        studentIDInput.grabFocus();
    }
	
	private void properties() {
		try {
		File file = new File("config.properties");
		if(!file.exists()) {
			prop.setProperty("IP", "10.204.72.28");
			prop.setProperty("Database", "data");
			prop.setProperty("Table", "userdata");
			prop.setProperty("User_Name", "Java");
			prop.setProperty("Password", "?yUd+GTMc4-M]h`@yLC(y+:j");
			prop.setProperty("Port", "4040");
			
			prop.store(new FileOutputStream(file), null);
		}
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		
		fis.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}


class RoundedBorder implements Border {
	
	private int radius;
	private Color c;
	
	RoundedBorder(int radius, Color fillColor) {
		this.radius = radius;
		this.c = fillColor;
	}
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	}
	
	@Override
	public boolean isBorderOpaque() {
		return true;
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.setColor(this.c);
		g.fillRoundRect(x,y,width-1,height-1,radius,radius);
	}
}

class JTextFieldLimit extends PlainDocument{
	private int limit;
	
	JTextFieldLimit(int limt){
		super();
		this.limit = limt;
	}
	
	public void insetString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if(str == null)
			return;
		
		if((getLength() + str.length()) <= limit)
			super.insertString(offset,str,attr);
	}
}

