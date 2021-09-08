import java.net.*;
public class NsLookup {
public static void main(String args[]) {
if (args.length == 0) {
System.out.println("Pemakaian: <namahost>");
System.exit(0);
}
String host = args[0];
InetAddress address = null;
try {
address = InetAddress.getByName(host);
} catch(UnknownHostException e) {
System.out.println("Unknown host");
System.exit(0);
}
byte[] ip = address.getAddress();
for (int i=0; i<ip.length; i++) {
if (i > 0) System.out.print(".");
System.out.print((ip[i]) & 0xff);
}
System.out.println();
}
}