
import org.apache.commons.lang.StringUtils;

import java.time.*;
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
        System.out.println("indiaDateTime to AST " + indiaDateTime.withZoneSameInstant(
                ZoneId.of("Australia/Sydney")));
        System.out.println("india->AST->india " + indiaDateTime.withZoneSameInstant(
                ZoneId.of("Australia/Sydney")).withZoneSameInstant(ZoneId.of("Asia/Calcutta")));
        System.out.println("utcDateTime " + utcDateTime);

        System.out.println(indiaDateTime.withZoneSameInstant(ZoneId.of("UTC")));

        System.out.println(ZonedDateTime.parse("2019-01-26T12:04:57.369+05:30[Asia/Calcutta]"));

        System.out.println(ZonedDateTime.parse("2019-01-26T12:04:57.369+05:30[Asia/Calcutta]")
                .withZoneSameInstant(ZoneId.of("Australia/Sydney")));

        System.out.println(localDate.atStartOfDay(ZoneId.of("Asia/Calcutta")));

        System.out.println(LocalTime.parse("12:04:57+05:30", DateTimeFormatter.ISO_TIME));
        System.out.println(LocalTime.parse("22:03", DateTimeFormatter.ISO_TIME));
        System.out.println(ZonedDateTime.of(zonedDateTime.toLocalDate(), zonedDateTime.toLocalTime(), localZoneId));
        System.out.println(zonedDateTime.toLocalTime());


        ZonedDateTime zonedHour = ZonedDateTime.of(zonedDateTime.toLocalDate()
                , LocalTime.parse("19" + ":51"
                        , DateTimeFormatter.ISO_TIME)
                , ZoneId.of("Asia/Calcutta"))
                .withZoneSameInstant(localZoneId);
        System.out.println("zonedHour " + zonedHour);

        String llHr = StringUtils.leftPad(Integer.toString(1), 2, "0");
        System.out.println(llHr);

        System.out.println(localTime.atOffset(ZoneOffset.of("+05:30")).format(DateTimeFormatter.ISO_OFFSET_TIME));
        System.out.println(ZonedDateTime.now().toLocalTime());
    }
}