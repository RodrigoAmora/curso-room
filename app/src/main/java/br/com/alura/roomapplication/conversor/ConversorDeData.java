package br.com.alura.roomapplication.conversor;

import android.arch.persistence.room.TypeConverter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class ConversorDeData {

    @TypeConverter
    public static Long converterParaTime(Calendar data) {
        return data.getTime().getTime();
    }

    @TypeConverter
    public static Calendar converterParaCalendar(String data) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = simpleFormatter.parse(data);
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
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = simpleFormatter.format(data.getTime());
        return dataFormatada;
    }

}
