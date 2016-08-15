package zany.fwk.batch;

import java.text.DecimalFormat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;

import zany.fwk.service.CountService;
import zany.util.DateUtils;

/**
 * @author 윤준호
 * @see
 *      <ul>
 *      <li>배치 서비스 개발 시 상속 받는 클래스
 *      <li>각 배치는 아래의 규칙으로 코딩 되어야 한다 ex)MinorRegisterNotify.java 참조
 *      <ol>
 *      <li>@Component 선언</li>
 *      <li>@Profile("batch") 선언</li>
 *      <li>runByCron() 구현
 *      <ul>
 *      <li>아래 스케쥴 어노테이션 선언</li>
 *      <li>@Scheduled(cron="${batch.minorregisternotify.cron}")</li>
 *      </ul>
 *      </li>
 *      <li>run(String baseDate) 구현
 *      <ol>
 *      <li>run() 내 모든 사항은, try{}catch(Exception e){log.error("", e);} 처리</li>
 *      </ol>
 *      </li>
 *      </ol>
 *      </li>
 *      <li>run() 과 runByCron()</li>
 *      <li>beforeRun() 과 afterRun()
 *      <ul>
 *      <li>위 메소드는, 배치 코딩 규칙에 따르면 BaseBatchAdvice에 의해 호출되어진다.( BaseBatch 를 상속받아
 *      run(), runByCron() 메소드 구현</li>
 *      <li>beforeRun()
 *      <ul>
 *      <li>매 수행 시마다, 별도로 로그파일이 떨어지게 해 줌</li>
 *      <li>시작 전에 Batch Information Logging</li>
 *      </ul>
 *      </li>
 *      <li>afterRun()
 *      <ul>
 *      <li>매 수행 시마다, 걸었던 로그파일 설정 해제</li>
 *      <li>배치 중 호출되었던 counting 메소드들에 따라 배치결과건수를 Display 해 줌</li>
 *      </ul>
 *      </li>
 *      </ul>
 *      </li>
 *      <li>배치 수행 건수(Counting) 관련 function
 *      <ol>
 *      <li>setCntTot() : 총건수 셋팅</li>
 *      <li>addCntTot() : 총건수 증가</li>
 *      <li>addCntRead() : 읽기 건수 증가</li>
 *      <li>addCntSkip() : 스킵 건수 증가</li>
 *      <li>addCntSuccess(): 성공 건수 증가</li>
 *      <li>addCntError() : 에러 건수 증가</li>
 *      </ol>
 *      </li>
 *      </ul>
 * @version
 *          <ul>
 *          <li>151210 | 윤준호 | 최초생성</li>
 *          </ul>
 */

@Profile("batch")
public abstract class BaseBatch {

    private Logger               logger    = LoggerFactory.getLogger(this.getClass()); // Logger
                                                                                       // for
                                                                                       // Root
    protected Logger             log       = LoggerFactory.getLogger("eachBatch");     // Logger
                                                                                       // for
                                                                                       // sub
                                                                                       // class

    @Autowired
    protected ApplicationContext ctx;

    @Autowired
    protected CountService       cs;                                                   // 카운팅
                                                                                       // 서비스

    protected Long               startTime = 0L;                                       // 시작
                                                                                       // 시간
    protected Long               endTime   = 0L;                                       // 종료
                                                                                       // 시간
    protected String             batchName;                                            // 배치명
    protected String             baseDate;                                             // 기준일자

    protected String             strCountTitle;                                        // 카운팅서비스에
                                                                                       // 사용할
                                                                                       // 제목

    /**
     * 배치 시작점
     * - 각 배치에서 구현
     * 
     * @param baseDate void
     */
    public abstract void run(String baseDate);

    /**
     * Spring Scheduler 에 의한 실행
     * - 각 배치에서 구현.
     */
    public abstract void runByCron();

