package com.rf.tw_sample.domain.common

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestScheduleProvider : SchedulerProvider {
    override fun io(): Scheduler = Schedulers.trampoline()
    override fun computation(): Scheduler = Schedulers.trampoline()
    override fun ui(): Scheduler = Schedulers.trampoline()
}