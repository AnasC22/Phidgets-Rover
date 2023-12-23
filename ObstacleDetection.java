/*

Program: ObstacleDetection.java 

Purpose: Implement object detection on the rover by configuring the Sonar Phidget's Data Interval 
so that it updates every 100 milliseconds (10 times a second).
 

*/

//Add Phidgets Library
import com.phidget22.*;

public class Ex3 {
  public static void main(String[] args) throws Exception {

      //Connect to wireless rover
      Net.addServer("", "192.168.100.1", 5661, "", 0);

      //Create
      DCMotor leftMotors = new DCMotor();
      DCMotor rightMotors = new DCMotor();
      DistanceSensor sonar = new DistanceSensor();

      //Address
      leftMotors.setChannel(0);
      rightMotors.setChannel(1);

      //Open
      leftMotors.open(5000);
      rightMotors.open(5000);
      sonar.open(5000);

      while (true) {

          System.out.println("Distance: " + sonar.getDistance() + " mm");
          
          //Check to see if distance from sonar is less than 700 mm
          if (sonar.getDistance() < 700) {
              //Object detected! Stop motors
              leftMotors.setTargetVelocity(0);
              rightMotors.setTargetVelocity(0);
          } else {
              //Move forward at max speed
              leftMotors.setTargetVelocity(1);
              rightMotors.setTargetVelocity(1);
          }

          //Wait for 100 milliseconds
          Thread.sleep(100);
      }
  }
}
