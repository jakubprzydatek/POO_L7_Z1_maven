package GUI.popups;

import GUI.UserTree;
import lombok.Getter;
import lombok.Setter;
import model.Lecturer;
import model.Student;
import model.User;
import model.UserType;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Setter
@Getter
public class AddUserModalFrame {
    private JPanel jPanel;
    private JDialog jDialog;

    private JTextField surnameField;
    private JTextField nameField;

    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    JFormattedTextField birthDateField;

    private JTextField addressField;


    private JLabel surnameLabel;
    private JLabel nameLabel;
    private JLabel birthDateLabel;
    private JLabel addressLabel;

    private JDialog modelDialog;
    private Container dialogContainer;

    private UserTree userTree;

    public AddUserModalFrame(JFrame frame, UserType userType, UserTree userTree)
    {
        this.userTree = userTree;
        jPanel = new JPanel();
        LayoutManager layout = new FlowLayout();
        jPanel.setLayout(layout);

        jDialog = createDialog(frame, userType);
        frame.getContentPane().add(jPanel, BorderLayout.CENTER);
    }

    private void setDialogForLecturer(JFrame frame)
    {
        modelDialog = new JDialog(frame, "Add Lecturer",
                Dialog.ModalityType.DOCUMENT_MODAL);
    }

    private void setDialogForStudent(JFrame frame)
    {
        modelDialog = new JDialog(frame, "Add Student",
                Dialog.ModalityType.DOCUMENT_MODAL);
    }

    private JDialog createDialog(JFrame frame, UserType userType)
    {
        if(userType == UserType.STUDENT)
        {
            setDialogForStudent(frame);
        }else{
            setDialogForLecturer(frame);
        }

        modelDialog.setBounds(132, 132, 800, 200);

        dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new GridLayout(0,4));


        setFieldsWithDefaultValue();
        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton addButton;
        if(userType == UserType.LECTURER)
        {
            addButton = new JButton("Add Lecturer");
            addButton.addActionListener(e ->
                    {
                        modelDialog.setVisible(false);
                        userTree.addLecturerToTree(createUserFromForm(UserType.LECTURER));
                        userTree.refreshLecturers();
                    }
            );
        }else{
            addButton = new JButton("Add Student");
            addButton.addActionListener(e ->
                    {
                        modelDialog.setVisible(false);
                        userTree.addStudentToTree(createUserFromForm(UserType.STUDENT));
                        userTree.refreshStudents();
                    }
            );
        }


        addButton.setBounds(0, 80, 100, 20);
        panel1.add(addButton);

        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }

    public void setFieldsWithDefaultValue()
    {
        surnameField = new JTextField();
        nameField = new JTextField();
        birthDateField = new JFormattedTextField(df);
        addressField = new JTextField();

        surnameLabel = new JLabel("Surname");
        nameLabel = new JLabel("Name");
        birthDateLabel = new JLabel("Birth Date");
        addressLabel = new JLabel("Address");


        dialogContainer.add(surnameLabel);
        dialogContainer.add(surnameField);
        dialogContainer.add(nameLabel);
        dialogContainer.add(nameField);
        dialogContainer.add(birthDateLabel);
        dialogContainer.add(birthDateField);
        dialogContainer.add(addressLabel);
        dialogContainer.add(addressField);
    }

    public User createUserFromForm(UserType userType)
    {

        User userToReturn;
        if(userType == UserType.LECTURER)
        {
            userToReturn = new Lecturer();
        }else{
            userToReturn = new Student();
        }
        System.out.println(birthDateField.getText());
        userToReturn.setSurname(surnameField.getText());
        userToReturn.set_name(nameField.getText());
        userToReturn.setBirthDate(birthDateField.getText());
        userToReturn.setAddress(addressField.getText());
        System.out.println(userToReturn);
        return userToReturn;
    }
}
