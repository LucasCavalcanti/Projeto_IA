package src;

import javax.tools.*;
import java.io.*;
/*
 * Create Robot
 * Creates and compiles Robocode Java file to test
 *
 */

public class createRobot {

    public static void create(double[] chromo) {
        createRobotFile(chromo); // create file
        compile(); // now compile it
    }

    public static void compile () {
        String fileToCompile = "C:\\robocode\\robots\\sample\\SamBot.java"; // which file to compile * rhyming :) *
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, fileToCompile); // run compile
    }

    public static void createRobotFile(double[] chromo){
        try {
            FileWriter fstream = new FileWriter("C:\\robocode\\robots\\sample\\SamBot.java"); // file name to create
            BufferedWriter out = new BufferedWriter(fstream);

            //start code
            out.write("package sample;\n" +
                    "import robocode.*;\n" +
                    "import robocode.Robot;\n" +
                    "import java.awt.Color;\n" +
                    "public class SamBot extends Robot {\n" +
                    "    public void run(){\n" +
                    "        while(true){\n" +
                    "            ahead(" + chromo[0] +");\n" +
                    "            turnGunRight(" + chromo[1] + "); //scan\n" +
                    "            back(" + chromo[2] + ");\n" +
                    "            turnGunRight(" + chromo[3] + "); //scan\n" +
                    "\n" +
                    "            \n" +
                    "        }\n" +
                    "    }\n" +
                    "    public void onScannedRobot(ScannedRobotEvent e){\n" +
                    "        double distance = e.getDistance(); //get the distance of the scanned robot\n" +
                    "        if(distance > 800) //this conditions adjust the fire force according the distance of the scanned robot.\n" +
                    "            fire(" + chromo[4] + ");\n" +
                    "        else if(distance > 600 && distance <= 800)\n" +
                    "            fire(" + chromo[5] + ");\n" +
                    "        else if(distance > 400 && distance <= 600)\n" +
                    "            fire(" + chromo[6] + ");\n" +
                    "        else if(distance > 200 && distance <= 400)\n" +
                    "            fire(" + chromo[7] + ");\n" +
                    "        else if(distance < 200)\n" +
                    "            fire(" + chromo[8] + ");\n" +
                    "    }\n" +
                    "    public void onHitByBullet(HitByBulletEvent e){\n" +
                    "        double energy = getEnergy();\n" +
                    "        double bearing = e.getBearing(); //Get the direction which is arrived the bullet.\n" +
                    "        if(energy <" + chromo[9] + "){ // if the energy is low, the robot go away from the enemy\n" +
                    "            turnRight(-bearing); //This isn't accurate but release your robot.\n" +
                    "            ahead(" + chromo[10] + "); //The robot goes away from the enemy.\n" +
                    "        }\n" +
                    "        else\n" +
                    "            turnRight(" + chromo[11] + "); // scan\n" +
                    "    }\n" +
                    "    public void onHitWall(HitWallEvent e){\n" +
                    "        double bearing = e.getBearing(); //get the bearing of the wall\n" +
                    "        turnRight(-bearing); //This isn't accurate but release your robot.\n" +
                    "        ahead(" + chromo[12] + "); //The robot goes away from the wall.\n" +
                    "    }\n" +
                    "");




            out.append("\n}");

            out.close(); // close output stream

        } catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}