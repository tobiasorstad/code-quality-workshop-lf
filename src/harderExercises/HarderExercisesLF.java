package harderExercises;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class HarderExercisesLF {

    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6372.8;
        double latDifference = toRadians(lat2-lat1);
        double lonDifference = toRadians(lon2-lon1);
        double latDifferenceSine = pow(sin( latDifference/2),2);
        double lonDifferenceSine = pow(sin(lonDifference/ 2),2);
        double latOneCosine = cos(toRadians(lat1));
        double latTwoCosine = cos(toRadians(lat2));

        return 2 * earthRadius * asin(sqrt( latDifferenceSine + lonDifferenceSine * latOneCosine * latTwoCosine));
    }

    public static double haversine2(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6372.8;
        double innerExpression =
            pow(Math.sin( toRadians(lat2-lat1)/2),2) +
                pow(sin(Math.toRadians(lon2 - lon1)/ 2),2) * cos(toRadians(lat1)) * cos(toRadians(lat2));
        return 2 * earthRadius * asin(sqrt(innerExpression));
    }


    // EXERCISE J
    // -------------------------------------------------------------------------
    //
    // [HARDER exercise]
    //
    // the method below is an extended version of formatURL, which we met above.
    // this version also allows for FTP and SFTP connections, IRC connections,
    // websocket URLs and non-standard port numbers.
    //
    // here, do all the refactorings you did above, but also try to clean up the
    // code dealing with port numbers. one option is to introduce a java.util.Map
    // from schemes to their standard port numbers. if the method grows large,
    // try extracting some code into new, smaller methods.
    //
    public static String formatURL2(String scheme, String hostname, Integer portNum, String path) {

        checkForExceptions(scheme, hostname, portNum, path);
        String res = scheme + "://" + hostname;

        if (!portNum.equals(acceptedSchemePortNumbers.get(scheme))) {
            res += ":" + portNum;
        }

        res += "/" + path;
        return res;
    }

    private static void checkForExceptions(String scheme, String hostname, Integer portNum, String path) throws IllegalArgumentException {
        if(scheme == null) {
            throw new IllegalArgumentException("scheme is null");
        }

        if (!acceptedSchemePortNumbers.containsKey(scheme)) {
            throw new IllegalArgumentException("illegal scheme '" + scheme + "'");
        }

        if(hostname == null){
            throw new IllegalArgumentException("hostname is null");
        }

        if(portNum == null){
            throw new IllegalArgumentException("portNum is null");
        }

        if(path == null){
            throw new IllegalArgumentException("path is null");
        }
    }

    private static final Map<String, Integer> acceptedSchemePortNumbers =
        Map.of(
            "ftp", 21,
            "sftp", 22,
            "irc", 6667,
            "http", 80,
            "https", 443,
            "ws", 80,
            "wss", 443
        );

    // EXERCISE K
    public static class Employee{
        String name;
        int salary;
        boolean active;
        String department;

        public Employee(String name, int salary, boolean active, String department){
            this.name = name;
            this.salary = salary;
            this.active = active;
            this.department = department;
        }
    }

    public static List<String> getTopFivePaidEmployeeNames(List<HarderExercisesLF.Employee> employees) {
        return employees.stream()
            .filter(e -> employeeIsValid(e) && e.active)
            .sorted((e1, e2) -> compareBasedOnSalary(e1, e2))
            .limit(5)
            .map(e -> e.name.toUpperCase())
            .distinct()
            .toList();
    }

    public static int compareBasedOnSalary(HarderExercisesLF.Employee employee1, HarderExercisesLF.Employee employee2){
        return Double.compare(employee2.salary, employee1.salary);
    }

    private static boolean employeeIsValid(HarderExercisesLF.Employee employee){
        return employee != null && employee.name != null;
    }

    // Exercise L
    public static boolean isValidUser(String username, String password) {
        final Map<String, String> users = Map.of("alice", "password123", "bob", "securepass");
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Exercise M
    public class calculateReceipt {
        private static final double BULK_DISCOUNT = 0.9; // 10% discount (multiply by 0.9)
        private static final int BULK_QUANTITY_THRESHOLD = 3;

        public static String calculateReceipt(String[] items, double[] prices, int[] qty) {
            if (!isValidInput(items, prices, qty)) {
                return "ERROR";
            }

            double totalPrice = calculateTotalPrice(prices, qty);
            int totalItems = calculateTotalItems(qty);
            String itemWithHighestTotal = findItemWithHighestTotal(items, prices, qty);

            return formatResult(totalPrice, totalItems, itemWithHighestTotal);
        }

        private static boolean isValidInput(String[] items, double[] prices, int[] qty) {
            return items != null &&
                    prices != null &&
                    qty != null &&
                    items.length == prices.length &&
                    prices.length == qty.length;
        }

        private static double calculateItemTotal(double price, int quantity) {
            double total = price * quantity;
            return quantity >= BULK_QUANTITY_THRESHOLD ? total * BULK_DISCOUNT : total;
        }

        private static double calculateTotalPrice(double[] prices, int[] qty) {
            return IntStream.range(0, prices.length)
                    .mapToDouble(i -> calculateItemTotal(prices[i], qty[i]))
                    .sum();
        }

        private static int calculateTotalItems(int[] qty) {
            return Arrays.stream(qty).sum();
        }

        private static String findItemWithHighestTotal(String[] items, double[] prices, int[] qty) {
            return IntStream.range(0, items.length)
                    .boxed()
                    .max(Comparator.comparingDouble(i -> calculateItemTotal(prices[i], qty[i])))
                    .map(i -> items[i])
                    .orElse("");
        }

        private static String formatResult(double totalPrice, int totalItems, String maxItem) {
            double roundedTotal = Math.round(totalPrice * 100.0) / 100.0;
            return roundedTotal + "," + totalItems + "," + maxItem;
        }
    }

    // Exercise N
    private static final Map<String, Double> BASE_DISCOUNTS = Map.of(
        "Gold", 15.0,
        "Silver", 5.0,
        "Bronze", 3.0
    );

    public static double calculateDiscount(String membershipLevel, boolean isHoliday) {
        double discount = BASE_DISCOUNTS.getOrDefault(membershipLevel, 0.0);
        return isHoliday ? discount + 5.0 : discount;
    }

    // EXERCISE O
    // -------------------------------------------------------------------------
    //
    // [HARDER exercise]
    //
    // This method decides if a year is a leap year or not.
    // The function has many nested if-checks, different ways of checking modulo, inconsistent
    // indentation and is overall pretty difficult to read. Additionally, it has a
    // useless helper function and the comments are misleading.
    //
    // To fix, check modulo in a more consistent way, tidy the if-checks, and fix or remove comments.
    // To consider: do you want to solve this with or without helper functions?
    //
    public static boolean isLeapYear(int year) {
        // If year is divisible by 4
        if (year % 4 == 0) {
            // If year is divisible by 100
            if (year % 100 == 0) {
                // If year is divisible by 400
                if (year % 400 == 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    // Or, using helper functions (personally I think it's easier to read)

    public static boolean isLeapYear2(int year) {
        return (isDivisibleByFour(year) && !isDivisibleByHundred(year)) || isDivisibleByFourHundred(year);
    }

    private static boolean isDivisibleByFour(int year) {
        return year % 4 == 0;
    }

    private static boolean isDivisibleByHundred(int year) {
        return year % 100 == 0;
    }

    private static boolean isDivisibleByFourHundred(int year) {
        return year % 400 == 0;
    }
}
