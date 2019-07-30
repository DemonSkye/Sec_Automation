public class main {
    public static void main(String args[]){
        String accessLogLoc;
        if(args.length > 0){
            accessLogLoc = args[0];
        }
        else{
            accessLogLoc = "access.log";
        }
        LogParse.parse(accessLogLoc);
    }
}
