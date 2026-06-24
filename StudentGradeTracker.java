import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> marks = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            System.out.print("Enter marks: ");
            int mark = sc.nextInt();
            sc.nextLine();

            names.add(name);
            marks.add(mark);
        }

        int sum = 0;
        int highest = marks.get(0);
        int lowest = marks.get(0);

        for (int mark : marks) {
            sum += mark;

            if (mark > highest) {
                highest = mark;
            }

            if (mark < lowest) {
                lowest = mark;
            }
        }

        double average = (double) sum / n;

        System.out.println("\n----- Student Report -----");

        for (int i = 0; i < n; i++) {
            System.out.println(names.get(i) + " : " + marks.get(i));
        }

        System.out.println("\nAverage Score = " + average);
        System.out.println("Highest Score = " + highest);
        System.out.println("Lowest Score = " + lowest);

        sc.close();
    }
}