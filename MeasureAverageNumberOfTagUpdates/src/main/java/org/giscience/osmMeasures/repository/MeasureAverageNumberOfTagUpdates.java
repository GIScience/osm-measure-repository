package org.giscience.osmMeasures.repository;

import org.giscience.measures.rest.measure.MeasureOSHDB;
import org.giscience.measures.rest.server.OSHDBRequestParameter;
import org.giscience.measures.rest.server.RequestParameter;
import org.giscience.measures.tools.Cast;
import org.giscience.utils.geogrid.cells.GridCell;
import org.heigit.bigspatialdata.oshdb.api.mapreducer.MapAggregator;
import org.heigit.bigspatialdata.oshdb.api.object.OSMContribution;
import org.heigit.bigspatialdata.oshdb.api.object.OSMEntitySnapshot;
import org.heigit.bigspatialdata.oshdb.osm.OSMType;
import org.heigit.bigspatialdata.oshdb.util.celliterator.ContributionType;

import java.util.SortedMap;

public class MeasureAverageNumberOfTagUpdates extends MeasureOSHDB<Number, OSMContribution> {

    @Override
    public Boolean refersToTimeSpan() {
        return true;
    }

    @Override
    public SortedMap<GridCell, Number> compute(MapAggregator<GridCell, OSMContribution> mapReducer, OSHDBRequestParameter p) throws Exception {
        return Cast.result(mapReducer
                .osmType(OSMType.WAY)
                .osmTag(p.getOSMTag())
                .filter(contribution -> {
                    try {
                        if (contribution.getContributionTypes().contains(ContributionType.TAG_CHANGE)) {
                            return true;
                        }
                    } catch (Exception e) {}
                    return false;
                })
                .average(contribution -> 1));
    }
}
