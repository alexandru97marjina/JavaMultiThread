//package sample;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.omg.CORBA.TIMEOUT;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Text tx_FCIM;

    @FXML
    private Sphere tx_ball;

    @FXML
    private Text tx_UTM;

    @FXML
    private Text tx_Calc;

    @FXML
    private Text tx_Info;

    @FXML
    private Text tx_MN;

    @FXML
    private Button btn_Start;

    @FXML
    private Button btn_Pause;

    @FXML
    private AnchorPane id_pane;
    public boolean paused=false;

    public void pauseThread(){
        paused=true;

    }
    public void resumeThread(){
        paused=false;
    }

    @FXML
    void initialize() {

        Service<Void> service1=new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected synchronized Void call() throws Exception {
                        CountDownLatch latch =new CountDownLatch(1);
                        boolean paused=false;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Timeline time=new Timeline();
                                    time.setCycleCount(Timeline.INDEFINITE);
                                    time.setAutoReverse(true);
                                    KeyValue kv1=new KeyValue(tx_FCIM.xProperty(),200);
                                    KeyFrame kf=new KeyFrame(Duration.millis(1000),kv1);
                                    time.getKeyFrames().add(kf);
//                                    try {
//                                        Thread.sleep(1000);
//                                    } catch (InterruptedException e) {
//                                        e.printStackTrace();
//                                    }
                                    time.play();
                                } finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        Service<Void> service2=new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        CountDownLatch latch =new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Timeline time = new Timeline(new KeyFrame(Duration.seconds(0.1),
                                            new EventHandler<ActionEvent>() {
                                                @Override
                                                public void handle(ActionEvent event) {
                                                    tx_UTM.getTransforms().add(new Rotate(5,0,0));
                                                }
                                            }));
                                    time.setCycleCount(Timeline.INDEFINITE);
                                    time.play();
                                    while (paused){
                                        try {
                                            this.wait();
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        Service<Void> service3=new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        CountDownLatch latch =new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Timeline time=new Timeline();
                                    time.setCycleCount(Timeline.INDEFINITE);
                                    time.setAutoReverse(true);
                                    KeyValue kv1=new KeyValue(tx_Calc.yProperty(),250);
                                    KeyFrame kf=new KeyFrame(Duration.millis(1000),kv1);
                                    time.getKeyFrames().add(kf);
                                    time.play();
                                }
                                finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        Service<Void> service4=new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        CountDownLatch latch =new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Timeline time=new Timeline();
                                    time.setCycleCount(Timeline.INDEFINITE);
                                    time.setAutoReverse(true);
                                    KeyValue kv1=new KeyValue(tx_Info.xProperty(),-100);
                                    KeyValue kv2=new KeyValue(tx_Info.yProperty(),100);
                                    KeyFrame kf=new KeyFrame(Duration.millis(1000),kv1,kv2);
                                    time.getKeyFrames().add(kf);
                                    time.play();
                                    while (paused){
                                        try {
                                            Thread.sleep(20);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        Service<Void> service5= new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        CountDownLatch latch =new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), tx_MN);
                                    fadeTransition.setFromValue(1.0);
                                    fadeTransition.setToValue(0.0);
                                    fadeTransition.setCycleCount(Animation.INDEFINITE);
                                    fadeTransition.play();
                                }
                                finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        Service<Void> service6= new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        CountDownLatch latch =new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), tx_ball);
                                    fadeTransition.setFromValue(1.0);
                                    fadeTransition.setToValue(0.0);
                                    fadeTransition.setCycleCount(Animation.INDEFINITE);
                                    Timeline time=new Timeline();
                                    time.setCycleCount(Timeline.INDEFINITE);
                                    time.setAutoReverse(true);
                                    KeyValue kv1=new KeyValue(tx_ball.radiusProperty(),-50);
                                    KeyValue kv2=new KeyValue(tx_ball.translateXProperty(),500);
                                    KeyFrame kf=new KeyFrame(Duration.millis(1000),kv1,kv2);
                                    time.getKeyFrames().add(kf);
                                    fadeTransition.play();
                                    time.play();

                                }
                                finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        return null;
                    }
                };
            }
        };
        btn_Start.setOnAction(event -> {
           // notifyAll();
            resumeThread();
            service1.restart();
            service2.restart();
            service3.restart();
            service4.restart();
            service5.restart();
            service6.restart();
            System.out.println("A fost apasat start");
        });
        btn_Pause.setOnAction(event -> {
             pauseThread();
            service1.cancel();
            service4.cancel();
            System.out.println("A fost apasat pause" );
           // time.stop();
        });

    }
}