public class FlowLogRecord {

    private Version version;
    private String account_id;
    private String interface_id;
    private String src_addr;
    private String dst_addr;
    private int src_port;
    private int dst_port;
    private IPProtocols protocol;
    private long packets;
    private long bytes;
    private long start_time;
    private long end_time;
    private String action;
    private String log_status;

    public Version getVersion() {
        return version;
    }

    public String getAccount_id() {
        return account_id;
    }

    public String getInterface_id() {
        return interface_id;
    }

    public String getSrc_addr() {
        return src_addr;
    }

    public String getDst_addr() {
        return dst_addr;
    }

    public int getSrc_port() {
        return src_port;
    }

    public int getDst_port() {
        return dst_port;
    }

    public IPProtocols getProtocol() {
        return protocol;
    }

    public long getPackets() {
        return packets;
    }

    public long getBytes() {
        return bytes;
    }

    public long getStart_time() {
        return start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public String getAction() {
        return action;
    }

    public String getLog_status() {
        return log_status;
    }


    private FlowLogRecord(FlowLogRecordBuilder builder) {
        this.version = builder.version;
        this.account_id = builder.account_id;
        this.action = builder.action;
        this.bytes = builder.bytes;
        this.dst_addr = builder.dst_addr;
        this.dst_port = builder.dst_port;
        this.src_addr = builder.src_addr;
        this.src_port = builder.src_port;
        this.packets = builder.packets;
        this.interface_id = builder.interface_id;
        this.start_time = builder.start_time;
        this.end_time = builder.end_time;
        this.log_status = builder.log_status;
        this.protocol = builder.protocol;
    }

    public static class FlowLogRecordBuilder {
        private Version version;
        private String account_id;
        private String interface_id;
        private String src_addr;
        private String dst_addr;
        private int src_port;
        private int dst_port;
        private IPProtocols protocol;
        private long packets;
        private long bytes;
        private long start_time;
        private long end_time;
        private String action;
        private String log_status;

        public FlowLogRecordBuilder() {}

        public FlowLogRecordBuilder version(Version version){
            this.version = version;
            return this;
        }

        public FlowLogRecordBuilder accountId(String account_id){
            this.account_id = account_id;
            return this;
        }

        public FlowLogRecordBuilder interfaceId(String interface_id){
            this.interface_id = interface_id;
            return this;
        }

        public FlowLogRecordBuilder srcAddr(String src_addr){
            this.src_addr = src_addr;
            return this;
        }

        public FlowLogRecordBuilder srcPort(int src_port){
            this.src_port = src_port;
            return this;
        }

        public FlowLogRecordBuilder dstAddr(String dst_addr){
            this.dst_addr = dst_addr;
            return this;
        }

        public FlowLogRecordBuilder dstPort(int dst_port){
            this.dst_port = dst_port;
            return this;
        }

        public FlowLogRecordBuilder protocol(IPProtocols protocol){
            this.protocol = protocol;
            return this;
        }

        public FlowLogRecordBuilder packets(long packets){
            this.packets = packets;
            return this;
        }

        public FlowLogRecordBuilder bytes(long bytes){
            this.bytes = bytes;
            return this;
        }

        public FlowLogRecordBuilder startTime(long start_time){
            this.start_time = start_time;
            return this;
        }

        public FlowLogRecordBuilder endTime(long end_time){
            this.end_time = end_time;
            return this;
        }

        public FlowLogRecordBuilder action(String action){
            this.action = action;
            return this;
        }

        public FlowLogRecordBuilder logStatus(String logStatus){
            this.log_status = logStatus;
            return this;
        }

        public FlowLogRecord build() {
            return new FlowLogRecord(this);
        }
    }
}
