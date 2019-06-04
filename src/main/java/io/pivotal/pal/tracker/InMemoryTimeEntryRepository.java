package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    Map<Long,TimeEntry> timeEntryMap = new HashMap();

    private int counter = 0;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        counter += 1;
        timeEntry.setId(counter);
        timeEntryMap.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        return timeEntryMap.get(timeEntryId);
    }

    @Override
    public List<TimeEntry> list() {
        ArrayList<TimeEntry> valueList = new ArrayList<TimeEntry>(timeEntryMap.values());
        return valueList;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(find(id) == null){
            return null;
        }

        timeEntry.setId(id);
        timeEntryMap.put(id, timeEntry);
        return timeEntryMap.get(id);
    }

    @Override
    public void delete(long timeEntryId) {
        timeEntryMap.remove(timeEntryId);
    }
}
