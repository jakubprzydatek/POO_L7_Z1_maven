import GUI.Frame;
import model.Student;
import model.User;
import notifiers.EventAggregator;
import notifiers.ISubscriber;

public class Main {
    public static void main(String[] args) {
        new Frame();

        /*ISubscriber<String> user = new Student();

        System.out.println(user.getClass());
        System.out.println(Student.class);

        EventAggregator eventAggregator = new EventAggregator();
        eventAggregator.addSubscriber(user);

        eventAggregator.publish("hi");*/
    }
}
