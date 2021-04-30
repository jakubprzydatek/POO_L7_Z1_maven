package GUI.popups;

import GUI.UserTree;
import model.UserType;
import notifiers.EventAggregator;

import javax.swing.*;

public class AddPopUpMenu extends JPopupMenu {
    private JMenuItem addLecturer;
    private JMenuItem addStudent;
    private JFrame frame;
    private UserTree userTree;

    private EventAggregator eventAggregator;
    public AddPopUpMenu(JFrame frame, UserTree userTree)
    {
        eventAggregator = new EventAggregator();
        this.userTree = userTree;
        eventAggregator.addSubscriber(userTree);
        this.frame = frame;
        addLecturer = new JMenuItem("Add Lecturer");
        addStudent = new JMenuItem("Add Student");
        add(addLecturer);
        add(addStudent);
        listenAddLecturer();
        listenAddStudent();
    }


    private AddUserModalFrame addLecturerModalFrame;
    private void listenAddLecturer()
    {
        addLecturer.addActionListener(e ->
        {
            addLecturerModalFrame = new AddUserModalFrame(frame, UserType.LECTURER, userTree);
            eventAggregator.addSubscriber(addLecturerModalFrame);
            eventAggregator.publish(e);
            eventAggregator.removeSubscriber(addLecturerModalFrame);
        });

    }

    private AddUserModalFrame addStudentModalFrame;
    private void listenAddStudent()
    {

        addStudent.addActionListener(e ->
        {
            addStudentModalFrame = new AddUserModalFrame(frame, UserType.STUDENT, userTree);
            eventAggregator.addSubscriber(addStudentModalFrame);
            eventAggregator.publish(e);
            eventAggregator.removeSubscriber(addStudentModalFrame);
        });

    }





}
