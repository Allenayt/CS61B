public class Body{
	double xxPos, yyPos, xxVel, yyVel, mass;
	String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body p){
		double dx, dy, r;
		dx = p.xxPos - xxPos;
		dy = p.yyPos - yyPos;
		r = Math.sqrt(dx * dx + dy * dy);
		return r;
	}

	public double calcForceExertedBy(Body p){
		final double G = 6.67e-11;
		double r = calcDistance(p);
		double force = G * mass * p.mass / (r*r);
		return force;

	}


	public double calcForceExertedByX(Body p){
		double f = calcForceExertedBy(p);
		double fx = f * (p.xxPos - xxPos) / calcDistance(p);
		return fx;
	}


	public double calcForceExertedByY(Body p){
		double f = calcForceExertedBy(p);
		double fy = f * (p.yyPos - yyPos) / calcDistance(p);
		return fy;

	}

	public double calcNetForceExertedByX(Body p){
		double f = calcForceExertedBy(p);
		double fx = f * (p.xxPos - xxPos) / calcDistance(p);
		return fx;
	}


	public double calcNetForceExertedByY(Body[] allbody){

		double net = 0.0;
		for (Body p: allbody){
			if(!p.equals(this)){
				net += calcForceExertedByY(p);
			}
		}
		return net;
	}

	public double calcNetForceExertedByX(Body[] allbody){
		
		double net = 0.0;
		for (Body p: allbody){
			if(!p.equals(this)){
				net += calcForceExertedByX(p);
			}
		}
		return net;
	}

	public void update(double dt, double fx, double fy){
		double ax, ay;
		
		ax = fx / mass;
		ay = fy / mass;
		xxVel += dt * ax;
		yyVel += dt * ay;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw(){
		StdDraw.picture(xxPos, yyPos, imgFileName);
		StdDraw.show();

	}



}