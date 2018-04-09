package fr.kriszt.theo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Base {

    public List<Item> getItems() {
        return items;
    }

    private List<Item> items = new ArrayList<>(); // items courants de la generation
    private List<Transaction> transactions;        // transactions du systeme
    private List<Item> baseItems = new ArrayList<>(); // items de base  combiner
    private List<Item> itemsLeft = new ArrayList<>(); // items ayant "survecu" à la generation (occurences > minSup)
    private int minSup;

    public List<Item> getItemsLeft() {
        return itemsLeft;
    }

    private  Base(int min, List<Item> itemsList, List<Transaction> tr, List<Item> base, List<Item> left){
        this(min, itemsList, tr);
        baseItems = base;
        baseItems.sort(Comparator.comparing(Item::toString));
        itemsLeft = left;
    }

    public  Base(int min, List<Item> itemsList, List<Transaction> tr){
        minSup = min;
        transactions = tr;
        baseItems.addAll(itemsList);
        items.addAll(itemsList);
        items.sort(Comparator.comparing(Item::toString));
        baseItems.sort(Comparator.comparing(Item::toString));
        itemsLeft.addAll(baseItems);
    }

    Base generateCandidates(){

        ArrayList<Item> newItems = new ArrayList<>();


        for (int i = 0; i < items.size(); i++){

            String first = items.get(i).toString().substring(items.get(i).toString().length()-1);
//            System.out.println("First : " + first);
            for (int j = 0; j < baseItems.size(); j++){
                String second = baseItems.get(j).toString();
//                System.out.println("\tSecond : " + second);
                if( first.compareTo(second) < 0 ){
//                    System.out.println("\tItem : " + items.get(i) + "|" + baseItems.get(j));
//                    System.out.println("\tItem : " + items.get(i) + "|" + second);
                    newItems.add( new Item(items.get(i) + second) );
                }

            }
        }

//        itemsLeft.addAll(newItems);
//        System.out.println("Adding all : " + newItems);

        Base ci = new Base(minSup, newItems, transactions, baseItems, itemsLeft);

        return ci;

    }

    int getItemCount(Item item){
        int count = 0;
        for (Transaction t : transactions){
            if(t.usesItem(item)){
               count++;
            }
        }

        return count;
    }

    List<Item> filterUncommonItems(){

        ArrayList<Item>  filteredItems = new ArrayList<>();
        ArrayList<Item> rejectedItems = new ArrayList<>();

        for (Item item : items){
            item.count = getItemCount(item);
            if (item.count >= minSup){
//            if (item.count > 0){
                filteredItems.add(item);
            }else {
//                System.out.println(item + " est inutile");
                rejectedItems.add(item);
            }
        }

        items = filteredItems;
        itemsLeft.addAll(items);
        return rejectedItems;
    }


    public String toString(){
        String ret ="";// "MinSup : " + minSup + "\n";
//        ret += "BaseItems : "+ baseItems+"\n";
        ret += "Items : " + items ;

        for (Item i : items){
//            ret += "\n\t" + i + " : " + i.count;
        }
        return ret + "\n";
    }

}
