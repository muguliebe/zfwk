package zany.fwk.batch;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;

/**
 * Batch Advisor
 * @author 윤준호
 * @see
 * <ul>
 *  <li> Batch Advice 기능 </li>
 *      <ul>
 *          <li>배치 전후로, 각 배치의 부모 클래스의 beforeRun()과 afterRun()을 수행 시켜준다.</li>
 *      </ul>
 * </ul>
 * @version
 * <ul>
 *  <li> 151230 | 윤준호 | 최초생성 </li>
 * </ul>
 */
@Aspect
@Component
public class BaseBatchAdvice {

    /** The log. */
    private Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
    
    /**
     * 모든 배치
     */
    @Pointcut("execution(* com.skcc.ifds..batch..*.*(..))")
    public void allBatch(){}
    
    /**
     * 모든 run으로 시작하는 method
     */
    @Pointcut("execution(public void run*(String))")
    public void startRun(){}
    
    /**
     * 모든 run으로 끝나는 method
     */
    @Pointcut("execution(public void *run(String))")
    public void endRun(){}
    
    /**
     * 모든 runByCron으로 시작하는 method
     */
    @Pointcut("execution(public void runByCron*())")
    public void startRunByCron(){}
    
    /**
     * 모든 run으로 끝나는 method
     */
    @Pointcut("execution(public void *runByCron())")
    public void endRunByCron(){}
    
    /**
     * <pre>
     * 모든 run(), runByCron() 메소드 Advice
     * - baseBatch 부모클래스의 beforeRun(), afterRun() 메소드를 invoke 한다.
     * </pre>
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("allBatch() && ( (startRun() && endRun()) || ( startRunByCron() && endRunByCron() ) )")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable{

        //==========================================================================
        // Declaration
        //==========================================================================
        Method methodBf = pjp.getThis().getClass().getDeclaredMethod( "beforeRun" , String.class );  // get beforeRun Method
        Method methodAf = pjp.getThis().getClass().getDeclaredMethod( "afterRun"                 );  // get afterRun  Method
        
        log.warn(">>>>> Start Batch:{}.{}()", pjp.getSignature().getDeclaringType().getSimpleName(), pjp.getSignature().getName());
        
        //==========================================================================
        // Batch Main
        //==========================================================================
        // 1. beforeRun() 메소드 수행: 첫번째 인자값이 String이라면, beforeRun에 해당 인자를 넘겨준다.
        Object[] args = pjp.getArgs();
        String arg    = null;
        if( args.length > 0){
            if( args[0] instanceof String ){
                arg = (String) args[0];
            }
        }
        methodBf.invoke(pjp.getThis(), arg);
        
        // 2. run()       메소드 수행
        Object obj = pjp.proceed();
        
        // 3. afterRun()  메소드 수행
        methodAf.invoke(pjp.getThis());

        log.warn("End Batch <<<<<");

        return obj;

    }
    
}
