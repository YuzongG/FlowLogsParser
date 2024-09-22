import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
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