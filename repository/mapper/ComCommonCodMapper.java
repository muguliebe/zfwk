package zany.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import zany.repository.entity.ComCommonCod;
import zany.repository.support.Nxm;

/**
 * 공통코드
 * 
 * @author 윤준호
 * @version
 * <ul>
 *  <li> 151208 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Nxm
public interface ComCommonCodMapper {

    /**
     * 공통코드 조회 by PK  
     * @param commGrpCd 공통그룹코드
     * @param commCd 공통코드
     * @return ComCommonCod 공통코드
     */
    public ComCommonCod selectCode(@Param("commGrpCd") String commGrpCd
    		                      ,@Param("commCd") String commCd
    		                      );
    
    /**
     * 공통코드 리스트 조회 by 공통그룹코드
     * @param commGrpCd 공통그룹코드
     * @return List<ComCommonCod> 공통코드
     */
    public List<ComCommonCod> selectCodes(@Param("commGrpCd") String commGrpCd);
    
}