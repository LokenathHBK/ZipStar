package com.esolz.su.zipstar.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by su on 15/10/15.
 */
public class ArrayListSortingExample {

    private static class SmartPhone implements Comparable {
        private String brand;
        private String model;
        private int price;

        public SmartPhone(String brand, String model, int price){
            this.brand = brand;
            this.model = model;
            this.price = price;
        }



        @Override
        public String toString() {
            return "SmartPhone{" + "brand=" + brand + ", model=" + model + ", price=" + price + '}';
        }

        @Override
        public int compareTo(Object another) {
            return 0;
        }
    }

    private static class PriceComparator implements Comparator {


        @Override
        public int compare(Object lhs, Object rhs) {
            return 0;
        }
    }

    public static void main(String... args) {

        //creating objects for arraylist sorting example
        SmartPhone apple = new SmartPhone("Apple", "IPhone4S",1000);
        SmartPhone nokia = new SmartPhone("Nokia", "Lumia 800",600);
        SmartPhone samsung = new SmartPhone("Samsung", "Galaxy Ace",800);
        SmartPhone lg = new SmartPhone("LG", "Optimus",500);

        //creating Arraylist for sorting example
        ArrayList smartPhones = new ArrayList();

        //storing objects into ArrayList for sorting
        smartPhones.add(apple);
        smartPhones.add(nokia);
        smartPhones.add(samsung);
        smartPhones.add(lg);

        //Sorting Arraylist in Java on natural order of object
        Collections.sort(smartPhones);

        //print sorted arraylist on natural order
        System.out.println(smartPhones);

        //Sorting Arraylist in Java on custom order defined by Comparator
        Collections.sort(smartPhones,new PriceComparator());

        //print sorted arraylist on custom order
        System.out.println(smartPhones);

    }
}


