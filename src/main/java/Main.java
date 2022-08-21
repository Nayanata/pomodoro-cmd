import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println ("Let's start timer (-h for help)");
        String[] userInput = new Scanner(System.in).nextLine().split(" ");
        int workTime = 1;
        int breakTime = 1;
        int workSize = 30;
        int breakSize = 30;
        int countIterations = 1;
        int multiSize = 1;

        for (int i = 0; i < userInput.length; i++) {
            switch (userInput[i]) {
                case "-h" -> System.out.println("""
                        \n\n Pomodoro is a self management programm
                        -h : how to use the program
                        -w : working time in min
                        -b : time for break in min
                        """);
                case "-w" -> workTime = Integer.parseInt(userInput[++i]);
                case "-b" -> breakTime = Integer.parseInt(userInput[++i]);
                case "-count" -> countIterations = Integer.parseInt(userInput[++i]);
                case "-m" -> multiSize = Integer.parseInt(userInput[++i]);
            }
        }
        System.out.printf("Working time = %d, break time = %d \n", workTime, breakTime);

        timer(workTime,breakTime,workSize, breakSize, countIterations, multiSize);
    }

    public static void timer(int workTimeMin, int breakTimeMin, int sizeWork, int sizeBreak, int cnt, int multiplicator) throws InterruptedException {

        int newWorkTime = workTimeMin;
        long startTime;
        long endTime;

        for (int i = 1; i <= cnt; i++) {

            startTime = System.currentTimeMillis();
            printProgress("Working progress :: ", newWorkTime, sizeWork);
            printProgress("Break progress :: ", breakTimeMin, sizeBreak);
            endTime = System.currentTimeMillis();
            newWorkTime *= multiplicator;

            System.out.printf("Timer has been worked for %d min \n", (endTime - startTime)/(1000*60));
        }
    }
    private static void printProgress(String process, int time, int size) throws InterruptedException {
        int length;
        int rep;
        length = 60* time / size;
        rep = 60* time /length;
        int stretchb = size /(3* time);
        for(int i=1; i <= rep; i++){
            double x = i;
            x = 1.0/3.0 *x;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            double w = time *stretchb;
            double percent = (x/w) *1000;
            x /=stretchb;
            x *= 10;
            x = Math.round(x);
            x /= 10;
            percent = Math.round(percent);
            percent /= 10;
            System.out.print(process + percent+"% " + (" ").repeat(5 - (String.valueOf(percent).length())) +"[" + ("#").repeat(i) + ("-").repeat(rep - i)+"]    ( " + x +"min / " + time +"min )"+  "\r");
            TimeUnit.SECONDS.sleep(length);

        }
        System.out.println();
    }
}


