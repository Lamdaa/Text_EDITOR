import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

//    Contents of TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    //File menu items.
    JMenuItem saveFile,newFile,openFile;
    //Edit menu items.
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;

    //Creating the constructor for textEditor class;
    TextEditor(){
        //Creating Instance Of JFrame Library.
        frame = new JFrame();

        //Initialising the menuBar
        menuBar = new JMenuBar();

        //Initialising  Menu Contents
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialising File menu item.
        newFile = new JMenuItem("NEW WINDOW");
        openFile = new JMenuItem("OPEN FILE");
        saveFile = new JMenuItem("SAVE FILE");

        //Adding ActionListeners to Listen to the actions performed through these buttons.
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding file menu to menuList
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialising Edit menu items.
        cut = new JMenuItem("CUT");
        copy = new JMenuItem("COPY");
        paste = new JMenuItem("PASTE");
        selectAll = new JMenuItem("SELECT ALL");
        close = new JMenuItem("CLOSE");

        //Adding ActionListeners to Listen to the actions performed through these buttons.
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding Edit menus to menuList.

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Adding Menus to the menu bar.
        menuBar.add(file);
        menuBar.add(edit);

        textArea = new JTextArea();

        //setting the menuBar to Frame.
        frame.setJMenuBar(menuBar);
        menuBar.setBackground(Color.red);

        //create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(8,8,8,8));
        panel.setLayout(new BorderLayout(0,0));
        panel.setBackground(Color.red);

        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);

        //adding scroll features
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);
        frame.setTitle("TEXT EDITOR");
        frame.setBounds(50,50,400,400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //performing cut action
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //performing copy action
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //performing paste action
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //performing selectAll action
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //performing close action
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //performing open file action
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked on the Open File Menu
            if(chooseOption==JFileChooser.APPROVE_OPTION) {
                //Getting Selected File
                File file = fileChooser.getSelectedFile();
                //Getting the path of the selected file
                String filePath = file.getPath();
                try {
                    //Initialising the File reader
                    FileReader fileReader = new FileReader(filePath);

                    //Initialise an buffer reader to read the contents from the file which we need to open
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermidiate = "", output = "";

                    //read contents of the file line by line
                    while ((intermidiate = bufferedReader.readLine()) != null) {
                        output += intermidiate + "\n";
                    }

                    //set the output string to text area
                    textArea.setText(output);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Performing the save file action
            JFileChooser fileChooser1 = new JFileChooser("C:");

            //get choose option from file chooser
            int chooseOption1 = fileChooser1.showSaveDialog(null);

            //check if we selected save button
            if(chooseOption1==JFileChooser.APPROVE_OPTION){

                //create new file white chosen directory path and file name
                File file1 = new File(fileChooser1.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    //Creating file writer because we need to write this file this time
                    FileWriter fileWriter1 = new FileWriter(file1);
                    //initialise buffer writer
                    BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
                    //write contents of text area  to file
                    textArea.write(bufferedWriter1);
                    bufferedWriter1.close();
                }
                catch(IOException iioException){
                    iioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newtextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}