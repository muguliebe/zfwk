package zany.fwk.filter.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * The Class PointcutList.
 */
@Aspect
public class PointcutList {

    @Pointcut("execution(* com.skcc.ifds.service..*.*(..)) || execution(* *..*.service..*.*(..))")
    public void packService() {
    }

    @Pointcut("execution(* zany.fwk.service.CountService.*(..))")
    public void serviceCounting() {
    }

    @Pointcut("packService() && !serviceCounting()")
    public void allService() {
    }

    @Pointcut("execution(* zany.fwk.controller..*.*(..)) || execution(* *..*.controller..*.*(..))")
    public void allController() {
    }

    @Pointcut("execution(* zany.util..*.*(..))")
    public void zanyUtil() {
    }

}
