/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xpresstek.zprint.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import net.xpresstek.zprint.jsf.JobsController;

/**
 *
 * @author alex
 */
public class RAWServer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        (new Thread(new RawListener())).start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private class RawListener implements Runnable {

        @Override
        public void run() {
            try {
                ServerSocket socket = new ServerSocket(9100);

                while (true) {
                    Socket sock = socket.accept();
                    InputStream is = sock.getInputStream();
                    ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                    int nRead;
                    byte[] data = new byte[16384];

                    while ((nRead = is.read(data, 0, data.length)) != -1) {
                        buffer.write(data, 0, nRead);
                    }

                    InetAddress host = sock.getInetAddress();
                    JobsController controller = JobsController.getController();
                    controller.insertJob(buffer.toByteArray(), host.getHostAddress());
                }

            } catch (IOException ex) {
                Logger.getLogger(RAWServer.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
