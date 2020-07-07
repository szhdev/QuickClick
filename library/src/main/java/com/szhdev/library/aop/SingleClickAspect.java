package com.szhdev.library.aop;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

/**
 * author : szhdev
 * time   : 2020/6/30
 * desc   : 防重复点击处理
 */
@Aspect
public class SingleClickAspect {

    /**
     * 最近一次点击的时间
     */
    private long mLastTime;
    /**
     * 最近一次点击的控件ID
     */
    private int mLastId;

    /**
     * 方法切入点
     * 监听系统的onClick()
     */
    @Pointcut("execution(* android.view.View.OnClickListener.onClick(..))")
    public void method() {
    }
    /**
     * 定义切点，标记切点为所有被@SingleClick注解的方法
     * 注意：这里com.szhdev.library.aop.SingleClick需要替换成
     * 你自己项目中SingleClick这个类的全路径
     */
    @Pointcut("execution(@com.szhdev.library.aop.SingleClick * *(..))")
    public void methodSingle() {}

    /**
     * 在连接点进行方法替换
     */
    @Around("method() || methodSingle()")
    public void aroundJoinPointSingle(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            if (arg instanceof View) {
                view = (View) arg;
            }
        }
        if (view != null) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - mLastTime >80  && currentTime - mLastTime < 1000 && view.getId()
                    == mLastId) {
                Log.i("SingleClick", "发生快速点击");
                return;
            }
            mLastTime = currentTime;
            mLastId = view.getId();
            //执行原方法
            joinPoint.proceed();
        }
    }
}