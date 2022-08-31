import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

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
        cardList = new ArrayList<FlashCard>();



        //Create label
        JLabel qLabel = new JLabel("Question");
        JLabel aLabel = new JLabel("Answer");

        // Add components to mainPanel
        mainPanel.add(qLabel);
        mainPanel.add(qJScrollPane);
        mainPanel.add(aLabel);
        mainPanel.add(aJScrollPane);
        mainPanel.add(nextButton);
        nextButton.addActionListener(new NextCardListener());

        // menu bar

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);


        //add eventListeners
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        // Add MenuBar to frame
        frame.setJMenuBar(menuBar);

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

    class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FlashCard flashCard =  new FlashCard(question.getText(),answer.getText());
            cardList.add(flashCard);
            clearCard();


        }
    }
    // Clear the textArea and focus on the question


    class NewMenuListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("New clicked");

        }
    }

    class SaveMenuListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            FlashCard card = new FlashCard(question.getText(), answer.getText());
            cardList.add(card);

            //Create file dialog
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());

        }


    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();

    }

    private void saveFile(File selectedFile) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

            Iterator<FlashCard>  cardIterator = cardList.iterator();
            while(cardIterator.hasNext()){

                FlashCard card = (FlashCard)cardIterator.next();
                writer.write(card.getQuestion() + "/" );
                writer.write(card.getAnswer() + "\n");



            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Couldn't write to file");
            e.printStackTrace();

        }





    }

}
