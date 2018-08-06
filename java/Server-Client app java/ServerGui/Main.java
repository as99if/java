package com.uchihaitachi;


/*
* multiple client done
* data entry in database from client - not done
* ekbar data client peye giye abar id dile ashei na oita
* */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.stage.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.io.ObjectInputStream;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application implements EventHandler<ActionEvent>{

    Button new_input_btn, get_btn;
    Scene scene1;
    Stage window;
    static TextField status, req_id, got_id, got_name, got_sem, got_dept;


    private static ServerSocket serverSocket = null;
    private static Socket socket;


    static final String database = "jdbc:mysql://localhost/studentdb";
    static final String db_user = "root";
    static final String db_pass = "";
    static Connection conn;
    static Statement stmnt;

    protected Student student1;
    protected Student student2;

    static String recieved_id;


    static String st_name, st_id,  st_sem, st_dept;


    static String client_login = null;

    static String idFromClient = null;

    private final String clientPass = "2199";


    public static void main(String[] args) {
	// write your code here

        launch(args);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Server");

        student1 = new Student();

        get_btn = new Button();
        get_btn.setText("Get info");
        get_btn.setOnAction(this);

        req_id = new TextField();
        req_id.setPromptText("id");
        req_id.setPrefColumnCount(9);

        got_id = new TextField();
        got_id.setText("ID : ");
        got_name = new TextField();
        got_name.setText("Name : ");
        got_sem = new TextField();
        got_sem.setText("Semester : ");
        got_dept = new TextField();
        got_dept.setText("Department : ");

        status = new TextField();

        Label label_server = new Label("Server");
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label_server, req_id, get_btn,
                got_id, got_name, got_sem, got_dept, status);
        scene1 = new Scene(layout1, 400, 350);
        window.setScene(scene1);
        window.show();


        Thread socketServerThread = new Thread(new SocketServerThread());
        socketServerThread.setDaemon(true); //terminate the thread when program end
        socketServerThread.start();

    }


    private class SocketServerThread extends Thread {

        static final int SocketServerPORT = 4444;
        int count = 0;

        @Override
        public void run() {
            try {
                socket = null;

                serverSocket = new ServerSocket(SocketServerPORT);
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("I'm waiting here: "
                                + serverSocket.getLocalPort());
                    }
                });

                while (true) {
                    socket = serverSocket.accept();
                    count++;
                    System.out.println("accept korse");

                    try {
                        DataInputStream pass_in = new DataInputStream(socket.getInputStream());

                        client_login = pass_in.readUTF();
                        System.out.println("ashche pass " + client_login);



                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (client_login.equals(clientPass)){

                        System.out.println("pass accepted");
                        DataOutputStream confirm_out = new DataOutputStream(socket.getOutputStream());

                        // id pass match korle eta pathabe
                        String isLoggedIn = "true";
                        confirm_out.writeUTF(isLoggedIn);
                        System.out.println("confirmation sent");

                        System.out.println("Connected with client " + count);

                        Thread acceptedThread = new Thread(
                                new ServerSocketAcceptedThread(socket, count));
                        acceptedThread.setDaemon(true); //terminate the thread when program end
                        acceptedThread.start();
                        //login kore ei thread e jabe
                    }
                    else if (!client_login.equals(clientPass)){
                        System.out.println("mile nai");
                        socket.close();
                    }



                }
            }catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    private class ServerSocketAcceptedThread extends Thread {

        Socket socket = null;
        ObjectOutputStream outputStream = null;

        DataInputStream id_inputStream = null;
        int count;

        ServerSocketAcceptedThread(Socket s, int c) {
            socket = s;
            count = c;
        }

        @Override
        public void run() {
            try {
                id_inputStream = new DataInputStream(socket.getInputStream());      // id in

                outputStream = new ObjectOutputStream(socket.getOutputStream());      //student object out
                System.out.println("Connected with client " + count);

                while (true) {  // disconnect howar option dite hobe
                    try {
                        System.out.println("waitin for id from client "+ count);
                        idFromClient = id_inputStream.readUTF();
                        System.out.println(idFromClient + " from client " + count);
                        fetch_data(idFromClient);
                        outputStream.writeObject(student1);
                    }catch (Exception e){
                        //e.printStackTrace();
                        System.out.println("Client " + count + " gone");
                    }/*finally {
                        if (id_inputStream != null) {
                            try {
                                id_inputStream.close();

                            } catch (IOException ex) {
                                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }*/
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }  /*finally {
                /*if (socket != null) {
                    try {
                        System.out.println("closing");
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }*/
        }

    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == get_btn){
            if(req_id.getText() != null && !req_id.getText().isEmpty()){
                System.out.println("id " + req_id.getText());
                fetch_data(req_id.getText());
                display();
            }
        }

    }

    protected void display(){
        got_id.setText("ID : " + st_id);
        got_name.setText("Name : " + st_name);
        got_sem.setText("Semester : " + st_sem);
        got_dept.setText("Dept : " + st_dept.toUpperCase());
    }



    protected void fetch_data(String id){
        // fetches from db

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(database, db_user, db_pass);

            stmnt = conn.createStatement();
            String query = "Select * FROM student WHERE id = "+id;
            ResultSet rs = stmnt.executeQuery(query);

            while (rs.next()) {
                //Retrieve by column name

                st_name = rs.getString("name");
                st_id = rs.getString("id");
                st_sem = rs.getString("semester");
                st_dept = rs.getString("dept");

                student1.setId(st_id);
                student1.setName(st_name);
                student1.setSem(st_sem);
                student1.setDept(st_dept);

                //Display values
                System.out.print("ID: " + st_id);
                System.out.print(", Name: " + st_name);
                System.out.print(", Semester: " + st_sem);
                System.out.println(", Department: " + st_dept);
                /*
                got_id.setText("ID : " + st_id);
                got_name.setText("Name : " + st_name);
                got_sem.setText("Semester : " + st_sem);
                got_dept.setText("Dept : " + st_dept);
                */
            }
            rs.close();
            stmnt.close();
            conn.close();

            return;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmnt != null)
                    stmnt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

}
