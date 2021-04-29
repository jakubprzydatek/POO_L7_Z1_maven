package GUI;

import lombok.Getter;
import lombok.Setter;
import model.User;
import notifiers.ISubscriber;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

@Getter
@Setter
public class UserTable implements ISubscriber<ArrayList> {

    private JTable table;

    public UserTable(ArrayList<User> users)
    {
        table = new JTable();
        setTable(users);

    }

    public void setTable(ArrayList<User> users)
    {

        table.setModel(new DefaultTableModel(
                getRowData(users),
                getColNames()
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });


    }

    private Object[][] getRowData(ArrayList<User> users)
    {
        Object[][] rowData = new Object[users.size()][4];
        int i = 0;
        for (User user:
             users) {
            rowData[i][0] = user.getSurname();
            rowData[i][1] = user.get_name();
            rowData[i][2] = user.getBirthDate();
            rowData[i][3] = user.getAddress();
            i+=1;
        }
        return rowData;
    }

    private String[] getColNames()
    {
        String[] colNames = new String[4];
        colNames[0] = "Surname";
        colNames[1] ="Name";
        colNames[2] = "Birth Date";
        colNames[3] = "Address";
        return colNames;
    }

    @Override
    public void Handle(ArrayList notification) {

    }

    @Override
    public Class<ArrayList> getT() {
        return ArrayList.class;
    }
}
