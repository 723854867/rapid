package org.rapid.sample.soa.deploy;

import org.rapid.dubbo.SpringBoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import com.alibaba.dubbo.container.Main;

public class SoaMain {
	
	private static boolean running = true;

	public static void main(String[] args) {
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		System.setProperty("logback.configurationFile", SoaMain.class.getResource("/conf/logback.xml").getFile());
				
		SpringBoot boot = new SpringBoot();
		boot.start(SoaBoot.class);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			Logger logger = LoggerFactory.getLogger(SoaMain.class);
            public void run() {
                try {
                    boot.stop();
                    logger.info("Dubbo spring container stopped!");
                } catch (Throwable t) {
                    logger.error(t.getMessage(), t);
                }
                synchronized (Main.class) {
                    running = false;
                    Main.class.notify();
                }
            }
        });
		synchronized (Main.class) {
			while (running) {
				try {
	                Main.class.wait();
	            } catch (Throwable e) {
	            }
			}
        }
	}
}
