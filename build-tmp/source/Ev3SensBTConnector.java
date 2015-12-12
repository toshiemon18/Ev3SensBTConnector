import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Ev3SensBTConnector extends PApplet {


Serial port;
String EV3Port;

public void setup() {
  size(700, 700);
  frameRate(4);
  background(100);
  println(Serial.list()); 
  EV3Port = Serial.list()[1];
  port=new Serial(this,EV3Port,9600);
}

public void draw() {
  String s="";
  while (port.available() > 0) {
    int inByte = port.read();
    if ((inByte >= 65 && inByte <= 90) || (inByte >= 97 && inByte <= 122)) {
      s = s + (char)inByte;
    }
  }
  // if (s.length == 0) { 
  //   exit();
  // }
  println(s);
  if (match(s,"Blue") != null) { fill(18, 138, 237); }
  else if (match(s,"Green") != null) { fill(95, 237, 14); }
  else if (match(s,"Yellow") != null) { fill(237, 229, 0); }
  else if (match(s,"Red") != null) { fill(237, 2, 53); }

  rect(-10, -10, width + 20, height + 20);
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Ev3SensBTConnector" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
