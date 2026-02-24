package org.opennms.plugins.ianus;

import org.opennms.integration.api.v1.timeseries.Aggregation;
import org.opennms.integration.api.v1.timeseries.Metric;
import org.opennms.integration.api.v1.timeseries.Sample;
import org.opennms.integration.api.v1.timeseries.StorageException;
import org.opennms.integration.api.v1.timeseries.TagMatcher;
import org.opennms.integration.api.v1.timeseries.TimeSeriesData;
import org.opennms.integration.api.v1.timeseries.TimeSeriesFetchRequest;
import org.opennms.integration.api.v1.timeseries.TimeSeriesStorage;

import java.util.Collection;
import java.util.List;

public class IanusPlugin implements TimeSeriesStorage {
    @Override
    public void store(List<Sample> list) throws StorageException {

    }

    @Override
    public List<Metric> findMetrics(Collection<TagMatcher> collection) throws StorageException {
        return List.of();
    }

    @Override
    public List<Sample> getTimeseries(TimeSeriesFetchRequest timeSeriesFetchRequest) throws StorageException {
        return List.of();
    }

    @Override
    public TimeSeriesData getTimeSeriesData(TimeSeriesFetchRequest request) throws StorageException {
        return TimeSeriesStorage.super.getTimeSeriesData(request);
    }

    @Override
    public void delete(Metric metric) throws StorageException {

    }

    @Override
    public boolean supportsAggregation(Aggregation aggregation) {
        return TimeSeriesStorage.super.supportsAggregation(aggregation);
    }
}
