package com.twoclams.hww.server.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    private static long A_DAY_IN_MILLSEGUNDO = 24 * 3600000;

    private static long A_SECOND_IN_MILLSEGUNDO = 1000;

    public static final long SECOND_MILLISECS = 1000L;
    public static final long MINUTE_MILLISECS = 60L * SECOND_MILLISECS;
    public static final long HOUR_MILLISECS = 60L * MINUTE_MILLISECS;
    public static final long DAY_MILLISECS = 24L * HOUR_MILLISECS;
    public static final long WEEK_MILLISECS = 7L * DAY_MILLISECS;
    public static final long MOUNTH_MILLISECS = 30L * DAY_MILLISECS;
    public static final long YEAR_MILLISECS = 365L * DAY_MILLISECS;

    // ENUMS
    private enum Time {
        SECOND("second", "seconds", SECOND_MILLISECS), MINUTE("minute", "minutes", MINUTE_MILLISECS), HOUR(
                "hour", "hours", HOUR_MILLISECS), DAY("day", "days", DAY_MILLISECS), WEEK("week",
                "weeks", WEEK_MILLISECS), MONTH("month", "months", MOUNTH_MILLISECS), YEAR("año",
                "años", YEAR_MILLISECS);

        private String singular;
        private String plural;
        private long millisecs;

        private Time(String name, String plural, long millisecs) {
            this.singular = name;
            this.plural = plural;
            this.setMillisecs(millisecs);
        }

        public String getPlural() {
            return this.plural;
        }

        public long getMillisecs() {
            return this.millisecs;
        }

        private void setMillisecs(long millisecs) {
            this.millisecs = millisecs;
        }

        public String getSingular() {
            return singular;
        }
    }

    // METHODS
    public static boolean isInInterval(Date date, long interval) {
        return getTimeDifferenceToCurrent(date) < interval;
    }

    public static long getTimeDifferenceToCurrent(Date date) {
        return System.currentTimeMillis() - date.getTime();
    }

    public static String getTimeDifference(Date a, Date b) {
        long time = getTimeDiference(a, b);
        int seconds = (int) ((time / 1000) % 60);
        int minutes = (int) ((time / 60000) % 60);
        int hours = (int) ((time / 3600000) % 24);
        String secondsStr = (seconds < 10 ? "0" : "") + seconds;
        String minutesStr = (minutes < 10 ? "0" : "") + minutes;
        String hoursStr = (hours < 10 ? "0" : "") + hours;
        return hoursStr + "h:" + minutesStr + "m:" + secondsStr + "s";
    }

    public static long getDayDiferenceBetween(Date date, Date anotherDate) {
        long time = getTimeDiference(date, anotherDate);
        long days = getDayInitialTimeMill();
        return (days > time) ? 0 : (time / A_DAY_IN_MILLSEGUNDO) + 1;
    }

    public static String formatDate(Date date) {
        // TODO: format it according to user locale
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy HH:mm");
        return fmt.format(date);
    }

    public static String getTimeDiferenceAsString(Date date) {
        return getTimeDiferenceAsString(date, new Date());
    }

    public static String getTimeDiferenceAsString(Date date, Date now) {
        long timeDif = Math.abs(now.getTime() - date.getTime());
        long quantity = 0;
        Time[] dateQuantities = Time.values();
        for (int i = dateQuantities.length - 1; i >= 0; i--) {
            if ((quantity = timeDif / dateQuantities[i].getMillisecs()) > 0) {
                return getTimeDiferenceQuantity(quantity, dateQuantities[i]);
            }
        }
        return "0";
    }

    public static String getTimeDiferenceUnitAsString(Date date) {
        Date now = new Date();
        long timeDif = Math.abs(now.getTime() - date.getTime());
        long quantity = 0;
        Time[] dateQuantities = Time.values();
        for (int i = dateQuantities.length - 1; i >= 0; i--) {
            if ((quantity = timeDif / dateQuantities[i].getMillisecs()) > 0) {
                if (quantity == 1) {
                    return dateQuantities[i].getSingular();
                } else {
                    return dateQuantities[i].getPlural();
                }
            }
        }
        return Time.SECOND.plural;

    }

    /**
     * Devuelta la cantidad de dias de diferencia entre una fecha y hoy. 0,
     * 1,3,..., n etc dias
     * 
     * @param date
     * @return
     */
    public static long getDayDiference(Date date) {
        long time = getTimeDiference(new Date(), date);
        long days = getDayInitialTimeMill();
        return (days > time) ? 0 : (time / A_DAY_IN_MILLSEGUNDO) + 1;
    }

    /**
     * Devuelta la cantidad de dias de diferencia entre una fecha y hoy a las
     * 00:00 hs. 0, 1,3,..., n etc dias
     * 
     * @param date
     * @return
     */
    public static long getDayDiferenceToTodaysMightnight(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(new Date());
        gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
        gc.set(GregorianCalendar.MINUTE, 59);
        gc.set(GregorianCalendar.SECOND, 59);
        long time = getTimeDiference(gc.getTime(), date);
        // long days = getDayInitialTimeMill();
        return time / A_DAY_IN_MILLSEGUNDO;// + 1;
    }

    // TODO Improve
    public static int getYearDiferenceToCurrent(Date date) {
        if (date == null) {
            date = new Date();
        }
        Date birthday = getDayOf(date);
        Date currentDate = getCurrentDay();

        Date dateDiference = new Date(currentDate.getTime() - birthday.getTime() + DAY_MILLISECS);
        Calendar calendarDifference = new GregorianCalendar();
        calendarDifference.setTime(dateDiference);

        int result = calendarDifference.get(Calendar.YEAR) - 1970;

        return result;
    }

    public static int getCurrentYear() {
        return getYear(new Date());
    }

    public static int getYear(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int result = calendar.get(Calendar.YEAR);

        return result;
    }

    public static long getSecondsDiference(Date date) {

        long time = getTimeDiference(new Date(), date);
        return (time / A_SECOND_IN_MILLSEGUNDO);
    }

    private static long getTimeDiference(Date a, Date b) {
        return a.getTime() > b.getTime() ? a.getTime() - b.getTime() : b.getTime() - a.getTime();
    }

    private static String getTimeDiferenceQuantity(long quantity, Time dateQuantity) {
        return String.valueOf(quantity);
    }

    public static boolean isAdult(Date date) {
        Date yearsBefore = org.apache.commons.lang.time.DateUtils.addYears(new Date(), -18);
        return date.before(yearsBefore);
    }

    private static long getDayInitialTimeMill() {
        Calendar todayStartDay = Calendar.getInstance();
        todayStartDay.set(Calendar.HOUR_OF_DAY, 0);
        todayStartDay.set(Calendar.MINUTE, 0);
        todayStartDay.set(Calendar.SECOND, 0);
        todayStartDay.set(Calendar.MILLISECOND, 0);

        return getTimeDiference(new Date(), todayStartDay.getTime());
    }

    public static Date getCurrentDay() {
        return getDayOf(new Date());
    }

    public static Date getDayOf(Date instant) {
        return org.apache.commons.lang.time.DateUtils.truncate(instant, Calendar.DATE);
    }

    public static Date getTodayDay() {
        return getCurrentDay();
    }

    public static int getDifferenceToMidnight() {
        Calendar calendar = Calendar.getInstance();
        long currentTime = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);

        long midnightTime = calendar.getTimeInMillis();

        return (int) ((midnightTime - currentTime) / 1000);
    }

    /**
     * Get the minutes from the time in seconds
     * 
     * @param seconds
     * @return
     */
    public static int getMinutes(Long seconds) {
        Long mod = seconds % 3600;
        Long minutes = mod / 60;
        return minutes.intValue();
    }

    /**
     * Get the hours from the time in seconds.
     * 
     * @param seconds
     * @return
     */
    public static int getHours(Long seconds) {
        Long hours = seconds / 3600;
        return hours.intValue();
    }

    public static long differenceBetweenDates(Date checkin, Date checkout) {
        if (checkin == null || checkout == null) {
            return 0;
        }
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(checkin);
        calendar2.setTime(checkout);
        long milliseconds1 = calendar1.getTimeInMillis();
        long milliseconds2 = calendar2.getTimeInMillis();
        long diff = milliseconds2 - milliseconds1;
        return diff / (24 * 60 * 60 * 1000);
    }
}
