public class Planet{
    public double xxPos;  //Its current x position
    public double yyPos;  //Its current y position
    public double xxVel;  //Its current velocity in the x direction
    public double yyVel;  //Its current velocity in the x direction
    public double mass;  // Its mass
    String imgFileName;  //The name of the file that corresponds to the image that depicts the planet

    /** 
    *initialize an identical Planet object (a copy).
    *the following two are instructors.
    */
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    //calculate the distance between two planets
    public double calDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double r2 = dx * dx + dy * dy;
        double r = Math.sqrt(r2);

        return r;
    }

    //calculate the total force between two planets
    public double calcForceExertedBy(Planet p){
        double r = calDistance(p);
        double G = 6.67e-11;
        double F = G * this.mass * p.mass / (r * r);

        return F;
    }

    //calculate the force in x direction
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - this.xxPos;
        double r = calDistance(p);
        double F = calcForceExertedBy(p);
        double Fx = F * dx / r;

        return Fx;
    }

    //calculate the force in y direction
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - this.yyPos;
        double r = calDistance(p);
        double F = calcForceExertedBy(p);
        double Fy = F * dy / r;

        return Fy;
    }

    //calculate the force exerted by all the planets in x direction. 
    public double calcNetForceExertedByX(Planet[] ps){
        double FxNet = 0;
        for(int i = 0; i < ps.length; i++){
            if(!this.equals(ps[i])){
                FxNet = FxNet + calcForceExertedByX(ps[i]);
            }
        }

        return FxNet;
    }

    //calculate the force exerted by all the planets in x direction. 
    public double calcNetForceExertedByY(Planet[] ps){
        double FyNet = 0;
        for(int i = 0; i < ps.length; i++){
            if(!this.equals(ps[i])){
                FyNet = FyNet + calcForceExertedByY(ps[i]);
            }
        }

        return FyNet;
    }

    //update the position and velocity of the planets
    public void update(double dt, double fX, double fY){
        double a = 0;
        aX = fX / this.mass;
        aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;

    }

    public void draw(){
        StdDraw.picture(this.xp, this.yp, this.img);
    }
}   