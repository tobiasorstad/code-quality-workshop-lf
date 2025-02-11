package harderExercises;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.*;
import static java.lang.Math.toRadians;

public class HarderExercises {

    /*
        Forslag til tema:
        Lambda og streams.
        Designpatters, strategy pattern, adapter, factory, building pattern.
     */

    // EXERCISE I
    // -------------------------------------------------------------------------
    //
    // [HARDER exercise]
    //
    // the method below implements the famous Haversine formula for computing
    // the shortest (non-flat-earth) distance between two coordinates on earth.
    //
    // the body is written in a quite cryptic and terse way, and is a good
    // candidate for some refactoring...
    //
    // your tasks are as follows:
    // - try to split the one giant expression into simpler expressions
    // - extract the magic number 6372.8 to a constant, with a nice name
    // - fix the coding style so it's consistent
    // - use either the Math.[method] syntax consistently, or the static imports.
    //   mixing both doesn't look nice.
    //
    // note that even after a nice refactoring, this formula should still look
    // a bit cryptic! it's advanced stuff, after all. all we ask here, is that
    // you make it a bit clearer.
    //
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        return 2 *  6372.8 * asin(sqrt(pow(Math.sin( toRadians(lat2-lat1)/2),2) +
            pow(sin(Math.toRadians(lon2 - lon1)/ 2),2) * cos(toRadians(lat1)) *cos(toRadians(lat2))));
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
        String res = "";

        if (scheme != null) {
            if (!scheme.equals("ftp") && !scheme.equals("sftp") &&
                !scheme.equals("irc") && !scheme.equals("http") &&
                !scheme.equals("https") && !scheme.equals("ws") &&
                !scheme.equals("wss")) {
                throw new IllegalArgumentException("illegal scheme '" + scheme + "'");
            }

            res += scheme;
            res += "://";

            if (hostname != null) {
                res += hostname;
                if (portNum != null) {
                    if (scheme.equals("ftp") && portNum != 21) {
                        res += ":";
                        res += portNum;
                    } else if (scheme.equals("sftp") && portNum != 22) {
                        res += ":";
                        res += portNum;
                    } else if (scheme.equals("irc") && portNum != 6667) {
                        res += ":";
                        res += portNum;
                    } else if (scheme.equals("http") && portNum != 80) {
                        res += ":";
                        res += portNum;
                    } else if (scheme.equals("https") && portNum != 443) {
                        res += ":";
                        res += portNum;
                    } else if (scheme.equals("ws") && portNum != 80) {
                        res += ":";
                        res += portNum;
                    } else if (scheme.equals("wss") && portNum != 443) {
                        res += ":";
                        res += portNum;
                    }

                    res += "/";

                    if (path != null) {
                        res += path;
                    } else {
                        throw new IllegalArgumentException("path is null");
                    }
                } else {
                    throw new IllegalArgumentException("portNum is null");
                }
            } else {
                throw new IllegalArgumentException("hostname is null");
            }
        } else {
            throw new IllegalArgumentException("scheme is null");
        }

        return res;
    }

    // EXERCISE K
    // -------------------------------------------------------------------------
    //
    // [HARDER exercise]
    //
    // The method below does a lot of things, and it is very hard to understand.
    // Consider removing some lines of code that does not affect the final result.
    // Create some smaller methods with descriptive names that make the method
    // easier to understand. Consider creating a new method name.
    //
    public record Employee( String name, int salary, boolean active, String department){}

    public static List<String> getTopEmployees(List<Employee> employees) {
        return employees.stream()
            .sorted(Comparator.comparing(e -> e.name))
            .collect(Collectors.groupingBy(Employee::department))
            .values()
            .stream().flatMap(List::stream)
            .filter(e -> e != null && e.active())
            .sorted((e1, e2) -> Double.compare(e2.salary(), e1.salary()))
            .limit(5)
            .filter(e -> e.name() != null)
            .map(e -> e.name().toUpperCase())
            .distinct()
            .collect(Collectors.toList());
    }

    public static List<String> getTopFivePaidEmployeeNames(List<Employee> employees) {
        return employees.stream()
            .filter(e -> employeeIsValid(e) && e.active())
            .sorted((e1, e2) -> compareBasedOnSalary(e1, e2))
            .limit(5)
            .map(e -> e.name().toUpperCase())
            .distinct()
            .toList();
    }

    public static int compareBasedOnSalary(Employee employee1, Employee employee2){
        return Double.compare(employee2.salary(), employee1.salary());
    }

    private static boolean employeeIsValid(Employee employee){
        return employee != null && employee.name != null;
    }


}
