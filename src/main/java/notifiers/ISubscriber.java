package notifiers;

public interface ISubscriber<T> {
    void Handle(T notification);
    Class<T> getT();
}
