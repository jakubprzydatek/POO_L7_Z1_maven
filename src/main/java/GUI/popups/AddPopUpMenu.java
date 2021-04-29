package GUI.popups;

import GUI.UserTree;
import model.UserType;

import javax.swing.*;

public class AddPopUpMenu extends JPopupMenu {
    private JMenuItem addLecturer;
    private JMenuItem addStudent;
    private JFrame frame;
    private UserTree userTree;
    public AddPopUpMenu(JFrame frame, UserTree userTree)
    {
        this.userTree = userTree;
        this.frame = frame;
        addLecturer = new JMenuItem("Add Lecturer");
        addStudent = new JMenuItem("Add Student");
        add(addLecturer);
        add(addStudent);
        listenAddLecturer();
        listenAddStudent();
    }

    private void listenAddLecturer()
    {
        AddUserModalFrame addUserModalFrame = new AddUserModalFrame(frame, UserType.LECTURER, userTree);
        addLecturer.addActionListener(e -> addUserModalFrame.getJDialog().setVisible(true));
        userTree.refreshLecturers();
    }

    private void listenAddStudent()
    {
        AddUserModalFrame addUserModalFrame = new AddUserModalFrame(frame, UserType.STUDENT, userTree);
        addStudent.addActionListener(e -> addUserModalFrame.getJDialog().setVisible(true));
        userTree.refreshStudents();
    }





}
