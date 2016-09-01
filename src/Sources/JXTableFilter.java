/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sources;

/**
 *
 * @author mmh
 */
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Filter;
import org.jdesktop.swingx.decorator.FilterPipeline;

import com.ezware.oxbow.swingbits.table.filter.AbstractTableFilter;
import com.ezware.oxbow.swingbits.table.filter.DistinctColumnItem;
import java.util.ArrayList;
import javax.swing.table.TableModel;

public class JXTableFilter extends AbstractTableFilter<JXTable> {

    private final Map<Integer, ColumnFilter> filterMap = new HashMap<Integer, ColumnFilter>();

    public JXTableFilter(JXTable table) {
        super(table);
    }

    private static final long serialVersionUID = 1L;

    @Override
    public boolean execute(int col, Collection<DistinctColumnItem> items) {
        resetFilter(col, items);
        getTable().setFilters( new FilterPipeline(getFilters()));
        return true;
    }

    private void resetFilter(int col, Collection<DistinctColumnItem> items) {

        if ( !isFiltered(col)) {
            filterMap.remove(col);
        } else {
            ColumnFilter filter = filterMap.get(col);
            if ( filter == null ) {
                filter = new ColumnFilter(col);
                filterMap.put(col, filter);
            }
            filter.setItems(items);
        }
    }

    private ColumnFilter[] getFilters() {
        Collection<ColumnFilter> filters = filterMap.values();
        ColumnFilter[] result = new ColumnFilter[filters.size()];
        int i = 0;
        for( ColumnFilter f: filterMap.values() ) {
            result[i++] = f.copy();
        }
        return result;
    }

    @Override
    public void modelChanged(TableModel model) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    static class ColumnFilter extends HidingRowFilter implements Cloneable{

        private static final long serialVersionUID = 1L;

        private Collection<DistinctColumnItem> items = Collections.emptyList();

        public ColumnFilter( int col ) {
            super(col);
        }

        public void setItems(Collection<DistinctColumnItem> items) {
            this.items = items== null? Collections.<DistinctColumnItem>emptyList(): items;
        }

        @Override
        public boolean testValue(Object value) {
            return items.contains( new DistinctColumnItem(value,0));
        }

        protected ColumnFilter copy()  {
            ColumnFilter filter;
            try {
                filter = (ColumnFilter) super.clone();
                filter.setColumnIndex( this.getColumnIndex());
                filter.setItems(this.items);
                return filter;
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        } 

    }


static abstract class HidingRowFilter extends Filter {
    private ArrayList<Integer>  toPrevious;

    /**
     * Instantiates a includeAll PatternFilter with matchFlag 0 on
     * column 0.
     *
     */
    public HidingRowFilter(int col) {
        super(col);
    }


    /**
     * Resets the internal row mappings from this filter to the previous filter.
     */
    @Override
    protected void reset() {
        toPrevious.clear();
        int inputSize = getInputSize();
        fromPrevious = new int[inputSize];  // fromPrevious is inherited protected
        for (int i = 0; i < inputSize; i++) {
            fromPrevious[i] = -1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void filter() {
        int inputSize = getInputSize();
        int current = 0;
        for (int i = 0; i < inputSize; i++) {
            if (test(i)) {
                toPrevious.add(new Integer(i));
                // generate inverse map entry while we are here
                fromPrevious[i] = current++;
            }
        }
    }

    /**
     * Tests whether the given row (in this filter's coordinates) should
     * be added. <p>
     * 
     * PENDING JW: why is this public? called from a protected method? 
     * @param row the row to test
     * @return true if the row should be added, false if not.
     */
    public boolean test(int row) {
        if (!adapter.isTestable(getColumnIndex())) {
            return false; 
        }

        Object value = getInputValue(row, getColumnIndex());
        return testValue(value);
    }

    public abstract boolean testValue(Object value);

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return toPrevious.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int mapTowardModel(int row) {
        return toPrevious.get(row);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void init() {
        toPrevious = new ArrayList<Integer>();
    }

}


}
