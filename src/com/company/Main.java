
package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenseTYPE  = "";
    public static int [] heroesHealth = {270,260,250,240, 230};
    public static int [] heroesDamage = {25,15,20,30, 0};
    public static String [] HeroesAttackType = {"physical", "Magical", "Kinetic", "Archer", "Medic"};
    public static int roundCounter = 0;

    public static void main(String[] args) {
        printstatistics();
        while (!isGameOver()){
            round();
        }


    }
    public static void printstatistics(){
        System.out.println("-------------------");
        System.out.println("Round: "+ roundCounter);
        roundCounter++;
        System.out.println("Boss health: "+bossHealth);
        for(int i =0; i <HeroesAttackType.length;i++){
            System.out.println(HeroesAttackType[i] + " health: " + heroesHealth[i]);
        }
        System.out.println("-------------------");
    }
    public static void bossHits(){
        for(int i = 0; i < HeroesAttackType.length; i++){
            if(heroesHealth[i] > 0){
                if (heroesHealth[i]- bossDamage<0){
                    heroesHealth[i]=0;
                }else{
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
            }
        }

    }
    public static void heroeshits(){
        for (int i = 0; i < HeroesAttackType.length; i++){
            if (heroesHealth[i] > 0 && bossHealth > 0){
                if(HeroesAttackType[i] == bossDefenseTYPE) {
                    Random random = new Random();
                    int randomValue = random.nextInt(10);
                    heroesDamage[i] = heroesDamage[i] - randomValue;
                }else{
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }else {
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                }   else {
                    bossHealth = bossHealth - heroesDamage[i];
                }

            }
        }
    }
    public static boolean isGameOver() {
        if (bossHealth<0){
            System.out.println("Heroes won!!");
            return true;
        }
        boolean allHeroesDead = true;
        for ( int i = 0; i< HeroesAttackType.length;i++){
            if (heroesHealth[i]>0) {
            allHeroesDead = false;
            break;
            }
        }
        if (allHeroesDead){
            System.out.println("boss won!!");
        }
        return allHeroesDead;
    }
    public static void changeDefenceType(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesHealth.length);
        bossDefenseTYPE = HeroesAttackType[randomIndex];
        System.out.println("Boss choose: "+ bossDefenseTYPE);
            }
    public static void medicHealth(){
        int randomHealth = new Random().nextInt(15);
        if (heroesHealth[4]>0){
            for (int i = 0; i < HeroesAttackType.length-1; i++) {
                if (heroesHealth[i]<=100){
                    heroesHealth[i] = heroesHealth[i]+randomHealth;
                    System.out.println("Medic has healed"+ HeroesAttackType[i]+ "  for"+ randomHealth);
                    break;
                }

            }
        }

    }
    public static void round(){
        if (bossHealth>0){
            changeDefenceType();
            bossHits();
        }
        heroeshits();
        printstatistics();
    }
}
