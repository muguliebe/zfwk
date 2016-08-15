package zany.fwk.filter.aop;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import zany.fwk.service.CountService;
import zany.util.DateUtils;
import zany.util.StringUtils;

/**
 * Advice .
 *
 * @author 윤준호
 * @version
 *          <ul>
 *          <li>151119 | 윤준호 | 최초생성</li>
 *          </ul>
 * @see
 *      <ul>
 *      <li>aroundLog()
 *      <ul>
 *      <li>모든 서비스 전,후 Advice</li>
 *      </ul>
 *      </li>
 *      <li>aroundLogController()
 *      <ul>
 *      <li>모든 콘트롤러 전,후 Advice</li>
 *      </ul>
 *      </li>
 *      </ul>
 */
@Aspect
@Component
public class Advice {

    private static final Logger log        = LoggerFactory.getLogger(Advice.class);
    private static final Logger logService = LoggerFactory.getLogger("eachService"); // 서비스
                                                                                     // 로그

    @Autowired
    protected CountService      cs;                                                  // 카운팅
                                                                                     // 서비스

    /**
     * All Services Around
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("PointcutList.allService()")
    public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {

        Object obj = null;

        String methodName = pjp.getSignature().getDeclaringType().getSimpleName() + "." + pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= args.length; i++) {
            sb.append(args[i - 1]);
            if (i < args.length) {
                sb.append(",");
            }
        }

        // ==========================================================================
        // MDC Insert and set Log
        // ==========================================================================
        String bfFileName = (String) MDC.get("logServiceFileName");
        MDC.put("logServiceFileName",
            pjp.getSignature().getDeclaringType().getSimpleName() + DateUtils.getCurrent("_yyMMdd"));

        // ==========================================================================
        // log
        // ==========================================================================
        logService.info(" >>>>>  Service    start [" + methodName + "()] with {" + sb.toString() + "}");

        // ==========================================================================
        // run
        // ==========================================================================
        long start = System.currentTimeMillis();
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            MDC.put("logServiceFileName", bfFileName);
            long end = System.currentTimeMillis();
            logService.error("        Service end   [{}()] [{}ms] with Exception[{}] <<<<<<<<<<",
                methodName,
                (end - start),
                e.getMessage());
            throw new Exception(e);
        }

        // ==========================================================================
        // log
        // ==========================================================================
        long end = System.currentTimeMillis();
        logService.info("       Service    end   [" + methodName + "()] [" + (end - start) + "ms] with {" + obj
            + "}     <<<<<<<<<<");

        // ==========================================================================
        // MDC Remove and set Log
        // ==========================================================================
        if (bfFileName == null) {
            MDC.remove("logServiceFileName");
        } else {
            MDC.put("logServiceFileName", bfFileName);
        }

        // ==========================================================================
        // End
        // ==========================================================================
        return obj;

    }

    /**
     * Controller Around
     *
     * @param pjp the pjp
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("PointcutList.allController()")
    public Object aroundLogController(ProceedingJoinPoint pjp) throws Throwable {

        // ==========================================================================
        // get signature
        // ==========================================================================
        String methodName = pjp.getSignature().getDeclaringType().getSimpleName() + "." + pjp.getSignature().getName();

        // ==========================================================================
        // get servlet information
        // ==========================================================================
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // parameters
        Enumeration<String> parameterNames = req.getParameterNames();
        StringBuilder sbParams = new StringBuilder("{");

        while (parameterNames.hasMoreElements()) {

            String paramName = parameterNames.nextElement();
            String[] paramValues = req.getParameterValues(paramName);

            for (String val : paramValues) {
                sbParams.append(paramName + "=" + val);
            }

            if (parameterNames.hasMoreElements()) {
                sbParams.append(",");
            }

        }
        sbParams.append("}");

        // bodyContent
        String bodyContent = (String) req.getAttribute("bodyContent");
        if (StringUtils.isEmpty(bodyContent) == false) {
            sbParams.append(" bodyContent=" + bodyContent);
        }

        // ==========================================================================
        // log
        // ==========================================================================
        log.info("");
        log.info(">>>>>  Controller start [" + methodName + "()] from [" + req.getRemoteAddr() + ":] by "
            + req.getRequestURL() + " with " + sbParams);

        // session
        if (log.isTraceEnabled()) {
            String sessionId = null;
            HttpSession ssn = req.getSession();
            if (ssn != null) {
                sessionId = ssn.getId();
                log.trace("session:{}", sessionId);
            }
        }

        // ==========================================================================
        // run
        // ==========================================================================
        long start = System.currentTimeMillis();
        Object obj = null;
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            long end = System.currentTimeMillis();
            log.info("        Controller end   [{}()] [{}ms] with Exception[{}] <<<<<<<<<<",
                methodName,
                (end - start),
                e.getMessage());
            throw new Exception(e);
        }
        long end = System.currentTimeMillis();

        // ==========================================================================
        // Controller Called Counting
        // ==========================================================================
        cs.add("Controller", methodName);

        // ==========================================================================
        // Log
        // ==========================================================================
        log.info("       Controller end   [" + methodName + "()] from [" + req.getRemoteAddr() + ":] [" + (end - start)
            + "ms] with {" + obj + "}     <<<<<<<<<<");

        // ==========================================================================
        // End
        // ==========================================================================
        return obj;

    }

    @Around("PointcutList.zanyUtil()")
    public Object aourngUtils(ProceedingJoinPoint pjp) throws Throwable {

        Object obj = null;

        String methodName = pjp.getSignature().getDeclaringType().getSimpleName() + "." + pjp.getSignature().getName();
        Object[] args = pjp.getArgs();
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i <= args.length; i++) {
            sb.append(args[i - 1]);
            if (i < args.length) {
                sb.append(",");
            }
        }

        // ==========================================================================
        // log
        // ==========================================================================
        log.info(" >>>>>  Util start [" + methodName + "()] with {" + sb.toString() + "}");

        // ==========================================================================
        // run
        // ==========================================================================
        long start = System.currentTimeMillis();
        obj = pjp.proceed();
        long end = System.currentTimeMillis();

        // ==========================================================================
        // log
        // ==========================================================================
        log.info(
            "        Util end   [" + methodName + "()] [" + (end - start) + "ms] with {" + obj + "}     <<<<<<<<<<");

        // ==========================================================================
        // MDC Remove and set Log
        // ==========================================================================
        MDC.remove("logServiceFileName");

        // ==========================================================================
        // End
        // ==========================================================================
        return obj;

    }

}