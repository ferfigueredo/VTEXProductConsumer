package com.app.test;

public class Test {
    public static final int MAX_THREADS = 3;
    public static int threads_counter = 0;

    public class MyThread extends Thread {
          String name;
          String says;
          private final int REPEATS = 1;
          private final int DELAY = 200;

          public MyThread(String in_name, String in_says) {
                this.name = in_name;
                this.says = in_says;
          }

          public void run() {
                if(Test.threads_counter >= Test.MAX_THREADS) {
                      try {
                            Thread.sleep(this.DELAY);
                      } catch(Exception e) {
                            Test.addResponse("Thread error");
                      }
                      this.run();
                      return;
                }

                Test.threads_counter++;
                try {
                      for(int i = 0; i < this.REPEATS; ++i) {
                            Test.addResponse(this.name + " says \"" + this.says + "\"");
                            Thread.sleep(this.DELAY);
                      }
                } catch(Exception e) {
                      Test.addResponse("And error occured for Thread " + this.name);
                } finally {
                      Test.addResponse("Thread " + this.name + " stopping");
                      Test.threads_counter--;
                }
          }
    }

    public Test() {
          this.addResponse("Starting...");

          String[]threads = new String[10];
          threads[0] = "hello";
          threads[1] = "go away";
          threads[2] = "ok, ok";
          threads[3] = "yeah right";

          threads[4] = "hola";
          threads[5] = "ke ase";
          threads[6] = "piola";
          threads[7] = "eeeaaaaamigo";
          threads[8] = "gato";
          threads[9] = "terecabio";
          
          for(int i = 0; i < threads.length; i++) {

                MyThread mt = new MyThread("Thread #" + i, threads[i]);
                Thread t = new Thread(mt);

                t.start();
          }
    }

    public static void addResponse(String response) {
          System.out.println(response);
    }

    public static void main(String[] args) {
          Test t = new Test();
    }
}