package br.com.petshop.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static Date date;
    private static Calendar calendar;
    private static DateFormat df;
    private static java.sql.Date dateSQL;
    
    private DateUtil(){
    	
    }
    
    public static Date calendarToDate(Calendar calendar)throws Exception{
        return  calendar.getTime();
        
    }
    public static Calendar dateToCalendar(Date date)throws Exception{        
    	calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    
    public static String formatDMY(Date data)throws Exception{
        
             df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(data);
        
    }

    public static String formatDMYHHMMSS(Date data)throws Exception{
        
        df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
       return df.format(data);
   
}
    
     public static String formatDMY(Calendar calendar)throws Exception{                   
              date= calendar.getTime();
             df = new SimpleDateFormat("dd/MM/yyyy");
            return df.format(date);        
    }
    
   public static Calendar somaDiasCalendar(int dias)throws Exception{
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dias);
        return calendar;
   }
   
   public static Date somaDiasDate(int dias)  throws Exception{
	      calendar = Calendar.getInstance(); 
          calendar.add(Calendar.DATE, dias);          
          return  calendar.getTime();              
   }
   
   public static Date getUtilDate(String data)throws Exception{	   
	   
       if(data.equals("")||data==null)
             return null;
              
        //df = new SimpleDateFormat("dd/MM/yyyy");        
        //return (Date) df.parse(data);
       return (Date)new SimpleDateFormat("dd/MM/yyyy").parse(data);
   }
   
   public static Date getSqlDate(String data)throws Exception{
       
       if(data.equals("")||data==null)
             return null;
       
        //df = new SimpleDateFormat("dd/MM/yyyy");              
       //return new java.sql.Date(df.parse(data).getTime()) ;
       return new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(data).getTime());
   }
   
   public static Timestamp getTimestampDMY(String data)throws Exception{
	   
       if(data.equals("")||data==null)
           return null;
             
       return new Timestamp(new SimpleDateFormat("dd/MM/yyyy").parse(data).getTime());
   }
   
   public static Timestamp getTimestampDMYHMS(String data)throws Exception{
	   
       if(data.equals("")||data==null)
           return null;
         
       return new Timestamp(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").parse(data).getTime());
   }
   

   public static String getTimestamp(Timestamp timestamp)throws Exception{
       dateSQL = new java.sql.Date(timestamp.getTime());
        df = new SimpleDateFormat("dd/MM/yyyy");       
       return df.format(dateSQL);
   }
}
