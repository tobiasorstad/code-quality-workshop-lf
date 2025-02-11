package harderExercises;

import java.util.List;
import java.util.Map;

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

    public record Employee( String name, int salary, boolean active, String department){}

    public static List<String> getTopFivePaidEmployeeNames(List<HarderExercisesLF.Employee> employees) {
        return employees.stream()
            .filter(e -> employeeIsValid(e) && e.active())
            .sorted((e1, e2) -> compareBasedOnSalary(e1, e2))
            .limit(5)
            .map(e -> e.name().toUpperCase())
            .distinct()
            .toList();
    }

    public static int compareBasedOnSalary(HarderExercisesLF.Employee employee1, HarderExercisesLF.Employee employee2){
        return Double.compare(employee2.salary(), employee1.salary());
    }

    private static boolean employeeIsValid(HarderExercisesLF.Employee employee){
        return employee != null && employee.name != null;
    }
}
