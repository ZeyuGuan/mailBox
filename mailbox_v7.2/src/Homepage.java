import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class Homepage extends JFrame{

    private JPanel bottomPanel, midPanel;           //  declare mid panel components
    private static int counter = 1;
    private Mailboxpage mailboxpage = new Mailboxpage();
    public JFrame homepage = new JFrame("homepage");
    private JMenuBar menubar;
    public ArrayList<JButton> buttonList = new ArrayList<>();
    public ArrayList<JMenuItem> settingsList = new ArrayList<>();

    public JMenuBar menuBar;                           //  declare menubar (aka top panel) components
    public JMenu menuSetting, menuSetting3, menuUser, st1;
    public JLabel label;
    public JMenuItem  ur1, ur2;

    public void init(){

        bottomPanel = new JPanel();         //panel instantiation, top "panel" is a menubar
        midPanel = new  JPanel();
        mailboxpage.setVisible(false);

//----------------------------------------------adding everything to the frame------------------------------------------------//

        homepage.setLayout(new BorderLayout());       //defines gaps between panels (doesnt work for some reason) + defines which layout is used
        homepage.add(makeTopbar(), BorderLayout.PAGE_START);     //set panels in right spot
        homepage.add(midPanel, BorderLayout.CENTER);
        homepage.add(bottomPanel, BorderLayout.PAGE_END);


//-----------------------------------------------mailboxes------------------------------------------------------------------//

        for(int i = 0; i < 6; i++) {        //make all buttons for mailboxes
            JButton button = new JButton("Mailbox " + (i+1));
            button.setPreferredSize(new Dimension (295,50));
            button.setBackground(Color.white);
            button.setForeground(Color.black);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setIcon(new ImageIcon(this.getClass().getResource("mailbox.png")));
            buttonList.add(button);
        }

        for(int i = 0; i < 6; i++) {
            int mailbox = i + 1;
            buttonList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mailboxpage.init(mailbox);
                    homepage.setVisible(false);

                    mailboxpage.getBackButton().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mailboxpage.dispose();
                            homepage.setVisible(true);
                        }
                    });
                }
            });
        }

        JLabel mailboxTitle = new JLabel("Your mailboxes                  ", SwingConstants.LEFT);

        FlowLayout midPanelLayout = new FlowLayout(FlowLayout.LEFT);        //set type of layout of midpanel
        midPanel.setLayout(midPanelLayout);

        midPanel.add(Box.createVerticalStrut(50));        //assembly of mid panel
        midPanel.add(mailboxTitle);
        midPanel.add(Box.createVerticalStrut(10));
        midPanel.add(buttonList.get(0));

 //---------------------------------------------------bottom bar--------------------------------------------------------------//

        JButton bottomButton1 = new JButton("delete");         //instantiate bottom Panel components
        bottomButton1.setPreferredSize(new Dimension (100,  40));
        JButton bottomButton2 = new JButton("add");
        bottomButton2.setPreferredSize(new Dimension (100,  40));

        bottomButton1.addActionListener(new ActionListener() {          //delete button
            @Override
            public void actionPerformed(ActionEvent e) {
                if (counter == 6) {counter = 5;}
                if (counter > 0) {
                    buttonList.get(counter).setVisible(false);
                    midPanel.remove(buttonList.get(counter));
                    label.setText("total mailboxes: " + counter);
                    buttonList.get(counter).setText("Mailbox " + (counter+1));
                    menuSetting3.remove(settingsList.get(counter));
                    counter = counter - 1;

                } else {
                    errorMessage("can't remove default mailbox");
                }
            }
        });

        bottomButton2.addActionListener(new ActionListener() {              //add button
            @Override
            public void actionPerformed(ActionEvent e) {
                if(counter == 0) {counter = 1;}
                if (counter < 6) {
                    label.setText("total mailboxes: " + (counter+1));
                    buttonList.get(counter).setVisible(true);
                    midPanel.add(buttonList.get(counter));
                    menuSetting3.add(settingsList.get(counter));
                    counter++;
                } else {
                    errorMessage("max amount of mailboxes reached");
                }
            }
        });

        bottomPanel.add(bottomButton1);                 //assembly bottom panel (default layout is used, no need to set this explicitly)
        bottomPanel.add(bottomButton2);
//------------------------------------settings-----------------------------------------------------------//

        for(int i = 0; i < 6; i++) {
            int box = i;
            settingsList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String whatTheUserEntered = JOptionPane.showInputDialog(new JFrame(), "change name:");
                    if (whatTheUserEntered != null) {
                        buttonList.get(box).setText(whatTheUserEntered);
                    }
                }
            });
        }



//------------------------------------------------------aesthetics-----------------------------------------------------------//

        mailboxTitle.setFont (mailboxTitle.getFont ().deriveFont (20f));
        midPanel.setBackground(new Color(235, 245, 251 ));
        bottomButton1.setBackground(Color.white);
        bottomButton1.setForeground(Color.black);
        bottomButton2.setBackground(Color.white);
        bottomButton2.setForeground(Color.black);
        bottomPanel.setBackground(new Color(214, 234, 248 ));

//------------------------------------------------------general-----------------------------------------------------------//

        homepage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homepage.setSize(320,500);
        homepage.setResizable(false);
        homepage.setLocationRelativeTo(null);
        homepage.setVisible(true);
    }

    private void errorMessage(String message) {
        JOptionPane.showMessageDialog(new JFrame(), message);
    }

    public ArrayList<JButton> getButtonList() {
        return buttonList;
    }

    public JMenuBar makeTopbar() {
        menuBar = new JMenuBar();
        menuSetting = new JMenu("settings");     //instantiate menubar (topPanel) components
        menuSetting3 = new JMenu("change name");
        menuUser = new JMenu("User");

        for(int i=0; i < 6; i++) {                        //make all settings
            JMenuItem setting = new JMenuItem("Mailbox " + (i+1));
            settingsList.add(setting);
        }

        st1 = new JMenu("delete mail");
        ur1 = new JMenuItem("manage user");
        ur2 = new JMenuItem("logout");

        label = new JLabel("BasicHomepageDemo");

        menuSetting.add(st1);               //assembly Settings
        menuSetting.addSeparator();
        menuSetting.add(menuSetting3);
        menuSetting3.add(settingsList.get(0));

        menuUser.add(ur1);          //assembly Users
        menuUser.add(ur2);

        menuBar.add(menuSetting);                       //add components to topPanel
        menuBar.add(Box.createHorizontalGlue());        //makes the label centered
        menuBar.add(label);
        menuBar.add(Box.createHorizontalGlue());        //put user menu in upper right corner
        menuBar.add(menuUser);

        menuBar.setBackground(new Color(214, 234, 248 ));
        return menuBar;
    }

    public static void main(String[] args) {
        new Homepage().init();
    }

}
