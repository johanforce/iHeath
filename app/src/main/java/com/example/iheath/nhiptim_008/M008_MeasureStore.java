package com.example.iheath.nhiptim_008;

import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

class M008_MeasureStore {
    private final CopyOnWriteArrayList<M008_Measurement<Integer>> measurements = new CopyOnWriteArrayList<>();
    private int minimum = 2147483647;
    private int maximum = -2147483648;

    /**
     * The latest N measurements are always averaged in order to smooth the values before it is
     * analyzed.
     *
     * This value may need to be experimented with - it is better on the class level than putting it
     * into local scope
     */
    @SuppressWarnings("FieldCanBeLocal")
    private final int rollingAverageSize = 4;

    void add(int measurement) {
        M008_Measurement<Integer> measurementWithDate = new M008_Measurement<>(new Date(), measurement);

        measurements.add(measurementWithDate);
        if (measurement < minimum) minimum = measurement;
        if (measurement > maximum) maximum = measurement;
    }

    CopyOnWriteArrayList<M008_Measurement<Float>> getStdValues() {
        CopyOnWriteArrayList<M008_Measurement<Float>> stdValues = new CopyOnWriteArrayList<>();

        for (int i = 0; i < measurements.size(); i++) {
            int sum = 0;
            for (int rollingAverageCounter = 0; rollingAverageCounter < rollingAverageSize; rollingAverageCounter++) {
                sum += measurements.get(Math.max(0, i - rollingAverageCounter)).measurement;
            }

            M008_Measurement<Float> stdValue =
                    new M008_Measurement<>(
                            measurements.get(i).timestamp,
                            ((float)sum / rollingAverageSize - minimum ) / (maximum - minimum));
            stdValues.add(stdValue);
        }

        return stdValues;
    }

    @SuppressWarnings("SameParameterValue") // this parameter can be set at OutputAnalyzer
    CopyOnWriteArrayList<M008_Measurement<Integer>> getLastStdValues(int count) {
        if (count < measurements.size()) {
            return  new CopyOnWriteArrayList<>(measurements.subList(measurements.size() - 1 - count, measurements.size() - 1));
        } else {
            return measurements;
        }
    }

    Date getLastTimestamp() {
        return measurements.get(measurements.size() - 1).timestamp;
    }
}
