
import java.io.*;//(class input output dot all) untuk mengakses semua kelas input dan output 
import java.net.*;//class net untuk mengakses semua kelas network/komunikasi jaringan yang ada

public class ChatServerMulti {

    private static ServerSocket servSock;//membuat objek untuk serversocket dengan nama servsock
    private static final int PORT = 1234;//membuat objek bernama port yang berisi nomor port

    public static void main(String args[]) throws IOException {
//method main/utama yang menggunakan Method throws untuk Membaca Input Data String
        System.out.println("Opening Port.....\n"); //menampilkan kata opening port
        try { ////Blok try digunakan untuk menempatkan kode-kode program Java yang memungkinkan terjadinya exception.
            servSock = new ServerSocket(PORT); //mengecek port server
        } catch (IOException e) { //jika blok try error maka sistem akan menjalankan blok catch
            System.out.println("Unable to attach to port");//dan akan menampilkan unable to attach to port
            System.exit(1); //lalu sistem akan otomatis tertutup.
        }
        do {//perulangan do
            Socket client = servSock.accept(); //untuk menghubungkan dan menunggu persetujuan dari client
            ClientHandler handler = new ClientHandler(client);//penamaan handler untuk client
            handler.start();//memulai handler
        } while (true); //akan terus berulang jika bernilai true
    }
}

class ClientHandler extends Thread { //membuat class turunan clienthandler  

    private Socket client; //definisi objek socket sebagai client
    private BufferedReader in; // definisi untuk objek bufferedreader
    private PrintWriter out; //definisi untuk objek printerwriter

    public ClientHandler(Socket socket) { //class client
        client = socket; //client samadengan socket
        try {//memulai blok try
            in = new BufferedReader(new InputStreamReader(client.getInputStream())); //mengambil inputan dari objek client
            out = new PrintWriter(client.getOutputStream(), true); ///mengirimkan data ke client
        } catch (IOException e) { //blok catch
            e.printStackTrace();
        }
    }

    public void run() { //membuat class baru yaitu run
        try { //memulai blok try
            String received; //membuat variabel dengan nama received dengan tipe string
            do { //perulangan do
                received = in.readLine(); // menginput data
                System.out.println(received); //menampilkan data inputan received
                out.println("ECHO : " + received);//menampilkan kata echo dan hasil inputan dari reveived
            } while (!received.equals("QUIT")); //jika received sama dengan quit
        } catch (IOException e) { //blok catch
            e.printStackTrace();
        } finally { //blok finally
            try { //memulai blok try
                if (client != null) { //jika client nya berisi null atau kosong 
                    System.out.println("Closing down connection");//maka akan tampil Closing down connection
                    client.close(); //dan sistem akan langsung tertutup
                }
            } catch (IOException e) { //blok catch
                e.printStackTrace();
            }
        }
    }
}
