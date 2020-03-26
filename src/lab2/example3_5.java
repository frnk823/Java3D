package lab2;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class example3_5 extends Applet{
    public BranchGroup createBranchGroup(){
        BranchGroup BranchGroupRoot = new BranchGroup();                             //BranchGroup
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);  //创建球心坐标在0.0.0  半径为100 ?
        Color3f bgColor = new Color3f(1.0f,1.0f,1.0f);                               //Color3f(r,g,b)
        Background bg = new Background(bgColor);                                     //Background
        bg.setApplicationBounds(bounds);
        BranchGroupRoot.addChild(bg);                                                // BranchGroup addChild (Background)
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
        Shape3D shapelines=new Shape3D();
        //定义点坐标
        float vertexes[] = {0.5f, 0.5f, 0.0f, -0.5f, 0.5f, 0.0f,
                            0.5f, 0.3f, 0.0f, -0.5f, 0.3f, 0.0f,
                           -0.5f, -0.1f, 0.0f, 0.5f, -0.1f, 0.0f,};
        //定义颜色
        float pointcolors[] = {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
                          0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f,
                          0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f,};
        int vCount=6;
        int indexCount=4;
        int index[]={0,1,3,5};
        IndexedLineArray lines=new IndexedLineArray(vCount,LineArray.COORDINATES|LineArray.COLOR_3,indexCount);
        lines.setCoordinates(0,vertexes);
        lines.setCoordinateIndices(0,index);
        lines.setColors(0,pointcolors);
        lines.setColorIndices(0,index);
        Appearance app = new Appearance();
        LineAttributes lineattributes=new LineAttributes();
        lineattributes.setLineWidth(30.0f);
        lineattributes.setLineAntialiasingEnable(true);
        app.setLineAttributes(lineattributes);
        shapelines.setGeometry(lines);
        shapelines.setAppearance(app);
        transformgroup.addChild(shapelines);
        BranchGroupRoot.compile();
        return BranchGroupRoot;
    }

    public example3_5() {
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
        new MainFrame (new example3_5(),300,300);
    }

}

