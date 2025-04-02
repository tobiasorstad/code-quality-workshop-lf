package harderExercises;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class HarderExercisesLFTest {

  @Test
  public void testHaversine() {
    assertEquals(2887.2599506071106, HarderExercisesLF.haversine(36.12, -86.67, 33.94, -118.40), 1E-6);
  }

  @Test
  public void testHaversine2() {
    assertEquals(2887.2599506071106, HarderExercisesLF.haversine2(36.12, -86.67, 33.94, -118.40), 1E-6);
  }

  @Test
  public void testFormatURL2Basics() {
    assertEquals("http://gv.no/nyheter", HarderExercisesLF.formatURL2("http", "gv.no", 80, "nyheter"));
    assertEquals("https://lantega.com/", HarderExercisesLF.formatURL2("https", "lantega.com", 443, ""));
    assertEquals("https://lantega.com:4242/", HarderExercisesLF.formatURL2("https", "lantega.com", 4242, ""));
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
            HarderExercisesLF.formatURL2(scheme, hostname, standardPort, path)
          );

          assertEquals(
            String.format("%s://%s:%d/%s", scheme, hostname, nonStandardPort, path),
            HarderExercisesLF.formatURL2(scheme, hostname, nonStandardPort, path)
          );
        }
      }
    }
  }

  @Test
  public void testFormatURL2ThrowsIllegalArgumentExceptionForUnsupportedSchemes() {
    assertThrows(
      IllegalArgumentException.class,
      () -> HarderExercisesLF.formatURL2("not-supported-scheme", "foo", 4242, "bar")
    );

    assertThrows(
      IllegalArgumentException.class,
      () -> HarderExercisesLF.formatURL2("", "foo", 4242, "bar")
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
                () -> HarderExercisesLF.formatURL2(
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

  @Test
  public void testGetTopPaidActiveHarderExercisesLF() {
    List<HarderExercisesLF.Employee> employees = List.of(
        new HarderExercisesLF.Employee("Alice", 60000, true, "1"),
        new HarderExercisesLF.Employee("Bob", 55000, true, "1"),
        new HarderExercisesLF.Employee("Charlie", 70000, false, "1"), // Inactive
        new HarderExercisesLF.Employee("Dave", 80000, true, "1"),
        new HarderExercisesLF.Employee("Eve", 90000, true, "1"),
        new HarderExercisesLF.Employee("Frank", 75000, true, "1"),
        new HarderExercisesLF.Employee("Grace", 65000, true, "1")
    );

    List<String> result = HarderExercisesLF.getTopFivePaidEmployeeNames(employees);
    assertEquals(List.of("EVE", "DAVE", "FRANK",  "GRACE","ALICE"), result);
  }

  @Test
  public void testHandlesNullAndDuplicates() {
    List<HarderExercisesLF.Employee> employees = List.of(
        new HarderExercisesLF.Employee("Alice", 60000, true, "1"),
        new HarderExercisesLF.Employee("Bob", 55000, true, "1"),
        new HarderExercisesLF.Employee("Alice", 60000, true, "1"), // Duplicate name
        new HarderExercisesLF.Employee(null, 70000, true, "1"), // Null name
        new HarderExercisesLF.Employee("Eve", 90000, true, "1")
    );

    List<String> result = HarderExercisesLF.getTopFivePaidEmployeeNames(employees);
    assertEquals(List.of("EVE", "ALICE", "BOB"), result);
  }

  @Test
  public void validUserReturnsTrue() {
    assertTrue(HarderExercisesLF.isValidUser("alice", "password123"));
  }

  @Test
  public void invalidUserReturnsFalse() {
    assertFalse(HarderExercisesLF.isValidUser("charlie", "randompass"));
  }

  @Test
  public void goldMemberOnHolidayGets20Percent() {
    assertEquals(20.0, HarderExercisesLF.calculateDiscount("Gold", true), 0.00001);
  }

  @Test
  public void silverMemberNotOnHolidayGets5Percent() {
    assertEquals(5.0, HarderExercisesLF.calculateDiscount("Silver", false), 0.00001);
  }

  @Test
  public void bronzeMemberOnHolidayGets7Percent() {
    assertEquals(8.0, HarderExercisesLF.calculateDiscount("Bronze", true), 0.00001);
  }

  @Test
  public void regularCustomerOnHolidayGets5Percent() {
    assertEquals(5.0, HarderExercisesLF.calculateDiscount("Regular", true), 0.00001);
  }

  @Test
  public void bronzeMemberNotOnHolidayGets3Percent() {
    assertEquals(3.0, HarderExercisesLF.calculateDiscount("Bronze", false), 0.00001);
  }

  @Test
  public void goldMemberOnHolidayGets20Percent2() {
    assertEquals(20.0, HarderExercisesLF.calculateDiscount2("Gold", true), 0.00001);
  }

  @Test
  public void silverMemberNotOnHolidayGets5Percent2() {
    assertEquals(5.0, HarderExercisesLF.calculateDiscount2("Silver", false), 0.00001);
  }

  @Test
  public void testIsLeapYear() {
    assertTrue(HarderExercisesLF.isLeapYear(420));
    assertTrue(HarderExercisesLF.isLeapYear(2000));
    assertFalse(HarderExercisesLF.isLeapYear(2001));
    assertTrue(HarderExercisesLF.isLeapYear(2004));
    assertFalse(HarderExercisesLF.isLeapYear(2100));
    assertFalse(HarderExercisesLF.isLeapYear(2200));
    assertFalse(HarderExercisesLF.isLeapYear(2300));
    assertTrue(HarderExercisesLF.isLeapYear(2400));
  }

  @Test
  public void testIsLeapYear3() {
    assertTrue(HarderExercisesLF.isLeapYear3(420));
    assertTrue(HarderExercisesLF.isLeapYear3(2000));
    assertFalse(HarderExercisesLF.isLeapYear3(2001));
    assertTrue(HarderExercisesLF.isLeapYear3(2004));
    assertFalse(HarderExercisesLF.isLeapYear3(2100));
    assertFalse(HarderExercisesLF.isLeapYear3(2200));
    assertFalse(HarderExercisesLF.isLeapYear3(2300));
    assertTrue(HarderExercisesLF.isLeapYear3(2400));
  }
}
