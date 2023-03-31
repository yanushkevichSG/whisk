package framework.listeners;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class ParallelizeTestsListener implements IAlterSuiteListener {
    private static final String THREAD_COUNT_NAME = "threadCount";
    private static final long DEFAULT_THREAD_COUNT = 2;

    @Override
    public void alter(List<XmlSuite> suites) {
        int dataProviderThreadCount = Integer.parseInt(
                System.getProperty(THREAD_COUNT_NAME, String.valueOf(DEFAULT_THREAD_COUNT)));
        suites.forEach(s -> s.setThreadCount(dataProviderThreadCount));
    }
}
