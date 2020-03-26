package lab3;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class TriangleStripArrays extends Applet {
    public BranchGroup createBranchGroup() {
        BranchGroup BranchGroupRoot = new BranchGroup();                             //BranchGroup
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);  //创建球心坐标在0.0.0  半径为100 ?
        Color3f bgColor = new Color3f(1.0f, 1.0f, 1.0f);                               //Color3f(r,g,b)
        Background bg = new Background(bgColor);                                     //Background
        bg.setApplicationBounds(bounds);
        BranchGroupRoot.addChild(bg);                                                // BranchGroup addChild (Background)
        Color3f directionalColor = new Color3f(1.f, 0.f, 0.f);
        Vector3f vec = new Vector3f(0.f, 0.f, -1.0f);
        DirectionalLight directionalLight = new DirectionalLight(directionalColor, vec);
        directionalLight.setInfluencingBounds(bounds);
        BranchGroupRoot.addChild(directionalLight);
        TransformGroup transformgroup = new TransformGroup();
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        BranchGroupRoot.addChild(transformgroup);
        MouseRotate mouserotate = new MouseRotate();
        mouserotate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mouserotate);
        mouserotate.setSchedulingBounds(bounds);
        MouseZoom mousezoom = new MouseZoom();
        mousezoom.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousezoom);
        mousezoom.setSchedulingBounds(bounds);
        MouseTranslate mousetranslate = new MouseTranslate();
        mousetranslate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousetranslate);
        mousetranslate.setSchedulingBounds(bounds);
        transformgroup.addChild(new TriangleStrip());
        BranchGroupRoot.compile();
        return BranchGroupRoot;
    }

    public TriangleStripArrays() {
        setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿"));
        add(p, BorderLayout.NORTH);
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(gc);
        add("Center", c);
        BranchGroup BranchGroupScene = createBranchGroup();
        SimpleUniverse u = new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }


    public static void main(String[] args) {
        new MainFrame(new TriangleStripArrays(), 300, 300);
    }

    class TriangleStrip extends Shape3D {
        public TriangleStrip() {
            int vertexesCount = 12;
            int stripCount[]=new int[3];
            float vertexes[] = {-0.9f, 0.9f, 0.0f, -0.8f, -0.9f, 0.2f,
                                -0.6f, 0.8f, -0.2f, -0.4f, -0.8f, 0.2f,
                                -0.3f, 0.9f, -0.2f, -0.2f, -0.9f, 0.2f,
                                0.4f, 0.7f, 0.0f, 0.4f, -0.7f, 0.3f,
                                0.6f, 0.9f, -0.3f, 0.6f, -0.9f, 0.0f,
                                0.9f, 0.8f, 0.2f, 0.8f, -0.8f, 0.3f,};
            float colors[] = {0.0f, 0.5f, 1.0f, 0.0f, 0.5f, 1.0f,
                    0.0f, 0.8f, 0.0f, 1.0f, 0.0f, 0.3f,
                    0.0f, 1.0f, 0.5f, 0.9f, 1.0f, 0.0f,
                    0.5f, 0.0f, 1.0f, 0.0f, 0.5f, 1.0f,
                    1.0f, 0.5f, 0.0f, 1.0f, 0.0f, 0.5f,
                    1.0f, 0.8f, 0.0f, 1.0f, 0.5f, 0.0f,};
            //生成3个子Strip，每个含有4个顶点
            stripCount[0]=4;
            stripCount[1]=4;
            stripCount[2]=4;
            TriangleStripArray triangleStriparrray = new TriangleStripArray(vertexesCount,TriangleStripArray.COORDINATES | TriangleStripArray.COLOR_3,stripCount);
            triangleStriparrray.setCoordinates(0, vertexes);
            triangleStriparrray.setColors(0, colors);
            PolygonAttributes polygonattributes = new PolygonAttributes();
            polygonattributes.setCullFace(PolygonAttributes.CULL_NONE);
            Appearance app = new Appearance();
            app.setPolygonAttributes(polygonattributes);
            this.setGeometry(triangleStriparrray);
            this.setAppearance(app);
        }


    }
}
