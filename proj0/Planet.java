public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double gg=6.67*(1.0e-11);
    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        this.xxPos=xP;
        this.yyPos=yP;
        this.xxVel=xV;
        this.yyVel=yV;
        this.mass=m;
        this.imgFileName=img;

    }

    public Planet(Planet P){
        this.xxPos=P.xxPos;
        this.yyPos=P.yyPos;
        this.xxVel=P.xxVel;
        this.yyVel=P.yyVel;
        this.mass=P.mass;
        this.imgFileName=P.imgFileName;
    }

    public double calcDistance(Planet P){
        double dx=this.xxPos-P.xxPos;
        double dy=this.yyPos-P.yyPos;
        double rr=Math.sqrt(dx*dx+dy*dy);
        return rr;

    }

    public double calcForceExertedBy(Planet P){

        double ff=gg*mass*P.mass/(calcDistance(P)*calcDistance(P));
        return ff;
    }

    public double calcForceExertedByX(Planet P){
        double dx=P.xxPos-xxPos;
        double r=this.calcDistance(P);
        double ff=this.calcForceExertedBy(P);
        double fx=ff*dx/r;
        return fx;
    }

    public double calcForceExertedByY(Planet P){
        double dy=P.yyPos-yyPos;
        double r=this.calcDistance(P);
        double ff=this.calcForceExertedBy(P);
        double fy=ff*dy/r;
        return fy;
        
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netforcex=0;
        for(int i=0;i<allPlanets.length;i+=1){
            if(this.equals(allPlanets[i])){
                continue;
            }
            netforcex+=this.calcForceExertedByX(allPlanets[i]);
        }
        return netforcex;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netforcey=0;
        for(int i=0;i<allPlanets.length;i+=1){
            if(this.equals(allPlanets[i])){
                continue;
            }
            netforcey=netforcey+this.calcForceExertedByY(allPlanets[i]);
        }
        return netforcey;
    }

    public void update(double dt,double fX,double fY){
        double ax=fX/this.mass;
        double ay=fY/this.mass;
        this.xxVel=this.xxVel+ax*dt;
        this.yyVel=this.yyVel+ay*dt;
        this.xxPos=this.xxPos+dt*this.xxVel;
        this.yyPos=this.yyPos+dt*this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }


}