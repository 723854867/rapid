package org.rapid.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBoot {

    private static final Logger logger = LoggerFactory.getLogger(SpringBoot.class);
    
    private AnnotationConfigApplicationContext context;

    public void start(Class<?> annotatedClasse) {
        context = new AnnotationConfigApplicationContext(annotatedClasse);
        context.start();
        logger.info("Dubbo spring container started!");
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }
}
