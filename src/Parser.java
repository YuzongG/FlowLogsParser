import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static FlowLogRecord parseInternal(String log){
        String[] log_segments = log.split(" ");
        FlowLogRecord.FlowLogRecordBuilder builder = new FlowLogRecord.FlowLogRecordBuilder();
        if (log_segments.length != 14) {
           throw new IllegalArgumentException("This record is corrupted, cannot be parsed");
        }
        builder.version(Version.fromValue(log_segments[0]));
        builder.accountId(log_segments[1]);
        builder.interfaceId(log_segments[2]);
        builder.srcAddr(log_segments[3]);
        builder.dstAddr(log_segments[4]);
        builder.srcPort(Integer.parseInt(log_segments[5]));
        builder.dstPort(Integer.parseInt(log_segments[6]));
        builder.protocol(IPProtocols.valueOf(IPProtocols.getProtocolName(Integer.parseInt(log_segments[7]))));
        builder.packets(Long.parseLong(log_segments[8]));
        builder.bytes(Long.parseLong(log_segments[9]));
        builder.startTime(Long.parseLong(log_segments[10]));
        builder.endTime(Long.parseLong(log_segments[11]));
        builder.action(log_segments[12]);
        builder.logStatus(log_segments[13]);

        return builder.build();
    }

    public static List<FlowLogRecord> parse() {
        List<FlowLogRecord> records = new ArrayList<>();
        try {
            BufferedReader lineReader = new BufferedReader(
                    new FileReader("/Users/yuzonggao/IdeaProjects/FlowLogsParser/src/data.txt"));
            String lineText;
            while ((lineText = lineReader.readLine()) != null) {
                records.add(parseInternal(lineText));
            }
            lineReader.close();
        } catch (IOException ex) {
            throw new IllegalArgumentException("cannot load the data", ex);
        }
        return records;

    }
}
