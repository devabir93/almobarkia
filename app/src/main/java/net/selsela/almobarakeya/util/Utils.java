package net.selsela.almobarakeya.util;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import timber.log.Timber;

public class Utils {

    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    private static Locale currentLocal = Locale.ENGLISH;

    // Takes Strings like "[a, b, c]"
    @NonNull
    public static ArrayList<String> parseStringToList(String s) {
        String tempString = s;
        if (s.contains("[")) {
            tempString = tempString.replace("[", "");
            tempString = tempString.replace("]", "");
            tempString = tempString.replace(" ", "");
            return new ArrayList<>(Arrays.asList(tempString.split(",")));
        }
        return new ArrayList<>(Collections.singletonList(s));

    }

    public static int findMax(@NonNull int... vals) {
        int max = 0;

        for (int d : vals) {
            if (d > max) max = d;
        }

        return max;
    }

    @NonNull
    public static List<String> getNoDuplicateList(@NonNull List<String> al) {
        // add elements to al, including duplicates
        Set<String> hs = new HashSet<>();
        hs.addAll(al);
        al.clear();
        al.addAll(hs);

        return al;
    }

    public static List<Integer> randomColor(int size) {
        int[] redValue = {255, 0, 0, 255, 0, 255, 192, 128, 128, 128, 0, 128, 0, 0};
        int[] greenValue = {0, 255, 0, 255, 255, 0, 192, 128, 0, 128, 128, 0, 128, 128};
        int[] blueValue = {0, 0, 255, 0, 255, 255, 192, 128, 0, 0, 0, 128, 128, 128};
        List<Integer> colorList = new ArrayList<>();
        while (colorList.size() < size) {
            for (int i = 0; i < redValue.length; i++) {
                int posRed = i;
                int posGreen = i;
                int posBlue = i;
                int currentRed = 0, currentGreen = 0, currentBlue = 0;

                if (i < redValue.length) {
                    currentRed = redValue[posRed];
                    currentGreen = greenValue[posGreen];
                    currentBlue = blueValue[posBlue];
                }

                if (currentRed < 253) {
                    currentRed = redValue[posRed] + 2;
                } else {
                    currentRed = redValue[posRed] - 2;
                }
                if (currentGreen < 253) {
                    currentGreen = greenValue[posGreen] + 2;
                } else {
                    currentGreen = greenValue[posGreen] - 2;

                }
                if (currentBlue < 253) {
                    currentBlue = blueValue[posBlue] + 2;
                } else {
                    currentBlue = blueValue[posBlue] - 2;

                }

                colorList.add(Color.rgb(currentRed, currentGreen, currentBlue));
                //  }
                Timber.d("colorList %s", colorList);

            }
        }
        Timber.d("colorList2 %s", colorList);
        return colorList;
    }

    public static char[] searchForChar(String parsedEquation) {
        char[] charArray = new char[2];
        for (int i = 0; i < parsedEquation.length(); i++) {
            if (String.valueOf(parsedEquation.charAt(i)).equals("+")
                    || String.valueOf(parsedEquation.charAt(i)).equals("-")) {
                charArray[0] = (char) i;
                charArray[1] = parsedEquation.charAt(i);
                return charArray;
            }
        }
        return null;
    }


    /////////////formate//////////

    public static String formatDecimal(String value) {
        if (value.equals("0"))
            return String.valueOf(value);
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat decimalFormat = new DecimalFormat("#.##", symbols);
        return decimalFormat.format(Float.parseFloat(value));
    }

    public static double formatDecimal(double value, String decimal) {
        try {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
            DecimalFormat decimalFormat = new DecimalFormat(decimal, symbols);
            return Double.parseDouble(decimalFormat.format(value));
        } catch (NumberFormatException ex) { // handle your exception
            return value;
        }
    }

