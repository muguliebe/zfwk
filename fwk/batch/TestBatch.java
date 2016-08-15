package zany.fwk.batch;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import zany.util.DateUtils;

/**
 * ...
 * 
 * @author 윤준호
 * @see
 * <ul>
 *  <li></li>
 * </ul>
 * @version
 * <ul>
 *  <li> 151229 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Component
@Profile("batch")
public class TestBatch extends BaseBatch{

    /**
     * Scheduler 진입점
     */
    @Scheduled(cron="0/60 0 0 * * *")
    public void runByCron(){
        baseDate = DateUtils.getCurrentDate();
        run(baseDate);
    }
    
    /**
     * 배치진입점.
     *
     * @param baseDate the base date
     */
    @Async
    public void run(String baseDate){

        try{
            
            //==========================================================================
            // Main Process
            //==========================================================================
            log.info("----------test batch----------");
            log.info("info");
            log.debug("debug");

        
        } catch (Exception e) {
            log.error("Test Batch Errored...", e);
        }
        
    }

}
