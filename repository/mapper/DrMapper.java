package zany.repository.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zany.repository.support.Dr;

/**
 * 사용자
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151229 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Dr
public interface DrMapper {

    @Select(
    		"select rownum       ,a.*  from (select table_name, column_name, data_type          from all_tab_columns          where 1=1 and owner='MP3RDOBJ'            and data_type not in ('NUMBER', 'DATE', 'RAW', 'LONG', 'ROWID', 'CLOB', 'TIMESTAMP(6)', 'NCLOB')         order            by table_name, column_name       )a where rownum >= #{row}"
    		)
    public ArrayList<HashMap<String, Object>> selectTableName(@Param("row") Integer row);
    
    @Select("${str}")
    public ArrayList<HashMap<String, Object>> literal(@Param("str") String str);
    
    @Select("${str}")
    public Integer literalCount(@Param("str") String str);
}