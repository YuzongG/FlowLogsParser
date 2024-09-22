import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookupTable {
    private final Map<Combination, String> lookupTable;
    private final Map<String, Long> recordsTable;
    private final Map<Combination, Long> combinationTable;
    public LookupTable () {
        this.lookupTable = new HashMap<>();
        this.recordsTable = new HashMap<>();
        combinationTable = new HashMap<>();
        loadTables();
    }

    private void loadTables() {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader("lookUpTable.txt"));
            String lineText;
            while ((lineText = lineReader.readLine()) != null) {
                loadTables(lineText);
            }
            lineReader.close();
        } catch (IOException ex) {
            throw new IllegalArgumentException("cannot load the look up table", ex);
        }
    }

    public void loadRecords(List<FlowLogRecord> list) {
        for (FlowLogRecord record: list) {
            Combination c = new Combination(record.getDst_port(), record.getProtocol());
            if (lookupTable.containsKey(c)) {
                recordsTable.put(lookupTable.get(c), recordsTable.getOrDefault(lookupTable.get(c), 0L) + 1L);
            } else {
                recordsTable.put("Untagged", recordsTable.getOrDefault("Untagged", 0L) + 1);
            }
        }
    }

    public void loadCombination(List<FlowLogRecord> list) {
        for (FlowLogRecord record : list) {
            Combination combination = new Combination(record.getDst_port(), record.getProtocol());
            combinationTable.put(combination, combinationTable.getOrDefault(combination, 0L) + 1L);
        }
    }

    private void loadTables(String data) {
        String[] data_arr = data.split(",");
        if (data_arr.length != 3) {
            throw new IllegalArgumentException("cannot load the lookup table");
        }
        lookupTable.put(new Combination(Integer.parseInt(data_arr[0]), IPProtocols.valueOf(data_arr[1].toUpperCase())), data_arr[2]);
    }

    public void outputMatchesForCombination() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("combinations.txt", false));
            writer.write("Port,Protocol,Count");
            writer.newLine();
            for(Combination c: combinationTable.keySet()){
                String res = c.getDstPort()+","+ c.getProtocols().name().toLowerCase() + "," + combinationTable.get(c);
                writer.write(res);
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }

    public void outputMatchesForTag() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("tags.txt", false));
            writer.write("Tag,Count");
            writer.newLine();
            for(String k: recordsTable.keySet()){
                String res = k + "," + recordsTable.get(k);
                writer.write(res);
                writer.newLine();
            }
            writer.close();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public class Combination {
        private int dstPort;
        private IPProtocols protocols;

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Combination c) {
                return c.getDstPort() == dstPort && c.getProtocols() == protocols;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (dstPort + protocols.name()).hashCode();
        }

        public Combination (int dstPort, IPProtocols protocols) {
            this.dstPort = dstPort;
            this.protocols = protocols;
        }

        public int getDstPort() {
            return dstPort;
        }

        public IPProtocols getProtocols() {
            return protocols;
        }
    }
}
