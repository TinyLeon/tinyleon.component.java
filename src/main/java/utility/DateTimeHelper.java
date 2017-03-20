package utility;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by LeonChen
 */
public class DateTimeHelper {
    private static Date _minDateTime = new Date(70, 0, 1, 8, 0, 0);

    public static long convertDateToLong(Date targetDate) {
        long timeSpan = targetDate.getTime() - _minDateTime.getTime();
        return timeSpan / 1000;
    }

    public static Date convertLongToDate(long dateLong) {
        return new Date(_minDateTime.getTime() + dateLong * 1000);
    }

    /*
     * 将时间转换为指定格式
     *
     */
    public static String toString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String res = formatter.format(date);
        return res;
    }

    public static Date getDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date targetDate = new Date();
        try {
            targetDate = sdf.parse(DateTimeHelper.toString(new Date(), format));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return targetDate;
        }
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateStr, pos);
        return currentTime_2;
    }

    public static Date convertStrToDate(String dateStr) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date dateTime = formatter.parse(dateStr, pos);
        return dateTime;
    }

    public static Date LocalDateTimeToDate(LocalDateTime dateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = dateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime DateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }
}
