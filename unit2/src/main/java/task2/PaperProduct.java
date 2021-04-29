package task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import task1.Stationery;

// бумажные изделия
public abstract class PaperProduct extends Stationery {

    private List<Record> records;
    private int durability;

    protected int getDurability() {
        return durability;
    }

    protected void setDurability(int durability) {
        this.durability = durability;
    }

    public PaperProduct() {
        records = new ArrayList<>();
    }

    protected UUID saveRecord(String text, WritingUtensil writingTool) {
        Record newRecord = null;
        if (durability != 0) {
            newRecord = new Record(text, writingTool);
            records.add(newRecord);
            durability--;
        }
        return newRecord.getId();
    }

    protected void deleteRecord(UUID recordId, Corrector corrector) {

        Optional.of(getWritingTool(recordId))
            .filter(c -> corrector.getErasingEverything() || !c.getIndelible())
            .flatMap(c -> records
                .stream()
                .filter(currentRecord -> recordId.equals(currentRecord.getId()))
                .findAny()).ifPresent(value -> {
            records.remove(value);
            durability++;
        });

    }

    protected WritingUtensil getWritingTool(UUID recordId) {
        return records
            .stream()
            .filter(c -> recordId.equals(c.getId()))
            .map(Record::getWritingTool)
            .findFirst()
            .orElse(null);
    }

    public List<Record> getRecords() {
        return records;
    }
}
