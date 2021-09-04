import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Soduko {
	static final int N=9;
	static ArrayList<String> arrayList = new ArrayList<String>();
	static ArrayList<String[]> arrayList2 = new ArrayList<String[]>();
	public static void print(int[][] list) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < list.length;i++) {
			for(int j = 0; j < list[0].length ;j++)
				System.out.print(list[i][j]);
			System.out.println();
		}

	}

	public static void print(String[] list) {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < list.length;i++) {
			System.out.print(list[i]);
		}
		System.out.println();
	}


	public static int getValue(String str) {
		if(str.equals(" "))
			return 0;
		else
			return Integer.parseInt(str);
	}

	static boolean solveSuduko(int grid[][], int row, 
			int col) 
	{ 

		/*if we have reached the 8th  
row and 9th column (0 
indexed matrix) , 
we are returning true to avoid further 
backtracking       */
		if (row == N - 1 && col == N) 
			return true; 

		// Check if column value  becomes 9 , 
		// we move to next row 
		// and column start from 0 
		if (col == N)  
		{ 
			row++; 
			col = 0; 
		} 

		// Check if the current position 
		// of the grid already 
		// contains value >0, we iterate 
		// for next column 
		if (grid[row][col] != 0) 
			return solveSuduko(grid, row, col + 1); 

		for (int num = 1; num < 10; num++) 
		{ 

			// Check if it is safe to place 
			// the num (1-9)  in the 
			// given row ,col ->we move to next column 
			if (isSafe(grid, row, col, num))  
			{ 

				/*  assigning the num in the current 
(row,col)  position of the grid and 
assuming our assined num in the position 
is correct */
				grid[row][col] = num; 

				// Checking for next  
				// possibility with next column 
				if (solveSuduko(grid, row, col + 1)) 
					return true; 
			} 
			/* removing the assigned num , since our 
assumption was wrong , and we go for next 
assumption with diff num value   */
			grid[row][col] = 0; 
		} 
		return false; 
	} 

	// Check whether it will be legal 
	// to assign num to the 
	// given row, col 
	static boolean isSafe(int[][] grid, int row,
			int col, int num) 
	{ 

		// Check if we find the same num 
		// in the similar row , we 
		// return false 
		for (int x = 0; x <= 8; x++) 
			if (grid[row][x] == num) 
				return false; 

		// Check if we find the same num 
		// in the similar column , 
		// we return false 
		for (int x = 0; x <= 8; x++) 
			if (grid[x][col] == num) 
				return false; 

		// Check if we find the same num 
		// in the particular 3*3 
		// matrix, we return false 
		int startRow = row - row % 3, startCol = 
				col - col % 3; 
		for (int i = 0; i < 3; i++) 
			for (int j = 0; j < 3; j++) 
				if (grid[i + startRow] 
						[j + startCol] == num) 
					return false; 

		return true; 
	} 
	
	public static void print(ArrayList list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	
	public static void makeOutput(int [][] list) {
		int [] indexes = {3,7,11,16,20,24,29,33,37};
		for(int i =0 ; i < arrayList2.size(); i++) {
			String[] l = arrayList2.get(i);
			for(int j = 0; j < list.length ; j++) {
				l[indexes[j]]=Integer.toString(list[i][j]);
			}
		}
	
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileReader fReader = new FileReader("src/COSC602_PB2_SuDokuSheet.txt");
		Scanner input = new Scanner(fReader);
		arrayList.add(input.nextLine());
		arrayList.add(input.nextLine());
		int [][] sudokuList = new int[9][9];
		int [][] list = new int[9][9];
		int row = 0;
		int count = 0;
		int c = 0;
		while(c < 9) {
			if(count==3) {
				count = 0;
				String str = input.nextLine();
				arrayList.add(str);
			}
			String strr = input.nextLine();
			arrayList.add(strr);
			String [] str = strr.split("|");
			arrayList2.add(str);
			list[row][0]= getValue(str[3]);
			list[row][1]= getValue(str[7]);
			list[row][2]= getValue(str[11]);

			list[row][3]= getValue(str[16]);
			list[row][4]= getValue(str[20]);
			list[row][5]= getValue(str[24]);

			list[row][6]= getValue(str[29]);
			list[row][7]= getValue(str[33]);
			list[row][8]= getValue(str[37]);
			row = row +1;
			count++;
			c++;
			arrayList.add(input.nextLine());
		}
		arrayList.add("||-------------------------------------||");
		arrayList.add("|---------------------------------------|");
		long start = System. currentTimeMillis();
		if (!solveSuduko(list, 0, 0)) 
            System.out.println("No Solution exists");
		long end = System. currentTimeMillis();
		System.out.println("Total Time : " +(end-start));
		makeOutput(list);
		
		FileWriter fwriter = new FileWriter("OUTPUT.txt");
		for(int i = 0; i < arrayList.size(); i++) {
			fwriter.write(arrayList.get(i));
			fwriter.write("\n");
		}
		
		fwriter.write("\n");
		fwriter.write("\n");
		fwriter.write("Solution: ");
		fwriter.write("\n");
		fwriter.write("|---------------------------------------|\r\n" + 
				"||-------------------------------------||");
		fwriter.write("\n");
		for(int i = 0 ;i < arrayList2.size(); i++){
			for(int j = 0; j < arrayList2.get(i).length; j++) {
				fwriter.write(arrayList2.get(i)[j]);
			}
			fwriter.write("\n||-------------------------------------||\n");
			if(i%3==0)
				fwriter.write("||-------------------------------------||\n");				
		}
		fwriter.write("|---------------------------------------|");
		fwriter.write("\n\n Computation Time: "+(end-start)+" Miliseconds");
		fwriter.close();
		
	}//main

}
