package zany.util;


/**
 * String Utility
 * @version
 * <ul>
 *  <li> 151218 | 윤준호 | 최초생성 </li>
 * </ul>
 */
public class StringUtils {

    /**
     * 빈 문자열인가?
     *  
     * @param in 검사할 문자열
     * @return boolean 빈 문자열 여부
     */
    public static boolean isEmpty(String in){
        
        if( in == null ){
            return true;
        }else if( in.isEmpty() ){
            return true;
        }
        
        return false;

    }
    
    /**
     * 숫자인가?
     *   
     * @param in 검사할 문자열
     * @return boolean 숫자여부
     */
    public static boolean isNumeric(String in){
        
        if( isEmpty(in) == false ){
            return false;
        }
        for(char c : in.toCharArray() ){
            if( Character.isDigit(c) == false ){
                return false;
            }
        }
        return true;
    }
    
}
