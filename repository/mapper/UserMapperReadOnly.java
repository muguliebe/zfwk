package zany.repository.mapper;

import zany.repository.entity.User;
import zany.repository.support.Local;

/**
 * ReadOnly Sample..
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Local
public interface UserMapperReadOnly {

	public User selectOne(String id);
}