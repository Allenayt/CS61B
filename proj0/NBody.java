public class NBody{
	public static double readRadius(String txtPath){
		In in = new In(txtPath);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String txtPath){
		In in = new In(txtPath);
		int num = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[num];
		for(int i = 0; i < num; i++){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			bodies[i] = new Body(xP, yP, xV, yV, m, img);		
		}
		return bodies;

	}

	public static void main(String[] args){
		
		int len = args.length;
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Body[] bodies = readBodies(filename);

		
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-r, r);
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.show();
		StdDraw.enableDoubleBuffering();

		for(Body b: bodies){
			b.draw();
		}

		
		double time = 0;
		while (time < T){
			
			int len2 = bodies.length;
			double[] xForces = new double[len2];
			double[] yForces = new double[len2];
			for (int i = 0; i < len2; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}
			for (int i = 0; i < len2; i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, imageToDraw);
			StdDraw.show();
			for(Body b: bodies){
				b.draw();
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.pause(10);

			time += dt;
		}




	}
	
}