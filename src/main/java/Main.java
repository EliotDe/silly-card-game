package main.java;

import java.io.File;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("enter the number of players: ");
        int playerNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("enter the location of the pack to load");
        File packFile = new File(scanner.nextLine());

    }
}