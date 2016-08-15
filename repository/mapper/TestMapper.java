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
 *  <li> 151228 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Dr
public interface TestMapper {

    @Select(
    		"select rownumber       ,a.*  from (select table_name, column_name, data_type, rownum as rownumber          from all_tab_columns          where 1=1 and owner='MP3RDOBJ'            and data_type not in ('NUMBER', 'DATE', 'RAW', 'LONG', 'ROWID', 'CLOB', 'TIMESTAMP(6)', 'NCLOB') and table_name not like 'WAS%' and table_name not in ('APR_DEALBO_TRN', 'APR_DEALTR_TRN', 'APR_RETRY_LOG')       order            by table_name, column_name       )a where rownumber >= #{row}"
    		)
    public ArrayList<HashMap<String, Object>> selectTableName(@Param("row") Integer row);
    
    @Select(
    		"select rownumber       ,a.*  from (select table_name, column_name, data_type, rownum as rownumber          from all_tab_columns          where 1=1 and owner='MP3RDOBJ'            and data_type not in ('NUMBER', 'DATE', 'RAW', 'LONG', 'ROWID', 'CLOB', 'TIMESTAMP(6)', 'NCLOB') and table_name not like 'WAS%' and table_name not in ('APR_DEALBO_TRN', 'APR_DEALTR_TRN', 'APR_RETRY_LOG')        order            by table_name DESC, column_name       )a where rownumber <= #{row}"
    		)
    public ArrayList<HashMap<String, Object>> selectTableNameDESC(@Param("row") Integer row);
    
    @Select("${str}")
    public ArrayList<HashMap<String, Object>> literal(@Param("str") String str);
    
    @Select("${str}")
    public Integer literalCount(@Param("str") String str);
}