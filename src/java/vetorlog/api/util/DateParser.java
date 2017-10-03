package vetorlog.api.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


@Log4j2
public class DateParser extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        if(value == null) {
            gen.writeNull();
        } else {
            gen.writeString(getFormatedDate(value));
        }
    }

    private static String getFormatedDate(Date value) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.format(value);
    }

    public static Date getFormatedDate(String value) throws ParseException {
        return new Date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(value).getTime());
    }

    public static Date formatDate(String data) throws Exception {
        if (data == null || data.equals(""))
            return null;

        Date date;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        date = formatter.parse(data);
        return date;
    }
}