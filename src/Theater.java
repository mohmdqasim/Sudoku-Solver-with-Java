import java.util.*;
public class Theater {
	
	static ArrayList<Integer> empty = new ArrayList<Integer>();
	static int totalMismatch = 0;
	
	public static void initialise(String [][] list) {
		for(int i = 0 ; i < list.length;i++) {
			for(int j = 0; j < list[0].length;j++) {
				list[i][j] = "...";
			}
			empty.add(list[0].length);
		}
	}
	
	public static void print(String [][] list) {
		for(int i = 0 ; i <list[0].length;i++) {
			System.out.print("   "+(i+1));
		}
		System.out.println();
		for(int i = 0 ; i < list.length;i++) {
			System.out.print((i+1) + " ");
			for(int j = 0; j < list[0].length;j++) {
				System.out.print(list[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean isFilled(String [][] list) {
		boolean result = true;
		for(int i = 0 ; i < list.length;i++) {
			for(int j = 0; j < list[0].length;j++) {
				if(list[i][j].equals("...")) {
					result = false;
				}
			}
		}
		return result;
	}
	
    static int findRowWithAvailableSeat(String[][] matrix, int tickets) {
        final int rows = matrix.length;
        final int columns = matrix[0].length;
        int seatCounter = 0;
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < columns-1; c++) {
                if (!matrix[r][c].equals("...")) {
                    continue;
                } if (matrix[r][c].equals(matrix[r][c + 1])) {
                    seatCounter++;
                    if (seatCounter == tickets) {
                        return r;
                    }
                }
            }
        }
        return 0;
    }
    
    public static int getSeat(String[][] list, int row, int booking) {
    	int count = 0;
		for(int i = 0; i < list[row].length;i++) {
			if(list[row][i]=="...") {
				count++;
				if(count == booking) {
					return i-(count-1);
				}
			}else {
				count = 0;
			}
		}
		return -1;
    }
	
	public static boolean book(String[][] list, int newBooking,int count) {
		int row = findRowWithAvailableSeat(list,newBooking);
		if(row<0)
			return false;
		int seat = getSeat(list,row,newBooking);
		if(seat<0)
		{
			return false;
		}

		for(int i = 0; i < newBooking; i++) {
			if(count < 10)
				list[row][seat+i] = "00" + count;
			else if(count < 100)
				list[row][seat+i] = "0" + count;
			else
				list[row][seat+i] = "" + count;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter Number of Rows : ");
		int rows = input.nextInt();
		System.out.print("Enter Number of Seats is Each Row : ");
		int seats = input.nextInt();
		
		String [][] theater = new String[rows][seats];
		initialise(theater);
		print(theater);
		Random rand = new Random();
		
		
		int count = 1;
		while(totalMismatch != 10 && ! isFilled(theater)) {
			int newBooking = rand.nextInt(5) + 1;
			boolean status = book(theater,newBooking,count);
			if(status) {	
				System.out.println();
				System.out.println("Booking Successfull.. \n After Booking new Theater is ");
				print(theater);
				count = count+1;
			}else {
				totalMismatch++;
			}
		}
		
		if(totalMismatch==10) {
			System.out.println("Total Mismatch Limit Reached..");
		}
		if(isFilled(theater)) {
			System.out.println("Theater is Filled..");
		}
		

	}

}
