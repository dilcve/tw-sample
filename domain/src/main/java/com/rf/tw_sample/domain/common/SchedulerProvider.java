package com.rf.tw_sample.domain.common;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
    Scheduler ui();
    Scheduler io();
    Scheduler computation();
}