    /**
     * 배치 시작 전 수행
     * - 각 배치에서 run 시작 시 호출
     * 
     * @param baseDate 기준일자
     */
    public void beforeRun(String baseDate) {

        logger.warn("{} Batch beforeRun()", this.getClass().getSimpleName());

        // 수행되는 배치명 + yyMMdd 로 로그파일 명명
        // 배치 수행 시마다 로그를 남기려면 yyMMdd_HHmmssSSS 로 변경
        Class<?> clazz = this.getClass();
        strCountTitle = clazz.getSimpleName() + "_" + DateUtils.getCurrent("yyMMdd");

        // MDC Log - by milliSecond unit
        MDC.put("logFileName", strCountTitle);

        startTime = System.currentTimeMillis();
        batchName = clazz.getSimpleName();
        this.baseDate = baseDate;

        // display Information
        displayBefore();

    }

    /**
     * 배치 시작 전 수행
     * - BaseBatchAdvice 에서 호출
     */
    public void beforeRun() {
        beforeRun(null);
    }

    /**
     * 배치 수행 후
     * - BaseBatchAdvice 에서 호출
     */
    public void afterRun() {


        try {

            endTime = System.currentTimeMillis();

            if ((endTime - startTime) < 1000) {

            }
            logger.warn("{} Batch afterun() elapsed:{}", this.getClass().getSimpleName(), calcElapsed());

            displayResult();
            cs.save();

        } catch (Exception e) {
            log.error("배치 종료 처리 중 에러 발생", e.getMessage());

        }

        MDC.remove("logFileName");

    }

    private String calcElapsed() {

        String result = "";
        long elapsed = endTime - startTime;

        if (elapsed < 10000) {
            result = String.format(elapsed + "", "0,000") + " ms";

        } else if (elapsed / 1000 > 3660000) {
            result = String.format(elapsed / 1000 + "", "000,000,000,000,000,000") + " min";

        } else {
            result = String.format(elapsed / 1000 + "", "000,000,000,000,000,000") + " sec";

        }

        return result;
    }

    /**
     * 배치 수행 전 정보 표시.
     */
    private void displayBefore() {

        log.info("========================================================");
        log.info(batchName + " Batch Start");
        log.info("실행일자   : " + DateUtils.getCurrentTimeStamp());
        log.info("========================================================");

    }

    /**
     * 배치 수행 수 결과 표시
     */
    private void displayResult() {

        DecimalFormat df = new DecimalFormat("0,000,000,000");

        log.info("========================================================");
        log.info(batchName + " Properly Ended");
        if (this.baseDate != null) {
            log.info("기준일자   : " + baseDate);
        }
        log.info("Elapsed    : " + calcElapsed());
        log.info("TOTAL      : " + df.format(cs.get(strCountTitle, "TOTAL")));
        log.info("READ       : " + df.format(cs.get(strCountTitle, "READ")));
        log.info("SUCCESS    : " + df.format(cs.get(strCountTitle, "SUCESS")));
        log.info("SKIP       : " + df.format(cs.get(strCountTitle, "SKIP")));
        log.info("ERROR      : " + df.format(cs.get(strCountTitle, "ERROR")));
        log.info("========================================================");

    }

    /**
     * PostConstructor - do not anything
     */
    @PostConstruct
    public void postContruct() {
        // do not anything
    }

    /**
     * PreDestroy - do not anything
     */
    @PreDestroy
    public void preDestroy() {
        // do not anything
    }

    /**
     * 총건수 지정
     */
    public void setCntTot(Long cntTot) {
        cs.set(strCountTitle, "TOTAL", cntTot);
    }

    /**
     * 총건수 증가
     */
    public void addCntTot() {
        cs.add(strCountTitle, "TOTAL");
    }

    /**
     * 읽기 건수 증가
     */
    public void addCntRead() {
        cs.add(strCountTitle, "READ");
    }

    /**
     * 스킵 건수 증가
     */
    public void addCntSkip() {
        cs.add(strCountTitle, "SKIP");
    }

    /**
     * 성공 건수 증가
     */
    public void addCntSuccess() {
        cs.add(strCountTitle, "SUCESS");
    }

    /**
     * 에러 건수 증가
     */
    public void addCntError() {
        cs.add(strCountTitle, "ERROR");
    }

}
