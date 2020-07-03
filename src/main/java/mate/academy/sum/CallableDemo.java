package mate.academy.sum;

import java.util.List;
import java.util.concurrent.Callable;

public class CallableDemo implements Callable<Long> {
    private List<Integer> list;

    public CallableDemo(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Long call() {
        long sum = 0L;
        for (int num : list) {
            sum += num;
        }
        return sum;
    }
}
