package zany.repository.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.ResultHandler;

import zany.repository.entity.User;
import zany.repository.support.Local;

/**
 * 사용자
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151228 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Local
public interface LocalMapper {

    @Select("select table_name, column_name, owner from all_tab_columns where owner='MUGU'")
    public ArrayList<HashMap<String, Object>> selectTableName();
    
    @Select("${str}")
    public HashMap<String, Object> literal(@Param("str") String str);
    
    @Select("select id, name from bulk")
    @ResultType(User.class)
    public void selectBulk(ResultHandler<?> resultHandler);
    
}