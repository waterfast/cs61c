public class NBody {
    
    public static double readRadius(String file){
        In in = new In(file);
        int N=in.readInt();
        double radius=in.readDouble();
        return radius;
    }
    
    public static Planet[] readPlanets(String file){
        In in = new In(file); 
        int N=in.readInt(); 
        double radius=in.readDouble();
        Planet[] allPlanets=new Planet[N];
        for(int i=0;i<N;i++){
            allPlanets[i]=new Planet(in.readDouble(),in.readDouble(),
            in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return allPlanets;

    }

    public static void main(String[] args) {
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double radius=readRadius(filename);
        Planet[] allplanets=readPlanets(filename);
        int n=allplanets.length;
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Planet planet:allplanets){
            planet.draw();
        }
        StdDraw.show(); 
        StdDraw.pause(2000);

        for (double time = 0; time < T; time += dt) {
            double[] xForce=new double[n];
            double[] yForce=new double[n];
            for(int i=0;i<n;i++){
                xForce[i]=allplanets[i].calcNetForceExertedByX(allplanets);
                yForce[i]=allplanets[i].calcNetForceExertedByY(allplanets);


            }

            for(int i=0;i<n;i++){
                allplanets[i].update(dt, xForce[i], yForce[i]);

            }   

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(int i=0;i<n;i++){
                allplanets[i].draw();

            }   

            StdDraw.show(); 
            StdDraw.pause(10);


        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }

    }








}


