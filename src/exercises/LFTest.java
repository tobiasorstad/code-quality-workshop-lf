package exercises.lf;

import org.junit.Test;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class LFTest {
  @Test
  public void testCalculateLittleFormula() {
    assertEquals(42, SmallExercisesLF.calculateLittleFormula(0, 0, 0, true));
    assertEquals(-42, SmallExercisesLF.calculateLittleFormula(0, 0, 0, false));
    assertEquals(89, SmallExercisesLF.calculateLittleFormula(3, 7, 11, true));
    assertEquals(-89, SmallExercisesLF.calculateLittleFormula(3, 7, 11, false));
    assertEquals(125, SmallExercisesLF.calculateLittleFormula(7, 3, 11, true));
    assertEquals(-125, SmallExercisesLF.calculateLittleFormula(7, 3, 11, false));
    assertEquals(145, SmallExercisesLF.calculateLittleFormula(11, 13, 7, true));
    assertEquals(-145, SmallExercisesLF.calculateLittleFormula(11, 13, 7, false));
  }

  @Test
  public void testMayAccess() {
    assertFalse(SmallExercisesLF.mayAccess(true, true, true));
    assertTrue(SmallExercisesLF.mayAccess(true, true, false));
    assertFalse(SmallExercisesLF.mayAccess(true, false, true));
    assertFalse(SmallExercisesLF.mayAccess(true, false, false));
    assertFalse(SmallExercisesLF.mayAccess(false, true, true));
    assertFalse(SmallExercisesLF.mayAccess(false, true, false));
    assertFalse(SmallExercisesLF.mayAccess(false, false, true));
    assertFalse(SmallExercisesLF.mayAccess(false, false, false));
  }

  @Test
  public void testGetMinorYears() {
    assertEquals(
      IntStream.rangeClosed(0, 17).boxed().collect(Collectors.toSet()),
      SmallExercisesLF.getMinorYears()
    );
  }

  @Test
  public void testDescribeNumber() {
    assertEquals("HUGE!", SmallExercisesLF.describeNumber(20001));
    assertEquals("HUGE!", SmallExercisesLF.describeNumber(20000));
    assertEquals("very big!", SmallExercisesLF.describeNumber(19999));
    assertEquals("very big!", SmallExercisesLF.describeNumber(10001));
    assertEquals("very big!", SmallExercisesLF.describeNumber(10000));
    assertEquals("decently sized", SmallExercisesLF.describeNumber(9999));
    assertEquals("decently sized", SmallExercisesLF.describeNumber(0));
    assertEquals("decently sized", SmallExercisesLF.describeNumber(-9999));
    assertEquals("very small!", SmallExercisesLF.describeNumber(-10000));
    assertEquals("very small!", SmallExercisesLF.describeNumber(-10001));
    assertEquals("very small!", SmallExercisesLF.describeNumber(-19999));
    assertEquals("TINY!", SmallExercisesLF.describeNumber(-20000));
    assertEquals("TINY!", SmallExercisesLF.describeNumber(-20001));
  }

  @Test
  public void testIsIncreasingSequence() {
    // positive cases
    assertTrue(SmallExercisesLF.isIncreasingSequence(-1, 0, 1, 2));
    assertTrue(SmallExercisesLF.isIncreasingSequence(100, 200, 300, 400));
    assertTrue(SmallExercisesLF.isIncreasingSequence(100, 101, 102, 103));

    // negative cases
    assertFalse(SmallExercisesLF.isIncreasingSequence(0, 0, 0, 0));
    assertFalse(SmallExercisesLF.isIncreasingSequence(0, 1, 2, 2));
    assertFalse(SmallExercisesLF.isIncreasingSequence(0, -1, 2, 3));
    assertFalse(SmallExercisesLF.isIncreasingSequence(1, 1, 2, 3));
    assertFalse(SmallExercisesLF.isIncreasingSequence(201, 200, 300, 301));
  }

  @Test
  public void testExtractQueryParameters() {
    assertEquals(Map.of(), SmallExercisesLF.extractQueryParameters("https://noogle.net/"));
    assertEquals(Map.of(), SmallExercisesLF.extractQueryParameters("https://noogle.net/?"));

    assertEquals(
      Map.of("foo", "bar"),
      SmallExercisesLF.extractQueryParameters("https://noogle.net/?foo=bar")
    );

    assertEquals(
      Map.of("a", "0", "b", "1", "c", "2"),
      SmallExercisesLF.extractQueryParameters("https://noogle.net/?a=0&b=1&c=2")
    );

    assertEquals(
      Map.of("keyword1", "sonny", "keyword2", "cher", "maxage", "2049"),
      SmallExercisesLF.extractQueryParameters("https://gugle.no/?keyword1=sonny&keyword2=cher&maxage=2049")
    );
  }

  @Test
  public void testFlooredRoot() {
    assertEquals(0, SmallExercisesLF.flooredRoot(0.0));
    assertEquals(5, SmallExercisesLF.flooredRoot(25.5));
    assertEquals(9, SmallExercisesLF.flooredRoot(99.999));

    // test negative inputs
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.flooredRoot(-1.0));

    // just above Integer.MAX_VALUE
    assertThrows(
        IllegalArgumentException.class,
        () -> SmallExercisesLF.flooredRoot(4.6116860184273879E18)
    );

    // just at Integer.MAX_VALUE
    assertEquals(
        Integer.MAX_VALUE,
        SmallExercisesLF.flooredRoot(4.6116860141324206E18)
    );
  }

  @Test
  public void testFormatURL() {
    assertEquals(
        "http://gv.no/nyheter",
        SmallExercisesLF.formatURL("http", "gv.no", "nyheter")
    );

    assertEquals(
        "https://lantega.com/",
        SmallExercisesLF.formatURL("https", "lantega.com", "")
    );

    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL("not-http-or-https", "foo", "bar"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL("", "foo", "bar"));

    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL(null, "ok", "ok"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL("https", null, "ok"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL("https", "ok", null));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL(null, null, "ok"));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL(null, "ok", null));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL("https", null, null));
    assertThrows(IllegalArgumentException.class, () -> SmallExercisesLF.formatURL(null, null, null));
  }

  @Test
  public void testHaversine() {
    assertEquals(2887.2599506071106, SmallExercisesLF.haversine(36.12, -86.67, 33.94, -118.40), 1E-6);
  }

  @Test
  public void testHaversine2() {
    assertEquals(2887.2599506071106, SmallExercisesLF.haversine2(36.12, -86.67, 33.94, -118.40), 1E-6);
  }

  @Test
  public void testFormatURL2Basics() {
    assertEquals("http://gv.no/nyheter", SmallExercisesLF.formatURL2("http", "gv.no", 80, "nyheter"));
    assertEquals("https://lantega.com/", SmallExercisesLF.formatURL2("https", "lantega.com", 443, ""));
    assertEquals("https://lantega.com:4242/", SmallExercisesLF.formatURL2("https", "lantega.com", 4242, ""));
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
            SmallExercisesLF.formatURL2(scheme, hostname, standardPort, path)
          );

          assertEquals(
            String.format("%s://%s:%d/%s", scheme, hostname, nonStandardPort, path),
            SmallExercisesLF.formatURL2(scheme, hostname, nonStandardPort, path)
          );
        }
      }
    }
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForUnsupportedSchemes() {
    assertThrows(
      IllegalArgumentException.class,
      () -> SmallExercisesLF.formatURL2("not-supported-scheme", "foo", 4242, "bar")
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> SmallExercisesLF.formatURL2("", "foo", 4242, "bar")
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
                () -> SmallExercisesLF.formatURL2(
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
