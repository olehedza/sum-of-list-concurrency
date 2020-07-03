package mate.academy.sum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceSum {
    private static final int NUM_OF_OPERATIONS = 1_000_000;
    private static final int NUM_OF_THREADS = Runtime.getRuntime()
            .availableProcessors();
    private List<Integer> list;
    private final ExecutorService executorService;

    public ExecutorServiceSum(List<Integer> list) {
        this.list = list;
        this.executorService = Executors.newFixedThreadPool(NUM_OF_THREADS);
    }

    public List<Callable<Long>> getCallables() {
        List<Callable<Long>> callables = new ArrayList<>();
        int n = NUM_OF_OPERATIONS / NUM_OF_THREADS;
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            Callable<Long> callable = new CallableDemo(list.subList(n * i, (n * (i + 1))));
            callables.add(callable);
        }
        return callables;
    }

    public Long getSum() throws InterruptedException, ExecutionException {
        List<Future<Long>> futureList = executorService
                .invokeAll(getCallables());
        Long sum = 0L;
        for (Future<Long> future : futureList) {
            sum += future.get();
        }
        return sum;
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
