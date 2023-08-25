import java.util.ArrayList;

public class NotificationSystem {
    private ArrayList<String> weightNotifications;
    private ArrayList<String> ageRestrictionNoti;
    private ArrayList<String> giftCardNoti;

    public NotificationSystem() {
        this.weightNotifications = new ArrayList<>();
        this.ageRestrictionNoti = new ArrayList<>();
        this.giftCardNoti = new ArrayList<>();
    }

    public void addWeightNotification(String notification) {
        this.weightNotifications.add(notification);
    }

    public void addAgeRestrictionNoti(String notification) {
        this.ageRestrictionNoti.add(notification);
    }

    public void addGiftCardNoti(String notification) {
        this.giftCardNoti.add(notification);
    }

    public void getWeightNotifications() 
    {
        for(int i = 0; i < weightNotifications.size(); i++)
        {
            System.out.println(weightNotifications.get(i));
        }
    }
    public void getAgeRestrictionNotifications() 
    {
        System.out.println(ageRestrictionNoti.get(0));
    }
    public void getGiftCardNotifications()
    {
        System.out.println(giftCardNoti.get(0));
    }
}


