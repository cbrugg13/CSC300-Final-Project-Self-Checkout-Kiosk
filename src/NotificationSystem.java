import java.util.ArrayList;

public class NotificationSystem {
    private ArrayList<String> notifications;

    public NotificationSystem() {
        this.notifications = new ArrayList<>();
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }
}


