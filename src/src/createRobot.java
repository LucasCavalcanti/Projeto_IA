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
            out.write("/*******************************************************************************\n" +
                    " * Copyright (c) 2001-2013 Mathew A. Nelson and Robocode contributors\n" +
                    " * All rights reserved. This program and the accompanying materials\n" +
                    " * are made available under the terms of the Eclipse Public License v1.0\n" +
                    " * which accompanies this distribution, and is available at\n" +
                    " * http://robocode.sourceforge.net/license/epl-v10.html\n" +
                    " *******************************************************************************/\n" +
                    "\n" +
                    "package sample;\n" +
                    "\n" +
                    "\n" +
                    "import robocode.HitByBulletEvent;\n" +
                    "import robocode.HitWallEvent;\n" +
                    "import robocode.RateControlRobot;\n" +
                    "import robocode.ScannedRobotEvent;\n" +
                    "\n" +
                    "\n" +
                    "/**\n" +
                    " * This is a sample of a robot using the RateControlRobot class\n" +
                    " * \n" +
                    " * @author Joshua Galecki (original)\n" +
                    " */\n" +
                    "public class SamBot extends RateControlRobot {\n" +
                    "\n" +
                    "\tint turnCounter;\n" +
                    "\tpublic void run() {\n" +
                    "\n" +
                    "\t\tturnCounter = 0;\n" +
                    "\t\tsetGunRotationRate(" + chromo[0] + ");\n" +
                    "\t\t\n" +
                    "\t\twhile (true) {\n" +
                    "\t\t\tif (turnCounter % 64 == 0) {\n" +
                    "\t\t\t\t// Straighten out, if we were hit by a bullet and are turning\n" +
                    "\t\t\t\tsetTurnRate(0);\n" +
                    "\t\t\t\t// Go forward with a velocity of 4\n" +
                    "\t\t\t\tsetVelocityRate(" + chromo[1] + ");\n" +
                    "\t\t\t}\n" +
                    "\t\t\tif (turnCounter % 64 == 32) {\n" +
                    "\t\t\t\t// Go backwards, faster\n" +
                    "\t\t\t\tsetVelocityRate(-" + chromo[2] +");\n" +
                    "\t\t\t}\n" +
                    "\t\t\tturnCounter++;\n" +
                    "\t\t\texecute();\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpublic void onScannedRobot(ScannedRobotEvent e) {\n" +
                    "\t\tfire(1);\n" +
                    "\t}\n" +
                    "\n" +
                    "\tpublic void onHitByBullet(HitByBulletEvent e) {\n" +
                    "\t\t// Turn to confuse the other robot\n" +
                    "\t\tsetTurnRate(" + chromo[3] + ");\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\tpublic void onHitWall(HitWallEvent e) {\n" +
                    "\t\t// Move away from the wall\n" +
                    "\t\tsetVelocityRate(-1 * getVelocityRate());\n" +
                    "\t}\n" +
                    "}\n");

            out.close(); // close output stream

        } catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}