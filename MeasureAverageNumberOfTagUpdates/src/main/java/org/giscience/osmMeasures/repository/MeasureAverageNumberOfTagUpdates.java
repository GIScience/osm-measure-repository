package org.giscience.osmMeasures.repository;

import org.giscience.measures.rest.measure.MeasureOSHDB;
import org.giscience.measures.rest.server.OSHDBRequestParameter;
import org.giscience.measures.tools.Index;
import org.giscience.measures.tools.Lineage;
import org.giscience.utils.geogrid.cells.GridCell;
import org.heigit.bigspatialdata.oshdb.api.mapreducer.MapAggregator;
import org.heigit.bigspatialdata.oshdb.api.object.OSMContribution;
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
        return Index.reduce(
                mapReducer
                        .osmTag(p.getOSMTag())
                        .aggregateBy(contribution -> contribution.getEntityAfter().getId())
                        .map(contribution -> {
                            try {
                                if (contribution.getContributionTypes().contains(ContributionType.TAG_CHANGE)) {
                                    return 1.;
                                }
                            } catch (Exception e) {}
                            return 0.;
                        })
                        .sum(),
                Lineage::average);
    }
}
