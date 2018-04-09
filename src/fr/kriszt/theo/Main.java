package fr.kriszt.theo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if(args.length < 2){
            System.err.println("usage : \"minSup nbItems  item1 item2 ... itemn transac1 transac2 ... transacn\"");
            System.err.println("exemple : \"3 5  A B C D E ACD BCE ABCE BE ABCE BCE\" => minSup : 3, Base : {A, B, C, D, E}, 6 transactions de ACD à BCE");
            return;
        }

        int minSup = 0;
        int baseSize = 0;
//        List<String> chars = new ArrayList<>();
        List<Item> baseCandidates = new ArrayList<>();
        List<String> transactions = new ArrayList<>();
        ArrayList<Transaction> transactionsList = new ArrayList<>();

        try{
            minSup = Integer.parseInt(args[0]);
            baseSize = Integer.parseInt(args[1]);


            for(int i = 3; i < 3 + baseSize; i++){
//                chars.add(args[i]);
                baseCandidates.add(new Item(args[i]));
            }

            for(int i = 3 + baseSize; i < args.length; i++){
                transactionsList.add(new Transaction(args[i]));
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("minSup : " + minSup);
//        System.out.println("Base Size : " + baseSize);
        System.out.println("Base : " + baseCandidates);
        System.out.println("Transactions : " + transactionsList);


        for (String t : transactions){
            transactionsList.add(new Transaction(t));
        }

//        System.out.println("===================================\n");

        Base base = new Base(minSup, baseCandidates, transactionsList);
//        System.out.println("Base : ");
//        System.out.print(base);

        Base candidates = base.generateCandidates();
        System.out.println("===================================");

        int i = 1;
        while(candidates.getItems().size() > 0){

            System.out.println("\nGeneration C"+ i +" : \n" + candidates);

            System.out.println("Removed the following uncommon items: " + candidates.filterUncommonItems());

            System.out.println("Items left : ");
            for (Item it : candidates.getItems()){
                System.out.println("\t" + it + " : counted " + candidates.getItemCount(it) + " times");
            }

            System.out.println("--------------------------");

            candidates = candidates.generateCandidates();

            i++;
        }

        System.out.println("Items occuring at least " + minSup + " times : " + candidates.getItemsLeft());


    }
}
