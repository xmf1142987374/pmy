package hb.xm.common;

import javax.servlet.http.HttpServletRequest;

public class Ip {

    public static  String getIp(HttpServletRequest request, String ip){
            String userip;
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            userip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            userip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            userip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            userip=ip.split(",")[0];
        } else {
            userip=ip;
        }
        return userip;

    }
}
