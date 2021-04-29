package GUI;

import lombok.Getter;
import lombok.Setter;
import model.User;
import notifiers.ISubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.ArrayList;

@Setter
@Getter
public class UserTree implements ISubscriber<User> {

    private JTree tree;
    private DefaultMutableTreeNode lecturersRoot;
    private ArrayList<User> lecturers;
    private DefaultMutableTreeNode studentsRoot;
    private ArrayList<User> students;


    public UserTree()
    {
        lecturersRoot = new DefaultMutableTreeNode("Lecturers");
        lecturers = new ArrayList<>();

        studentsRoot = new DefaultMutableTreeNode("Students");
        students = new ArrayList<>();

        DefaultMutableTreeNode usersRoot = new DefaultMutableTreeNode("Users");
        usersRoot.add(lecturersRoot);
        usersRoot.add(studentsRoot);
        tree = new JTree(usersRoot);
    }

    public void addStudentToTree(User user)
    {
        students.add(user);
        DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
        DefaultMutableTreeNode child = (DefaultMutableTreeNode)root.getLastChild();
        child.add(new DefaultMutableTreeNode(user.get_name() + user.getSurname()));
        treeModel.reload(root);

        //treeModel.insertNodeInto(new DefaultMutableTreeNode(user.get_name() + user.getSurname()), root, root.getChildCount());
        //root.add(new DefaultMutableTreeNode(user.get_name() + user.getSurname()));

    }

    public void addLecturerToTree(User user) {
        lecturers.add(user);
        DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) treeModel.getRoot();
        DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getFirstChild();
        child.add(new DefaultMutableTreeNode(user.get_name() + user.getSurname()));
        treeModel.reload(root);
    }


    public void refreshStudents()
    {
        DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
        DefaultMutableTreeNode child = (DefaultMutableTreeNode)root.getLastChild();
        child.removeAllChildren();
        for (User student :
                students) {
            child.add(new DefaultMutableTreeNode(student.get_name() + student.getSurname()));
        }
        treeModel.reload(root);
    }

    public void refreshLecturers()
    {
        DefaultTreeModel treeModel = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)treeModel.getRoot();
        DefaultMutableTreeNode child = (DefaultMutableTreeNode)root.getFirstChild();
        child.removeAllChildren();
        for (User lecturer :
                lecturers) {
            child.add(new DefaultMutableTreeNode(lecturer.get_name() + lecturer.getSurname()));
        }
        treeModel.reload(root);
    }

    @Override
    public void Handle(User notification) {

    }

    @Override
    public Class<User> getT() {
        return User.class;
    }
}
