package tasks;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {
    public static List<Integer> getEvenNumbers(List<Object> objects) {
        List<Integer> evenNumbers = new ArrayList<>();
        for (Object obj : objects) {
            if (obj instanceof Integer) {
                Integer number = (Integer) obj;
                if (number % 2 == 0) {
                    evenNumbers.add(number);
                }
            }
        }
        return evenNumbers;
    }

    public static int getSum(List<Object> objects) {
        int sum = 0;
        for (Object obj : objects) {
            if (obj instanceof Integer) {
                sum += (Integer) obj;
            }
        }
        return sum;
    }

    public static double getAverage(List<Object> objects) {
        if (objects.isEmpty()) {
            return 0;
        }
        int sum = getSum(objects);
        return (double) sum / objects.size();
    }
}