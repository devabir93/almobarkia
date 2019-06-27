package com.selsela.example.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import net.objecthunter.exp4j.ExpressionBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

import static android.content.Context.MODE_PRIVATE;

public final class AppUtils {


    public static String convertobjToJson(Object objectList, Class<?> aClass) {
        SuperclassExclusionStrategy ex = new SuperclassExclusionStrategy();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        return gson.toJson(objectList);
    }

    public static Object convertJsonToObj(String objectList, Class<?> aClass) {
/*        SuperclassExclusionStrategy ex = new SuperclassExclusionStrategy();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().addDeserializationExclusionStrategy(ex).addSerializationExclusionStrategy(ex).create();
        return gson.fromJson(objectList, aClass);*/

        Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();
        Gson gson = new Gson();
        Collection<Integer> ints2 = gson.fromJson(objectList, collectionType);
        return ints2;
    }

    public static List<Object> getListFromJsonObject(JSONObject jObject) throws JSONException {
        List<Object> returnList = new ArrayList<Object>();
        Iterator<String> keys = jObject.keys();

        List<String> keysList = new ArrayList<String>();
        while (keys.hasNext()) {
            keysList.add(keys.next());
        }
        Collections.sort(keysList);

        for (String key : keysList) {
            List<Object> nestedList = new ArrayList<Object>();
            nestedList.add(key);
            nestedList.add(convertJsonItem(jObject.get(key)));
            returnList.add(nestedList);
        }

        return returnList;
    }

    /**
     * Returns a Java object representation of objects that are
     * encountered inside of JSON created using the org.json package.
     * JSON arrays and objects are transformed into their list
     * representations using getListFromJsonArray and
     * getListFromJsonObject respectively.
     * <p>
     * Java Boolean values and the Strings "true" and "false" (case
     * insensitive) are inserted as Booleans. Java Numbers are
     * inserted without modification and all other values are inserted
     * as their toString(). value.
     *
     * @param o An item in a JSON array or JSON object to convert.
     * @return A Java Object representing o or the String "null"
     * if o is null.
     * @throws JSONException if o fails to parse.
     */
    public static Object convertJsonItem(Object o) throws JSONException {
        if (o == null) {
            return "null";
        }

        if (o instanceof JSONObject) {
            return getListFromJsonObject((JSONObject) o);
        }

        if (o instanceof JSONArray) {
            return getListFromJsonArray((JSONArray) o);
        }

        if (o.equals(Boolean.FALSE) || (o instanceof String &&
                ((String) o).equalsIgnoreCase("false"))) {
            return false;
        }

        if (o.equals(Boolean.TRUE) || (o instanceof String && ((String) o).equalsIgnoreCase("true"))) {
            return true;
        }

        if (o instanceof Number) {
            return o;
        }

        return o.toString();
    }

    public static String getJsonRepresentation(Object value) throws JSONException {
        if (value == null || value.equals(null)) {
            return "null";
        }
      /*
       * This has been commented out to prevent the need for the Kawa library. Do NOT use Fstring
       * or YailList in any of your data, otherwise this conversion won't work.
       *
      if (value instanceof FString) {
        return JSONObject.quote(value.toString());
      }
      if (value instanceof YailList) {
        return ((YailList) value).toJSONString();
      }
      */

        if (value instanceof Number) {
            return JSONObject.numberToString((Number) value);
        }
        if (value instanceof Boolean) {
            return value.toString();
        }
        if (value.getClass().isArray()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            String separator = "";
            for (Object o : (Object[]) value) {
                sb.append(separator).append(getJsonRepresentation(o));
                separator = ",";
            }
            sb.append("]");
            return sb.toString();
        }
        if (value instanceof String) {
            return value.toString();
        }
        return JSONObject.quote(value.toString());
    }

    /**
     * Returns a list of String objects from a JSONArray. This
     * does not do any kind of recursive unpacking of the array.
     * Thus, if the array includes other JSON arrays or JSON objects
     * their string representation will be a single item in the
     * returned list.
     *
     * @param jArray The JSONArray to convert.
     * @return A List of the String representation of each item in
     * the JSON array.
     * @throws JSONException if an element of jArray cannot be
     *                       converted to a String.
     */
    public static List<String> getStringListFromJsonArray(JSONArray jArray) throws JSONException {
        List<String> returnList = new ArrayList<String>();
        for (int i = 0; i < jArray.length(); i++) {
            String val = jArray.getString(i);
            returnList.add(val);
        }
        return returnList;
    }

