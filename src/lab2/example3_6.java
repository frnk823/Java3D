package lab2;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class example3_6 extends Applet{
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
        transformgroup.addChild(Striplines());
        BranchGroupRoot.compile();
        return BranchGroupRoot;
    }
        public Shape3D Striplines(){
        Shape3D Stiplines0=new Shape3D();
        //定义点坐标
        float vertexes[] = {-0.8f, 0.5f, 0.0f, 0.8f, 0.9f, 0.0f,
                -0.8f, 0.2f, 0.0f, 0.8f, 0.7f, 0.0f,
                -0.8f, -0.2f, 0.0f, 0.8f, -0.5f, 0.0f,
                -0.8f,-0.5f,0.0f,0.8f,-0.8f,0.0f};
        //定义颜色
        float colors[] = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
                0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
                0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 0.0f, 0.3f, 0.8f, 0.0f,};
        int[] substrips=new int[2];
        substrips[0]=4;
        substrips[1]=4;
        LineStripArray Striplines=new LineStripArray(8,LineArray.COORDINATES|LineArray.COLOR_3,substrips);
        Striplines.setCoordinates(0,vertexes);
        Striplines.setColors(0,colors);
        LineAttributes lineattributes=new LineAttributes();
        lineattributes.setLineWidth(9.0f);
        lineattributes.setLineAntialiasingEnable(true);
        lineattributes.setLinePattern(0);
        Appearance app=new Appearance();
        app.setLineAttributes(lineattributes);
        Stiplines0.setGeometry(Striplines);
        Stiplines0.setAppearance(app);
        return Stiplines0;
    }

    public example3_6() {
        setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿"));
        add(p, BorderLayout.NORTH);
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene = createBranchGroup();
        SimpleUniverse u = new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }



    public static void main(String[] args) {
        new MainFrame (new example3_6(),300,300);
    }

}

