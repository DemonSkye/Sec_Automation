import java.util.ArrayList;

public class LogObject {
    private String ip_addr;
    private ArrayList<String> timeStamp = new ArrayList<>();
    private ArrayList<String> verb = new ArrayList<>();
    private ArrayList<String> req = new ArrayList<>();
    private ArrayList<Integer> statusResp = new ArrayList<>();
    private ArrayList<Integer> respTime = new ArrayList<>();
    private ArrayList<String> referrer = new ArrayList<>();
    private ArrayList<String> userAgent = new ArrayList<>();
    private int count =0;


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

    void output(){
        System.out.println("IPv4 Address: " + ip_addr);
        System.out.println("TimeStamps: " + timeStamp);
        System.out.println("Verbs: " + verb);
        System.out.println("Requests: " + req);
        System.out.println("Status Responses: " + statusResp);
        System.out.println("ResponseTimes: " + respTime);
        System.out.println("Referrers: " + referrer);
        System.out.println("User Agents: ");
        System.out.println("Count for IP: " + count);

    }


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
