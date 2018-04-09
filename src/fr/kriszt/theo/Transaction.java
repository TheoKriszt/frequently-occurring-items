package fr.kriszt.theo;


public class Transaction {

    private String content; // ex : ACD

    public Transaction(String items){
        content = items;

    }

    public boolean usesItem(Item item){ // ex : ACD contient "AC" ou "CD"

//        int count = 0;
        for (char c : item.getName().toCharArray()){
            if(!content.contains( String.valueOf(c) )){
                return false;
            }else {
//                count++;
            }
        }

//        System.out.println(item + " est utilisé " + count + " fois");

        return true;


//        return content.contains(item);
    }

    public String toString(){
        return content;
    }
}
