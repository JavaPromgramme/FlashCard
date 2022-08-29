import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlashCardBuilder {

    private JTextArea question;
    private JTextArea answer;
    private ArrayList<FlashCard> cardList;
    private JFrame frame;

    public FlashCardBuilder() {
        frame = new JFrame("Flash Card");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();

        // Create font
        Font greatFont = new Font("Helvetica Neue", Font.BOLD, 21);
        question = new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(greatFont);

        //question Area
        JScrollPane qJScrollPane  = new JScrollPane(question);
        qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        //Answer area
        answer = new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(greatFont);


        //Jscroll panel
        JScrollPane  aJScrollPane = new JScrollPane(answer);
        //Set vertical scroll only
        aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");

        //Create label
        JLabel qLabel = new JLabel("Question");
        JLabel aLabel = new JLabel("Answer");

        // Add components to mainPanel
        mainPanel.add(qLabel);
        mainPanel.add(qJScrollPane);
        mainPanel.add(aLabel);
        mainPanel.add(aJScrollPane);
        mainPanel.add(nextButton);

        // add to frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500,600);
        frame.setVisible(true);




    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardBuilder();
            }
        });

    }

}
