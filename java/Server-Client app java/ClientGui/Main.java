package com.uchihaitachi;

/* ekbar data niye abar id pathale kaj hoy na...
* multiple client done
* data entry in database from client - not done
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
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main extends Application implements EventHandler<ActionEvent>{

    private static Socket socket = null;

    Button connect_btn, request_btn;
    Scene scene1, scene2;
    Stage window;
    TextField login_id, login_pass, req_id, got_id, got_name, got_sem, got_dept;
    protected static Student student1;

    static String send_id;
    static String isLoggedIn = null;

    static DataInputStream confirmation = null;
    static DataOutputStream pass_out = null;

    public static void main(String[] args) {
	// write your code here


        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Client");

        // scene1 er layout
        connect_btn = new Button();
        connect_btn.setText("Connect");
        connect_btn.setOnAction(this);

        login_id = new TextField();
        login_id.setPromptText("Enter login id");
        login_id.setPrefColumnCount(9);

        login_pass = new TextField();
        login_pass.setPromptText("Enter login pass");
        login_id.setPrefColumnCount(4);

        //status = new TextField();

        Label label_login = new Label("Login");
        VBox layout1 = new VBox();
        layout1.getChildren().addAll(label_login, login_id, login_pass, connect_btn);

        scene1 = new Scene(layout1, 400, 350);

        // scene1 er layout ends

        //scene2 er layout
        request_btn = new Button();
        request_btn.setText("Request info");
        request_btn.setOnAction(this);

        req_id = new TextField();
        req_id.setPromptText("Input id for requesting info");
        req_id.setPrefColumnCount(9);

        got_id = new TextField();
        got_id.setText("ID : ");
        got_name = new TextField();
        got_name.setText("Name : ");
        got_sem = new TextField();
        got_sem.setText("Semester : ");
        got_dept = new TextField();
        got_dept.setText("Department : ");

        Label label_reqeust = new Label("Input id");
        VBox layout2 = new VBox();
        layout2.getChildren().addAll(label_reqeust, req_id, request_btn,
                got_id, got_name, got_sem, got_dept);

        scene2 = new Scene(layout2, 400, 350);
        //scene2 er layout ends

        isLoggedIn = "false";
        window.setScene(scene1);
        window.show();

    }

    class RunnableClient implements Runnable{

        String dstAddress;
        int dstPort;

        public RunnableClient(String addr, int port) {
            dstAddress = addr;
            dstPort = port;
        }

        @Override
        public void run() {
            try {
                socket = new Socket(dstAddress, dstPort);

                pass_out = new DataOutputStream(socket.getOutputStream());

                    pass_out.writeUTF(login_pass.getText());
                    System.out.println("pass sent");

                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        System.out.println("I'm waiting here1");
                    }
                });

                System.out.println("uhu");

                while(!isLoggedIn.equals("true")) {
                    System.out.println("waiting for confirmation");
                    confirmation =  new DataInputStream(socket.getInputStream());

                    try {
                        isLoggedIn = (String) confirmation.readUTF();
                        System.out.println("huu " + isLoggedIn);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (isLoggedIn.equals("true")) {
                        System.out.println("got confirmation");
                        break;
                    }
                    else {
                        System.out.println("Not connected");
                        break;
                        //socket.close();
                    }
                }
            }  catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        System.out.println("..waiting.");
                    }

                });
                /*if (socket != null) {
                    System.out.println("jhamela hoise, socket close kortesi");

                    try {
                        System.out.println("jhamela hoise, socket close kortesi");
                        confirmation.close();
                        //socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }*/
            }
        }

    }

    public class Data_fetch extends Thread{

        DataOutputStream id_outputStream = null;
        ObjectInputStream inputStream = null;

        String msgToServer;

        public Data_fetch(String s_id){
            msgToServer = s_id;
        }

        public void run() {
            System.out.println("Connected to server " + msgToServer);
            try {
                id_outputStream = new DataOutputStream(socket.getOutputStream());
                // id to server

                inputStream = new ObjectInputStream(socket.getInputStream());
                 // Student object from server

           // if (msgToServer != null) {
                id_outputStream.writeUTF(msgToServer);
                System.out.println("sent to server " + msgToServer);

                //id_outputStream.flush();
            //}

                student1 = (Student) inputStream.readObject();

                if (student1 != null) {
                    got_id.setText("ID : " + student1.getId());
                    got_name.setText("Name : " + student1.getName());
                    got_sem.setText("Semester : " + student1.getSem());
                    got_dept.setText("Dept : " + student1.getDept().toUpperCase());
                }
                System.out.println("data done");
            } catch (Exception e) {
                e.printStackTrace();
            }/*finally {
                //if (id_outputStream != null) {
                    try {
                        id_outputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
                /*if (dataInputStream != null) {
                    try {
                        dataInputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }*/
        }

    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == connect_btn){
            // connection request
            if(login_pass.getText() != null || !login_pass.getText().isEmpty()){
                System.out.println("pass " + login_pass.getText());
                //log = new Login_info(login_id.getText(), login_pass.getText());

                // login id pass server k dibe
                // arekta runnable thread jetate login id ar pass
                // server e pathabe, server confirmation flag pathabe

                RunnableClient runnableClient
                        = new RunnableClient("localhost", 4444);

                new Thread(runnableClient).start();

                //if(isLoggedIn == "true")
                    window.setScene(scene2);


            }
            else
                System.out.println("id or pass error");

        }

        if(event.getSource() == request_btn){
            // id pathabe server er kase
            if(req_id.getText() != null && !req_id.getText().isEmpty()){
                System.out.println("requested id " + req_id.getText());
                send_id = req_id.getText();

                Data_fetch data_fetch
                        = new Data_fetch(send_id);

                new Thread(data_fetch).start();
                //new Data_fetch(send_id).run();
                // request id for information
            }

            // textfiled e info chole ashbe
            //window.setScene(scene1);
        }
    }
}
