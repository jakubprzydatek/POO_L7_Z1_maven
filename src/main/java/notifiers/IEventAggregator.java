package notifiers;

public interface IEventAggregator {
    <T> void addSubscriber(ISubscriber<T> subscriber);
    <T> void removeSubscriber(ISubscriber<T> subscriber);
    <T> void publish(T event);
}
