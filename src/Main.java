import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Items that will be in the cart.
        Item apple = new Item(1, "Apple", 1.0f, 0.33, false, false);
        Item wine = new Item(2, "Wine", 10.0f, 2.65, true, true);
        Item bread = new Item(3, "Loaf of Bread", 4.0f, 1.0, false, false);
        Item beer = new Item(4, "10 Pack of Beer", 9.0f, 0.63, true, true);
        Item cereal = new Item(5, "Cereal", 4.0f, 1.10, false, false);
        Item peanutButter = new Item(6, "Peanut Butter", 5.0f, 110.0, false, false);
        Item liquor = new Item(7, "Liquor", 15.0f, 1.8, true, true);
        Item ibuprofen = new Item(8, "Ibuprofen", 10.0f, 0.25, true, false);

        // Items being added to the cart.
        ArrayList<Item> items = new ArrayList<>();
        items.add(apple);
        items.add(wine);
        items.add(bread);
        items.add(beer);
        items.add(cereal);
        items.add(peanutButter);
        items.add(liquor);
        items.add(ibuprofen);

        // ArrayList of items that will be removed from the cart(if necessary)
        ArrayList<Item>itemsRemoved = new ArrayList<>();

        // Creation of the Weight System.
        WeightDetectionSystem weightSystem = new WeightDetectionSystem(0.0f, 100.0f, true);

        // Creation of the Notification System
        NotificationSystem notificationSystem = new NotificationSystem();

        // The kiosk that the customer is currently using.
        Kiosk kiosk = new Kiosk(1, "Front", "Grocery", "Online", 0.10);

        // Employee that will be attending the customer (if necessary)
        Employee employee = new Employee(1, "John", "Manager", "john@email.com");


        // ---------------------------------------------The customer-----------------------------------------------------------
        Customer customer1 = new Customer(1, "Adam", 22, 1, true, items);
        // --------------------------------------------------------------------------------------------------------------------


        // Iteration of the items that are in the Customer's cart.
        for (int i = 0; i < items.size(); i++)
        {
            int weightCheck = 0;    // amount of times the customer has incorrectly weighed their item.

            // if statement that accesses items in the cart that are not Age-Restricted.
            if(items.get(i).getPluCode() == i+1 && !items.get(i).isAgeRestricted())
            {
                System.out.println();
                System.out.println("Found item: " + items.get(i).getName());
                
                // if statement to make sure the weight is valid.
                if(items.get(i).getWeight() > 0 && items.get(i).getWeight() < 100)
                {
                    kiosk.scanItem(items.get(i), weightSystem, notificationSystem);
                }
                else 
                {
                    //FIXME: ADD WAY TO OVERRIDE KIOSK OR REMOVE ITEM IF WEIGHING PROCESS CONTINUES TO FAIL.

                    while(weightCheck >= 0 && weightCheck < 3)      // while loop for making sure that the customer only has 3 tries to reweigh their item.
                    {
                        weightCheck += 1;
                        System.out.println("Please reweigh your item.");
                    }
                    if(weightCheck == 3)                            // once customer reweighs 3 times, the nearby employee will be notified for assistance.
                    {
                        notificationSystem.addWeightNotification("**Customer at kiosk " + customer1.getKioskLocation() + " is in need of assistance with weighing item.**");
                        notificationSystem.getWeightNotifications();
                    }
                }
                System.out.println();
            }

            // else if statement that accesses items in the cart that are Age-Restricted, but are not alcohol.
            else if(items.get(i).getPluCode() == i+1 && items.get(i).isAgeRestricted() && !items.get(i).isAlcohol()) 
            {
                System.out.println("Found item: " + items.get(i).getName());
                notificationSystem.addAgeRestrictionNoti("**Customer at kiosk " + customer1.getKioskLocation() + " is in need of assistance with scanning an Age-Restricted item.**");
                notificationSystem.getAgeRestrictionNotifications();
                System.out.println();
                System.out.println("An employee is on the way to assist you. Please wait here.\n");

                // if statement that helps verify the customer's age in order to proceed.
                if(employee.verifyAge(customer1)) {
                    System.out.println("~~Age verified.~~");
                    kiosk.scanItem(items.get(i), weightSystem, notificationSystem);
                }
                else {
                    System.out.println("This item will not be added to your cart.");

                    // removes the now irrelevant item from the cart, and adds it to another ArrayList for the later calculations of the total.
                    itemsRemoved.add(items.remove(i));
                }
            }

            // else if statement that accesses items in the cart that are considered alcohol.
            else if(items.get(i).getPluCode() == i+1 && items.get(i).isAlcohol())
            {
                System.out.println("Found item: " + items.get(i).getName());
                notificationSystem.addAgeRestrictionNoti("**Customer at kiosk " + customer1.getKioskLocation() + " is in need of assistance with scanning an Age-Restricted item.\n");
                notificationSystem.getAgeRestrictionNotifications();

                // The employee informs the customer of the California law, and asks them to pay for that item at an attended checkout counter.
                System.out.println("Alcohol cannot be purchased at self-checkout due to laws in Calfornia. This item (" + items.get(i).getName() + ") will be removed from the cart.");
                System.out.println("Please purchase this item at a checkout counter with an employee.");

                itemsRemoved.add(items.get(i));
            }

            else 
            {
                System.out.println("Item not found.");
            }
            System.out.println();
        }

        // Creation of the payment method being used by the customer.
        PaymentMethod paymentMethod = new PaymentMethod("Credit Card", "1234 5678 9012 3456", "12/25");

        // Receipt to be printed if the customer wishes to receive one.
        Receipt receipt = new Receipt(1, 1, items, 11.5f, "23-08-2023");

        // Total of the prices of the items that were removed from the cart.
        float removeFromTotal = 0.0f;
        for(int i = 0; i < itemsRemoved.size(); i++)
        {
            removeFromTotal += itemsRemoved.get(i).getPrice();      // added into one variable to later subtract from the customer's new total.
        }


    
        // ~~~~~~~~~~~~~~~~~~~~~~~~~~GIFT CARD~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ //

        // if statement that is entered if the customer wants to purchase a gift card.
        if(customer1.wantsGiftCard()) 
        {
            boolean giftCardCancel = false;     // can later change if the customer wishes to cancel their gift card purchase.

            notificationSystem.addGiftCardNoti("**Customer at kiosk " + customer1.getKioskLocation() + " needs assistance with purchasing a gift card.");
            notificationSystem.getGiftCardNotifications();
            System.out.println();

            // the customer makes their final decision on their gift card purchase----------------------------
            System.out.println("Are you sure you want to purchase this gift card?");
            System.out.println("1 = Yes \n0 = No");
            int userChoice = in.nextInt();
            if(userChoice == 1){
                giftCardCancel = false;
            }
            else{
                giftCardCancel = true;
            }
            //------------------------------------------------------------------------------------------------


            // if the customer changes their mind, the gift card amount will not be added to the total.
            // if not, the amount on the gift card will be added to the total.
            if(!giftCardCancel) {
                GiftCard giftcard1 = new GiftCard(1, true, 25.0f);
                Item giftCard = new Item(items.size()+1, "Gift Card", 0.1, false, false);
                items.add(giftCard);
                System.out.println(">> Your gift card of $" + giftcard1.getAmount() + "0 has been added to your cart.\n");
                kiosk.checkout(customer1, paymentMethod, receipt, notificationSystem, giftcard1, removeFromTotal);
            }
            else{
                GiftCard giftcard1 = new GiftCard(1, false, 0.0f);
                kiosk.checkout(customer1, paymentMethod, receipt, notificationSystem, giftcard1, removeFromTotal);
            }            
        }
        // gift card is created but nothing is added to the total.
        else {
            GiftCard giftcard1 = new GiftCard(1, false, 0.0f);
            kiosk.checkout(customer1, paymentMethod, receipt, notificationSystem, giftcard1, removeFromTotal);
        }        

        
        //kiosk.scanItem(apple, weightSystem, notificationSystem);
        //kiosk.authorizeAgeRestrictedItem(employee, wine, customer1, notificationSystem);
        //kiosk.scanItem(wine, weightSystem, notificationSystem);
        //kiosk.checkout(customer1, paymentMethod, receipt, notificationSystem);

        //boolean isVerified = employee.verifyReceipt(receipt);

        //System.out.println("\nReceipt verification: " + isVerified);
    }
}

