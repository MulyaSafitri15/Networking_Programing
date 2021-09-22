
//import java.io.BufferedReader untuk mengambil input dari keyboard dan 
//membaca input dari file dan jaringan.
//import java.io.IOException; // termasuk class bufferedreader.
//import java.io.InputStreamReader;// termasuk class bufferedreader.
//import java.io.PrintWriter; //class untuk input output untuk mengirim data ke client.
import java.io.*; //(class input output dot all) untuk mengakses semua kelas input dan output.
import java.net.*; //class net untuk mengakses semua kelas network/komunikasi jaringan yang ada.

public class ChatClientMulti { //nama class utama dan nama.

    private static InetAddress host; //membuat objek untuk pembaca ip address (inetaddress) dengan nama host
    private static final int PORT = 1234; //membuat objek bernama port yang berisi nomor port
    private static Socket link; //membuat objek untuk socket dengan nama link
    private static BufferedReader in; //Membaca input dari user melalui konsol dengan menggunakan BufferedReader
    private static PrintWriter out; //membuat objek untuk printwriter dengan nama out untuk mengirim data ke client.
    private static BufferedReader keyboard;//membuat objek baru untuk inputan bufferedreader untuk keyboard dengan nama keyboard.  

    public static void main(String args[]) { // method utama dari program dan akan di jalankan pertama kali oleh sistem.
        try { //Blok try digunakan untuk menempatkan kode-kode program Java yang memungkinkan terjadinya exception. 
            host = InetAddress.getLocalHost(); //mengecek ip address di localhost komputer kita
            link = new Socket(host, PORT); //membuat objek link dengan nama socket yang berisi ip dan nomor port.
            in = new BufferedReader(new InputStreamReader(link.getInputStream())); //membuat objek in dari kelas bufferedReader
            out = new PrintWriter(link.getOutputStream(), true);//akan menampilkan sesuatu dari objek link
            keyboard = new BufferedReader(new InputStreamReader(System.in)); //membuat objek keyboard dari kelas bufferedReader
            String message, response; //membuat variabel
            do { //perulangan do 
                System.out.print("Enter message(QUIT to exit)"); //menampilkan "Enter message(QUIT to exit)"
                message = keyboard.readLine(); //merupakan inputan dari keyboard
                out.println(message);// akan menampilkan varibel message
                response = in.readLine(); //menuliskan variabel respon
                System.out.println(response);//menampilkan variabel respon
            } while (!message.equals("QUIT")); //jika variabel message sama dengan kata quit
        } catch (UnknownHostException e) { //Blok catch digunakan untuk menangkap kesalahan yang terjadi pada blok try. 
            System.out.println("Host ID not found!"); //menampilkan kalimat host id not found
        } catch (IOException e) { //Blok catch digunakan untuk menangkap kesalahan yang terjadi pada blok try. 
            e.printStackTrace(); //pengecekan kesalahan kembali
        } finally {
//            Kode program pada blok finally akan selalu dijalankan, tidak peduli
//apakah terjadi eksepsi atau tidak. 

            try {
                if (link != null) { //jika objek link bernilai null maka 
                    System.out.println("Closing down connection"); //akan tampil kata closing down connetction
                    link.close();//lalu sistem akan otomatis tertutup
                }
            } catch (IOException e) { //jika blok try error maka akan dijalankan perintah catcth
                e.printStackTrace();
            }
        }
    }
}
