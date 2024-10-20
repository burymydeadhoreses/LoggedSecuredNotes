package io.github.burymydeadhoreses.loggedsecurednotes.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(io.github.burymydeadhoreses.loggedsecurednotes.annotations.TrackUserAction)")
    public void loggingPointcut() {
    }

    @AfterReturning(pointcut = "loggingPointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = (authentication != null) ? authentication.getName() : "anonymous";

        logger.info("User: {}, Method: {}, Result: {}", username, joinPoint.getSignature().toShortString(), result);
    }
}
