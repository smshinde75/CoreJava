package concurrency;

class DivisibleByRange extends Thread {
	private long range[];
	private long number;
	private boolean isDivisible = false;
	private boolean rangeChecked = false;

	DivisibleByRange(long r[], long n) {
		range = r;
		number = n;
		start();
	}

	@Override
	public void run() {
		setName("Thread: " + range[0] + "-" + range[1]);
		for (long i = range[0]; i <= range[1]; i++) {
			// System.out.println( this.getName()+", Checking " + number + "/" +
			// i);
			if (number % i == 0) {
				System.out.println(this.getName() + ",Got Divisible : "
						+ number + " / " + i);
				setDivisible(true);
				this.stop();
				break;
			}
			if (i == range[1]) {
				setRangeChecked(true);
				// System.out.println(
				// this.getName()+", Range Checked:"+this.isRangeChecked());
			}

		}
	}

	public boolean isDivisible() {
		return isDivisible;
	}

	public void setDivisible(boolean isDivisible) {
		this.isDivisible = isDivisible;
	}

	public boolean isRangeChecked() {
		return rangeChecked;
	}

	public void setRangeChecked(boolean rangeChecked) {
		this.rangeChecked = rangeChecked;
	}
}

public class CheckPrimeThreaded {
	long number;
	int numberOfThreads;
	long[][] rangeArray;

	CheckPrimeThreaded(long n, int t) {
		this.number = n;
		this.numberOfThreads = t;
		rangeArray = divideInRange4Prime(number, numberOfThreads);
		System.out.println("number=" + number + ",numberOfThreads="
				+ numberOfThreads);
		System.out.println("rangeArray=");
		print2DArray(rangeArray, numberOfThreads);
	}

	boolean isPrimeThreaded() {
		if (number <= 2) {
			return true;
		}

		DivisibleByRange threads[] = new DivisibleByRange[numberOfThreads];

		for (int i = 0; i < numberOfThreads; i++) {
			threads[i] = new DivisibleByRange(rangeArray[i], number);
		}

		boolean stateChanged = false;

		while (stateChanged == false) {

			for (int i = 0; i < numberOfThreads; i++) {
				if (threads[i].isDivisible() == true) {
					stateChanged = true;
					for (int j = 0; j < numberOfThreads; j++) {
						if (i != j)
							threads[j].stop();
					}
					return false;
				}
			}

			boolean allRangeCheckedFlag = true;
			for (int k = 0; k < numberOfThreads; k++) {
				allRangeCheckedFlag = allRangeCheckedFlag
						& threads[k].isRangeChecked();
			}

			if (allRangeCheckedFlag == true) {
				break;
			}
		}

		if (stateChanged == true)
			return false;
		else
			return true;
	}

	static long[][] divideInRange4Prime(long number, int divideIn) {
		if (number < 10)
			divideIn = 1;

		long[][] store = new long[divideIn][2];

		for (int i = 0; i < divideIn; i++) {
			int rangeId = i + 1;

			if (i == 0) {
				store[i][0] = 2;
			} else {
				store[i][0] = (rangeId - 1) * Math.round(number / divideIn) + 1;
			}

			if (i == divideIn - 1) {
				store[i][1] = number - 1;
			} else {
				store[i][1] = Math.round(number / divideIn) * rangeId;
			}
		}
		return store;
	}

	void print2DArray(long[][] arr, int range) {
		for (int i = 0; i < range; i++) {
			for (int j = 0; j < 2; j++) {
				System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
	}

	// normal function
	private boolean isPrime(long number) {
		if (number <= 2) {
			return true;
		}
		for (long i = 2; i < number; i++) {
			if ((number % i) == 0) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		long num = 1311377311; // Some Big Primes: 1121111111, 1311377311
		long ms1, ms2;
		int threads = 25;

		ms1 = System.currentTimeMillis();
		CheckPrimeThreaded pt1 = new CheckPrimeThreaded(num, threads);
		System.out.println("\nIs Prime Threaded : " + pt1.isPrimeThreaded());
		ms2 = System.currentTimeMillis();
		System.out.println("Time taken=" + (ms2 - ms1));// 4633

	}

}
