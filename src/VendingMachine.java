import java.util.*;

public class VendingMachine {
    private int coins;
    private final Inventory inventory;

    private final Scanner scanner = new Scanner(System.in);

    private final HashMap<String, ItemDescription> items = new HashMap<>();

    private final String PISTOL_AMMO = "10 Pistol Ammo";
    private final String MACHINE_GUN_AMMO = "50 Machine Gun Ammo";
    private final String SHOTGUN_AMMO = "5 Shotgun Ammo";
    private final String HEALTH_PACK = "1 Health Pack";
    private final String EVE_HYPO = "1 EVE Hypo";

    public VendingMachine(int coins, Inventory inventory) {
        this.coins = coins;
        this.inventory = inventory;

        items.put(PISTOL_AMMO, new ItemDescription(25, "Ammo for the pistol"));
        items.put(MACHINE_GUN_AMMO, new ItemDescription(60, "Ammo for the machine gun"));
        items.put(SHOTGUN_AMMO, new ItemDescription(50, "Ammo for the shotgun"));
        items.put(HEALTH_PACK, new ItemDescription(40, "Restores 50 health points"));
        items.put(EVE_HYPO, new ItemDescription(40, "Restores 3 EVE points that can be used to cast plasmids"));
    }

    private void displayItems() {
        System.out.println();
        items.forEach((key, value) -> System.out.println(key + " (" + value.getPrice() + " coins): " + value.getDescription()));
        System.out.println();
    }

    private boolean canBuy(String itemName) {
        if (items.get(itemName).getPrice() < coins)
            return true;
        else {
            AudioManager.playSound("sounds/effects/vending_machine_no_money.wav");
            System.out.println("You don't have enough coins to buy this item!");
            return false;
        }
    }

    private void buyItemSuccessful() {
        System.out.println("Item successfully bought!");
        AudioManager.playSound("sounds/effects/vending_machine_no_refunds.wav");
    }

    private void buyItem(String itemName) {
        if (itemName.equalsIgnoreCase(PISTOL_AMMO)) {
            if (canBuy(PISTOL_AMMO)) {
                coins -= items.get(PISTOL_AMMO).getPrice();
                inventory.getWeapon("pistol").addAmmo(10);
                buyItemSuccessful();
            }
        } else if (itemName.equalsIgnoreCase(MACHINE_GUN_AMMO)) {
            if (canBuy(MACHINE_GUN_AMMO)) {
                coins -= items.get(MACHINE_GUN_AMMO).getPrice();
                inventory.getWeapon("machine gun").addAmmo(50);
                buyItemSuccessful();
            }
        } else if (itemName.equalsIgnoreCase(SHOTGUN_AMMO)) {
            if (canBuy(SHOTGUN_AMMO)) {
                coins -= items.get(SHOTGUN_AMMO).getPrice();
                inventory.getWeapon("shotgun").addAmmo(5);
                buyItemSuccessful();
            }
        } else if (itemName.equalsIgnoreCase(HEALTH_PACK)) {
            if (canBuy(HEALTH_PACK)) {
                coins -= items.get(HEALTH_PACK).getPrice();
                inventory.addHealthPack();
                buyItemSuccessful();
            }
        } else if (itemName.equalsIgnoreCase(EVE_HYPO)) {
            if (canBuy(EVE_HYPO)) {
                coins -= items.get(EVE_HYPO).getPrice();
                inventory.addEveHypo();
                buyItemSuccessful();
            }
        } else {
            System.out.println("Item with the name " + itemName + " is not found!");
        }
    }

    public void enterMode() {
        System.out.println();
        System.out.println("You found a vending machine. You have now entered vending machine mode");
        AudioManager.playSound("sounds/effects/vending_machine_circus_of_values.wav");

        while (true) {
            System.out.println("You have " + coins + " coins");
            System.out.println("Type \"buy <item name>\" to buy an item. Type items to view all available items to buy. \n" +
                    "Type inventory to display your inventory, and get-info <item name> to view information about an item in your inventory. Type QUIT to quit vending machine mode.");
            String input = scanner.nextLine().toLowerCase().trim();

            if (input.equals("quit")) {
                AudioManager.playSound("sounds/effects/vending_machine_quit.wav");
                break;
            } else if (input.equals("items"))
                displayItems();
            else if (input.split(" ")[0].equals("buy") && input.split(" ").length >= 2)
                buyItem(input.split(" ", 2)[1]);
            else if(input.equals("inventory"))
                inventory.show();
            else if(input.split(" ")[0].equals("get-info") && input.split(" ").length >= 2)
                inventory.getItemDescription(input.split(" ", 2)[1]);
            else
                System.out.println("Try again with a valid input!");
        }
    }

    public int getRemainingCoins() {
        return coins;
    }

    public Inventory getRemainingInventory() {
        return inventory;
    }
}
