/*
 * Copyright (c) 2017. Phasmid Software
 */
package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    public void move(int dx, int dy) {
        // TODO you need to implement this
        this.x += dx;
        this.y += dy;

    }

    private void randomWalk(int n) {
        for (int i = 0; i < n; i++) {
            randomMove();
        }
    }

    private void randomMove() {
        // TODO you need to implement this
        int max = 4;
        int min = 1;
        int random_num = random.nextInt(max - min + 1) + min;
        switch (random_num) {
            case 1:
                this.x += 1;
                //System.out.println("East");
                break;
            case 2:
                this.x -= 1;
                //System.out.println("West");
                break;
            case 3:
                this.y += 1;
                //System.out.println("North");
                break;
            case 4:
                this.y -= 1;
                //System.out.println("South");
                break;
            default:
                break;
        }
    }

    public double distance() {
        //System.out.println("Final co-ordinats of x and y : "+ this.x+" "+this.y);
        double dist = Math.sqrt((Math.pow(this.x, 2) + Math.pow(this.y, 2)));
        return dist;        
    }
    

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        //int n = 3;
        RandomWalk walk = new RandomWalk();
        walk.randomWalk(n);
        System.out.println(n + " steps: " + walk.distance());
    }
}
