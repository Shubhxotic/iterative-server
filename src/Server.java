/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shubham
 **/
import java.io.*;
import java.net.*;
import java.util.HashMap;

class Server
{
   public static void main(String args[]) throws Exception
      {
         DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];

            while(true)
            {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String(receivePacket.getData());
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  System.out.println("RECEIVED: " + sentence+ "from "+IPAddress+":"+port);
                  String params[] = sentence.split(",");
                  DatagramPacket sendPacket;
                  char ch;
                  String res="";
                  String vowels="AEIOUaeoiu";
                  String splits[];
                  String s1,s2;
//                  System.out.println(IPAddress+" "+port);
                  switch(params[0]){
//                      case 9000:
//                          ch=sentence.charAt(0);
//                          switch(ch){      
                                case "si":
//                                    params=sentence.split(",");
                                    double p=Double.parseDouble(params[1]);
                                    double r=Double.parseDouble(params[2]);
                                    double t=Double.parseDouble(params[3]);
                                    double si=p*r*t/100;
                                    sendData = Double.toString(si).getBytes();
                                    sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                    serverSocket.send(sendPacket);
                                    receiveData = new byte[1024];
                                    break;
                                case "trig":
//                                    sentence = sentence.substring(2);
//                                    params=sentence.split(" ");
                                    double rad=Math.toRadians(Double.parseDouble(params[1].split(" ")[1]));
                                    switch (params[1].split(" ")[0]){
                                        case "sin":
                                            sendData = Double.toString(Math.sin(rad)).getBytes();
                                            System.out.println(Math.sin(rad));
                                            System.out.println(sendData);
                                            break;
                                        case "cos":
                                            sendData = Double.toString(Math.cos(rad)).getBytes();
                                            break;
                                        case "tan":
                                            sendData = Double.toString(Math.tan(rad)).getBytes();
                                            break;
                                        case "cosec":
                                            sendData = Double.toString(1/Math.sin(rad)).getBytes();
                                            break;
                                        case "sec":
                                            sendData = Double.toString(1/Math.cos(rad)).getBytes();
                                            break;
                                        case "cot":
                                            sendData = Double.toString(1/Math.tan(rad)).getBytes();
                                            break;
                                        default:
                                            System.out.println("Enter the input carefully and correctly");
                                    }
                                    sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                    serverSocket.send(sendPacket);
                                    receiveData = new byte[1024];;
                                    break;
//                                default:
//                                    System.out.println("Wrong Input");
//                          } 
//                          break;
//                      case 9001:
//                          ch=sentence.charAt(0);
//                          
//                          switch(ch){      
                                case "vow":
                                    res="";
                                    sentence= sentence.substring(params[0].length()+1);
                                    System.out.println("Sentence to find vowels is:- "+sentence);
                                    for(int i=0;i<sentence.length();i++){
                                        char ch1=sentence.charAt(i);
                                        if(vowels.indexOf(ch1)!=-1)
                                            res+=ch1;
                                    }
                                    sendData = res.getBytes();
                                    
                                    sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                    serverSocket.send(sendPacket);
                                    receiveData = new byte[1024];
                                    break;
                                case "con":
                                    sentence= sentence.substring(params[0].length()+1);
                                    res="";
                                    System.out.println("Sentence to find consonants is:- "+sentence);
                                    for(int i=0;i<sentence.length();i++){
                                        char ch1=sentence.charAt(i);
                                        if(vowels.indexOf(ch1)==-1)
                                            res+=ch1;
                                    }
                                    sendData = res.getBytes();
                                    sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                    serverSocket.send(sendPacket);
                                    receiveData = new byte[1024];
                                    break;
//                                default:
//                                    System.out.println("Wrong Input");
//                          }
                                    
//                          break;
                                    
                                    
                      case "solve":
                          double result=Solve(params[1].trim());
                          sendData = Double.toString(result).getBytes();
                          sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                          serverSocket.send(sendPacket);
                          receiveData = new byte[1024];
                          break;
//                      case 9003:
//                          ch=sentence.charAt(0);
//                          
//                          switch(ch){      
                            case "len":
                                sentence = sentence.substring(params[0].length()+1);
                                sentence=sentence.trim();
                                sendData = Integer.toString(sentence.length()).getBytes();
                                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                serverSocket.send(sendPacket);
                                receiveData = new byte[1024];
                                break;
                            case "upper":
                                sentence= sentence.substring(params[0].length()+1);
                                sendData = sentence.toUpperCase().getBytes();
                                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                serverSocket.send(sendPacket);
                                receiveData = new byte[1024];
                                break;
                            case "lower":
                                sentence= sentence.substring(params[0].length()+1);
                                sendData = sentence.toLowerCase().getBytes();
                                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                serverSocket.send(sendPacket);
                                receiveData = new byte[1024];
                                break;
                            case "concat":
                                sentence= sentence.substring(params[0].length()+1);
                                splits=sentence.split(",");
                                s1=splits[0];
                                s2=splits[1];
                                System.out.println("Input for concat:- "+s1+" and "+s2);
                                sendData = s1.concat(s2).getBytes();
                                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                serverSocket.send(sendPacket);
                                receiveData = new byte[1024];
                                break;
                            case "compare":
                                sentence= sentence.substring(params[0].length()+1);
                                splits=sentence.split(",");
                                s1=splits[0];
                                s2=splits[1];
                                System.out.println("Input for compare:- "+s1+" and "+s2);
                                if(s1.compareTo(s2)>0)
                                    sendData = (s1+" is larger than "+s2).getBytes();
                                else if(s1.compareTo(s2)<0)
                                    sendData = (s1+" is smaller than "+s2).getBytes();
                                else
                                    sendData = (s1+" is equal to "+s2).getBytes();
                                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                serverSocket.send(sendPacket);
                                receiveData = new byte[1024];
                                break;
                            case "substr":
                                sentence= sentence.substring(params[0].length()+1);
                                String indices="";
                                splits=sentence.split(",");
                                s1=splits[0].trim();
                                s2=splits[1].trim();
                                System.out.println(s1+"    substr= "+s2);
                                int c=0;
                                System.out.println(s1.indexOf(s2, c));
                                System.out.println(s1.indexOf(s2));
                                int x=0;
                                while(x!=-1)
                                {
                                    x=s1.indexOf(s2, c);
                                    System.out.println(x);
                                    if(x!=-1)
                                    {indices+=x+",";
//                                    s1=s1.substring(x+1);
                                    c=x+1;}
                                }
                                System.out.println("indices= "+indices);
                                sendData = indices.getBytes();
                                sendPacket =new DatagramPacket(sendData, sendData.length, IPAddress, port);
                                serverSocket.send(sendPacket);
                                receiveData = new byte[1024];
                                break;
                            default:
                                System.out.println("Wrong Input");
                          } 

//                                
//                                default:
//                                    System.out.println("Wrong Input");
//                          }
//                          break;
//                      default:
//                          System.out.println("Unknown Client");
//                  }
//                  String capitalizedSentence = sentence.toUpperCase();
                  
            }
      }
   
   public static double Solve(String s){
       System.out.println("equation received for solving: -" + s);
//       System.out.println("\U221B");
        if(s.contains("+"))
            return add(s.substring(0,s.indexOf("+")),s.substring(s.indexOf("+")+1));
        else if(s.contains("-"))
            return sub(s.substring(0,s.indexOf("-")),s.substring(s.indexOf("-")+1));
        else if(s.contains("*"))
            return mul(s.substring(0,s.indexOf("*")),s.substring(s.indexOf("*")+1));
        else if(s.contains("/"))
            return div(s.substring(0,s.indexOf("/")),s.substring(s.indexOf("/")+1));
        else if(s.contains("%"))
            return mod(s.substring(0,s.indexOf("%")),s.substring(s.indexOf("%")+1));
        else if(s.contains("^"))
            return exp(s.substring(0,s.indexOf("^")),s.substring(s.indexOf("^")+1));
        else if(s.contains("3√"))
            return cube(s.substring(s.indexOf("√")+1));
        else if(s.contains("√"))
            return sqrt(s.substring(s.indexOf("√")+1));
        else
            return 0.0;
//        else if(s.contains("^"))
//            return add(s.substring(0,s.indexOf("")),s.substring(s.indexOf("+")+1));
   }
   
   public static double add(String s1,String s2){
       double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       try{
           d2=Double.parseDouble(s2);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return d1+d2;
   }
   public static double sub(String s1,String s2){
       double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       try{
           d2=Double.parseDouble(s2);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return d1-d2;
   }
   public static double mul(String s1,String s2){
       double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       try{
           d2=Double.parseDouble(s2);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return d1*d2;
   }
   public static double div(String s1,String s2){
       double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       try{
           d2=Double.parseDouble(s2);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return d1/d2;
   }
   public static double mod(String s1,String s2){
       int d1=0,d2=0;
       try{
           d1=Integer.parseInt(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Int");
       }
       try{
           d2=Integer.parseInt(s2);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Int");
       }
       return d1%d2;
   }
   public static double exp(String s1,String s2){
       double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       try{
           d2=Double.parseDouble(s2);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return Math.pow(d1, d2);
   }
    public static double sqrt(String s1){
       double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return Math.sqrt(d1);
   }
    public static double cube(String s1){
       System.out.println(s1);
        double d1=0.0,d2=0.0;
       try{
           d1=Double.parseDouble(s1);
       }
       catch(NumberFormatException e)
       {
          System.out.println("Problem in converting "+s1+" to Double");
       }
       return (double)Math.cbrt(d1);
   }
   
}
