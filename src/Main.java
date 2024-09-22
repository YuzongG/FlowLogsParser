import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<FlowLogRecord> recordList = Parser.parse();
        LookupTable table = new LookupTable();
        table.loadRecords(recordList);
        table.loadCombination(recordList);
        table.outputMatchesForTag();
        table.outputMatchesForCombination();
    }
}