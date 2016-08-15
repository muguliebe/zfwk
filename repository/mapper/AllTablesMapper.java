package zany.repository.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import zany.repository.support.Local;

/**
 * 사용자
 * 
 * @author 윤준호
 * @version
 *          <ul>
 *          <li>151228 | 윤준호 | 최초생성</li>
 *          </ul>
 */
@Local
public interface AllTablesMapper {

    public List<Map<String, Object>> selectAll();

    public Collection<Object> selectTable(String tableName);

}