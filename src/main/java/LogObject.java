import java.util.ArrayList;
import java.util.Comparator;

public class LogObject {
    private String ip_addr;
    private ArrayList<String> timeStamp = new ArrayList<>();
    private ArrayList<String> verb = new ArrayList<>();
    private ArrayList<String> req = new ArrayList<>();
    private ArrayList<Integer> statusResp = new ArrayList<>();
    private ArrayList<Integer> respTime = new ArrayList<>();
    private ArrayList<String> referrer = new ArrayList<>();
    private ArrayList<String> userAgent = new ArrayList<>();
    private int count = 1;
    String country;
    String city;
    String lat;
    String lon;


    /**
     * Constructor
     * @param ip ip address
     * @param ts timestamp
     * @param v verb
     * @param r request
     * @param stat status response
     * @param respt response time
     * @param ref referrer
     * @param ua User Agent
     */
    LogObject(String ip, String ts, String v, String r, int stat, int respt, String ref, String ua){
        ip_addr = ip;
        timeStamp.add(ts);
        verb.add(v);
        req.add(r);
        statusResp.add(stat);
        respTime.add(respt);
        referrer.add(ref);
        userAgent.add(ua);
    }

    /**
     * Method to output contents of events where IP hit at least 5 times
     * Todo: configure 5 to be user defined via some interface.
     */
    void output(){
        if(count > 5){
            System.out.println("Summary for : " + ip_addr);
            System.out.println("TimeStamps: " + timeStamp);
            System.out.println("Verbs: " + verb);
            System.out.println("Requests: " + req);
            System.out.println("Status Responses: " + statusResp);
            System.out.println("ResponseTimes: " + respTime);
            System.out.println("Referrers: " + referrer);
            System.out.println("User Agents: ");
            System.out.println("Count for IP: " + count + "\n\n");
        }
    }

    static class sortByCount implements Comparator<LogObject> {
        public int compare(LogObject a, LogObject b){
            return a.count - b.count;
        }
    }


    /**
     * Accessors / setters / adders
     */
    public String getIp_addr() {
        return ip_addr;
    }

    public void addTimeStamp(String ts) {
        timeStamp.add(ts);
    }

    public void addVerb(String v) {
        verb.add(v);
    }

    public ArrayList<String> getReq() {
        return req;
    }

    public void addReq(String r) {
        req.add(r);
    }

    public void addStatusResp(int r){
        statusResp.add(r);
    }

    public void addRespTime(int rt){
        respTime.add(rt);
    }

    public void addRef(String ref){
        referrer.add(ref);
    }

    public void addUA(String ua){
        userAgent.add(ua);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
