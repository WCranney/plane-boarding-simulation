import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

public class World implements GLEventListener{
	
    public static int N_PLANE_ROWS = 10;
    public static int N_PLANE_COLS = 7;
    public static int N_PASSENGERS = N_PLANE_ROWS * (N_PLANE_COLS-1);
    public static int BORDER_SIZE = 2;
	
	@Override
	public void display(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
		
		// clear colour buffer
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
		
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
		
	    drawPlane(gl);
	    // draw people
	}
	
	// draw the plane seats and aisle
	private void drawPlane(GL2 gl) {
		int r, c;
		gl.glBegin(GL2.GL_QUADS);
	    // render the seats and aisle for the plane
	    for(r = 0; r < N_PLANE_ROWS; r++) {
	    	for(c = 0; c < N_PLANE_COLS; c++) {
	    		gl.glColor3f(1 ,0f, 0f);
	    		// don't render the aisle
	    		if(c == N_PLANE_COLS/2) gl.glColor3f(1, 1, 0f);
	    		gl.glVertex2d(r, c);
	    		gl.glVertex2d(r, c+1);
	    		gl.glVertex2d(r+1, c+1);
	    		gl.glVertex2d(r+1, c);
	    		
	    	}
	    }
	    gl.glEnd();
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		// set background colour
		gl.glClearColor(0.5f, 0.8f,  0.98f,  1);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int arg1, int arg2, int arg3, int arg4) {
		GL2 gl = drawable.getGL().getGL2();
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		
		// set up coordinate system
		GLU glu = new GLU();
		glu.gluOrtho2D(-BORDER_SIZE, N_PLANE_ROWS+BORDER_SIZE, -BORDER_SIZE, N_PLANE_COLS+BORDER_SIZE);
	}
	
	World() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	    GLJPanel glcanvas = new GLJPanel(capabilities);
	    glcanvas.addGLEventListener(this);
	   
	    JFrame frame = new JFrame ("Plane Boarding Simulator");
	    frame.add(glcanvas);
	      
	    // set the default size of the window
	    frame.setSize(500,500);
	    frame.setVisible(true);
	      
	    // quit on window close
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
