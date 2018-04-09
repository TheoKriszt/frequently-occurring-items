package fr.kriszt.theo;

public class Item {

    private String name;
    public int count = 0;


    public Item(String n){
        name = n;
    }

    public String toString(){
        return name;
    }

//    public Item merge(Item i2){
//        return new Item(name + i2.name);
//    }


    public String getName() {
        return name;
    }
}
