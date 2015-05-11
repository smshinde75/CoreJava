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
