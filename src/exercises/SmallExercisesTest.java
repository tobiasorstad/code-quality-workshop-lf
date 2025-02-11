package exercises;

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
}
