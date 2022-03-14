import javax.swing.*;
import java.awt.*;

public class TopBarBack {
    private JPanel panel;
    private JButton backButton;
    private JButton openButton;
    private JButton resetButton;

    public JPanel makeTopbarBack() {
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("back");
        resetButton = new JButton("reset");
        openButton = new JButton("open mailbox");

        panel.add(backButton);
        panel.add(resetButton);
        panel.add(openButton);


        panel.setBackground(new Color(214, 234, 248 ));
        backButton.setBackground(Color.white);
        backButton.setForeground(Color.black);
        openButton.setBackground(Color.white);
        openButton.setForeground(Color.black);
        resetButton.setBackground(Color.white);
        resetButton.setForeground(Color.black);

        return panel;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getResetButton() { return resetButton; }
}