    /**
     * Returns a Java Object list of a JSONArray with each item in
     * the array converted using convertJsonItem().
     *
     * @param jArray The JSONArray to convert.
     * @return A List of Strings and more Object lists.
     * @throws JSONException if an element in jArray cannot be
     *                       converted properly.
     */
    public static List<Object> getListFromJsonArray(JSONArray jArray) throws JSONException {
        List<Object> returnList = new ArrayList<Object>();
        for (int i = 0; i < jArray.length(); i++) {
            returnList.add(convertJsonItem(jArray.get(i)));
        }
        return returnList;
    }

    public static Object getObjectFromJson(String jsonString) throws JSONException {
        final Object value = (new JSONTokener(jsonString)).nextValue();
        // Note that the JSONTokener may return a value equals() to null.
        if (value == null || value.equals(null)) {
            return null;
        } else if ((value instanceof String) ||
                (value instanceof Number) ||
                (value instanceof Boolean)) {
            return value;
        } else if (value instanceof JSONArray) {
            return getListFromJsonArray((JSONArray) value);
        } else if (value instanceof JSONObject) {
            return getListFromJsonObject((JSONObject) value);
        }
        throw new JSONException("Invalid JSON string.");
    }

    public static String calculateDiscount(Double price, int discountRatio) {
        String price2 = String.valueOf(price);
        String ration2 = String.valueOf(discountRatio);
        String equ = price + "- (" + price + "*" + discountRatio + "/" + 100 + ")";

        double result = new ExpressionBuilder(equ)
                .build()
                .evaluate();
        Timber.d("result calculateDiscount %s", result);
        return String.format(Locale.ENGLISH, "%.01f", result);
    }

    public static String calculateEquation(String equation) {
        Timber.d("equation %s", equation);
        double result = new ExpressionBuilder(equation)
                .build()
                .evaluate();
        Timber.d("result %s", result);
        return Utils.formatDecimal(String.valueOf(result));
    }

    public static String loadPreferences(Context context) {
        String printVal = "";
        Map<String, ?> prefs = PreferenceManager.getDefaultSharedPreferences(
                context).getAll();
        for (String key : prefs.keySet()) {
            Object pref = prefs.get(key);
            if (pref instanceof Boolean) {
                printVal = key + " : " + (Boolean) pref;
            }
            if (pref instanceof Float) {
                printVal = key + " : " + (Float) pref;
            }
            if (pref instanceof Integer) {
                printVal = key + " : " + (Integer) pref;
            }
            if (pref instanceof Long) {
                printVal = key + " : " + (Long) pref;
            }
            if (pref instanceof String) {
                printVal = key + " : " + (String) pref;
            }
            if (pref instanceof Set<?>) {
                printVal = key + " : " + (Set<String>) pref;
            }
            // create a TextView with printVal as text and add to layout
        }
        return printVal;
    }


    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void loadSharedPrefs(Context context, String... prefs) {

        // Logging messages left in to view Shared Preferences. I filter out all logs except for ERROR; hence why I am printing error messages.

        Log.i("Loading Shared Prefs", "-----------------------------------");
        Log.i("----------------", "---------------------------------------");

        for (String pref_name : prefs) {

            SharedPreferences preference = context.getSharedPreferences(pref_name, MODE_PRIVATE);
            for (String key : preference.getAll().keySet()) {

                Log.i(String.format("Shared Preference : %s - %s", pref_name, key),
                        preference.getString(key, "error!"));

            }

            Log.i("----------------", "---------------------------------------");

        }

        Log.i("Finished Shared Prefs", "----------------------------------");

    }

    public static void openAppPageOnPlayStore(Context context) {
        Uri uri = Uri.parse("market://details?id=" + context.getPackageName());
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        }
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + context.getPackageName())));
        }
    }

    public static String loadJSONFromAsset(@NonNull Context context, String jsonFile) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
