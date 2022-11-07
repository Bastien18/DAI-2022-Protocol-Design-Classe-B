package ch.heigvd.api.calc;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Logger;

/**
 * Calculator worker implementation
 */
public class ServerWorker implements Runnable {

    private final static Logger LOG = Logger.getLogger(ServerWorker.class.getName());
    private Socket clientSocket;
    private BufferedReader in = null;
    private BufferedWriter out = null;

    /**
     * Instantiation of a new worker mapped to a socket
     *
     * @param clientSocket connected to worker
     */
    public ServerWorker(Socket clientSocket) {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        /* TODO: prepare everything for the ServerWorker to run when the
         *   server calls the ServerWorker.run method.
         *   Don't call the ServerWorker.run method here. It has to be called from the Server.
         */

        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Run method of the thread.
     */
    @Override
    public void run() {

        /* TODO: implement the handling of a client connection according to the specification.
         *   The server has to do the following:
         *   - initialize the dialog according to the specification (for example send the list
         *     of possible commands)
         *   - In a loop:
         *     - Read a message from the input stream (using BufferedReader.readLine)
         *     - Handle the message
         *     - Send to result to the client
         */

        String line;
        ReversePolishCalculator calculator = new ReversePolishCalculator();

        try {
            while (!clientSocket.isClosed()) {

                while( (line = in.readLine()) != null){
                    if(line.equalsIgnoreCase(":q"))
                        break;
                    out.write(String.format("Resultat = %d\n", calculator.calculatorInvRev(line)));
                }
            }
        }catch (Exception ex){
            System.err.println("Something went wrong during the client-server communication");
        }
    }

}