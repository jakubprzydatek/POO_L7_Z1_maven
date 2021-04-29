package notifiers;

import java.util.ArrayList;
import java.util.HashMap;

public class EventAggregator implements IEventAggregator {

    private HashMap<Class<?>, ArrayList<ISubscriber>> subscribers;

    public EventAggregator()
    {
        subscribers = new HashMap<>();
    }

    @Override
    public <T> void addSubscriber(ISubscriber<T> subscriber) {

        if(!subscribers.containsKey(subscriber.getT()))
        {
            subscribers.put(subscriber.getT(), new ArrayList<>());
        }
        subscribers.get(subscriber.getT()).add(subscriber);
    }

    @Override
    public <T> void removeSubscriber(ISubscriber<T> subscriber) {
        if(subscribers.containsKey(subscriber.getT()))
        {
            subscribers.get(subscriber.getT()).remove(subscriber);
        }
    }

    @Override
    public <T> void publish(T event) {
        if(subscribers.containsKey(event.getClass()))
        {
            for (ISubscriber<T> subscriber :
                    subscribers.get(event.getClass())) {
                subscriber.Handle(event);
            }
        }
    }
}
