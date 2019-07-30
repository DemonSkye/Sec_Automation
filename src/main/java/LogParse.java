import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParse {
    public static void parse(String filePath){
        ArrayList<String> contents = fileToStrings(new File(filePath));
        ArrayList<LogObject> events = createObjects(contents);
        contents = null;
        Collections.sort(events, new LogObject.sortByCount() );
        for(LogObject o:events){
            o.output();
        }
    }

    public static ArrayList<String> fileToStrings(File f){
        ArrayList<String> fileStrings = new ArrayList<>();
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line!=null){
                fileStrings.add(line);
                line = reader.readLine();
            }
        }catch (IOException ioe){ ioe.printStackTrace();}
        return fileStrings;
    }

    public static ArrayList<LogObject> createObjects(ArrayList<String> contents){
        final String regex = "(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s-\\s-\\s\\[([^\\]]*)\\]\\s\\\"(GET|POST|PUT|HEAD|DELETE)\\s([^ ]*)\\sHTTP\\/1\\.\\d\\\"\\s(\\d+)\\s(\\d+)\\s(-|[^ ]*)\\s\\\"([^\\\"]*)\\\"";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        ArrayList<LogObject> events = new ArrayList<>();
        boolean found = false;
        for(String s:contents){
            final Matcher matcher = pattern.matcher(s);
            while(matcher.find()){
                    LogObject o = new LogObject(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4),
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)),
                    matcher.group(7),
                    matcher.group(8));
                    if(events.size() > 0){
                        for(LogObject e: events){
                            if(e.getIp_addr().equalsIgnoreCase(o.getIp_addr())){
                                e.addTimeStamp(matcher.group(2));
                                e.addVerb(matcher.group(3));
                                e.addReq(matcher.group(4));
                                e.addStatusResp(Integer.parseInt(matcher.group(5)));
                                e.addRespTime(Integer.parseInt(matcher.group(6)));
                                e.addRef(matcher.group(7));
                                e.addUA(matcher.group(8));
                                e.setCount(e.getCount()+1);
                                if(e.getCount() > 5 && e.country == null){
                                    ArrayList<String> geo = GeoLookUp.getGeoFromIP(e.getIp_addr());
                                    e.country = geo.get(0);
                                    e.city = geo.get(1);
                                    e.lat = geo.get(2);
                                    e.lon = geo.get(3);
                                }
                                found = true;
                                break;
                            }
                        }
                        if(!found){
                            events.add(o);
                        }
                        found = false;
                    }
                    else{
                        events.add(o);
                    }
            }
        }
        return events;
    }
}
