/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uf;

import java.util.Random;

/**
 *
 * @author Mayank
 */
public class UFClient {    
    public static int count(int n){
        int connection = 0;
        WQUPC uf = new WQUPC(n);
        Random rand = new Random();
        while(uf.count() != 1){
            int p = rand.nextInt(n);
            int q = rand.nextInt(n);
            //System.out.println(p+"  "+q);
            connection++;
            if (!uf.connected(p, q)) uf.union(p, q);     
        }
        return connection; 
    }

    public static void main(String[] args) {
        for (int site = 100; site <=200; site=site+10) {
            int sum = 0;
            double avg = 0;
         for (int i = 0; i < 200; i++) {
            sum =sum + count(site);
        }   
            avg=sum/200;
            //double hypo = (Math.log(site))*(site/2);
            System.out.println("Site=" +site+   "  Avg=" + avg );
           // System.out.println(hypo);
        }
        
        

    }
    
}
