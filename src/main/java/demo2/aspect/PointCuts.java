package demo2.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(* demo2.service.AttractionService.get*(..))")
    public void allGetMethods(){};

    @Pointcut("execution(* demo2.service.AttractionService.add*(..))")
    public void allAddMethods(){};
}
