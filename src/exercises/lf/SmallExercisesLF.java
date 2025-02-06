package exercises.lf;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Math.*;


public final class SmallExercisesLF {
  // EXERCISE A
  // -------------------------------------------------------------------------
  //
  // the method below computes some cute little formula
  //
  // the body of the method contains an expression that appears in both the
  // "if" part and the "else" part of a conditional
  //
  // can you refactor the method so that this expression is not repeated?
  //
  public static int calculateLittleFormula(int a, int b, int c, boolean positive) {
    int z = c * a + 2 * b + 42;
    return positive ? z : -z;
  }


  // EXERCISE B
  // -------------------------------------------------------------------------
  //
  // the method below performs an access check
  //
  // as always, the implementation is correct, but the method urgently needs
  // some refactoring!
  //
  // can you refactor the method to make it cleaner? note that there is only
  // one combination of inputs that returns TRUE
  //
  public static boolean mayAccess(boolean hasAccount, boolean hasCorrectPassword, boolean hasBeenDenied) {
    return hasAccount && hasCorrectPassword && !hasBeenDenied;
  }


  // EXERCISE C
  // -------------------------------------------------------------------------
  //
  // the method below creates a set of all the numbers that correspond to years
  // were you are not considered an adult under Norwegian law.
  //
  // often, creating a small collection "manually" in the fashion we do here
  // might be okay, but when we're adding 18 elements, it gets quite tedious...
  //
  // refactor the method to make use of a for loop to add elements to the set
  //
  // (...or, choose some other nice and readable way to refactor the method,
  // instead of a for loop, if you prefer :-)
  //
  public static Set<Integer> getMinorYears() {
    Set<Integer> minorYears = new TreeSet<>();
    for(int i = 0; i < 18; i++){
      minorYears.add(i);
    }
    return minorYears;
  }


  // EXERCISE D
  // -------------------------------------------------------------------------
  //
  // the method below is used to describe a number
  // (I really have no use for such a method, but let's ignore that for now...)
  //
  // the implementation is correct, but the problem is that the code style
  // in the body is all over the place
  //
  // refactor the code so everything is nice and consistent.
  // you're welcome to let the editor help you along, if you like.
  //
  public static String describeNumber(int n) {
    if(n >= 20_000){
        return "HUGE!";
    }
    if(n >= 10_000){
      return "very big!";
    }
    if(n <= -20000){
      return "TINY!";
    }
    if(n <= -10 * 1000){
      return "very small!";
    }
    return "decently sized";
  }


  // EXERCISE E
  // -------------------------------------------------------------------------
  //
  // the method below is used to determine whether four integers make up a
  // (strictly) increasing sequence
  //
  // identify what's going on, and rewrite the method to a simpler and more
  // readable format.
  //
  // your refactored code should come out as a single readable line of code,
  // with no if statements AND no new variables...
  //
  public static boolean isIncreasingSequence(int a, int b, int c, int d) {
    return a < b && b < c && c < d;
  }


  // EXERCISE F
  // -------------------------------------------------------------------------
  //
  // the method below extracts query parameters from an URI string.
  // for example, for the URI string
  //    https://gugle.no/?keyword1=sonny&keyword2=cher&maxage=2049
  // it returns a Map with the entries
  //    "keyword1" -> "sonny"
  //    "keyword2" -> "cher"
  //    "maxage" -> "2049"
  //
  // the method is correct, but the implementation is sloppy and hard to read.  
  //
  // apply the following refactorings to improve the quality of the code:
  // - introduce better variables names
  // - fix the comment
  // - extract the repeated expression (uriString.indexOf("?")), and store it
  //   in a variable with a good name
  //
  // if you want to, you may also introduce constants for the magic string
  // literals, ?, &, and =
  //
  public static Map<String, String> extractQueryParameters(String uriString) {
    Map<String, String> queryParameters = new HashMap<>();
    int parameterStart = uriString.indexOf("?");

    // If uri has no question mark or question mark is at the end of the uri there are no parameters
    if(parameterStart == -1 || parameterStart == uriString.length()-1) {
      return queryParameters;
    }

    for (String parameter : uriString.substring(parameterStart + 1).split("&")) {
      String[] valueKeyArray = parameter.split("=");
      queryParameters.put(valueKeyArray[0], valueKeyArray[1]);
    }
    return queryParameters;
  }


  // EXERCISE G
  // -------------------------------------------------------------------------
  //
  // the method below has a good name, but the variables names in its body
  // are a disgrace, and most of the comments and exception messages
  // also need some urgent care
  //
  // fix all these issues:
  // - variable names,
  // - comments,
  // - exception messages
  //
  public static int flooredRoot(double x) {
    // Cannot calculate root of 0
    if (x < 0) {
      throw new IllegalArgumentException("Cannot calculate root of 0");
    }

    double root = Math.sqrt(x);

    // Check if root is over integer maxvalue, to make sure it can be stored as an int
    if (root > (double) Long.MAX_VALUE || (long) root > Integer.MAX_VALUE) {
      throw new IllegalArgumentException("Square root too large to store as an int");
    }

    return (int) root;
  }


  // EXERCISE H
  // -------------------------------------------------------------------------
  //
  // the method below is used to format an URL string. it will throw
  // IllegalArgumentException if any of its input arguments are null,
  // or if the argument scheme is not "http" or "https".
  //
  // the method body is written in a very complicated manner, which makes it
  // hard to see what's going on.
  //
  // refactor the method so you start by dealing with all the errors that
  // should cause IllegalArgumentException at the beginning of the method.
  // make it so that you don't use the else keyword anywhere.
  //
  public static String formatURL(String scheme, String hostname, String path) {
    if(scheme == null) {
      throw new IllegalArgumentException("scheme is null");
    }

    if(!scheme.equals("http") && !scheme.equals("https")) {
      throw new IllegalArgumentException("scheme must be 'http' or 'https'");
    }

    if(hostname == null){
      throw new IllegalArgumentException("hostname is null");
    }

    if(path == null){
      throw new IllegalArgumentException("path is null");
    }
    return scheme + "://" + hostname + "/" + path;
  }


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

    if( portNum == null){
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
}
