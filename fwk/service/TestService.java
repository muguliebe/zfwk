package zany.fwk.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import zany.repository.entity.AllTable;
import zany.repository.jpa.AllTablesRepository;
import zany.repository.mapper.AllTablesMapper;

//@Service
@Transactional("transactionManagerJpaFirst")
public class TestService extends BaseService{

    @Autowired
    private AllTablesMapper mapper;
    
    @Autowired
    private AllTablesRepository repository;

//    @Scheduled(fixedRate = 600000)
    public List<AllTable> getTables() {
        
        // using myBatis dataSource
//        @SuppressWarnings("unused")
//        List<Map<String, Object>> selectAll = mapper.selectAll();
        
        // using Hibernate dataSource
        List<AllTable> findAll = repository.findAll();
//        for( AllTable t : findAll ) {
//            log.debug("aa:{}", t.getTableName());
//        }
        
        log.debug("==> log Jpa table end 333");
        
        return findAll;
    }
    
    public List<Map<String, Object>> getTablesByJdbc() {
        
        List<Map<String, Object>> selectAll = mapper.selectAll();
        log.debug("==> log Jdbc table end");
        
        return selectAll;
    }

}
