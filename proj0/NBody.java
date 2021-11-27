public class NBody{
    public static double readRadius(String fileName){
        In in = new In(fileName);
        in.readInt();
        double radius = in.readdouble();
        
        return radius;
    }
    
    public static Planet[] readPlanets(String fileName){      //create an array class that store the values of the planets
        In in = new In(fileName);
        int num = in.readInt();                            //obtain the numbers of planets
        Planet[] planets = new.Planet[num];                       //create an array whose size is the number of planet
        in.readDouble();                                   //eat the radius

        /**get the values of the planets */
        for(int i = 0; i < num; i++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }

        return planets;
    }

    public static void main(String[], args){
        /**collect all Deeded input */
        double T = Double.paraseDouble(args[0]);
        double dt = Double.paraseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /**Draw the background */
        StdDraw.setscale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");  //inside the double quotation marks is the path of the picture.l
        for(int i = 0; i < planets.length; i++){
            planets[i].Draw();
        }

        StdDraw.enableDoubleBuffering();

        /**
         * Set up a loop to loop until time variable reaches T
         *
         * For each time through the loop, do the following:
         * 1.Create an xForces array and yForces array.
         *
         * 2.Calculate the net x and y forces for each Body, storing these in the xForces and yForces arrays respectively.
         *
         * 3.After calculating the net forces for every Body, call update on each of the Bodys. This will update each body’s position,
         * velocity, and acceleration.
         */
        for(int t = 0; i < T; t += dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < T; i++){
            planets[i].update(dt, xForces[i], yForces[i]);
            }

            //Draw the background image.
            StdDraw.picture(0, 0, "images/starfield.jpg");

            //Draw all of the Bodys.
            for(int i = 0; i < planets.length; i++ ){
                planets[i].draw();
            }

            //Show the offscreen buffer
            StdDraw.show();

            //Pause the animation for 10 milliseconds
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for(int i = 0; i < planets.length; i++){
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                           planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                           planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}