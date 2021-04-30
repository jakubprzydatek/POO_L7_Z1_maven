package GUI.popups;

import GUI.UserTree;
import lombok.Getter;
import lombok.Setter;
import model.User;
import model.UserType;
import notifiers.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Setter
@Getter
public class ModifyUserModalFrame implements ISubscriber<MouseEvent> {
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
    private int userId;
    private User user;

    public ModifyUserModalFrame(JFrame frame, UserType userType, UserTree userTree, int userId)
    {
        this.userTree = userTree;
        this.userId = userId;
        jPanel = new JPanel();
        LayoutManager layout = new FlowLayout();
        jPanel.setLayout(layout);

        jDialog = createDialog(frame, userType);
        frame.getContentPane().add(jPanel, BorderLayout.CENTER);
    }

    private void setDialogForLecturer(JFrame frame)
    {
        modelDialog = new JDialog(frame, "Modify Lecturer",
                Dialog.ModalityType.DOCUMENT_MODAL);
    }

    private void setDialogForStudent(JFrame frame)
    {
        modelDialog = new JDialog(frame, "Modify Student",
                Dialog.ModalityType.DOCUMENT_MODAL);
    }

    private JDialog createDialog(JFrame frame, UserType userType)
    {
        if(userType == UserType.STUDENT)
        {
            setDialogForStudent(frame);
            user = userTree.getStudents().get(userId);
        }else{
            setDialogForLecturer(frame);
            user = userTree.getLecturers().get(userId);
        }

        modelDialog.setBounds(132, 132, 800, 200);

        dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new GridLayout(0,4));

        setFieldsWithDefaultValue();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        JButton addButton;
        addButton = getButtonType(userType);


        addButton.setBounds(0, 80, 100, 20);
        panel1.add(addButton);

        dialogContainer.add(panel1, BorderLayout.SOUTH);

        return modelDialog;
    }

    private JButton getButtonType(UserType userType) {
        JButton addButton;
        if(userType == UserType.LECTURER)
        {
            addButton = new JButton("Modify Lecturer");
            addButton.addActionListener(e ->
                    {
                        modelDialog.setVisible(false);
                        modifyUserFromForm();
                        userTree.refreshLecturers();
                    }
            );
        }else{
            addButton = new JButton("Modify Student");
            addButton.addActionListener(e ->
                    {
                        modelDialog.setVisible(false);
                        modifyUserFromForm();
                        userTree.refreshStudents();
                    }
            );
        }
        return addButton;
    }

    public void setFieldsWithDefaultValue()
    {
        surnameField = new JTextField();
        surnameField.setText(user.getSurname());
        nameField = new JTextField();
        nameField.setText(user.get_name());
        birthDateField = new JFormattedTextField(df);
        birthDateField.setText(user.getBirthDate());
        addressField = new JTextField();
        addressField.setText(user.getAddress());

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

    public void modifyUserFromForm()
    {
        user.setSurname(surnameField.getText());
        user.set_name(nameField.getText());
        user.setBirthDate(birthDateField.getText());
        user.setAddress(addressField.getText());
        //System.out.println(user);

    }

    @Override
    public void Handle(MouseEvent notification) {
        jDialog.setVisible(true);
    }

    @Override
    public Class<MouseEvent> getT() {
        return MouseEvent.class;
    }
}
