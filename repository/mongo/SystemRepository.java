package zany.repository.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import zany.model.system.System;

public interface SystemRepository extends MongoRepository<System, String> {

    public List<System> findByHostName(String hostName);

    public List<System> findByHostNameOrderByDayDesc(String hostName);

    public List<System> findByHostNameOrderByDayAsc(String hostName);

    public List<System> findByHostNameOrderByTimeDesc(String hostName);

    public List<System> findByHostNameOrderByTimeAsc(String hostName);

    // work
    public List<System> findByHostNameAndTimeGreaterThan(String hostName, String time);

    // not work
    public List<System> findByHostNameAndDayGreaterThanAndTimeGreaterThan(String hostName, String day, String time);

    @Query(value = "{ hostName: ?0, day:{$gte:?1}, time:{$gte:?2} }", fields = "{'hostName':1, 'lastUpdate':1, 'day':1, 'time':1, 'cpu.idle':1, 'memory.physicalUsedPerc':1, 'memory.swapUsedPerc':1}")
    public List<System> findByHostNameRecent(String hostName, String day, String time);


}
