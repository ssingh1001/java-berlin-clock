package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created on 11/06/2017.
 *
 * @author : Sachin Singh ( er.ssingh10@gmail.com )
 */
public class TimeConverterImpl implements TimeConverter{

    @Override
    public String convertTime(String aTime) {

        int[] parts = Arrays.asList(aTime.split(":")).stream().mapToInt(Integer::parseInt).toArray();
        StringJoiner joiner=new StringJoiner(System.getProperty("line.separator"));
        joiner.add(getSeconds(parts[2]))
                .add(getTopHours(parts[0]))
                .add(getBottomHours(parts[0]))
                .add(getTopMinutes(parts[1]))
                .add(getBottomMinutes(parts[1]));
        return joiner.toString();
    }
    protected String getSeconds(int number) {
        if (number % 2 == 0) return "Y";
        else return "O";
    }

    protected String getTopHours(int number) {
        return getOnOff(4, getTopNumberOfOnSigns(number));
    }

    protected String getBottomHours(int number) {
        return getOnOff(4, number % 5);
    }

    protected String getTopMinutes(int number) {
        return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
    }

    protected String getBottomMinutes(int number) {
        return getOnOff(4, number % 5, "Y");
    }

    // Default value for onSign would be useful
    private String getOnOff(int lamps, int onSigns) {
        return getOnOff(lamps, onSigns, "R");
    }
    private String getOnOff(int lamps, int onSigns, String onSign) {
        String out = "";
        // String multiplication would be useful
        for (int i = 0; i < onSigns; i++) {
            out += onSign;
        }
        for (int i = 0; i < (lamps - onSigns); i++) {
            out += "O";
        }
        return out;
    }

    private int getTopNumberOfOnSigns(int number) {
        return (number - (number % 5)) / 5;
    }
}
