package main.server;

/* This class provides a very simple implementation of a web server. As a web server
 * must be capable of handling multiple requests from web browsers at the same time,
 * it is essential that the server is threaded, i.e. that the web server can perform
 * tasks in parallel and serially (one request at a time, after another).
 *
 * In programming languages, all network communication is handled using sockets. A
 * socket is a software abstraction of a connection between one computer on a network
 * and another. A server-socket is a process that listens on a port number for
 * incoming client requests. For example, the standard port number for a HTTP server (a
 * web server) is port 80. Most of the commonly used Java networking classes are
 * available in the java.net package. The java.io package contains a set of classes
 * designed to handle Input/Output (I/O) activity. We will use both packages in the web
 * server class below.
 */

import main.http.request.Request;
import main.http.request.RequestHandler;
import main.http.request.errorHandling.RequestHandlerException;

import java.io.*; //Contains classes for all kinds of I/O activity
import java.net.*; //Contains basic networking classes

class WebServer {
    private ServerSocket ss; //A server socket listens on a port number for incoming requests

    //The first 1024 ports require administrator privileges. We'll use 8080 instead. The range
    //of port numbers runs up to 2 ^ 16 = 65536 ports.
    private static final int SERVER_PORT = 8080;

    //The boolean value keepRunning is used to control the while loop in the inner class called
    //Listener. The volatile keyword tells the JVM not to cache the value of keepRunning during
    //optimisation, but to check it's value in memory before using it.
    private volatile boolean keepRunning = true;


    //A null constructor for the WebServer class
    WebServer() {
        try { //Try the following. If anything goes wrong, the error will be passed to the catch block

            ss = new ServerSocket(SERVER_PORT); //Start the server socket listening on port 8080

			/* A Thread is a worker. A runnable is a job. We'll give the worker thread called "server"
             * the job of handling incoming requests from clients.
			 * Note: calling start results in a new JVM stack being created. The run() method of the Thread
			 * or Runnable will be placed on the new stack and executed when the Thread Scheduler (consider
			 * this a cantankerous and uncommunicative part of the JVM) decides so. There is absolutely NO
			 * GUARANTEE of either order or execution time. We can however ask the Thread Scheduler
			 * (politely) to run a thread as a max, min or normal priority.
			 */
            Thread server = new Thread(new Listener(), "Web server Listener"); //We can also name threads
            server.setPriority(Thread.MAX_PRIORITY); //Ask the Thread Scheduler to run this thread as a priority
            server.start(); //The Hollywood Principle - Don't call us, we'll call you

            System.out.println("server started and listening on port " + SERVER_PORT);

        } catch (IOException e) { //Something nasty happened. We should handle error gracefully, i.e. not like this...
            System.out.println("Yikes! Something bad happened..." + e.getMessage());
        }
    }

    /* The inner class Listener is a Runnable, i.e. a job that can be given to a Thread. The job that
     * the class has been given is to intercept incoming client requests and farm them out to other
     * threads. Each client request is in the form of a socket and will be handled by a separate new thread.
     */
    private class Listener implements Runnable { //A Listener IS-A Runnable
        //The interface Runnable declare the method "public void run();" that must be implemented
        public void run() {
            int counter = 0; //A counter to track the number of requests
            while (keepRunning) { //Loop will keepRunning is true. Note that keepRunning is "volatile"
                try { //Try the following. If anything goes wrong, the error will be passed to the catch block

                    Socket socket = ss.accept(); //This is a blocking method, causing this thread to stop and wait here for an incoming request

                    RequestHandler requestHandler = new RequestHandler(socket);

                    Request request = null;

                    /* If we get to this line, it means that a client request was received and that the socket "s" is a real network
                     * connection between some computer and this programme. We'll farm out this request to a new Thread (worker),
					 * allowing us to handle the next incoming request (we could have many requests hitting the server at the same time),
					 * so we have to be able to handle them quickly.
					 */
                    try {
                        request = requestHandler.deserialize(socket);
                        requestHandler.start(request, "T -" + counter);
                    } catch (RequestHandlerException e) {
                        e.printStackTrace();
                    }

                    counter++; //Increment counter
                } catch (IOException e) { //Something nasty happened. We should handle error gracefully, i.e. not like this...
                    //hopefully log something here
                    System.out.println("Error handling incoming request..." + e.getMessage());
                }
            }
        }
    }//End of inner class Listener
}//End of class WebServer

