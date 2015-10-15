package test;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Scanner;

import desktop_board.Board;
import desktop_board.Center;
import desktop_codebehind.Player;
import desktop_fields.Field;
import desktop_resources.GUI;
import game.Dice;
import game.DiceResult;

public class TestDiceUniformness {

	public static void main(String[] args) {
		String welcome = "*************************\n" +
						 "*                       *\n" +
						 "*        Uniform        *\n" +
						 "*      Terning test     *\n" +
						 "*                       *\n" +
						 "*************************\n";
		Scanner sc = new Scanner(System.in);
		System.out.println(welcome);
		/*
		System.out.println("Indtast venligst antallet af par terninger, som skal kastes:");
		while(!sc.hasNextInt())
		{
			System.out.println("Ugyldigt input. Prøv igen.");
			sc.next();
		}
		int repeat = sc.nextInt(); */
		testDice(1000);
	}
	public static void testDice(int repeat)
	{
			double[] points = new double[6];
			double pairs = 0;
			
			Dice testDice = new Dice();
			double sum = repeat*2;
			while(repeat-- > 0)
			{
				DiceResult result = testDice.rollDice();
				
				++points[result.getFirstDice()-1];
				++points[result.getSecondDice()-1];
				if(result.isPair())
					++pairs;
			}
			String results  = 	 "*************************\n" +
								 "*                       *\n" +
								 "*       Resultater     *\n" +
								 "*     ud af 1000 kast   *\n" +
								 "*                       *\n" +
								 "*************************\n";
			System.out.println(results);
			System.out.println("Terninger:");
			double highestDiversion = 0;
			for(int i=0; i<points.length;++i)
			{
				double percent = (points[i]/sum)*100;
				double diversion = percent-(100/6);
				if(Math.abs(diversion) > Math.abs(highestDiversion))
				{
					highestDiversion = diversion;
				}
				System.out.printf("\t%d = %.2f%%", i+1, percent);
			}
			System.out.println("\nAntallet af par:");
			System.out.printf("\t%.2f%%", (pairs/(sum/2))*100);
			System.out.println("\nStørste afvigelse(procentpoint):");
			System.out.printf("\t%.2f%%", highestDiversion);
			
	}

}
