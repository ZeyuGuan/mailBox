import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Mailboxpage extends JFrame {

    private JFrame mailboxpage;

    //-----------------------recent mail declare
    private JPanel recMail,recInfo;

    private JLabel recTitle;
    private JLabel recPic;
    private JLabel recTime,rType,arType,recType;
    private ImageIcon recIcon;

    //-----------------------past mail declare
    private JPanel pastmail;

    //-----------------------topBar declare
    private JMenuBar menuBar;
    private JButton backButton;
    private JButton resetButton;
    private JButton openButton;

    //---------------

    private int mailboxnumber;

    public void init(int mailboxnumber) {

        DB rc = new DB();
        this.mailboxnumber = mailboxnumber;
        mailboxpage = new JFrame("MailBox");
        recMail = new JPanel();

        //--------------------------topBar
        menuBar = new JMenuBar();
        backButton = new JButton("back");
        resetButton = new JButton("reset");
        openButton = new JButton("open mailbox");



        menuBar.add(backButton);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(openButton);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(resetButton);

        backButton.setBackground(Color.white);
        backButton.setForeground(Color.black);
        openButton.setBackground(Color.white);
        openButton.setForeground(Color.black);
        resetButton.setBackground(Color.white);
        resetButton.setForeground(Color.black);
        menuBar.setBackground(new Color(214, 234, 248 ));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mailboxpage.dispose();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pastmail.removeAll();
                pastmail.setBackground(new Color(214, 234, 248 ));
            }
        });

//--------------------------------------------past Mail Page---------------------------------------------------//
        pastmail = new JPanel();
//        pastmail.setBackground(new Color(214, 234, 248 ));
        pastmail.setLayout(new BoxLayout(pastmail,BoxLayout.Y_AXIS));

        int recent = 0;
        if(mailboxnumber == 1) {
            ArrayList mails = rc.parseJSON(rc.makeGETRequest("https://studev.groept.be/api/a21ib2a02/mailboxworking"));
            mailinitialse(mails, recent);
        } else {
            ArrayList mails = rc.parseJSON(rc.makeGETRequest("https://studev.groept.be/api/a21ib2a02/mailboxdummydataLocation/" + mailboxnumber));
            mailinitialse(mails, recent);
        }

        JScrollPane jScrollPane = new JScrollPane(pastmail);
        jScrollPane.setPreferredSize(new Dimension(300,200));

//-----------------------------------------------assemble recent Mail page---------------------------------------------//

        Box rMail = Box.createVerticalBox();
        rMail.add(recTitle);
        rMail.add(recPic);
        rMail.add(recTime);
        rMail.add(recType);
        recMail.add(rMail);

        recMail.setBackground(new Color(214, 234, 238));

//        recMail.setLayout(null);
//
//
//        recTitle.setLocation(25,15);
//
//        recPic.setLocation(25,25);
//
//        recTime.setLocation(25,175);
//
//        recType.setLocation(25,185);
//
//        recMail.add(recTime);
//        recMail.add(recPic);
//        recMail.add(recTime);
//        recMail.add(recType);

//-------------------------------------add everything to frame--------------------------------------------//

        mailboxpage.setLayout(new BorderLayout());

        mailboxpage.add(menuBar,BorderLayout.PAGE_START);
        mailboxpage.add(recMail,BorderLayout.CENTER);
        mailboxpage.add(jScrollPane,BorderLayout.SOUTH);


//------------------------------------------------------general-----------------------------------------------------------//

        mailboxpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mailboxpage.setSize(320,500);
        mailboxpage.setResizable(false);
        mailboxpage.setVisible(true);
        mailboxpage.setLocationRelativeTo(null);
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    private void mailinitialse(ArrayList mails, int recent) {
        for(int i=0; i<mails.size();i = i+4) {
            String date = (String) mails.get(i);
            String time = (String) mails.get(i + 1);
            String weight = (String) mails.get(i + 2);
            String id = (String) mails.get(i + 3);
            PastMail mail = new PastMail();
            pastmail.add(mail.makePastMail(time, date, weight, id));

            if (Integer.parseInt(id) > recent) {
                recTime = new JLabel(date + "    " + time);
                recType = new JLabel("weight:   " + weight);
                recPic = new JLabel();
                recTitle = new JLabel("recent mail preview");


                recTitle.setFont(recTitle.getFont().deriveFont(16f));
                recIcon = new ImageIcon(this.getClass().getResource("RecentMail.jpg"));
                recIcon.setImage(recIcon.getImage().getScaledInstance(250, 150, Image.SCALE_DEFAULT));
                recPic.setIcon(recIcon);


            }
        }
    }

    public static void main(String[] args) {
        new Mailboxpage().init(0);
    }
}








