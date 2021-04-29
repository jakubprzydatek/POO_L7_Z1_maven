package notifiers;

public interface ISubscriber<T> {
    public void Handle(T notification);
    Class<T> getT();
}
