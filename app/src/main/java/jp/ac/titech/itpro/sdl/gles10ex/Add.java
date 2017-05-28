package jp.ac.titech.itpro.sdl.gles10ex;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Add implements SimpleRenderer.Obj {

    private FloatBuffer vbuf;
    private float x, y, z;

    public Add(float s, float x, float y, float z) {
        float m = s/2;
        float n = s/4;
        float[] vertices = {
                // bottom
                -s,  0, -s,
                 s,  0, -s,
                -s,  0,  s,
                 s,  0,  s,

                // left
                -s,  0, -s,
                 0,  s,  0,
                -s,  0,  s,

                // right
                 s,  0, -s,
                 0,  s,  0,
                 s,  0,  s,

                // back
                -s,  0, -s,
                 0,  s,  0,
                 s,  0, -s,

                // front
                -s,  0,  s,
                 0,  s,  0,
                 s,  0,  s,

                /* above bottom */

                // left
                -s,  0, -s,
                 0, -s,  0,
                -s,  0,  s,

                // right
                 s,  0, -s,
                 0, -s,  0,
                 s,  0,  s,

                // back
                -s,  0, -s,
                 0, -s,  0,
                 s,  0, -s,

                // front
                -s,  0,  s,
                 0, -s,  0,
                 s,  0,  s,



                // left
                -s, -s, -s,
                -s, -s, s,
                -s, s, -s,
                -s, s, s,
                // right
                s, -s, -s,
                s, -s, s,
                s, s, -s,
                s, s, s,
                // bottom
                -s, -s, -s,
                s, -s, -s,
                -s, -s, s,
                s, -s, s,
                // top
                -s, s, -s,
                s, s, -s,
                -s, s, s,
                s, s, s,
                // back
                -s, -s, -s,
                -s, s, -s,
                s, -s, -s,
                s, s, -s,
                // front
                -s, -s, s,
                -s, s, s,
                s, -s, s,
                s, s, s


        };
        vbuf = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        vbuf.put(vertices);
        vbuf.position(0);
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void draw(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vbuf);

        // bottom
        gl.glNormal3f( 0, -1,  0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

        // left
        gl.glNormal3f(-1,  1,  0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 4, 3);

        // right
        gl.glNormal3f( 1,  1,  0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 7, 3);

        // back
        gl.glNormal3f( 0,  1, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 10, 3);

        // front
        gl.glNormal3f( 0,  1,  1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 13, 3);



        // left
        gl.glNormal3f(-1,  -1,  0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 16, 3);

        // right
        gl.glNormal3f( 1,  -1,  0);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 19, 3);

        // back
        gl.glNormal3f( 0,  -1, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 22, 3);

        // front
        gl.glNormal3f( 0,  -1,  1);
        gl.glDrawArrays(GL10.GL_TRIANGLES, 25, 3);



/*
        // left
        gl.glNormal3f(-1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 29, 4);

        // right
        gl.glNormal3f(1, 0, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 33, 4);

        // bottom
        gl.glNormal3f(0, -1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 37, 4);

        // top
        gl.glNormal3f(0, 1, 0);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 41, 4);

        // rear
        gl.glNormal3f(0, 0, -1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 45, 4);

        // front
        gl.glNormal3f(0, 0, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 49, 4);
  */
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getZ() {
        return z;
    }
}
