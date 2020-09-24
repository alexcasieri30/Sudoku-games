package assign3;
import java.util.*;

public class assign3 {

	public static void main(String[] args) 
	{
		int[] seeds = new int[100];
		int max = 0;
		int winning_seed = 0;
		int total = 0;
		for (int seed = 0; seed<100; seed++)
		{
			Random r = new Random(seed);
			int[][] sudoku = new int[9][9];
			
			for (int i = 0; i < 1000; i++)
			{
				sudoku = populate(sudoku,r);
				sudoku = depopulate(sudoku);
			}
			printArr(sudoku);
			System.out.println(" ");
			seeds[seed] = checknonzeroes(sudoku);
		}
		for (int i = 0; i < seeds.length; i++)
		{
			if (seeds[i] > max)
			{
				max = seeds[i];
				winning_seed = i;
			}
			total += seeds[i];
		}
		double average = (double) total / 100;
		System.out.println("From seeds 0-100, seed #" + winning_seed + " will produce the most populated board, filling " + max + " of the total 81 spaces.");
		System.out.println("The average number of spaces filled across seeds 1-100 is " + average + ".");
	}
	public static int checknonzeroes(int[][] sudoku)
	{
		int nonzeroes = 0;
		for (int i = 0; i < sudoku.length; i++)
		{
			for (int j = 0; j < sudoku.length; j++)
			{
				if (sudoku[i][j] != 0)
				{
					nonzeroes += 1;
				}
			}
		}
		return nonzeroes;
	}
	public static int[][] populate(int[][] sudoku, Random r)
	{
		for (int row = 0; row < sudoku.length; row++)
		{
			for (int col = 0; col < sudoku[row].length; col++)
			{
				if (sudoku[row][col]==0)
				{
					sudoku[row][col] = r.nextInt(9)+1;		
				}
			}
		}
		return sudoku;
	}
	public static int[][] depopulate(int[][] sudoku)
	{
		for(int col = 0; col<sudoku.length; col++)
		{
			for(int row = 0; row<sudoku[col].length; row++)
			{
				sudoku = checkCol(sudoku, row, col);
				sudoku = checkRow(sudoku, row, col);
			}
		}
		return sudoku;
	}
	public static int[][] checkRow(int[][] sudoku, int row, int col)
	{
		for (int i = 0; i < sudoku.length; i++)
		{
			if ( i != col ) 
			{
				if (sudoku[row][col] == sudoku[row][i] && sudoku[row][col] != 0)
				{
					sudoku[row][col] = 0;
				}
			}
		}
		return sudoku;
	}
	public static int[][] checkCol(int[][] sudoku, int row, int col)
	{
		for (int i = 0; i < sudoku.length; i++)
		{
			if ( i != row ) 
			{
				if (sudoku[row][col] == sudoku[i][col] && sudoku[row][col] != 0)
				{
					sudoku[row][col] = 0;
				}
			}
		}
		return sudoku;
	}
	public static void printArr(int[][] arr) 
	{
		for (int row = 0; row < arr.length; row++)
		{
			for (int col = 0; col < arr[row].length; col++)
			{
				System.out.print(arr[row][col] + " ");
			}
			System.out.println(" ");
		}
	}


}
