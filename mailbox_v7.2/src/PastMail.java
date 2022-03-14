import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PastMail {

    JPanel pastMail = new JPanel();
    JPanel pastInfo = new JPanel();
    JPanel pastType = new JPanel();
    JLabel pastIcon = new JLabel();
    JLabel pastTime = new JLabel("xx/xx/xxxx");
    JLabel pType    = new JLabel("weight:   ");
    JLabel apType   = new JLabel("type");
    private String id;

    JButton button = new JButton();



    public JPanel makePastMail(String time, String date, String type, String id){

        pastMail.setPreferredSize(new Dimension(245,60));
        pastMail.setBackground(new Color(214, 234, 248 ));

        this.setPastTime(date + "    "+ time);
        this.setPastType(type);
        this.id = id;
//            pastTime.setBackground(new Color(214, 234, 248 ));

        pastType.setLayout(new BorderLayout());
        pastType.add(pType,BorderLayout.WEST);
        pastType.add(apType,BorderLayout.CENTER);

        pastType.setBackground(new Color(235, 245, 251 ));

        pastIcon.setIcon(new ImageIcon(this.getClass().getResource("mailIcon.jpg")));

        pastInfo.setLayout(new BorderLayout());
        pastInfo.add(pastTime,BorderLayout.NORTH);
        pastInfo.add(pastType,BorderLayout.CENTER);

        pastMail.setLayout(null);

        pastIcon.setBounds(30,5,80,50);
        pastInfo.setBounds(110,5,160,50);
        pastInfo.setBackground(new Color(235, 245, 251 ));
        pastInfo.setBorder(BorderFactory.createRaisedSoftBevelBorder());

        //---------cover pastMail(Jpanel) with transparent button--------------------//

        //-----------set up button
        button.setBounds(30,5,240,50);
        button.setOpaque(false);
        button.setBorder(null);


        //--------------assemble
        pastMail.add(pastIcon);
        pastMail.add(pastInfo);
        pastMail.add(button);


        //-----------------button action test--------------//
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JFrame pic = new JFrame();
//                JLabel p = new JLabel("Picture!");
                JLabel p = new JLabel();

                ImageIcon icon = new ImageIcon("D:\\tools\\Program Tech(Java)\\Projects\\Picture\\RecentMail.jpg");
                icon.setImage(icon.getImage().getScaledInstance(700,500,Image.SCALE_DEFAULT));

                p.setIcon(icon);

                p.setBounds(5,5,icon.getIconWidth(),icon.getIconHeight());

                pic.add(p);

                pic.pack();

//                    pic.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pic.setVisible(true);
                pic.setLocationRelativeTo(null);

            }
        });

        return pastMail;
    }

    private void setPastTime(String time){
        this.pastTime.setText(time);
    }

    private void setPastType(String type){
        this.apType.setText(type);
    }


}