    public static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }

    @NonNull
    public static String fomratePercentage(int number, int total) {
        if (total == 0 || number == 0) {
            return 0 + "%";
        } else {
            float f = (number * 100.0f) / total;
            String percent = String.format(currentLocal, "%.02f", f);

            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.format(Math.round(f));
            String mPercent = NumberFormat.getNumberInstance(Locale.ENGLISH).format(f);
            return Math.round(f) + "%";
        }
    }

    ////////////// Date /////////////////////

    public static String formatDateText(Date date, @NonNull String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, currentLocal);
        return simpleDateFormat.format(date);
    }

    /**
     * Format date to be displayed.
     *
     * @param stringDate
     * @return
     */
    public static String formatDateToDisplay(String stringDate) {
        Date date;
        String dateText;
        try {
            date = stringToDateWithFormat(stringDate, "yyyy-MM-dd hh:mm:ss");
            dateText = formatDateText(date, "yyyy-MM-dd");
        } catch (Exception e) {
            // e.printStackTrace();
//                date = ViewUtil.stringToDateWithFormat(mQuestion.getAnswer().getAnswer() , "dd/MM/yyyy");

            dateText = stringDate;
        }
        return dateText;
    }

    public static String formatTime(@NonNull Date date, @Nullable String timeFormat) {
        if (timeFormat != null) {
            // 24-hour mode.
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timeFormat, currentLocal);
            return simpleDateFormat.format(date);
        } else {
            // 12-hour mode.
            int hour = date.getHours();
            int minutes = date.getMinutes();
            String format, miniutesFormat = "";
            if (hour == 0) {
                hour += 12;
                format = "AM";
            } else if (hour == 12) {
                format = "PM";
            } else if (hour > 12) {
                hour -= 12;
                format = "PM";
            } else {
                format = "AM";
            }
            if (minutes < 10) {
                miniutesFormat = "0" + minutes;
            } else {
                miniutesFormat = minutes + "";
            }
            return hour + ":" + miniutesFormat + " " + format;
        }
    }

    @Nullable
    public static String formatStringDate(String text, @NonNull String formateDest) {
        Timber.d("formateDest : %s -- %s", text, formateDest);
        SimpleDateFormat src = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", currentLocal);
        SimpleDateFormat dest = new SimpleDateFormat(formateDest, currentLocal);
        Date date = null;
        try {
            date = src.parse(text);
        } catch (ParseException e) {
            date = stringToDateWithFormat(text, "dd/MM/yyyy hh:mm:ss");
            //handle exception
        }
        String result = null;
        try {
            result = dest.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ,dd/ MMM /yyyy", currentLocal);
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }

    @NonNull
    public static List<String> formatStringDate(@NonNull List<String> srcList, @NonNull String formateDest) {
        SimpleDateFormat src = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", currentLocal);
        SimpleDateFormat dest = new SimpleDateFormat(formateDest, currentLocal);
        List<String> formatedList = new ArrayList();
        for (int i = 0; i < srcList.size(); i++) {

            Date date = null;
            try {
                date = src.parse(srcList.get(i));
            } catch (ParseException e) {
                date = stringToDateWithFormat(srcList.get(i), "dd/MM/yyyy hh:mm:ss");
                //handle exception
            }
            try {
                formatedList.add(dest.format(date));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formatedList;
    }

    @NonNull
    public static List<String> getCurrentDateForStatistics() {
        List<String> listOfDate = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Calendar cal = GregorianCalendar.getInstance();

            if (i > 0) {
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, -i);
            }
            Date date = cal.getTime();
            SimpleDateFormat df1 = new SimpleDateFormat("MMM dd", currentLocal);
            String formattedDate1 = df1.format(date.getTime());
            listOfDate.add(formattedDate1);
        }
        Timber.i("listOfFormattedDate %s", listOfDate.toString());

        //Date currentDate=currentDate();

        return listOfDate;
    }

    @NonNull
    public static List<String> getDateForLastWeek() {
        List<String> listOfDate = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Calendar cal = GregorianCalendar.getInstance();

            if (i > 0) {
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, -i);
            }
            Date date = cal.getTime();
            SimpleDateFormat df1 = new SimpleDateFormat("MMM dd", currentLocal);
            String formattedDate1 = df1.format(date.getTime());
            listOfDate.add(formattedDate1);
        }
        Timber.i("listOfDate %s", listOfDate.toString());
        return listOfDate;
    }

    @Nullable
    public static Date convertStringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", currentLocal);
        Date currentDateandTime = null;
        try {
            currentDateandTime = sdf.parse(date);
        } catch (ParseException e) {
            currentDateandTime = stringToDateWithFormat(date, "dd/MM/yyyy hh:mm:ss");
        }
        return currentDateandTime;
    }

    @Nullable
    public static Date stringToDateWithFormat(String dateText, @NonNull String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, currentLocal);
        Date currentDateAndTime = null;
        try {
            currentDateAndTime = sdf.parse(dateText);
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return currentDateAndTime;
    }

    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Calendar convertDateToCalendar(String stringDate) {
        Timber.d("convertDateToCalendar %s", stringDate);
        String format = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format, currentLocal);
        Date date = null;
        try {
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
            date = stringToDateWithFormat(stringDate, "dd/MM/yyyy");
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }


    private static int getTimeDistanceInMinutes(long time) {
        long timeDistance = currentDate().getTime() - time;
        return Math.round((Math.abs(timeDistance) / 1000) / 60);
    }

    public static String relativize(String sdate2) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzzz yyyy", currentLocal);
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = sdf.parse(getCurrentDate());
            date2 = sdf.parse(sdate2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timber.i("date1 %s", date1.toString());
        Timber.i("date2 %s", date2.toString());
        Timber.i("getCurrentDate() %s", getCurrentDate());
        Timber.i("sdate2 %s", sdate2);
        assert date2.getTime() >= date1.getTime();
        long duration = date2.getTime() - date1.getTime();
        long converted;
        if ((converted = TimeUnit.MILLISECONDS.toDays(duration)) > 0) {
            return String.format("%d %s ago", converted, converted == 1 ? "day" : "days");
        } else if ((converted = TimeUnit.MILLISECONDS.toHours(duration)) > 0) {
            return String.format("%d %s ago", converted, converted == 1 ? "hour" : "hours");
        } else if ((converted = TimeUnit.MILLISECONDS.toMinutes(duration)) > 0) {
            return String.format("%d %s ago", converted, converted == 1 ? "minute" : "minutes");
        } else if ((converted = TimeUnit.MILLISECONDS.toSeconds(duration)) > 0) {
            return String.format("%d %s ago", converted, converted == 1 ? "second" : "seconds");
        } else {
            return "just now";
        }
    }


    public static boolean compare(String date1, String date2) {
        Date convertedDate1 = convertStringToDate(date1);
        Date convertedDate2 = convertStringToDate(date2);
        if (convertedDate1 != null && convertedDate2 != null)
            return convertedDate1.after(convertedDate2);
        else if (convertedDate2 == null)
            return true; // ( date1) newer than date2
        else if (convertedDate1 == null)
            return false;// ( date1) not newer than date2

        return true;
    }
//    public String getSpeicifcValFromConfig(List<Config> configList, String attr) {
//        HashMap<String, String> productMap = new HashMap<String, String>();
//        for (Config config : configList) {
//            productMap.put(config.getName(), config.getValue());
//        }
//        return productMap.get(attr);
//    }
}