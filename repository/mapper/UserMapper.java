package zany.repository.mapper;

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
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Local
public interface UserMapper {

    @Select("select id, name from bulk")
    @ResultType(User.class)
    public void selectOne(ResultHandler<Object> handler);
}