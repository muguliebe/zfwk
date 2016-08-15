package zany.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 날짜 유틸리티
 * 
 * @version
 *          <ul>
 *          <li>151210 | 윤준호 | 최초생성</li>
 *          </ul>
 */
public class DateUtils {


    /**
     * 지정된 포맷 형식으로 변경
     * 
     * @param format SimpleDateFormat
     * @return String formated Current Time
     */
    public static String getFormated(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
        String result = formatter.format(date);
        return result;
    }

    /**
     * 지정된 포맷 형식으로 현재시간 구하기
     * 
     * @param format SimpleDateFormat
     * @return String formated Current Time
     */
    public static String getCurrent(String format) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.KOREA);
        String result = formatter.format(currentTime);
        return result;
    }

    /**
     * 현재일자
     * 
     * @return "YYYYMMDD"
     */
    public static String getCurrentDate() {
        return getCurrent("yyyyMMdd");
    }

    /**
     * 일자
     * 
     * @return Date
     */
    public static String getDay(Date date) {
        return getFormated(date, "yyyyMMdd");
    }

    /**
     * 현재년월
     * 
     * @return "YYYYMM"
     */
    public static String getYearMonth() {
        return getCurrent("yyyyMM");
    }

    /**
     * 현재시분.
     *
     * @return the hour min
     */
    public static String getHourMin() {
        return getCurrent("HHmm");
    }

    /**
     * 시분초.
     *
     * @return the hour min
     */
    public static String getTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss", Locale.KOREA);
        String result = formatter.format(date);
        return result;
    }

    /**
     * 현재시분초.
     *
     * @return the hour min
     */
    public static String getCurrentTime() {
        return getCurrent("HHmmss");
    }

    /**
     * 현재시간.
     *
     * @return the current time stamp
     */
    public static String getCurrentTimeStamp() {
        return getCurrent("yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * yyyyMMdd 형식인지 검사.
     *
     * @param date the date
     * @return true, if is date
     */
    public static boolean isDate(String date) {
        try {
            new SimpleDateFormat("yyyyMMdd").parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * yyyyMMddHHmmss 형식인지 검사.
     *
     * @param date the date
     * @return true, if is dtm
     */
    public static boolean isDtm(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date day = sdf.parse(date);
            if (sdf.format(day).equals(date) == false) {
                return false;
            }

            return true;

        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 일자계산.
     *
     * @param baseDate the base date
     * @param addDay the add day
     * @return the string
     * @throws ParseException
     * @throws ZException the z exception
     */
    public static String dayAfter(String baseDate, int addDay) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(baseDate));
        calendar.add(Calendar.DATE, addDay);

        String returnDate = dateFormat.format(calendar.getTime());
        return returnDate;

    }

    public static Date secBefore(Date baseDate, int second) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate);
        calendar.add(Calendar.SECOND, -second);

        return calendar.getTime();

    }

    public static Date minBefore(Date baseDate, int minute) throws ParseException {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate);
        calendar.add(Calendar.MINUTE, -minute);

        return calendar.getTime();

    }
}
