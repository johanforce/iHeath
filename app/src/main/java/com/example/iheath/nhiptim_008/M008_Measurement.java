package com.example.iheath.nhiptim_008;

import java.util.Date;

class M008_Measurement<T> {
    final Date timestamp;
    final T measurement;

    M008_Measurement(Date timestamp, T measurement) {
        this.timestamp = timestamp;
        this.measurement = measurement;
    }
}
