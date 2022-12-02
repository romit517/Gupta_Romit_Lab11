import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.File;

import javax.swing.JButton;

import javax.swing.JFileChooser;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;

public class RecursiveListFrame extends JFrame implements ActionListener {

    // DECLARATION OF COMPONENTS

    private JButton fileChooserBtn, quitBtn;

    private JTextArea output;

    private JLabel currDirLabel;

    //Constructor

    public RecursiveListFrame() {



        super("Recursive List Files");



        JPanel btnPanel = new JPanel();



        fileChooserBtn = new JButton("Choose Directory");

        quitBtn = new JButton("Quit");

        btnPanel.add(fileChooserBtn);

        btnPanel.add(quitBtn);



        output = new JTextArea(10, 50);



        currDirLabel = new JLabel("Current directory:");



        add(btnPanel, BorderLayout.PAGE_START);

        add(new JScrollPane(output));

        add(currDirLabel, BorderLayout.PAGE_END);



        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setSize(700, 700);

        setVisible(true);



        fileChooserBtn.addActionListener(this);

        quitBtn.addActionListener(this);

    }

    public static void main(String[] args) {



        new RecursiveListFrame();

    }

    @Override

    public void actionPerformed(ActionEvent e) {



        if (e.getSource().equals(fileChooserBtn)) {



            output.setText("");



            JFileChooser fileChooser = new JFileChooser();

            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);



            int status = fileChooser.showOpenDialog(this);



            if (status == JFileChooser.APPROVE_OPTION) {



                File f = fileChooser.getSelectedFile();



                currDirLabel.setText("Current directory: " + f);



                listFiles(f);

            }

        } else if (e.getSource().equals(quitBtn)) {



            System.exit(0);

        }

    }


    private void listFiles(File file) {



        if (file.isFile()) {

            output.append(file + "\n");

        } else {



            File files[] = file.listFiles();



            if (files != null) {



                for (File f : files) {



                    output.append(f + "\n");



                    if (f.isDirectory()) {

                        listFiles(f);

                    }

                }

            }

        }

    }

}