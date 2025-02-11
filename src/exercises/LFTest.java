package exercises;

import exercises.SmallExercisesLF;
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
}
