/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.the_vending_machine;
import java.util.Comparator;
/**
 *
 * @author premsharaan
 */
//This is a public that is compare and sort the details inside the stock text file
public class ItemSorter  implements Comparator<String> {
    
        public int compare(String sort1, String sort2) {
        return sort1.compareToIgnoreCase(sort2);
    }
    
}
