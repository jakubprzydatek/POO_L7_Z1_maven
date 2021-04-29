package GUI;

import GUI.popups.AddPopUpMenu;
import GUI.popups.ModifyUserModalFrame;
import lombok.Getter;
import lombok.Setter;
import model.Student;
import model.User;
import model.UserType;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
@Setter

public class Frame {
    private JFrame frame;
    private JSplitPane splitPane;
    private UserTree userTree;
    private String lastFromTreePath;
    private JLabel selectedLabel;
    private JScrollPane tableScrollPane;

    private UserTable userTable;
    private JTable jTable;
    public Frame()
    {
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        selectedLabel = new JLabel();

        frame.add(selectedLabel, BorderLayout.SOUTH);

        loadTree();
        //addSomePeople();
        loadTable();
        listenTree();
        listenTable();

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                userTree.getTree(), tableScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        frame.add(splitPane);

    }

    private void loadTree()
    {
        userTree = new UserTree();
        userTree.getTree().setComponentPopupMenu(new AddPopUpMenu(frame, userTree));
        lastFromTreePath = "";
    }

    private void loadTable()
    {
        userTable = new UserTable(userTree.getLecturers());
        jTable = userTable.getTable();
        tableScrollPane = new JScrollPane(jTable);

        /*AddUserModalFrame addUserModalFrame = new AddUserModalFrame(frame, UserType.STUDENT, userTree);
        addUserModalFrame.getJDialog().setVisible(true);*/
    }

    private void addSomePeople()
    {
        User student = new Student();
        student.set_name("Kuba");
        student.setSurname("Przydatek");
        student.setBirthDate("19/07/1999");
        student.setAddress("Tutaj");
        userTree.addStudentToTree(student);
        student.set_name("Anżej");
        userTree.addStudentToTree(student);
        student.set_name("Wojciech");
        userTree.addLecturerToTree(student);
        userTree.refreshStudents();
    }

    private void listenTree()
    {
        userTree.getTree().getSelectionModel().addTreeSelectionListener(e -> {
            lastFromTreePath = e.getPath().getLastPathComponent().toString();

            if(e.getPath().toString().contains("Student"))
            {
                if(!e.getPath().getLastPathComponent().toString().contains("Student"))
                {
                    System.out.println(userTree.getTree().getMinSelectionRow() - 3);
                }
                userTable.setTable(userTree.getStudents());
            }else{
                if(!e.getPath().getLastPathComponent().toString().contains("Lecturer"))
                {
                    System.out.println(userTree.getTree().getMinSelectionRow() - 2);
                }
                userTable.setTable(userTree.getLecturers());
            }
            selectedLabel.setText(e.getPath().toString());

            jTable = userTable.getTable();

        });
    }

    private void listenTable()
    {
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e) && jTable.getSelectedRow() >= 0)
                {
                    UserType userType;
                    if(lastFromTreePath.equals("Student")){
                        userType = UserType.STUDENT;
                    }else{
                        userType = UserType.LECTURER;
                    }
                    ModifyUserModalFrame modalFrame = new ModifyUserModalFrame(frame, userType, userTree, jTable.getSelectedRow());
                    modalFrame.getJDialog().setVisible(true);
                }
            }
        });
    }


}
