
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTest {

    public static void main(String[] args) {

        ZonedDateTime indiaDateTime = ZonedDateTime.now(ZoneId.of("Asia/Calcutta"));
        ZonedDateTime utcDateTime = ZonedDateTime.now(ZoneId.of("UTC"));
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        final ZoneId localZoneId = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.now(localZoneId);

        System.out.println(zonedDateTime);

        System.out.println("indiaDateTime " + indiaDateTime);
        System.out.println("utcDateTime " + utcDateTime);

        System.out.println(indiaDateTime.withZoneSameInstant(ZoneId.of("UTC")));

        System.out.println(ZonedDateTime.parse("2019-01-26T12:04:57.369+05:30[Asia/Calcutta]"));

        System.out.println(ZonedDateTime.parse("2019-01-26T12:04:57.369+05:30[Asia/Calcutta]")
                .withZoneSameInstant(ZoneId.of("Australia/Sydney")));

        System.out.println(localDate.atStartOfDay(ZoneId.of("Asia/Calcutta")));

        System.out.println(LocalTime.parse("12:04:57+05:30", DateTimeFormatter.ISO_TIME));
    }
}