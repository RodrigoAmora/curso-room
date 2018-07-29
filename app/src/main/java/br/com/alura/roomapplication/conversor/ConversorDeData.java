package br.com.alura.roomapplication.conversor;

import android.arch.persistence.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConversorDeData {

    private static String padraoDeData = "dd/MM/yyyy";
    private static SimpleDateFormat FORMATADOR = new SimpleDateFormat(padraoDeData);

    @TypeConverter
    public static Long converterParaTime(Calendar data) {
        return data.getTime().getTime();
    }

    @TypeConverter
    public static Calendar converterParaCalendar(String data) {
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = FORMATADOR.parse(data);
            calendar.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    @TypeConverter
    public static Calendar converterParaCalendar(Long tempoEmMiliSEgundos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(tempoEmMiliSEgundos));
        return calendar;
    }

    @TypeConverter
    public static String converterDo(Calendar data) {
        return FORMATADOR.format(data.getTime());
    }

}
