/*
To generate below given pattern, n=13 here:
     	   	  1
                 3,2
                4,5,6
             10, 9, 8, 7
            11,12,13,14,15
          21,20,19,18,17,16
         22,23,24,25,26,27,28
       36,35,34,33,32,31,30,29
      37,38,39,40,41,42,43,44,45
    55,54,53,52,51,50,49,48,47,46
   56,57,58,59,60,61,62,63,64,65,66
 78,77,76,75,74,73,72,71,70,69,68,67
79,80,81,82,83,84,85,86,87,88,89,90,91
*/


package general;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GeneratePattern {
	public static void main(String[] args) throws IOException {
		int n = 50;
		int start_digit = 0;
		int end_digit = 0;

		boolean last_number_odd = (n % 2 != 0 ? true : false);
		int last_start_digit = 0, last_end_digit = 0;
		int digits_in_last_number = 0;
		int total_chars_in_last_line = 0;

		if (last_number_odd) {
			last_end_digit = ((n * n) + n) / 2;
			digits_in_last_number = (new String(last_end_digit + "")).length();
		} else {
			last_start_digit = ((n * n) + n) / 2;
			digits_in_last_number = (new String(last_start_digit + "")).length();
		}

		total_chars_in_last_line = (digits_in_last_number * n) + (n - 1);

		File file = new File("c:\\pattern.txt");
		 
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		for (int i = 1; i <= n; i++) {

			if (i % 2 != 0) {
				start_digit = ((i * i) - i + 2) / 2;
				end_digit = ((i * i) + i) / 2;

				int digits_in_number = (new String(end_digit + "")).length();
				int characters_in_line = (digits_in_number * i) + (i - 1);
				int before_spaces = (total_chars_in_last_line - characters_in_line) / 2;
				for (int l = 1; l <= before_spaces; l++) {
					System.out.print(" ");
					bw.write(" ");
				}

				for (int k = start_digit; k <= end_digit; k++) {
					System.out.printf("%" + digits_in_number + "d%s", k,(k != end_digit ? "," : ""));
					bw.write(k+(k != end_digit ? "," : ""));
				}
			} else {
				start_digit = ((i * i) + i) / 2;
				end_digit = ((i * i) - i + 2) / 2;

				int digits_in_number = (new String(start_digit + "")).length();
				int characters_in_line = (digits_in_number * i) + (i - 1);
				int before_spaces = (total_chars_in_last_line - characters_in_line) / 2;
				for (int l = 1; l <= before_spaces; l++) {
					System.out.print(" ");
					bw.write(" ");
				}

				for (int k = start_digit; k >= end_digit; k--) {
					System.out.printf("%" + digits_in_number	+ "d%s", k, (k != end_digit ? "," : ""));
					bw.write(k+(k != end_digit ? "," : ""));
				}
			}
			
			System.out.println();
			bw.write("\n");
		}
		bw.close();
	}
}
