import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private int sum=0,total=0;
    private List<List<String>> itemN=new ArrayList<List<String>>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE

        if( getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime))
            return true;
        else
            return false;

    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {

        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return Collections.unmodifiableList(menu);
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int addOrRemoveItems(List<String> it, int add){
        for(Item item:menu){
            for (int i=0;i<it.size();i++){
                if((add==1)&&item.getName().equals(it.get(i))&&(!(itemN.contains(Collections.singletonList(it.get(i)))) )){
                    itemN.add(Collections.singletonList(it.get(i)));
                    sum=sum+item.getPrice();
                }
                else if ((add==0)&&item.getName().equals(it.get(i))&&(itemN.contains(Collections.singletonList(it.get(i))))){
                    itemN.remove(Collections.singletonList(it.get(i)));
                    sum=sum-item.getPrice();
                }
            }
        }
        total=total+sum;
        return sum;

    }
    public String getTotalPrice(){
        String a= "Your order will cost: ₹"+sum;
        return a;
    }

}
