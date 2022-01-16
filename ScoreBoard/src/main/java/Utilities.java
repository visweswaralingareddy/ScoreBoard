public class Utilities {

    public static boolean isNullOrEmpty(String value){
        if(value==null){
            return true;
        }else if("".equals(value.trim())){
            return true;
        }
        return false;
    }

    public static String getKey(String home, String away){
        return home.toLowerCase().trim()+away.toLowerCase().trim();
    }
}
