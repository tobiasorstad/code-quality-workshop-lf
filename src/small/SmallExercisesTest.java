package small;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class SmallExercisesTest {
  @Test
  public void testCalculateLittleFormula() {
    assertEquals(42, SmallExercises.calculateLittleFormula(0, 0, 0, true));
    assertEquals(-42, SmallExercises.calculateLittleFormula(0, 0, 0, false));
    assertEquals(89, SmallExercises.calculateLittleFormula(3, 7, 11, true));
    assertEquals(-89, SmallExercises.calculateLittleFormula(3, 7, 11, false));
    assertEquals(125, SmallExercises.calculateLittleFormula(7, 3, 11, true));
    assertEquals(-125, SmallExercises.calculateLittleFormula(7, 3, 11, false));
    assertEquals(145, SmallExercises.calculateLittleFormula(11, 13, 7, true));
    assertEquals(-145, SmallExercises.calculateLittleFormula(11, 13, 7, false));
  }

  @Test
  public void testMayAccess() {
    assertFalse(SmallExercises.mayAccess(true, true, true));
    assertTrue(SmallExercises.mayAccess(true, true, false));
    assertFalse(SmallExercises.mayAccess(true, false, true));
    assertFalse(SmallExercises.mayAccess(true, false, false));
    assertFalse(SmallExercises.mayAccess(false, true, true));
    assertFalse(SmallExercises.mayAccess(false, true, false));
    assertFalse(SmallExercises.mayAccess(false, false, true));
    assertFalse(SmallExercises.mayAccess(false, false, false));
  }

  @Test
  public void testGetMinorYears() {
    assertEquals(
      IntStream.rangeClosed(0, 17).boxed().collect(Collectors.toSet()),
      SmallExercises.getMinorYears()
    );
  }

  @Test
  public void testDescribeNumber() {
    assertEquals("HUGE!", SmallExercises.describeNumber(20001));
    assertEquals("HUGE!", SmallExercises.describeNumber(20000));
    assertEquals("very big!", SmallExercises.describeNumber(19999));
    assertEquals("very big!", SmallExercises.describeNumber(10001));
    assertEquals("very big!", SmallExercises.describeNumber(10000));
    assertEquals("decently sized", SmallExercises.describeNumber(9999));
    assertEquals("decently sized", SmallExercises.describeNumber(0));
    assertEquals("decently sized", SmallExercises.describeNumber(-9999));
    assertEquals("very small!", SmallExercises.describeNumber(-10000));
    assertEquals("very small!", SmallExercises.describeNumber(-10001));
    assertEquals("very small!", SmallExercises.describeNumber(-19999));
    assertEquals("TINY!", SmallExercises.describeNumber(-20000));
    assertEquals("TINY!", SmallExercises.describeNumber(-20001));
  }

  @Test
  public void testIsIncreasingSequence() {
    // positive cases
    assertTrue(SmallExercises.isIncreasingSequence(-1, 0, 1, 2));
    assertTrue(SmallExercises.isIncreasingSequence(100, 200, 300, 400));
    assertTrue(SmallExercises.isIncreasingSequence(100, 101, 102, 103));

    // negative cases
    assertFalse(SmallExercises.isIncreasingSequence(0, 0, 0, 0));
    assertFalse(SmallExercises.isIncreasingSequence(0, 1, 2, 2));
    assertFalse(SmallExercises.isIncreasingSequence(0, -1, 2, 3));
    assertFalse(SmallExercises.isIncreasingSequence(1, 1, 2, 3));
    assertFalse(SmallExercises.isIncreasingSequence(201, 200, 300, 301));
  }

  @Test
  public void testExtractQueryParameters() {
    assertEquals(Map.of(), SmallExercises.extractQueryParameters("https://noogle.net/"));
    assertEquals(Map.of(), SmallExercises.extractQueryParameters("https://noogle.net/?"));

    assertEquals(
      Map.of("foo", "bar"),
      SmallExercises.extractQueryParameters("https://noogle.net/?foo=bar")
    );

    assertEquals(
      Map.of("a", "0", "b", "1", "c", "2"),
      SmallExercises.extractQueryParameters("https://noogle.net/?a=0&b=1&c=2")
    );

    assertEquals(
      Map.of("keyword1", "sonny", "keyword2", "cher", "maxage", "2049"),
      SmallExercises.extractQueryParameters("https://gugle.no/?keyword1=sonny&keyword2=cher&maxage=2049")
    );
  }

  @Test
  public void testFlooredRoot() {
    assertEquals(0, SmallExercises.flooredRoot(0.0));
    assertEquals(5, SmallExercises.flooredRoot(25.5));
    assertEquals(9, SmallExercises.flooredRoot(99.999));

    // test negative inputs
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.flooredRoot(-1.0));

    // just above Integer.MAX_VALUE
    assertThrows(
        IllegalArgumentException.class,
        () -> SmallExercises.flooredRoot(4.6116860184273879E18)
    );

    // just at Integer.MAX_VALUE
    assertEquals(
        Integer.MAX_VALUE,
        SmallExercises.flooredRoot(4.6116860141324206E18)
    );
  }

  @Test
  public void testFormatURL() {
    assertEquals(
        "http://gv.no/nyheter",
        SmallExercises.formatURL("http", "gv.no", "nyheter")
    );

    assertEquals(
        "https://lantega.com/",
        SmallExercises.formatURL("https", "lantega.com", "")
    );

    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL("not-http-or-https", "foo", "bar"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL("", "foo", "bar"));

    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL(null, "ok", "ok"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL("https", null, "ok"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL("https", "ok", null));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL(null, null, "ok"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL(null, "ok", null));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL("https", null, null));
    assertThrows(IllegalArgumentException.class, () -> SmallExercises.formatURL(null, null, null));
  }

  @Test
  public void testHaversine() {
    assertEquals(2887.2599506071106, SmallExercises.haversine(36.12, -86.67, 33.94, -118.40), 1E-6);
  }

  @Test
  public void testFormatURL2Basics() {
    assertEquals("http://gv.no/nyheter", SmallExercises.formatURL2("http", "gv.no", 80, "nyheter"));
    assertEquals("https://lantega.com/", SmallExercises.formatURL2("https", "lantega.com", 443, ""));
    assertEquals("https://lantega.com:4242/", SmallExercises.formatURL2("https", "lantega.com", 4242, ""));
  }

  @Test
  public void testFormatURL2StandardHappyCases() {
    final Map<String, Integer> supportedSchemesAndStandardPortNumbers =
      Map.of(
        "ftp", 21,
        "sftp", 22,
        "irc", 6667,
        "http", 80,
        "https", 443,
        "ws", 80,
        "wss", 443
      );

    final int nonStandardPort = 34567;

    for (final Map.Entry<String, Integer> schemeAndPort : supportedSchemesAndStandardPortNumbers.entrySet()) {
      final String scheme = schemeAndPort.getKey();
      final int standardPort = schemeAndPort.getValue();

      for (final String hostname : new String[]{ "", "h.o.s.t.n.a.m.e.nu" }) {
        for (final String path : new String[]{ "", "path", "sub/path/index.php" }) {
          assertEquals(
            String.format("%s://%s/%s", scheme, hostname, path),
            SmallExercises.formatURL2(scheme, hostname, standardPort, path)
          );

          assertEquals(
            String.format("%s://%s:%d/%s", scheme, hostname, nonStandardPort, path),
            SmallExercises.formatURL2(scheme, hostname, nonStandardPort, path)
          );
        }
      }
    }
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForUnsupportedSchemes() {
    assertThrows(
      IllegalArgumentException.class,
      () -> SmallExercises.formatURL2("not-supported-scheme", "foo", 4242, "bar")
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> SmallExercises.formatURL2("", "foo", 4242, "bar")
    );
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForNullInputs() {
    final String[] schemeInputs = new String[] { null, "https" };
    final String[] hostnameInputs = new String[] { null, "host.ru" };
    final Integer[] portNumInputs = new Integer[] { null, 4242 };
    final String[] pathInputs = new String[] { null, "jimmy.page.co.uk" };

    for (final String schemeOrNull : schemeInputs) {
      for (final String hostnameOrNull : hostnameInputs) {
        for (final Integer portNumOrNull : portNumInputs) {
          for (final String pathOrNull : pathInputs) {
            final boolean shouldThrowIllegalArgumentExceptionBecauseOfNullArgument = (
              schemeOrNull == null ||
              hostnameOrNull == null ||
              portNumOrNull == null ||
              pathOrNull == null
            );

            if (shouldThrowIllegalArgumentExceptionBecauseOfNullArgument) {
              assertThrows(
                IllegalArgumentException.class,
                () -> SmallExercises.formatURL2(
                  schemeOrNull,
                  hostnameOrNull,
                  portNumOrNull,
                  pathOrNull
                )
              );
            }
          }
        }
      }
    }
  }
}
