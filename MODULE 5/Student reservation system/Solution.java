
import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.text.*;

class Student implements Comparable<Student> {
	String name;
	String dob;
	int sub1;
	int sub2;
	int sub3;
	int marks;
	String category;
	boolean flag;

	Student(String name, String dob, int sub1, int sub2, int sub3, int marks, String category) {
		// your code goes here.
		this.name = name;
		this.dob = dob;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
		this.marks = marks;
		this.category = category;
	}

	private long calculateDays(String expiry) {
		final long not = -100000000;
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		Date ConvertExpire;
		long days;
		try {
			ConvertExpire = ft.parse(expiry);
			Date date = new Date();
			days = -TimeUnit.DAYS.convert(ConvertExpire.getTime() - date.getTime(), TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			days = not;
		}
		if (days != not) {
			return days;
		} else
			return -1;
	}

	public int compareTo(Student stu) {
		// your code goes here.
		if (this.marks > stu.marks) {
			return 1;
		} else if (this.marks < stu.marks) {
			return -1;
		} else if (this.sub3 > stu.sub3) {
			return 1;
		} else if (this.sub3 < stu.sub3) {
			return -1;
		} else if (this.sub2 > stu.sub2) {
			return 1;
		} else if (this.sub2 < stu.sub2) {
			return -1;
		} else if (calculateDays(this.dob) < calculateDays(stu.dob)) {
			return 1;
		} else if (calculateDays(this.dob) > calculateDays(stu.dob)) {
			return -1;
		} else {
			return 1;
		}
	}

}

class Selection {
	Student[] list;
	int vacancies;
	int open;
	int bcReserved;
	int scReserved;
	int stReserved;

	Selection(int no_of_Students, int no_of_positions, int open, int bcReserved, int scReserved, int stReserved) {
		list = new Student[no_of_Students];
		vacancies = no_of_positions;
		this.open = open;
		this.bcReserved = bcReserved;
		this.scReserved = scReserved;
		this.stReserved = stReserved;
	}

	public void sort() {
		Student temp = null;
		for (int i = 1; i < list.length; i++) {
			for (int j = i; j > 0; j--) {
				if (list[j].compareTo(list[j - 1]) != -1) {
					temp = list[j];
					list[j] = list[j - 1];
					list[j - 1] = temp;
				}
			}
		}
	}

	public Student[] getSelectedList() {
		// your code goes here.
		sort();
		return list;
	}
}

public class Solution {
	public static void main(String[] args) throws Exception {
		int no_of_testcases = 4;
		int i = 3;
		String path1 = "C:\\Users\\PINAKA\\Desktop\\ADS 1\\ADS-1\\MODULE 5\\Student reservation system\\testcases\\input00";
		String path2 = "C:\\Users\\PINAKA\\Desktop\\ADS 1\\ADS-1\\MODULE 5\\Student reservation system\\testcases\\output00";
		while (i < no_of_testcases) {
			String inputFile = path1 + i + ".txt";
			String outputFile = path2 + i + ".txt";
			ReadInput(inputFile, outputFile);
			i++;
		}
	}

	public static void ReadInput(String inputFile, String outputFile) throws Exception {
		Scanner sc = new Scanner(new File(inputFile));
		int no_of_Students = Integer.parseInt(sc.nextLine());
		int no_of_positions = Integer.parseInt(sc.nextLine());
		int open = Integer.parseInt(sc.nextLine());
		int BC_count = Integer.parseInt(sc.nextLine());
		int SC_count = Integer.parseInt(sc.nextLine());
		int ST_count = Integer.parseInt(sc.nextLine());
		int BC_orig = 0;
		int SC_orig = 0;
		int ST_orig = 0;
		Selection sel_obj = new Selection(no_of_Students, no_of_positions, open, BC_count, SC_count, ST_count);
		for (int i = 0; i < no_of_Students; i++) {
			String[] Student_info = sc.nextLine().split(",");
			Student stu_obj = new Student(Student_info[0], Student_info[1], Integer.parseInt(Student_info[2]),
					Integer.parseInt(Student_info[3]), Integer.parseInt(Student_info[4]),
					Integer.parseInt(Student_info[5]), Student_info[6]);
			sel_obj.list[i] = stu_obj;
		}
		Student[] SortedList = sel_obj.getSelectedList();
		for (int i = 0; i < SortedList.length; i++) {
			System.out.println(SortedList[i].name);
			System.out.println(SortedList[i].category);
			System.out.println();
			if (SortedList[i].category.equals("BC")) {
				BC_orig++;
			}
			if (SortedList[i].category.equals("SC")) {
				SC_orig++;
			}
			if (SortedList[i].category.equals("ST")) {
				ST_orig++;
			}
		}
		Student[] finallist = new Student[no_of_positions];
		for (int i = 0; i < open; i++) {
			finallist[i] = SortedList[i];
			System.out.println(finallist[i].name);
			if (SortedList[i].category.equals("BC")) {
				BC_orig--;
			}
			if (SortedList[i].category.equals("SC")) {
				SC_orig--;
			}
			if (SortedList[i].category.equals("ST")) {
				ST_orig--;
			}
			SortedList[i] = null;
		}
		System.out.println();
		int left = open;
		while (left < no_of_positions) {
			for (int i = 0; i < no_of_Students; i++) {
				if (SortedList[i] != null && SortedList[i].category.equals("BC") && BC_count > 0 && BC_orig > 0) {
					finallist[left] = SortedList[i];
					SortedList[i] = null;
					left++;
					BC_count--;
					BC_orig--;
				}

				else if (SortedList[i] != null && SortedList[i].category.equals("SC") && SC_count > 0 && SC_orig > 0) {
					finallist[left] = SortedList[i];
					SortedList[i] = null;
					left++;
					SC_count--;
					SC_orig--;
				}

				else if (SortedList[i] != null && SortedList[i].category.equals("ST") && ST_count > 0 && ST_orig > 0) {
					finallist[left] = SortedList[i];
					SortedList[i] = null;
					left++;
					ST_count--;
					ST_orig--;
				}

				else if (SortedList[i] != null && (SC_count == 0 || SC_orig == 0) && (BC_count == 0 || BC_orig == 0)
						&& (ST_count == 0 || ST_orig == 0)) {
					finallist[left] = SortedList[i];
					SortedList[i] = null;
					left++;
				}
				if (left == no_of_positions)
					break;
			}
		}
		for (int i = 0; i < finallist.length; i++) {
			System.out.println(finallist[i].name);
		}
		System.out.println();
		checkOutput(finallist, no_of_positions, outputFile);

	}

	public static void checkOutput(Student[] arr, int no_of_positions, String outputFile) throws Exception {
		Scanner sc = new Scanner(new File(outputFile));
		for (int i = 0; i < no_of_positions; i++) {
			if (!sc.nextLine().equals(arr[i].name)) {
				System.out.println("Your output is not matching with output in file " + outputFile);
				return;
			}
		}
		System.out.println("your output with file " + outputFile + " Matched. This Test case passed");

	}

}