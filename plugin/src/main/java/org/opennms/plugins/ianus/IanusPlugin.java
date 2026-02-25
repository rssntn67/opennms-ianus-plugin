package org.opennms.plugins.ianus;

import org.opennms.integration.api.v1.timeseries.Aggregation;
import org.opennms.integration.api.v1.timeseries.Metric;
import org.opennms.integration.api.v1.timeseries.Sample;
import org.opennms.integration.api.v1.timeseries.StorageException;
import org.opennms.integration.api.v1.timeseries.TagMatcher;
import org.opennms.integration.api.v1.timeseries.TimeSeriesData;
import org.opennms.integration.api.v1.timeseries.TimeSeriesFetchRequest;
import org.opennms.integration.api.v1.timeseries.TimeSeriesStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class IanusPlugin implements TimeSeriesStorage {
    private static final Logger LOG = LoggerFactory.getLogger(IanusPlugin.class);

    @Override
    public void store(List<Sample> list) throws StorageException {
        list.forEach(s -> {
            LOG.info("store: key: {}", s.getMetric().getKey());
            LOG.info("store: value: {}", s.getValue());
            LOG.info("store: time: {}", s.getTime());
            LOG.info("store: iTags: {}", s.getMetric().getIntrinsicTags());
            LOG.info("store: eTags: {}", s.getMetric().getExternalTags());
            LOG.info("store: mdTags: {}", s.getMetric().getMetaTags());

        });
    }

    @Override
    public List<Metric> findMetrics(Collection<TagMatcher> collection) throws StorageException {
        collection.forEach(tagMatcher ->
        {
            LOG.info("findMetrics: key {}", tagMatcher.getKey());
            LOG.info("findMetrics: value {}", tagMatcher.getValue());
            LOG.info("findMetrics: type {}", tagMatcher.getType());
        });
        return List.of();
    }

    @Override
    public List<Sample> getTimeseries(TimeSeriesFetchRequest timeSeriesFetchRequest) throws StorageException {
        LOG.info("getTimeseries: aggregation: {}", timeSeriesFetchRequest.getAggregation());
        LOG.info("getTimeseries: key: {}", timeSeriesFetchRequest.getMetric().getKey());
        LOG.info("getTimeseries: start: {}", timeSeriesFetchRequest.getStart());
        LOG.info("getTimeseries: end: {}", timeSeriesFetchRequest.getEnd());
        LOG.info("getTimeseries: step: {}", timeSeriesFetchRequest.getStep());

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
